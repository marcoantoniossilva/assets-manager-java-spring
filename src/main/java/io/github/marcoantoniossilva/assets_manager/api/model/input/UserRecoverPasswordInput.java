package io.github.marcoantoniossilva.assets_manager.api.model.input;

public class UserRecoverPasswordInput {

  private String email;

  public UserRecoverPasswordInput() {
  }

  public UserRecoverPasswordInput(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

}
