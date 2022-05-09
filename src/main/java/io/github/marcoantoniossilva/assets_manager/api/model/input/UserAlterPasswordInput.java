package io.github.marcoantoniossilva.assets_manager.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserAlterPasswordInput {

  @NotBlank
  @Size(min = 5, max = 120)
  private String oldPassword;

  @NotBlank
  @Size(min = 5, max = 120)
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
