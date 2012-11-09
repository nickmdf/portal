package com.wdeanmedical.portal.dto; 

import java.util.List;

import com.wdeanmedical.portal.entity.PatientAllergen;
import com.wdeanmedical.portal.entity.PatientMedication;


public class SiteDTO extends AuthorizedDTO {

  private List<PatientAllergen> patientAllergens;
  private List<PatientMedication> patientMedications;


  public SiteDTO() {
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
 
}