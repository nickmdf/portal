package com.wdeanmedical.portal.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "patient")
public class Patient extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 3830770729543891440L;
  
  public static final Integer STATUS_AUTHORIZED = 1;
  public static final Integer STATUS_NOT_FOUND = 0;
  public static final Integer STATUS_INVALID_PASSWORD = -1;
  public static final Integer STATUS_INACTIVE = -2;
  
  public static final int ACCESS_LEVEL_LIMITED = 0;
  public static final int ACCESS_LEVEL_FULL = 1;

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
  private int accessLevel;
  private String sessionId;
  private Date lastLoginTime;
  private String previousLoginTime;

  public Patient() {
  }
  
  @Column(name = "username")
  @Basic(optional = false)
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(name = "password")
  @Basic(optional = false)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "first_name")
  @Basic(optional = false)
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column(name = "middle_name")
  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  @Column(name = "last_name")
  @Basic(optional = false)
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(name = "primary_phone")
  public String getPrimaryPhone() {
    return primaryPhone;
  }

  public void setPrimaryPhone(String primaryPhone) {
    this.primaryPhone = primaryPhone;
  }

  @Column(name = "secondary_phone")
  public String getSecondaryPhone() {
    return secondaryPhone;
  }

  public void setSecondaryPhone(String secondaryPhone) {
    this.secondaryPhone = secondaryPhone;
  }

  @Column(name = "email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "active")
  public boolean getActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Column(name = "purged")
  public boolean getPurged() {
    return purged;
  }

  public void setPurged(boolean purged) {
    this.purged = purged;
  }

  @Column(name = "salt")
  @Basic(optional = false)
  public String getSalt() {
    return salt;
  }
  public void setSalt(String salt) {
    this.salt = salt;
  }
  
  @Column(name = "last_login_time")
  public Date getLastLoginTime() {
    return lastLoginTime;
  }
  public void setLastLoginTime(Date lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }
  
  @Column(name = "access_level")
  public int getAccessLevel() {
    return accessLevel;
  }
  public void setAccessLevel(int accessLevel) {
    this.accessLevel = accessLevel;
  }

@Transient
  public Integer getAuthStatus() {
    return authStatus;
  }

  public void setAuthStatus(Integer authStatus) {
    this.authStatus = authStatus;
  }

  @Transient
  public String getPreviousLoginTime() {
    return previousLoginTime;
  }

  public void setPreviousLoginTime(String previousLoginTime) {
    this.previousLoginTime = previousLoginTime;
  }

  @Transient
  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }


}
