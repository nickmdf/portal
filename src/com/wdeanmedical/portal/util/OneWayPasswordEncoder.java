package com.wdeanmedical.portal.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class OneWayPasswordEncoder {

    private static final Logger log = Logger.getLogger(OneWayPasswordEncoder.class);
    
    private static OneWayPasswordEncoder instance;

    /**
     * Count for the number of time to hash, 
     * more you hash more difficult it would be for the attacker 
     */
    private final static int ITERATION_COUNT = 5;

    private OneWayPasswordEncoder() {
    }

    public static synchronized OneWayPasswordEncoder getInstance() {
	if (log.isDebugEnabled()) {
	    log.debug("getInstance() - start");
	}

	if (instance == null) {
	    OneWayPasswordEncoder returnOneWayPasswordEncoder = new OneWayPasswordEncoder();
	    log.info("New instance created");
	    if (log.isDebugEnabled()) {
		log.debug("getInstance() - end");
	    }
	    return returnOneWayPasswordEncoder;
	} else {
	    if (log.isDebugEnabled()) {
		log.debug("getInstance() - end");
	    }
	    return instance;
	}
    }
    
    public synchronized String encode(String password, String saltKey)
	    throws NoSuchAlgorithmException, IOException {
	if (log.isDebugEnabled()) {
	    log.debug("encode(String, String) - start");
	}

	String encodedPassword = null;
	byte[] salt = base64ToByte(saltKey);

	MessageDigest digest = MessageDigest.getInstance("SHA-256");
	digest.reset();
	digest.update(salt);

	byte[] btPass = digest.digest(password.getBytes("UTF-8"));
	for (int i = 0; i < ITERATION_COUNT; i++) {
	    digest.reset();
	    btPass = digest.digest(btPass);
	}

	encodedPassword = byteToBase64(btPass);

	if (log.isDebugEnabled()) {
	    log.debug("encode(String, String) - end");
	}
	return encodedPassword;
    }

    private byte[] base64ToByte(String str) throws IOException {
	if (log.isDebugEnabled()) {
	    log.debug("base64ToByte(String) - start");
	}

	BASE64Decoder decoder = new BASE64Decoder();
	byte[] returnbyteArray = decoder.decodeBuffer(str);
	if (log.isDebugEnabled()) {
	    log.debug("base64ToByte(String) - end");
	}
	return returnbyteArray;
    }

    private String byteToBase64(byte[] bt) {
	if (log.isDebugEnabled()) {
	    log.debug("byteToBase64(byte[]) - start");
	}

	BASE64Encoder endecoder = new BASE64Encoder();
	String returnString = endecoder.encode(bt);
	if (log.isDebugEnabled()) {
	    log.debug("byteToBase64(byte[]) - end");
	}
	return returnString;
    }

}
