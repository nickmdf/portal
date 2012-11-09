package com.wdeanmedical.portal.service;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import com.wdeanmedical.portal.core.Core;
import com.wdeanmedical.portal.dto.AuthorizedDTO;
import com.wdeanmedical.portal.dto.LoginDTO;
import com.wdeanmedical.portal.dto.PatientDTO;
import com.wdeanmedical.portal.dto.SiteDTO;
import com.wdeanmedical.portal.entity.Patient;
import com.wdeanmedical.portal.entity.PatientAllergen;
import com.wdeanmedical.portal.entity.PatientHealthIssue;
import com.wdeanmedical.portal.entity.PatientImmunization;
import com.wdeanmedical.portal.entity.PatientMedicalProcedure;
import com.wdeanmedical.portal.entity.PatientMedicalTest;
import com.wdeanmedical.portal.entity.PatientMedication;
import com.wdeanmedical.portal.entity.UserSession;
import com.wdeanmedical.portal.persistence.AppDAO;
import com.wdeanmedical.portal.util.UserSessionData;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AppService {

  private static Log logger = LogFactory.getLog(AppService.class);
  
  private ServletContext context;
  private WebApplicationContext wac;
  private AppDAO appDAO;


  public AppService() throws MalformedURLException {
    context = Core.getServletContext();
    wac = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
    appDAO = (AppDAO) wac.getBean("appDAO");
  }
  
  public  List<PatientAllergen> getPatientAllergens(PatientDTO dto) throws Exception {
    Patient patient = appDAO.findPatientById(dto.getId());
    return appDAO.getPatientAllergens(patient);
  }
  
  public  List<PatientMedication> getPatientMedications(PatientDTO dto) throws Exception {
    Patient patient = appDAO.findPatientById(dto.getId());
    return appDAO.getPatientMedications(patient);
  }
  
  public  List<PatientImmunization> getPatientImmunizations(PatientDTO dto) throws Exception {
    Patient patient = appDAO.findPatientById(dto.getId());
    return appDAO.getPatientImmunizations(patient);
  }
  
  public  List<PatientHealthIssue> getPatientHealthIssues(PatientDTO dto) throws Exception {
    Patient patient = appDAO.findPatientById(dto.getId());
    return appDAO.getPatientHealthIssues(patient);
  }
  
  public  List<PatientMedicalTest> getPatientMedicalTests(PatientDTO dto) throws Exception {
    Patient patient = appDAO.findPatientById(dto.getId());
    return appDAO.getPatientMedicalTests(patient);
  }
  
  public  List<PatientMedicalProcedure> getPatientMedicalProcedures(PatientDTO dto) throws Exception {
    Patient patient = appDAO.findPatientById(dto.getId());
    return appDAO.getPatientMedicalProcedures(patient);
  }
  
  public  void logoutPatient(AuthorizedDTO dto) throws Exception {
     appDAO.deleteUserSession(dto.getSessionId());
  }
  
  
  public  Patient doLogin(LoginDTO loginDTO, String ipAddress) throws Exception {
    Patient patient = appDAO.authenticatePatient(loginDTO.getUsername(), loginDTO.getPassword());
    if (patient.getAuthStatus() == Patient.STATUS_AUTHORIZED) {
      UserSession userSession = new UserSession();
      userSession.setUser(patient);
      userSession.setSessionId(patient.getSessionId());
      userSession.setIpAddress(ipAddress);
      userSession.setLastAccessTime(new Date());
      appDAO.create(userSession);
      UserSessionData userSessionData = new UserSessionData();
      userSessionData.setUserSession(userSession);
      // Core.getUserSessionMap().put(userSession.getSessionId(), userSessionData);
      logger.info("======= Added " + userSession.toString()); 
    }
    return patient;
  }
  
  
  public  boolean isValidSession(AuthorizedDTO dto, String ipAddress, String path) throws Exception {
    String username = "";
    
    appDAO.deleteExpiredUserSessions();
    
    if (dto == null || dto.getSessionId() == null) {
      logger.info("======= isValidSession() no session id submitted by user at ip address of " + ipAddress); 
      return false;
    }
    
    UserSession userSession = appDAO.findUserSessionBySessionId(dto.getSessionId());
    
    if (userSession == null) {
      logger.info("======= isValidSession() no session found for : " + dto.getSessionId()); 
      return false;
    }
    
    
    if (userSession.getIpAddress().equals(ipAddress) == false) {
      logger.info("======= isValidSession() submitted IP address is of " + ipAddress + " does not match the one found in current session"); 
      return false;
    }
    
     // check for proper access level
    int accessLevel = userSession.getUser().getAccessLevel();
    logger.info("======= isValidSession() checking " + path); 
    if (Core.getUserPermissionsMap().get(path) != null) {
      username = userSession.getUser().getUsername(); 
      logger.info("======= isValidSession() checking " + path + " for user " + username + " with a permissions level of " + accessLevel); 
      if (Core.getUserPermissionsMap().get(path)[accessLevel] == false) {
        logger.info("======= isValidSession() user " + username + " lacks permission level to execute " + path); 
        return false;
      }
    }
    
    // update session timestamp to current time 
    userSession.setLastAccessTime(new Date());
    appDAO.update(userSession);
    logger.info("======= isValidSession() user " + username + "'s timestamp updated to " + userSession.getLastAccessTime()); 
    
    return true;
  }
  

}
