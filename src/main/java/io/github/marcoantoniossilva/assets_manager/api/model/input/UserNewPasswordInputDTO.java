package io.github.marcoantoniossilva.assets_manager.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserNewPasswordInputDTO {

  @NotBlank
  @Size(min = 36,max = 36)
  private String token;

  @NotBlank
  @Size(min = 5, max = 120)
  private String newPassword;

  public UserNewPasswordInputDTO(String newPassword, String token) {
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
