package io.github.marcoantoniossilva.assets_manager.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRecoverPasswordInputDTO {

  @NotBlank
  @Email
  @Size(max = 120)
  private String email;

  public UserRecoverPasswordInputDTO() {
  }

  public UserRecoverPasswordInputDTO(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

}
