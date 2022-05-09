package io.github.marcoantoniossilva.assets_manager.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginInput {

  @NotBlank
  @Size(min = 4, max = 30)
  private String login;

  @NotBlank
  @Size(min = 5, max = 50)
  private String password;

  public UserLoginInput(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

}
