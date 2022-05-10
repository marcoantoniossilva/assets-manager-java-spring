package io.github.marcoantoniossilva.assets_manager.api.model.input;

import io.github.marcoantoniossilva.assets_manager.api.model.BaseEntityDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserEditInputDTO extends BaseEntityDTO {

  @NotBlank
  @Size(min = 5, max = 120)
  private String name;

  @NotBlank
  @Email
  @Size(max = 120)
  private String email;

  @NotBlank
  @Size(min = 4, max = 32)
  private String login;

  public UserEditInputDTO(Integer id, String name, String email, String login) {
    super(id);
    this.name = name;
    this.email = email;
    this.login = login;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getLogin() {
    return login;
  }

}
