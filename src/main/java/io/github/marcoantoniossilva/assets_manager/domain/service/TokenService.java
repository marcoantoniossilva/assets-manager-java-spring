package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.common.ApiPropertiesConfig;
import io.github.marcoantoniossilva.assets_manager.domain.model.Token;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import io.github.marcoantoniossilva.assets_manager.domain.repository.TokenRepository;
import io.github.marcoantoniossilva.assets_manager.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TokenService extends BaseCrudService<Token, Integer>{

  private final TokenRepository tokenRepository;
  private final ApiPropertiesConfig apiPropertiesConfig;

  public TokenService(TokenRepository tokenRepository, ApiPropertiesConfig apiPropertiesConfig) {
    this.tokenRepository = tokenRepository;
    this.apiPropertiesConfig = apiPropertiesConfig;
  }

  @Transactional
  public Token create(String uuid, User user) {
    Duration duration = apiPropertiesConfig.getExpirationTime();
    return tokenRepository.save(new Token(uuid, user, duration));
  }

  @Transactional
  public void deleteAllExpiredTokensByUserId(Integer userId) {
    tokenRepository.deleteAllByUserIdAndExpirationTimeBefore(userId, LocalDateTime.now());
  }

  public List<Token> findAllByUserIdOrderByExpirationTime(Integer userId) {
    return tokenRepository.findAllByUserIdOrderByExpirationTime(userId);
  }

  @Transactional
  public void delete(Token token) {
    tokenRepository.delete(token);
  }

  public Optional<Token> findByToken(String token) {
    return tokenRepository.findByToken(token);
  }

  @Transactional
  public void deleteByStringToken(String stringToken) {
    tokenRepository.deleteByToken(stringToken);
  }

  protected TokenRepository getRepository() {
    return this.tokenRepository;
  }

}
