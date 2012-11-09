package com.wdeanmedical.portal.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "patient_medical_procedure")
public class PatientMedicalProcedure extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 2224217532626243605L;
  private Patient patient;
  private MedicalProcedure medicalProcedure;
  private MedicalTestStatus status;
  private Date dueDate;
  private Date lastDone;

  public PatientMedicalProcedure() {
  }
  
  
  @JoinColumn(name = "patient", referencedColumnName = "id")
  @ManyToOne(optional = false)
  public Patient getPatient() {
    return patient;
  }
  public void setPatient(Patient patient) {
    this.patient = patient;
  }


  @JoinColumn(name = "medical_procedure", referencedColumnName = "id")
  @ManyToOne(optional = false)
  public  MedicalProcedure getMedicalProcedure() {
    return medicalProcedure;
  }
  public void setMedicalProcedure(MedicalProcedure medicalProcedure) {
    this.medicalProcedure = medicalProcedure;
  }

  @Column(name = "due_date")
  public Date getDueDate() {
    return dueDate;
  }
  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  @JoinColumn(name = "status", referencedColumnName = "id")
  @ManyToOne(optional = false)
  public MedicalTestStatus getStatus() {
    return status;
  }
  public void setStatus(MedicalTestStatus status) {
    this.status = status;
  }
  
  
  @Column(name = "last_done")
  public Date getLastDone() {
    return lastDone;
  }
  public void setLastDone(Date lastDone) {
    this.lastDone = lastDone;
  }


}
