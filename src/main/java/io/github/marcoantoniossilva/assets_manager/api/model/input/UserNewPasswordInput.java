package io.github.marcoantoniossilva.assets_manager.api.model.input;

public class UserNewPasswordInput {

  private String token;

  private String newPassword;

  public UserNewPasswordInput(String newPassword, String token) {
    this.token = token;
    this.newPassword = newPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public String getToken() {
    return token;
  }

}
