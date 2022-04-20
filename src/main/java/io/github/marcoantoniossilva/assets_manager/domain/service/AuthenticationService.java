package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.api.model.input.UserLoginInput;
import io.github.marcoantoniossilva.assets_manager.common.TokenConfigurationsProperties;
import io.github.marcoantoniossilva.assets_manager.domain.exception.IncorrectLoginException;
import io.github.marcoantoniossilva.assets_manager.domain.model.Token;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import io.github.marcoantoniossilva.assets_manager.domain.repository.TokenRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AuthenticationService {

  private final PasswordEncoder passwordEncoder;
  private final UserService userService;
  private final TokenService tokenService;
  private final TokenConfigurationsProperties tokenConfigurationsProperties;

  public AuthenticationService(PasswordEncoder passwordEncoder, UserService userService, TokenRepository tokenRepository, TokenService tokenService, TokenConfigurationsProperties tokenConfigurationsProperties) {
    this.passwordEncoder = passwordEncoder;
    this.userService = userService;
    this.tokenService = tokenService;
    this.tokenConfigurationsProperties = tokenConfigurationsProperties;
  }

  @Transactional
  public Token auth(UserLoginInput userLoginInput) {
    User user = userService.findByLogin(userLoginInput.getLogin());
    if (validateLoginPassword(user, userLoginInput)) {
      tokenService.deleteAllExpiredTokensByUserId(user.getId());
      verifyAndDeleteOldToken(user.getId());
      user.setLastAccess(LocalDateTime.now());
      userService.save(user);
      return tokenService.create(UUID.randomUUID().toString(), user);
    }
    throw new IncorrectLoginException("Dados do login incorretos!");
  }

  private boolean validateLoginPassword(User user, UserLoginInput userLoginInput) {
    return passwordEncoder.matches(userLoginInput.getPassword(), user.getPassword());
  }

  private void verifyAndDeleteOldToken(Integer userId) {
    List<Token> allTokensByUserId = tokenService.findAllByUserIdOrderByExpirationTime(userId);
    Integer maxTokensByUser = tokenConfigurationsProperties.getMaxTokensByUser();
    if (allTokensByUserId.size() >= maxTokensByUser) {
      tokenService.delete(allTokensByUserId.get(0));
    }
  }

}
