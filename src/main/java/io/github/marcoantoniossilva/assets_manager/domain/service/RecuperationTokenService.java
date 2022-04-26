package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.common.ApiPropertiesConfig;
import io.github.marcoantoniossilva.assets_manager.domain.model.RecuperationToken;
import io.github.marcoantoniossilva.assets_manager.domain.model.Token;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import io.github.marcoantoniossilva.assets_manager.domain.repository.RecuperationTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecuperationTokenService {

  private final RecuperationTokenRepository recuperationTokenRepository;
  private final ApiPropertiesConfig apiPropertiesConfig;
  private final EmailService emailService;

  public RecuperationTokenService(RecuperationTokenRepository recuperationTokenRepository, ApiPropertiesConfig apiPropertiesConfig, EmailService emailService) {
    this.recuperationTokenRepository = recuperationTokenRepository;
    this.apiPropertiesConfig = apiPropertiesConfig;
    this.emailService = emailService;
  }

  @Transactional
  private void save(RecuperationToken token) {
    recuperationTokenRepository.save(token);
  }

  @Transactional
  public void create(String uuid, User user) {
    Duration duration = apiPropertiesConfig.getExpirationTime();
    RecuperationToken recuperationToken = recuperationTokenRepository.save(new RecuperationToken(uuid, user, duration));

    UriComponents uriComponents = UriComponentsBuilder
        .fromHttpUrl(apiPropertiesConfig.getFrontEndUrl())
        .path("/new-password")
        .queryParam("token", recuperationToken.getToken())
        .build();

    String message = String.format("Clique no link abaixo em até %d minutos para criar uma nova senha.%n%s%nAssets Manager.",
        duration.toMinutes(),
        uriComponents);

    emailService.send(user.getEmail(), message);
  }

  public List<Token> findAllByUserId(Integer userId) {
    return recuperationTokenRepository.findAllByUserId(userId);
  }

  @Transactional
  public void deleteAllExpiredTokens() {
    recuperationTokenRepository.deleteAllByExpirationTimeBefore(LocalDateTime.now());
  }

  @Transactional
  public void deleteAllByUserId(Integer userId) {
    recuperationTokenRepository.deleteAllByUserId(userId);
  }

  public Optional<RecuperationToken> findByToken(String token) {
    return recuperationTokenRepository.findByToken(token);
  }

}
