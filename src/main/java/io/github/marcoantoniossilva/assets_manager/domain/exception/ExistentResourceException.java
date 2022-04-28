package io.github.marcoantoniossilva.assets_manager.domain.exception;

public class ExistentResourceException extends RuntimeException{

  private static final long serialVersionUID = 1L;

  public ExistentResourceException(String message) {
    super(message);
  }

}
