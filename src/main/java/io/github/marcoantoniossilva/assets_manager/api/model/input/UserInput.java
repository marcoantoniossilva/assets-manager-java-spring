package io.github.marcoantoniossilva.assets_manager.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserInput extends UserEditInput {

  public UserInput(Integer id, String name, String email, String login, String password) {
    super(id, name, email, login);
    this.password = password;
  }

  @NotBlank
  @Size(min = 5, max = 120)
  private String password;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
