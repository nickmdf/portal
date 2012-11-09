var DO_SCROLL = true;
var DONT_SCROLL = false;
var DO_AUTO_LOGOUT = true;
var DO_AUTO_SERVER_LOGOUT = true;
var INITIALIZED = false;
var PATIENT_STATUS_AUTHORIZED = 1;
var PATIENT_STATUS_NOT_FOUND = 0;
var PATIENT_STATUS_INVALID_PASSWORD = -1;
var PATIENT_STATUS_INACTIVE = -2;

var currentScreen = "home_screen";
var previousScreen = "home_screen";
var patientFullName = "";

var contentLabels = 
['#mr1_content','#mr2_content','#mr3_content','#mr4_content','#mr5_content','#mr6_content','#mr7_content','#mr8_content','#mr9_content',
'#ms1_content','#ms2_content','#ms3_content','#ms4_content','#ms5_content','#ms6_content','#a1_content','#a2_content','#a3_content',
'#faq_content','#contact_content','#family_content','#admin_content'];

var patientAllergens;
var patientMedications;
var patientImmunizations;
var patientHealthIssues;
var patientMedicalTests;
var patientProcedures;

var patient = null;

debug("console debugging enabled.");

/***********      @JQUERY INIT    *******************/
$(document).ready(function() {
  if (INITIALIZED == false) {
    //getStaticLists();
    INITIALIZED = true;
    app_viewStack('home_screen', DO_SCROLL);
    $('#loginForm').css({display: "block"});
    $('#logoutForm').css({display: "none"});
  }
});
  
  $(".img-swap").hover(
    function(){this.src = this.src.replace("-off","-on");},
    function(){this.src = this.src.replace("-on","-off");
  });
  
  
  
  function getPatientAllergens() {
    var jsonData = JSON.stringify({ id: patient.id, sessionId: patient.sessionId });
    $.post("app/getPatientAllergens", {data:jsonData}, function(data) {
      var parsedData = $.parseJSON(data);
      patientAllergens = parsedData.patientAllergens;
      var tableRow = 0;
      var out = 
      "<table cellspacing='0' cellpadding='0'>" +
      "<col style='width:386px;'> <col style='width:386px;'>" + 
      "<tr class='data_table_header'><td>Allergen</td><td>Reaction</td></tr>";
      
      for (var i=0;i< patientAllergens.length;i++) {
        var r = patientAllergens[i];
        out +=  ((tableRow % 2 == 1) ? "<tr class='even'>" : "<tr class='odd'>");
        out +=  "  <td>"  + r.allergen.name +  "</td><td>" + r.comment + "</td></tr>";
        tableRow++;
      }
      out += 
      "<tr style='height:44px;'>" +
      "<td style='vertical-align:middle; padding:1px; background-color:#e9eaed;' colspan='2'>" +
      "<p><span>Allergies " + (tableRow == 0 ? '0' : '1') + "-<wbr>" + tableRow + "</span></p></td></tr>" +
      " </table>";
      $('#patient_allergies_table').html(out);
    });
  }
  
  function getPatientMedications() {
    var jsonData = JSON.stringify({ id: patient.id, sessionId: patient.sessionId });
    $.post("app/getPatientMedications", {data:jsonData}, function(data) {
      var parsedData = $.parseJSON(data);
      patientMedications = parsedData.patientMedications;
      var tableRow = 0;
      var out = ""; 
      
      for (var i=0;i< patientMedications.length;i++) {
        var r = patientMedications[i];

        out += 
        '<div style="width:771px;height:139px;background-color:#bbbbbb;margin-bottom:10px">' +
        '<img src="images/rx.png" border="0" width="32" height="32" style="position:relative;left:12px;top:10px;">' +
        '<span style="position:relative;left:20px;top:0px;font-size:1.5em">' + r.medication.name + '</span><br/>' +
        '<a href="#" style="position:relative;left:12px;top:10px">' + 
        '<img src="images/info.png"/><span style="position:relative;left:10px;top:-8px;font-size:.7em">About This Medication</span></a><br/>' +
        '<span style="position:relative;left:20px;top:0px;font-size:1em">Instructions: ' + r.instructions + '</span><br/>' +
        '<span style="position:relative;left:20px;top:0px;font-size:1em">Prescribed by ' + 
        util_buildFullName(r.prescriber.firstName, r.prescriber.middleName, r.prescriber.lastName) +
        ', ' + r.prescriber.credential.name + ' on ' + dateFormat(r.date, 'mm/dd/yyyy') + '</span><br/>' +
        '</div>';
      }
      $('#patient_medications_table').html(out);
    });
  }
  
  
  function getPatientImmunizations() {
    var jsonData = JSON.stringify({ id: patient.id, sessionId: patient.sessionId });
    $.post("app/getPatientImmunizations", {data:jsonData}, function(data) {
      var parsedData = $.parseJSON(data);
      patientImmunizations = parsedData.patientImmunizations;
      var tableRow = 0;
      var out = 
      "<table cellspacing='0' cellpadding='0'>" +
      "<col style='width:386px;'> <col style='width:386px;'>" + 
      "<tr class='data_table_header'><td>Immunization</td><td>Date</td></tr>";
      
      for (var i=0;i< patientImmunizations.length;i++) {
        var r = patientImmunizations[i];
        out +=  ((tableRow % 2 == 1) ? "<tr class='even'>" : "<tr class='odd'>");
        out +=  "  <td>"  + r.immunization.name +  "</td><td>" + dateFormat(r.date, 'mm/dd/yyyy') + "</td></tr>";
        tableRow++;
      }
      out += 
      "<tr style='height:44px;'>" +
      "<td style='vertical-align:middle; padding:1px; background-color:#e9eaed;' colspan='2'>" +
      "<p><span>Immunizations " + (tableRow == 0 ? '0' : '1') + "-<wbr>" + tableRow + "</span></p></td></tr>" +
      " </table>";
      $('#patient_immunizations_table').html(out);
    });
  }
  
  
  function getPatientHealthIssues() {
    var jsonData = JSON.stringify({ id: patient.id, sessionId: patient.sessionId });
    $.post("app/getPatientHealthIssues", {data:jsonData}, function(data) {
      var parsedData = $.parseJSON(data);
      patientHealthIssues = parsedData.patientHealthIssues;
      var tableRow = 0;
      var out = 
      "<table cellspacing='0' cellpadding='0'>" +
      "<col style='width:386px;'> <col style='width:386px;'>" + 
      "<tr class='data_table_header'><td>HealthIssue</td><td>Date</td></tr>";
      
      for (var i=0;i< patientHealthIssues.length;i++) {
        var r = patientHealthIssues[i];
        out +=  ((tableRow % 2 == 1) ? "<tr class='even'>" : "<tr class='odd'>");
        out +=  "  <td>"  + r.healthIssue.name +  "</td><td>" + dateFormat(r.date, 'mm/dd/yyyy') + "</td></tr>";
        tableRow++;
      }
      out += 
      "<tr style='height:44px;'>" +
      "<td style='vertical-align:middle; padding:1px; background-color:#e9eaed;' colspan='2'>" +
      "<p><span>Health Issues " + (tableRow == 0 ? '0' : '1') + "-<wbr>" + tableRow + "</span></p></td></tr>" +
      " </table>";
      $('#patient_health_issues_table').html(out);
    });
  }
  
  
  function getPatientMedicalTests() {
    var jsonData = JSON.stringify({ id: patient.id, sessionId: patient.sessionId });
    $.post("app/getPatientMedicalTests", {data:jsonData}, function(data) {
      var parsedData = $.parseJSON(data);
      patientMedicalTests = parsedData.patientMedicalTests;
      var tableRow = 0;
      var out = 
      "<table cellspacing='0' cellpadding='0'>" +
      "<col style='width:198px;'> <col style='width:198px;'><col style='width:198px;'> <col style='width:198px;'>" + 
      "<tr class='data_table_header'><td>Date</td><td>Test</td><td>Ordered By</td><td>Status</td></tr>";
      
      for (var i=0;i< patientMedicalTests.length;i++) {
        var r = patientMedicalTests[i];
        out +=  ((tableRow % 2 == 1) ? "<tr class='even'>" : "<tr class='odd'>");
        out +=  "  <td>"  + dateFormat(r.date, 'mm/dd/yyyy') + "</td><td>" + r.medicalTest.name + "</td>";
        out +=  "  <td>"  +  util_buildFullName(r.clinician.firstName, r.clinician.middleName, r.clinician.lastName)+ "</td><td>" + r.status.name + "</td></tr>";
        tableRow++;
      }
      out += 
      "<tr style='height:44px;'>" +
      "<td style='vertical-align:middle; padding:1px; background-color:#e9eaed;' colspan='4'>" +
      "<p><span>Tests " + (tableRow == 0 ? '0' : '1') + "-<wbr>" + tableRow + "</span></p></td></tr>" +
      " </table>";
      $('#patient_medical_tests_table').html(out);
    });
  }
  
  
  function getPatientProcedures() {
    var jsonData = JSON.stringify({ id: patient.id, sessionId: patient.sessionId });
    $.post("app/getPatientProcedures", {data:jsonData}, function(data) {
      var parsedData = $.parseJSON(data);
      patientMedicalProcedures = parsedData.patientMedicalProcedures;
      var tableRow = 0;
      var out = 
      "<table cellspacing='0' cellpadding='0'>" +
      "<col style='width:198px;'> <col style='width:198px;'><col style='width:198px;'> <col style='width:198px;'>" + 
      "<tr class='data_table_header'><td>Name</td><td>Due Date</td><td>Status</td><td>Last Done</td></tr>";
      
      for (var i=0;i< patientMedicalProcedures.length;i++) {
        var r = patientMedicalProcedures[i];
        out +=  ((tableRow % 2 == 1) ? "<tr class='even'>" : "<tr class='odd'>");
        out +=  "  <td>" + r.medicalProcedure.name + "</td><td>"  + dateFormat(r.dueDate, 'mm/dd/yyyy') + "</td>";
        out +=  "  <td>" + r.status.name + "</td><td>"  + dateFormat(r.lastDone, 'mm/dd/yyyy') + "</td></tr>";
        tableRow++;
      }
      out += 
      "<tr style='height:44px;'>" +
      "<td style='vertical-align:middle; padding:1px; background-color:#e9eaed;' colspan='4'>" +
      "<p><span>Procedures " + (tableRow == 0 ? '0' : '1') + "-<wbr>" + tableRow + "</span></p></td></tr>" +
      " </table>";
      $('#patient_medical_procedures_table').html(out);
    });
  }
    
  
  $('#home_link').live('click', function(e){ e.preventDefault(); app_viewStack('home_screen', DO_SCROLL); });
  $('#med_records_link').live('click', function(e) { e.preventDefault(); app_viewStack('med_records_screen', DO_SCROLL); });
  $('#med_records_link_back').live('click', function(e){ e.preventDefault(); app_viewStack('home_screen', DO_SCROLL); });
  $('#message_center_link').live('click', function(e){ e.preventDefault(); app_viewStack('message_center_screen', DO_SCROLL); });
  $('#message_center_link_back').live('click', function(e){ e.preventDefault(); app_viewStack('home_screen', DO_SCROLL); });
  $('#appt_link').live('click', function(e){ e.preventDefault(); app_viewStack('appt_screen', DO_SCROLL); });
  $('#appt_link_back').live('click', function(e){ e.preventDefault(); app_viewStack('home_screen', DO_SCROLL); });
  $('#detail_screen_back').live('click', function(e){ e.preventDefault(); app_viewStack(previousScreen, DO_SCROLL); });
  
  $('#mr1').live('click', function(e){ e.preventDefault(); detail_viewStack('mr1_content', DO_SCROLL); });
  $('#mr2').live('click', function(e){ e.preventDefault(); detail_viewStack('mr2_content', DO_SCROLL); });
  $('#mr3').live('click', function(e){ e.preventDefault(); detail_viewStack('mr3_content', DO_SCROLL); });
  $('#mr4').live('click', function(e){ e.preventDefault(); detail_viewStack('mr4_content', DO_SCROLL); });
  $('#mr5').live('click', function(e){ e.preventDefault(); detail_viewStack('mr5_content', DO_SCROLL); });
  $('#mr6').live('click', function(e){ e.preventDefault(); detail_viewStack('mr6_content', DO_SCROLL); });
  $('#mr7').live('click', function(e){ e.preventDefault(); detail_viewStack('mr7_content', DO_SCROLL); });
  $('#mr8').live('click', function(e){ e.preventDefault(); detail_viewStack('mr8_content', DO_SCROLL); });
  $('#mr9').live('click', function(e){ e.preventDefault(); detail_viewStack('mr9_content', DO_SCROLL); });
  $('#ms1').live('click', function(e){ e.preventDefault(); detail_viewStack('ms1_content', DO_SCROLL); });
  $('#ms2').live('click', function(e){ e.preventDefault(); detail_viewStack('ms2_content', DO_SCROLL); });
  $('#ms3').live('click', function(e){ e.preventDefault(); detail_viewStack('ms3_content', DO_SCROLL); });
  $('#ms4').live('click', function(e){ e.preventDefault(); detail_viewStack('ms4_content', DO_SCROLL); });
  $('#ms5').live('click', function(e){ e.preventDefault(); detail_viewStack('ms5_content', DO_SCROLL); });
  $('#ms6').live('click', function(e){ e.preventDefault(); detail_viewStack('ms6_content', DO_SCROLL); });
  $('#a1').live('click', function(e){ e.preventDefault(); detail_viewStack('a1_content', DO_SCROLL); });
  $('#a2').live('click', function(e){ e.preventDefault(); detail_viewStack('a2_content', DO_SCROLL); });
  $('#a3').live('click', function(e){ e.preventDefault(); detail_viewStack('a3_content', DO_SCROLL); });
  $('#faq_link').live('click', function(e){ e.preventDefault(); detail_viewStack('faq_content', DO_SCROLL); });
  $('#reg_link').live('click', function(e){ e.preventDefault(); detail_viewStack('contact_content', DO_SCROLL); });
  $('#family_link').live('click', function(e){ e.preventDefault(); detail_viewStack('family_content', DO_SCROLL); });
  $('#admin_link').live('click', function(e){ e.preventDefault(); detail_viewStack('admin_content', DO_SCROLL); });
  $('#message_link').live('click', function(e){ e.preventDefault(); detail_viewStack('ms5_content', DO_SCROLL); });
  
  $('#login_submit').live('click', function(){ login(); });
  $('#logout_submit').live('click', function(){ logout(); });
  

