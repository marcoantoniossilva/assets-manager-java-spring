package io.github.marcoantoniossilva.assets_manager.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
public class Token extends BaseEntity {

  private String token;

  @ManyToOne
  private User user;

  @Column(name = "expiration_time")
  private LocalDateTime expirationTime;

  public Token() {
  }

  public Token(String token, User user, Duration duration) {
    this.token = token;
    this.user = user;
    this.expirationTime = LocalDateTime.now().plus(duration);
  }

  public Token(String token, LocalDateTime expirationTime) {
    this.token = token;
    this.expirationTime = expirationTime;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public LocalDateTime getExpirationTime() {
    return expirationTime;
  }

  public void setExpirationTime(LocalDateTime expirationTime) {
    this.expirationTime = expirationTime;
  }

}
