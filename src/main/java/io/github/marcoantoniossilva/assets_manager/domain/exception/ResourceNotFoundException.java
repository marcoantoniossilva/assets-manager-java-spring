package io.github.marcoantoniossilva.assets_manager.domain.exception;


public class ResourceNotFoundException extends ExistentResourceException {

  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
