package com.wdeanmedical.portal.dto;

public class DTO {

  private int total;
  private int size;
  private int start;
  private String errorMsg;

  public DTO() {
  }

  public int getTotal() {
    return total;
  }
  public void setTotal(int total) {
    this.total = total;
  }

  public int getSize() {
    return size;
  }
  public void setSize(int size) {
    this.size = size;
  }

  public int getStart() {
    return start;
  }
  public void setStart(int start) {
    this.start = start;
  }
  
  public String getErrorMsg() {
    return errorMsg;
  }
  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }
}
