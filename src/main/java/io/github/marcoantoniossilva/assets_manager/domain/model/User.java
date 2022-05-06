package io.github.marcoantoniossilva.assets_manager.domain.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

  private String name;

  private String email;

  private String login;

  private String password;

  @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
  private List<Token> tokens;

  @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
  private List<RecuperationToken> recuperationTokens;

  @Column(name = "last_access")
  private LocalDateTime lastAccess;

  @Column(name = "register_in")
  private LocalDate registerIn = LocalDate.now();

  public User() {
  }

  public User(String name, String email, String login, String password, List<Token> tokens, List<RecuperationToken> recuperationTokens, LocalDateTime lastAccess, LocalDate registerIn) {
    this.name = name;
    this.email = email;
    this.login = login;
    this.password = password;
    this.tokens = tokens;
    this.recuperationTokens = recuperationTokens;
    this.lastAccess = lastAccess;
    this.registerIn = registerIn;
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

  public List<Token> getTokens() {
    return tokens;
  }

  public void setTokens(List<Token> tokens) {
    this.tokens = tokens;
  }

  public List<RecuperationToken> getRecuperationTokens() {
    return recuperationTokens;
  }

  public void setRecuperationTokens(List<RecuperationToken> recuperationTokens) {
    this.recuperationTokens = recuperationTokens;
  }

  @PrePersist
  public void prePersist(){
    this.registerIn = LocalDate.now();
  }

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", login='" + login + '\'' +
        ", lastAccess=" + lastAccess +
        ", registerIn=" + registerIn +
        '}';
  }
}
