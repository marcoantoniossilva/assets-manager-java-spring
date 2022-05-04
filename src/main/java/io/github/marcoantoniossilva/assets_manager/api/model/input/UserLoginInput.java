package io.github.marcoantoniossilva.assets_manager.api.model.input;

public class UserLoginInput {

  private String login;

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
