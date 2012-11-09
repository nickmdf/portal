package com.wdeanmedical.portal.dto;

public class AuthorizedDTO extends DTO {

    private String sessionId;
    private boolean authenticated = true;


    public AuthorizedDTO() {
    }

    public String getSessionId() {
      return sessionId;
    }

    public void setSessionId(String sessionId) {
      this.sessionId = sessionId;
    }

    public boolean getAuthenticated() {
      return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
      this.authenticated = authenticated;
    }

}
