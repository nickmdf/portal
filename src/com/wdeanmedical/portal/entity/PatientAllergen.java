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
@Table(name = "patient_allergen")
public class PatientAllergen extends BaseEntity implements Serializable {
  private static final long serialVersionUID = 3930862315213189346L;
  
  private Patient patient;
  private Allergen allergen;
  private String comment;

  public PatientAllergen() {
  }



  @JoinColumn(name = "patient", referencedColumnName = "id")
  @ManyToOne(optional = false)
  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }
  
  
  @JoinColumn(name = "allergen", referencedColumnName = "id")
  @ManyToOne(optional = false)
  public Allergen getAllergen() {
    return allergen;
  }

  public void setAllergen(Allergen allergen) {
    this.allergen = allergen;
  }


  @Column(name = "comment")
  public String getComment() {
    return comment;
  }
  public void setComment(String comment) {
    this.comment = comment;
  }

 

}
