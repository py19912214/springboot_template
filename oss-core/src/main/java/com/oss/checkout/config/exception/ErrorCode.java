package com.oss.checkout.config.exception;

public enum ErrorCode {
  UN_CATCH_EXCEPTION("500","未知异常"),
  PARAM_ERROR("600", "参数错误"),
  NET_ERROR("700", "网络异常");
  private String code;
  private String msg;
  private ErrorCode(String code, String msg){
    this.code=code;
    this.msg=msg;
  }

  public String getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }
}