function detail_viewStack(screen, doScroll) {

  if (patient == null && screen != 'faq_content' && screen != 'contact_content') {
    return;
  }
  
    
  app_viewStack('detail_screen', DO_SCROLL); 

  for (var i=0;i<contentLabels.length;i++) {
    var contentLabel = contentLabels[i];
    if (contentLabel == '#' + screen) {
      $(contentLabel).css({display: "block"});
    }
    else {
      $(contentLabel).css({display: "none"});
    }
  }

  switch (screen) {
    case 'mr1_content':
      getPatientMedicalTests();
      $('#detail_heading').html("Test Results");
      $('#detail_icon').attr("src", "images/mr1_icon.png");
    break;
    case 'mr2_content':
      getPatientMedications();
      $('#detail_heading').html("Medications");
      $('#detail_icon').attr("src", "images/mr2_icon.png");
    break;
    case 'mr3_content':
      getPatientAllergens();
      $('#detail_heading').html("Allergies");
      $('#detail_icon').attr("src", "images/mr3_icon.png");
    break;
    case 'mr4_content':
      getPatientImmunizations();
      $('#detail_heading').html("Immunizations");
      $('#detail_icon').attr("src", "images/mr4_icon.png");
    break;
    case 'mr5_content':
      getPatientProcedures();
      $('#detail_heading').html("Preventive Care");
      $('#detail_icon').attr("src", "images/mr5_icon.png");
    break;
    case 'mr6_content':
      $('#detail_heading').html("Medical History");
      $('#detail_icon').attr("src", "images/mr6_icon.png");
    break;
    case 'mr7_content':
      getPatientHealthIssues();
      $('#detail_heading').html("Current Health Issues");
      $('#detail_icon').attr("src", "images/mr7_icon.png");
    break;
    case 'mr8_content':
      $('#detail_heading').html("Health Trends");
      $('#detail_icon').attr("src", "images/mr8_icon.png");
    break;
    case 'mr9_content':
      $('#detail_heading').html("Letters");
      $('#detail_icon').attr("src", "images/mr9_icon.png");
    break;
    case 'ms1_content':
      $('#detail_heading').html("Inbox");
      $('#detail_icon').attr("src", "images/ms1_icon.png");
    break;
    case 'ms2_content':
      $('#detail_heading').html("Sent Messages");
      $('#detail_icon').attr("src", "images/ms2_icon.png");
    break;
    case 'ms3_content':
      $('#detail_heading').html("Get Medical Advice");
      $('#detail_icon').attr("src", "images/ms3_icon.png");
    break;
    case 'ms4_content':
      $('#detail_heading').html("Request Rx Renewal");
      $('#detail_icon').attr("src", "images/ms4_icon.png");
    break;
    case 'ms5_content':
      $('#detail_heading').html("Send A Message To Your Doctor");
      $('#detail_icon').attr("src", "images/ms5_icon.png");
    break;
    case 'ms6_content':
      $('#detail_heading').html("Contact YourHealth");
      $('#detail_icon').attr("src", "images/ms6_icon.png");
    break;
    case 'a1_content':
      $('#detail_heading').html("Upcoming Appointmtents");
      $('#detail_icon').attr("src", "images/a1_icon.png");
    break;
    case 'a2_content':
      $('#detail_heading').html("Past Appointments");
      $('#detail_icon').attr("src", "images/a2_icon.png");
    break;
    case 'a3_content':
      $('#detail_heading').html("Request Appointment");
      $('#detail_icon').attr("src", "images/a3_icon.png");
    break;
    case 'faq_content':
      $('#detail_heading').html("FAQs");
      $('#detail_icon').attr("src", "images/faq_icon.png");
    break;
    case 'contact_content':
      $('#detail_heading').html("New User Signup");
      $('#detail_icon').attr("src", "images/contact_icon.png");
    break;
    case 'family_content':
      $('#detail_heading').html("Family Access Settings");
      $('#detail_icon').attr("src", "images/family_icon.png");
    break;
    case 'admin_content':
      $('#detail_heading').html("Terms and Conditions");
      $('#detail_icon').attr("src", "images/admin_icon.png");
    break;
  }
  if (doScroll) {scroll(0,0);}
}


