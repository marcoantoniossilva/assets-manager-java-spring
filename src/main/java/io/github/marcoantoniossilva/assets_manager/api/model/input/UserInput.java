package io.github.marcoantoniossilva.assets_manager.api.model.input;

import io.github.marcoantoniossilva.assets_manager.api.model.BaseEntityModel;

public class UserInput extends BaseEntityModel {

  private String name;

  private String email;

  private String login;

  private String password;

  public UserInput(Integer id, String name, String email, String login, String password) {
    super(id);
    this.name = name;
    this.email = email;
    this.login = login;
    this.password = password;
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

  public String getPassword() {
    return password;
  }

}
