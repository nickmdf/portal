package com.wdeanmedical.portal.dto;

import java.util.Date;
import java.util.List;

import com.wdeanmedical.portal.entity.PatientAllergen;
import com.wdeanmedical.portal.entity.PatientHealthIssue;
import com.wdeanmedical.portal.entity.PatientImmunization;
import com.wdeanmedical.portal.entity.PatientMedicalProcedure;
import com.wdeanmedical.portal.entity.PatientMedicalTest;
import com.wdeanmedical.portal.entity.PatientMedication;

public class PatientDTO extends AuthorizedDTO {
  private int id;
  private String username;
  private String password;
  private String firstName;
  private String middleName;
  private String lastName;
  private String primaryPhone;
  private String secondaryPhone;
  private String email;
  private boolean active;
  private boolean purged;
  private String salt;
  private int authStatus;
  private String sessionId;
  private Date lastLoginTime;
  private String previousLoginTime;
  private List<PatientAllergen> patientAllergens;
  private List<PatientMedication> patientMedications;
  private List<PatientImmunization> patientImmunizations;
  private List<PatientHealthIssue> patientHealthIssues;
  private List<PatientMedicalTest> patientMedicalTests;
  private List<PatientMedicalProcedure> patientMedicalProcedures;


  public PatientDTO() {
  }


  public String getPassword() {
    return this.password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getMiddleName() {
    return middleName;
  }
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }
  
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public String getPrimaryPhone() {
    return primaryPhone;
  }
  public void setPrimaryPhone(String primaryPhone) {
    this.primaryPhone = primaryPhone;
  }
  
  public String getSecondaryPhone() {
    return secondaryPhone;
  }
  public void setSecondaryPhone(String secondaryPhone) {
    this.secondaryPhone = secondaryPhone;
  }

  public boolean isActive() {
    return active;
  }
  public void setActive(boolean active) {
    this.active = active;
  }

  public String getSalt() {
    return salt;
  }
  public void setSalt(String salt) {
    this.salt = salt;
  }
  
  public int getAuthStatus() {
    return authStatus;
  }
  public void setAuthStatus(int authStatus) {
    this.authStatus = authStatus;
  }

  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
   this.username = username;
  }

  public boolean isPurged() {
    return purged;
  }
  public void setPurged(boolean purged) {
    this.purged = purged;
  }

  public String getSessionId() {
    return sessionId;
  }
  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public Date getLastLoginTime() {
    return lastLoginTime;
  }
  public void setLastLoginTime(Date lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  public String getPreviousLoginTime() {
    return previousLoginTime;
  }
  public void setPreviousLoginTime(String previousLoginTime) {
    this.previousLoginTime = previousLoginTime;
  }
  
  public List<PatientAllergen> getPatientAllergens() {
    return patientAllergens;
  }
  public void setPatientAllergens(List<PatientAllergen> patientAllergens) {
    this.patientAllergens = patientAllergens;
  }

  public List<PatientMedication> getPatientMedications() {
    return patientMedications;
  }
  public void setPatientMedications(List<PatientMedication> patientMedications) {
    this.patientMedications = patientMedications;
  }

  public List<PatientImmunization> getPatientImmunizations() {
    return patientImmunizations;
  }
  public void setPatientImmunizations(List<PatientImmunization> patientImmunizations) {
    this.patientImmunizations = patientImmunizations;
  }

  public List<PatientHealthIssue> getPatientHealthIssues() {
    return patientHealthIssues;
  }
  public void setPatientHealthIssues(List<PatientHealthIssue> patientHealthIssues) {
    this.patientHealthIssues = patientHealthIssues;
  }

  public List<PatientMedicalTest> getPatientMedicalTests() {
    return patientMedicalTests;
  }
  public void setPatientMedicalTests(List<PatientMedicalTest> patientMedicalTests) {
    this.patientMedicalTests = patientMedicalTests;
  }
  
  public List<PatientMedicalProcedure> getPatientMedicalProcedures() {
    return patientMedicalProcedures;
  }
  public void setPatientMedicalProcedures(List<PatientMedicalProcedure> patientProcedures) {
    this.patientMedicalProcedures = patientProcedures;
  }
  
}