function app_viewStack(screen, doScroll) {

  if (screen != 'home_screen' && screen != 'detail_screen' && patient == null) {
    return;
  }
 
  previousScreen = currentScreen;
  switch (screen) {
    case 'home_screen':
      $('#section_title').html("");
      $('#main_screen').css({display: "block"});
      $('#detail_screen').css({display: "none"});
      $('.your_prefs').css({display: "none"});
      if (patient != null) {
        $('#app_title').css({display: "none"});
        $('#welcome_message').css({display: "block"});
      }
      else {
        $('#app_title').css({display: "block"});
        $('#welcome_message').css({display: "none"});
      }
      $('#app_title').html("Patient Portal");
      $('#home_screen').css({display: "block"});
      $('#why_screen').css({display: "none"});
      $('#demo_screen').css({display: "none"});
      $('#demo_live_screen').css({display: "none"});
      $('#contact_screen').css({display: "none"});
      $('#appt_link').css({display: "block"});
      $('#appt_link_back').css({display: "none"});
      $('#features_screen').css({display: "none"});
      $('#arra_screen').css({display: "none"});
      $('#med_records_link').css({display: "block"});
      $('#med_records_link_back').css({display: "none"});
      $('#message_center_link').css({display: "block"});
      $('#message_center_link_back').css({display: "none"});
      $('#family_link').css({display: "block"});
      $('#message_link').css({display: "block"});
      $('#reg_link').css({display: "block"});
      $('#admin_link').css({display: "block"});
      $('#r3c1_why_tile').css({display: "none"});
      $('#r4c5_why_tile').css({display: "none"});
      $('#serpent').css({display: "none"});
      $('#gear3').css({display: "none"});
      $('#gear4').css({display: "none"});
      $('#tile1').css({display: "block"});
      $('#demo_live_screen_link_back').css({display: "none"});
      $('#med_records_img1').css({display: "none"});
      $('#med_records_img2').css({display: "none"});
      $('#med_records_screen').css({display: "none"});
      $('#message_center_screen').css({display: "none"});
      $('#appt_screen').css({display: "none"});
      $('#r5c2').css({display: "block"});
      $('#r5c3').css({display: "block"});
      $('#r5c4').css({display: "block"});
      $('#message_center_img1').css({display: "none"});
      $('#appt_img1').css({display: "none"});
			if($(window).width() < 500){
				$('#r3c1, #r3c5, #r4c1, #r4c5, #r5c1, #r5c5').css({display: "block"});
				$('#r4c1').css({top: "187px"});
				$('#r5c1').css({top: "260px"});
				//$('#r3c1').css({display: "block"});
			}
    break;
    case 'med_records_screen':
      $('#section_title').html("Your Medical Health Records");
      $('#main_screen').css({display: "block"});
      $('#detail_screen').css({display: "none"});
      $('#app_title').css({display: "none"});
      $('#welcome_message').css({display: "block"});
      $('#med_records_link').css({display: "none"});
      $('#med_records_link_back').css({display: "block"});
      $('.your_prefs').css({display: "block"});
      $('#med_records_img1').css({display: "block"});
      $('#med_records_img2').css({display: "block"});
      $('#home_screen').css({display: "none"});
      $('#med_records_screen').css({display: "block"});
      $('#message_center_screen').css({display: "none"});
      $('#appt_screen').css({display: "none"});
      $('#r5c2').css({display: "none"});
      $('#r5c3').css({display: "none"});
      $('#r5c4').css({display: "none"});
      $('#message_center_img1').css({display: "none"});
      $('#appt_link').css({display: "block"});
      $('#appt_link_back').css({display: "none"});
      $('#appt_img1').css({display: "none"});
      $('#message_center_link').css({display: "block"});
      $('#message_center_link_back').css({display: "none"});
			if($(window).width() < 500){
				$('#r3c5, #r4c1, #r4c5, #r5c1, #r5c5').css({display: "none"});
			}
    break;
    case 'message_center_screen':
      $('#section_title').html("Message Center");
      $('#main_screen').css({display: "block"});
      $('#detail_screen').css({display: "none"});
      $('#app_title').css({display: "none"});
      $('#welcome_message').css({display: "block"});
      $('#med_records_link').css({display: "block"});
      $('#med_records_link_back').css({display: "none"});
      $('.your_prefs').css({display: "block"});
      $('#med_records_img1').css({display: "none"});
      $('#med_records_img2').css({display: "none"});
      $('#home_screen').css({display: "none"});
      $('#med_records_screen').css({display: "none"});
      $('#message_center_screen').css({display: "block"});
      $('#appt_screen').css({display: "none"});
      $('#r5c2').css({display: "none"});
      $('#r5c3').css({display: "none"});
      $('#r5c4').css({display: "none"});
      $('#message_center_img1').css({display: "block"});
      $('#appt_link').css({display: "block"});
      $('#appt_link_back').css({display: "none"});
      $('#appt_img1').css({display: "none"});
      $('#message_center_link').css({display: "none"});
      $('#message_center_link_back').css({display: "block"});
			if($(window).width() < 500){
				$('#r3c1, #med_records_link, #r3c5, #r4c5, #r5c1, #r5c5').css({display: "none"});
				$('#r4c1').css({top: "114px"});
				$('#message_center_img1').css({width: "294px", height: "67px", top: "260px", left: "0px"});
			}
    break;
    case 'appt_screen':
      $('#section_title').html("Appointments");
      $('#main_screen').css({display: "block"});
      $('#detail_screen').css({display: "none"});
      $('#app_title').css({display: "none"});
      $('#welcome_message').css({display: "block"});
      $('#med_records_link').css({display: "block"});
      $('#med_records_link_back').css({display: "none"});
      $('.your_prefs').css({display: "block"});
      $('#med_records_img1').css({display: "none"});
      $('#med_records_img2').css({display: "none"});
      $('#home_screen').css({display: "none"});
      $('#med_records_screen').css({display: "none"});
      $('#message_center_screen').css({display: "none"});
      $('#appt_screen').css({display: "block"});
      $('#r5c2').css({display: "none"});
      $('#r5c3').css({display: "none"});
      $('#r5c4').css({display: "none"});
      $('#message_center_img1').css({display: "none"});
      $('#appt_link').css({display: "none"});
      $('#appt_link_back').css({display: "block"});
      $('#appt_img1').css({display: "block"});
      $('#message_center_link').css({display: "block"});
      $('#message_center_link_back').css({display: "none"});
			if($(window).width() < 500){
				$('#r3c1, #med_records_link, #r3c5, #r4c1, #r4c5, #r5c5').css({display: "none"});
				$('#r5c1').css({top: "114px"});
				$('#appt_img1').css({width: "294px", height: "67px", top: "260px", left: "0px"});
				$('#appt_img').css({display: "block", width: "194px", height: "67px", left: "-40px", top: "-47px", position: "absolute"});
			}
    break;
    case 'detail_screen':
      $('#main_screen').css({display: "none"});
      $('#detail_screen').css({display: "block"});
    break;
  }
  
  if (doScroll) {scroll(0,0);}
  currentScreen = screen;
}

