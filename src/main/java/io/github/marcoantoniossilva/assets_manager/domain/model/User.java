package io.github.marcoantoniossilva.assets_manager.domain.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private String email;

  private String login;

  private String password;

  @Column(name = "last_access")
  private LocalDateTime lastAccess;

  @Column(name = "register_in")
  private LocalDate registerIn = LocalDate.now();

  public User() {
  }

  public User(String name, String email, String login, String password, LocalDateTime lastAccess, LocalDate registerIn) {
    this.name = name;
    this.email = email;
    this.login = login;
    this.password = password;
    this.lastAccess = lastAccess;
    this.registerIn = registerIn;
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

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", login='" + login + '\'' +
        ", password='" + password + '\'' +
        ", lastAccess=" + lastAccess +
        ", registerIn=" + registerIn +
        '}';
  }
}
