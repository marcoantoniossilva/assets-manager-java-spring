package io.github.marcoantoniossilva.assets_manager.api.model;

import java.time.LocalDateTime;

public class TokenModel {

  private String token;

  private LocalDateTime expirationTime;

  public TokenModel() {
  }

  public TokenModel(String token, LocalDateTime expirationTime) {
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
