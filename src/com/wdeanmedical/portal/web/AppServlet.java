package com.wdeanmedical.portal.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.wdeanmedical.portal.service.AppService;
import com.wdeanmedical.portal.util.UserSessionData;

import com.google.gson.Gson;
import org.apache.log4j.Logger;


public class AppServlet extends HttpServlet  {
  
  private static final long serialVersionUID = 5141268230082988870L;
  private static final Logger logger = Logger.getLogger(AppServlet.class);
  
  private AppService appService;

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    ServletContext context = getServletContext();
    Core.setServletContext(context);
    Core.setTimeZone(context.getInitParameter("timeZone"));
    Core.setSendMail(context.getInitParameter("mail.send"));
    Core.setMailFrom(context.getInitParameter("mail.from"));
    Core.setSmtphost(context.getInitParameter("mail.smtp.host"));
    Core.setSmtpport(context.getInitParameter("mail.smtp.port"));
    Core.setSessionTimeout(Integer.parseInt(context.getInitParameter("appSessionTimeout")));
    Core.buildUserPermissionsMap();
    try{
      appService = new AppService();
    }
    catch(MalformedURLException e){
      e.printStackTrace();
    }
  }
    
  public void doPost( HttpServletRequest request, HttpServletResponse response) {
    String returnString = "";
    String pathInfo = request.getPathInfo();
    String servletPath = request.getServletPath();
     
    try { 


      if (pathInfo.equals("/login")) {
        returnString = doLogin(request, response);  
      }
      else { 
        if (isValidSession(request, response) == false) {
          returnString = doLogout(request, response);  
        }
        if (pathInfo.equals("/getPatientAllergens")) {
          returnString = getPatientAllergens(request, response);  
        }
        else if (pathInfo.equals("/getPatientMedications")) {
          returnString = getPatientMedications(request, response);  
        }
        else if (pathInfo.equals("/getPatientImmunizations")) {
          returnString = getPatientImmunizations(request, response);  
        }
        else if (pathInfo.equals("/getPatientHealthIssues")) {
          returnString = getPatientHealthIssues(request, response);  
        }
        else if (pathInfo.equals("/getPatientMedicalTests")) {
          returnString = getPatientMedicalTests(request, response);  
        }
        else if (pathInfo.equals("/getPatientProcedures")) {
          returnString = getPatientProcedures(request, response);  
        }
        else if (pathInfo.equals("/logout")) {
          returnString = doLogout(request, response);  
        }
      }
    
     
      ServletOutputStream  out = null;
      response.setContentType("text/plain");
      out = response.getOutputStream();
      out.println(returnString);
      logger.debug(returnString);
      out.close();
    }  
    catch( IOException ioe ) {
      ioe.printStackTrace();
    } 
    catch( Exception e ) {
      e.printStackTrace();
    }
  }
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    doPost(request, response);  
  }
  
  
  protected  boolean isValidSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String ipAddress = request.getRemoteHost();
    String data = request.getParameter("data");
    Gson gson = new Gson();
    AuthorizedDTO dto = gson.fromJson(data, AuthorizedDTO.class);  
    return appService.isValidSession(dto, ipAddress, request.getPathInfo());
  }
  
  
 
  private void removeExpiredUserSessions(Map<String, UserSessionData> userSessionMap) {
    for (Iterator<Entry<String, UserSessionData>> i =  userSessionMap.entrySet().iterator(); i.hasNext();) {
      Map.Entry<String, UserSessionData> entry = (Entry<String, UserSessionData>)i.next();
      String userSessionDataKey = (String) entry.getKey();
      UserSessionData userSessionData = (UserSessionData) entry.getValue();
      logger.info("======= removeExpiredSessions() inspecting: " + userSessionData.getUserSession().toString()); 
      Calendar timeoutThreshold = Calendar.getInstance();
      timeoutThreshold.add(Calendar.MINUTE, 0 - Core.getSessionTimeout());
      Calendar lastAccessTime = Calendar.getInstance();
      lastAccessTime.setTime(userSessionData.getUserSession().getLastAccessTime());
      
      if (timeoutThreshold.compareTo(lastAccessTime) > 0) {
        Core.getUserSessionMap().remove(userSessionDataKey);
        logger.info("======= removeExpiredSessions() removing: " + userSessionData.toString()); 
      } 
    }
  }
  
  public String getPatientAllergens(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String data = request.getParameter("data");
    Gson gson = new Gson();
    PatientDTO dto = gson.fromJson(data, PatientDTO.class); 
    List<PatientAllergen> patientAllergens = appService.getPatientAllergens(dto); 
    dto.setPatientAllergens(patientAllergens);
    String json = gson.toJson(dto);
    return json;
  }
  
  public String getPatientMedications(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String data = request.getParameter("data");
    Gson gson = new Gson();
    PatientDTO dto = gson.fromJson(data, PatientDTO.class); 
    List<PatientMedication> patientMedications = appService.getPatientMedications(dto); 
    dto.setPatientMedications(patientMedications);
    String json = gson.toJson(dto);
    return json;
  }
  
  
  public String getPatientImmunizations(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String data = request.getParameter("data");
    Gson gson = new Gson();
    PatientDTO dto = gson.fromJson(data, PatientDTO.class); 
    List<PatientImmunization> patientImmunizations = appService.getPatientImmunizations(dto); 
    dto.setPatientImmunizations(patientImmunizations);
    String json = gson.toJson(dto);
    return json;
  }
  
 
  public String getPatientHealthIssues(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String data = request.getParameter("data");
    Gson gson = new Gson();
    PatientDTO dto = gson.fromJson(data, PatientDTO.class); 
    List<PatientHealthIssue> patientHealthIssues = appService.getPatientHealthIssues(dto); 
    dto.setPatientHealthIssues(patientHealthIssues);
    String json = gson.toJson(dto);
    return json;
  }
  
  
  public String getPatientMedicalTests(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String data = request.getParameter("data");
    Gson gson = new Gson();
    PatientDTO dto = gson.fromJson(data, PatientDTO.class); 
    List<PatientMedicalTest> patientMedicalTests = appService.getPatientMedicalTests(dto); 
    dto.setPatientMedicalTests(patientMedicalTests);
    String json = gson.toJson(dto);
    return json;
  }
  
  
   public String getPatientProcedures(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String data = request.getParameter("data");
    Gson gson = new Gson();
    PatientDTO dto = gson.fromJson(data, PatientDTO.class); 
    List<PatientMedicalProcedure> patientProcedures = appService.getPatientMedicalProcedures(dto); 
    dto.setPatientMedicalProcedures(patientProcedures);
    String json = gson.toJson(dto);
    return json;
  }
  
  
  public String doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String data = request.getParameter("data");
    Gson gson = new Gson();
    LoginDTO loginDTO = gson.fromJson(data, LoginDTO.class);  
    String ipAddress = request.getRemoteHost();
    Patient patient = appService.doLogin(loginDTO, ipAddress); 
    String json = gson.toJson(patient);
    return (json);
  }
  
  
  public String doLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String data = request.getParameter("data");
    Gson gson = new Gson();
    AuthorizedDTO dto = gson.fromJson(data, AuthorizedDTO.class);  
    appService.logoutPatient(dto);
    dto.setAuthenticated(false);
    String json = gson.toJson(dto);
    return json;
  }
  
  
  public String checkSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String data = request.getParameter("data");
    Gson gson = new Gson();
    AuthorizedDTO dto = gson.fromJson(data, AuthorizedDTO.class);  
    if (dto == null) {
      dto = new AuthorizedDTO();
    }
    dto.setAuthenticated(isValidSession(request, response));
    String json = gson.toJson(dto);
    return json;
  }
  
}
 
 
