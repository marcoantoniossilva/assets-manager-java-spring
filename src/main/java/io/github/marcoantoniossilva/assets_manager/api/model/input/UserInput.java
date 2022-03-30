package io.github.marcoantoniossilva.assets_manager.api.model.input;

public class UserInput {
  private Integer id;

  private String name;

  private String email;

  private String login;

  private String password;

  public UserInput() {
  }

  public UserInput(Integer id, String name, String email, String login, String password) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.login = login;
    this.password = password;
  }

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