function login(){
   if($.trim($("#login_username").val()).length < 1 || $.trim($("#login_password").val()).length < 1) { 
      return;
    }
    $('#loginRunning').css({display: "block"});
    var jsonData = JSON.stringify({ username: $('#login_username').attr("value"), password: $('#login_password').attr("value")});
    $.post("app/login", {data:jsonData},
      function(data) {
        $('#loginError').css({display:'none'});
        var parsedData = $.parseJSON(data);
        patient = new Patient();
        patient.id = parsedData.id;
        patient.firstName = parsedData.firstName;
        patient.middleName = parsedData.middleName;
        patient.lastName = parsedData.lastName;
        patient.streetAddress1 = parsedData.streetAddress1;
        patient.streetAddress2 = parsedData.streetAddress2;
        patient.city = parsedData.city;
        patient.state = parsedData.state;
        patient.zip = parsedData.zip;
        patient.primaryPhone = parsedData.primaryPhone;
        patient.secondaryPhone = parsedData.secondaryPhone;
        patient.email = parsedData.email;
        patient.authStatus = parsedData.authStatus;
        patient.previousLoginTime = parsedData.previousLoginTime;
        patient.sessionId = parsedData.sessionId;
        
        $('#loginRunning').css({display: "none"});
        
        if (patient.authStatus == PATIENT_STATUS_AUTHORIZED) {
          patientFullName = util_buildFullName(patient.firstName, patient.middleName, patient.lastName);
          $('#logout_patientFullName').text(patientFullName);
          $('#home_full_name').text(patientFullName);
          $('#home_today').text(getTodaysDateFormatted());
          $('#home_previousLoginTime').css({visibility: "visible"});
          $('#loginForm').css({display: "none"});
          $('#logoutForm').css({display: "block"});
          $('#app_title').css({display: "none"});
          $('#welcome_message').css({display: "block"});
          //mainViewStack('home_screen', DO_SCROLL);
        }  
        else  {
          if (patient.authStatus == PATIENT_STATUS_NOT_FOUND) {
            $('#loginError').text('User not found in system');
          }
          else if (patient.authStatus == PATIENT_STATUS_INVALID_PASSWORD) {
            $('#loginError').text('Invalid password');
          }
          else if (patient.authStatus == PATIENT_STATUS_INACTIVE) {
            $('#loginError').text('User is inactive');
          }
          $('#loginError').css({display:'block'});
        }
      }
    );  
}


