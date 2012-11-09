package com.wdeanmedical.portal.core;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;

import com.wdeanmedical.portal.util.UserSessionData;

public class Core {

  private static ServletContext servletContext;
  private static String timeZone;
  private static Log logger;
  private static String sendMail;
  private static String mailUserName;
  private static String mailPassword;
  private static String mailFrom;
  private static String smtphost;
  private static String smtpport;
  private static String debug;
  private static String smtpauth;
  private static String factport;
  private static String factclass;
  private static String fallback;
  private static String starttls;
  private static int sessionTimeout;
  private static Map<String, UserSessionData> userSessionMap = Collections.synchronizedMap(new TreeMap<String, UserSessionData>());
  private static Map<String, boolean[]> userPermissionsMap = new TreeMap<String, boolean[]>();
  
  public static void buildUserPermissionsMap() {
    userPermissionsMap.put("/logout",                       new boolean[] {true ,true});
    userPermissionsMap.put("/getPatientAllergens",          new boolean[] {true ,true});
    userPermissionsMap.put("/getPatientMedications",        new boolean[] {true ,true});
 }
  
  public static ServletContext getServletContext() {
    return servletContext;
  }
  
  public static void setServletContext(ServletContext servletContext) {
    Core.servletContext = servletContext;
  }

  public static String getTimeZone() {
    return timeZone;
  }
  
  public static void setTimeZone(String timeZone) {
    Core.timeZone = timeZone;
  }

  public static Log getLogger() {
    return logger;
  }
  
  public static void setLogger(Log logger) {
    Core.logger = logger;
  }
  
  public static String getSendMail() {
      return sendMail;
  }
    
  public static void setSendMail(String sendMail) {
      Core.sendMail = sendMail;
  }
      
  public static String getMailUserName() {
      return mailUserName;
  }
    
  public static void setMailUserName(String mailUserName) {
      Core.mailUserName = mailUserName;
  }
        
  public static String getMailPassword() {
      return mailPassword;
  }
    
  public static void setMailPassword(String mailPassword) {
      Core.mailPassword = mailPassword;
  }
      
  public static String getMailFrom() {
      return mailFrom;
  }
    
  public static void setMailFrom(String mailFrom) {
      Core.mailFrom = mailFrom;
  }
  
  public static String getSmtphost() {
    return smtphost;
  }
  
  public static void setSmtphost(String smtphost) {
    Core.smtphost = smtphost;
  }
  
  public static String getSmtpport() {
    return smtpport;
  }
  
  public static void setSmtpport(String smtpport) {
    Core.smtpport = smtpport;
  }
  
  public static String getDebug() {
    return debug;
  }
  
  public static void setDebug(String debug) {
    Core.debug = debug;
  }
  
  public static String getSmtpauth() {
    return smtpauth;
  }
  
  public static void setSmtpauth(String smtpauth) {
    Core.smtpauth = smtpauth;
  }
  
  public static String getFactport() {
    return factport;
  }
  
  public static void setFactport(String factport) {
    Core.factport = factport;
  }
  
  public static String getFactclass() {
    return factclass;
  }
  
  public static void setFactclass(String factclass) {
    Core.factclass = factclass;
  }
  
  public static String getFallback() {
    return fallback;
  }
  
  public static void setFallback(String fallback) {
    Core.fallback = fallback;
  }
  
  public static String getStarttls() {
    return starttls;
  }
  
  public static void setStarttls(String starttls) {
    Core.starttls = starttls;
  }

  public static Map<String, UserSessionData> getUserSessionMap() {
    return userSessionMap;
  }
  public static void setUserSessionMap(Map<String, UserSessionData> userSessionMap) {
    Core.userSessionMap = userSessionMap;
  }
  
  public static Map<String, boolean[]> getUserPermissionsMap() {
    return userPermissionsMap;
  }
  public static void setUserPermissionsMap(Map<String, boolean[]> userPermissionsMap) {
    Core.userPermissionsMap = userPermissionsMap;
  }

public static int getSessionTimeout() {
    return sessionTimeout;
  }
  public static void setSessionTimeout(int sessionTimeout) {
    Core.sessionTimeout = sessionTimeout;
  }
  
}
