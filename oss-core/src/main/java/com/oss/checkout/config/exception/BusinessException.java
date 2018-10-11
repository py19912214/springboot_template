package com.oss.checkout.config.exception;

public class BusinessException extends RuntimeException {
  private ErrorCode errorCode;

  public BusinessException(ErrorCode errorCode, String msg) {
    super(msg);
    this.errorCode = errorCode;
  }
  public BusinessException(ErrorCode errorCode, String msg, Exception e){
    super(msg,e);
    this.errorCode=errorCode;
  }
  public BusinessException(ErrorCode errorCode) {
    super(errorCode.getMsg());
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