function logout() {
  $('#loginForm').css({display: "block"});
  $('#logoutForm').css({display: "none"});
  $('#logout_patientFullName').text('');
  $('#home_today').text('');
  $('#home_full_name').text('');
  $('#app_title').css({display: "block"});
  $('#welcome_message').css({display: "none"});
  var jsonData = JSON.stringify({ sessionId: patient.sessionId });
  $.post("app/logout", {data:jsonData}, function(data) {
    var parsedData = $.parseJSON(data);
    patient = null;
  });
}


function getTodaysDateFormatted() {
  return dateFormat("fullDate");
}

$(window).bind('resize', function () {
	if($(window).width() > 500 && currentScreen == 'home_screen') {
		$('#r5c1').css({top: "380px"});
		$('#r4c1').css({top: "271px"});
		$('#r3c1, #r3c5, #r4c1, #r4c5, #r5c1, #r5c5').css({display: "block"});
	}
	if($(window).width() < 500 && currentScreen == 'home_screen') {
		$('#r5c1').css({top: "260px"});
		$('#r4c1').css({top: "187px"});
	}
	
	if($(window).width() < 500 && currentScreen == 'appt_screen') {
		$('#r3c1, #med_records_link, #r3c5, #r4c1, #r4c5, #r5c5').css({display: "none"});
		$('#r5c1').css({top: "114px"});
		$('#appt_img1').css({width: "294px", height: "67px", top: "260px", left: "0px"});
		$('#appt_img').css({display: "block", width: "194px", height: "67px", left: "-40px", top: "-47px", position: "absolute"});
	}
	if($(window).width() > 500 && currentScreen == 'appt_screen') {
		$('#r3c1, #med_records_link, #r3c5, #r4c1, #r4c5, #r5c5').css({display: "block"});
		$('#appt_img').css({display: "none"});
		$('#r5c1').css({top: "380px"});
		$('#r4c1').css({top: "271px"});
		$('#appt_img1').css({top: "271px", left: "139px", width: "411px", height: "212px"});
	}
	if($(window).width() > 1000 && currentScreen == 'appt_screen') {
		$('#r3c1, #med_records_link, #r3c5, #r4c1, #r4c5, #r5c5').css({display: "block"});
		$('#appt_img').css({display: "none"});
		$('#r5c1').css({top: "516px"});
		$('#r4c1').css({top: "369px"});
		$('#appt_img1').css({top: "369px", left: "206px", width: "612px", height: "288px"});
	}
});
