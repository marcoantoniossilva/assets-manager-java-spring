package io.github.marcoantoniossilva.assets_manager.domain.exception;

import java.io.Serializable;

public class UnauthorizedException implements Serializable {

  private static final long serialVersionUID = 1L;

  private int code;
  private String msg;
  private String dateTime;

  public UnauthorizedException(int code, String msg, String dateTime) {
    this.code = code;
    this.msg = msg;
    this.dateTime = dateTime;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getDatetime() {
    return dateTime;
  }

  public void setDatetime(String dateTime) {
    this.dateTime = dateTime;
  }
}