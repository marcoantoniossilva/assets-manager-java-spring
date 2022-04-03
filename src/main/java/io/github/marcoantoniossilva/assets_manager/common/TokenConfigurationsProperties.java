package io.github.marcoantoniossilva.assets_manager.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class TokenConfigurationsProperties {

  @Value("${api.token.maxtokensbyuser}")
  public Integer maxTokensByUser;

  @Value("${api.token.expirationtime}")
  public Duration expirationTime;

  public Integer getMaxTokensByUser() {
    return maxTokensByUser;
  }

  public Duration getExpirationTime() {
    return expirationTime;
  }

}
