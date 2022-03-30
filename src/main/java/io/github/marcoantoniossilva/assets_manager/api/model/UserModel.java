package io.github.marcoantoniossilva.assets_manager.api.model;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserModel {

  private Integer id;

  private String name;

  private String email;

  private String login;

  private LocalDateTime lastAccess;

  private LocalDate registerIn;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public LocalDateTime getLastAccess() {
    return lastAccess;
  }

  public void setLastAccess(LocalDateTime lastAccess) {
    this.lastAccess = lastAccess;
  }

  public LocalDate getRegisterIn() {
    return registerIn;
  }

  public void setRegisterIn(LocalDate registerIn) {
    this.registerIn = registerIn;
  }
}
