package com.wdeanmedical.portal.persistence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.wdeanmedical.portal.core.Core;
import com.wdeanmedical.portal.entity.BaseEntity;
import com.wdeanmedical.portal.entity.Patient;
import com.wdeanmedical.portal.entity.PatientAllergen;
import com.wdeanmedical.portal.entity.PatientHealthIssue;
import com.wdeanmedical.portal.entity.PatientImmunization;
import com.wdeanmedical.portal.entity.PatientMedicalProcedure;
import com.wdeanmedical.portal.entity.PatientMedicalTest;
import com.wdeanmedical.portal.entity.PatientMedication;
import com.wdeanmedical.portal.entity.UserSession;
import com.wdeanmedical.portal.util.OneWayPasswordEncoder;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AppDAO extends SiteDAO {

  private static final Logger log = Logger.getLogger(AppDAO.class);

  private SessionFactory sessionFactory;

  public AppDAO() {
    super();
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  protected Session getSession() {
    return this.sessionFactory.getCurrentSession();
  }
  
  public void create(BaseEntity item) throws Exception {
    this.createEntity(item);
  }
  
  public void update(BaseEntity item) throws Exception {
    this.updateEntity(item);
  }
  
  public void delete(BaseEntity item) throws Exception {
    this.deleteEntity(item);
  }
  
  
  public List<PatientAllergen> getPatientAllergens(Patient patient) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(PatientAllergen.class);
    crit.add(Restrictions.eq("patient", patient));
    List<PatientAllergen> list =  crit.list();
    return list;
  }
  
  public List<PatientMedication> getPatientMedications(Patient patient) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(PatientMedication.class);
    crit.add(Restrictions.eq("patient", patient));
    List<PatientMedication> list =  crit.list();
    return list;
  }
  
  public List<PatientImmunization> getPatientImmunizations(Patient patient) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(PatientImmunization.class);
    crit.add(Restrictions.eq("patient", patient));
    List<PatientImmunization> list =  crit.list();
    return list;
  }
  
  public List<PatientHealthIssue> getPatientHealthIssues(Patient patient) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(PatientHealthIssue.class);
    crit.add(Restrictions.eq("patient", patient));
    List<PatientHealthIssue> list =  crit.list();
    return list;
  }
  
  public List<PatientMedicalTest> getPatientMedicalTests(Patient patient) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(PatientMedicalTest.class);
    crit.add(Restrictions.eq("patient", patient));
    List<PatientMedicalTest> list =  crit.list();
    return list;
  }
  
  public List<PatientMedicalProcedure> getPatientMedicalProcedures(Patient patient) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(PatientMedicalProcedure.class);
    crit.add(Restrictions.eq("patient", patient));
    List<PatientMedicalProcedure> list =  crit.list();
    return list;
  }
  
  
  public Patient findPatientById(int id ) throws Exception {
    return (Patient) this.findById(Patient.class, id);
  }
  
  public Patient findPatientBySessionId(String sessionId ) throws Exception {
    Session session = this.getSession();
    Criteria crit = session.createCriteria(UserSession.class);
    crit.add(Expression.eq("sessionId", sessionId));
    UserSession userSession = (UserSession) crit.uniqueResult();
    return (Patient) this.findById(Patient.class, userSession.getUser().getId());
  }
  
  public void deleteUserSession(String sessionId) {
    Session session = this.getSession();
    String hql = "delete from UserSession u where u.sessionId = :sessionId";
    Query query = session.createQuery(hql);
    query.setParameter("sessionId", sessionId);
    query.executeUpdate();
  }
  
  
  public void deleteExpiredUserSessions() throws Exception {
    Session session = getSession(); 
    Calendar timeoutThreshold = Calendar.getInstance();
    timeoutThreshold.add(Calendar.MINUTE, 0 - Core.getSessionTimeout());
    Date  expireTime = timeoutThreshold.getTime();
    String hql = "delete from UserSession u where u.lastAccessTime < :expireTime";
    Query query = session.createQuery(hql);
    query.setParameter("expireTime", expireTime);
    query.executeUpdate();
  }
  
  
   public Patient authenticatePatient(String username, String password) throws Exception {
    log.info("testing username: " + username);
    Patient patient = findPatientByUsername(username);
    if (patient == null) {
      patient = new Patient();
      patient.setAuthStatus(Patient.STATUS_NOT_FOUND);
      return patient;
    }
    String encodedPassword = OneWayPasswordEncoder.getInstance().encode(password, patient.getSalt());

    Session session = this.getSession();
    Criteria crit = session.createCriteria(Patient.class);
    crit.add(Expression.eq("username", username));
    crit.add(Expression.eq("password", encodedPassword));
    patient = (Patient) crit.uniqueResult();
    if (patient != null) {
      patient.setAuthStatus(Patient.STATUS_AUTHORIZED);
      if (patient.getActive() == false) {
        patient.setAuthStatus(Patient.STATUS_INACTIVE);
      } 
      else {
        DateFormat df = new SimpleDateFormat("EEE, MMM d, yyyy h:mm a");
        patient.setPreviousLoginTime(patient.getLastLoginTime() != null ? df.format(patient.getLastLoginTime().getTime()) : "");
        patient.setLastLoginTime(new Date());
        patient.setSessionId(UUID.randomUUID().toString());
        update(patient);
      }
    } 
    else {
      patient = new Patient();
      patient.setAuthStatus(Patient.STATUS_INVALID_PASSWORD);
    }
    return patient;
  }
  
  public Patient findPatientByUsername(String username) throws Exception {
    Session session = this.getSession();
    Patient patient = null;
    Criteria crit = session.createCriteria(Patient.class);
    crit.add(Expression.eq("username", username));
    patient = (Patient) crit.uniqueResult();
    return patient;
  }
  
  public UserSession findUserSessionBySessionId(String sessionId ) throws Exception {
    Session session = this.getSession();
    UserSession item = null;
    Criteria crit = session.createCriteria(UserSession.class);
    crit.add(Expression.eq("sessionId", sessionId));
    item = (UserSession) crit.uniqueResult();
    return item;
  }

  
 

}
