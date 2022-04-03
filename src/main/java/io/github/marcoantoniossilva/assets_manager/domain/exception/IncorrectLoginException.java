package io.github.marcoantoniossilva.assets_manager.domain.exception;


public class IncorrectLoginException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public IncorrectLoginException(String message) {
    super(message);
  }
}