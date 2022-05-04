package io.github.marcoantoniossilva.assets_manager.domain.model;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "recuperation_tokens")
public class RecuperationToken extends BaseEntity {

  private String token;

  @ManyToOne
  private User user;

  private Duration duration;

  @Column(name = "expiration_time")
  private LocalDateTime expirationTime;

  public RecuperationToken() {
  }

  public RecuperationToken(String token, User user, Duration duration) {
    this.token = token;
    this.user = user;
    this.duration = duration;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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

  @PrePersist
  public void prePersist() {
    this.expirationTime = LocalDateTime.now().plus(this.duration);
  }

}
