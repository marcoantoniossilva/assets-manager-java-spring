package io.github.marcoantoniossilva.assets_manager.domain.exception;

public class BusinessException extends RuntimeException{

  private static final long serialVersionUID = 1L;

  public BusinessException(String message) {
    super(message);
  }

}
