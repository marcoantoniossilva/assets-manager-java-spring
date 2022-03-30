package io.github.marcoantoniossilva.assets_manager.domain.exception;


public class EntityNotFoundException extends BusinessException {

  private static final long serialVersionUID = 1L;

  public EntityNotFoundException(String message) {
    super(message);
  }
}
