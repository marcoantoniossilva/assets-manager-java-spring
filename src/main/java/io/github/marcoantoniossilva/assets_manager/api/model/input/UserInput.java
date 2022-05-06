package io.github.marcoantoniossilva.assets_manager.api.model.input;

import io.github.marcoantoniossilva.assets_manager.api.model.BaseEntityModel;

import java.time.LocalDate;

public class UserInput extends BaseEntityModel {

  private String name;

  private String email;

  private String login;

  private LocalDate registerIn;

  public UserInput(Integer id, String name, String email, String login, LocalDate registerIn) {
    super(id);
    this.name = name;
    this.email = email;
    this.login = login;
    this.registerIn = registerIn;
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

  public LocalDate getRegisterIn() {
    return registerIn;
  }

}
