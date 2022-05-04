package io.github.marcoantoniossilva.assets_manager.api.model.input;

public class UserAlterPasswordInput {

  private String oldPassword;

  private String newPassword;

  public UserAlterPasswordInput(String oldPassword, String newPassword) {
    this.oldPassword = oldPassword;
    this.newPassword = newPassword;
  }

  public String getOldPassword() {
    return oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

}
