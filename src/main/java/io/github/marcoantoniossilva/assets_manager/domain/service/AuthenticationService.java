package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.api.model.input.UserLoginInput;
import io.github.marcoantoniossilva.assets_manager.domain.exception.IncorrectLoginException;
import io.github.marcoantoniossilva.assets_manager.domain.model.Token;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import io.github.marcoantoniossilva.assets_manager.domain.repository.TokenRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AuthenticationService {

  private final PasswordEncoder passwordEncoder;
  private final UserService userService;
  private final TokenService tokenService;

  public AuthenticationService(PasswordEncoder passwordEncoder, UserService userService, TokenRepository tokenRepository, TokenService tokenService) {
    this.passwordEncoder = passwordEncoder;
    this.userService = userService;
    this.tokenService = tokenService;
  }

  @Transactional
  public Token auth(UserLoginInput userLoginInput) {
    if (validateLoginPassword(userLoginInput)) {
      User user = userService.findByLogin(userLoginInput.getLogin());
      deleteAllExpiredTokensByUserId(user.getId());
      verifyAndDeleteOldToken(user.getId());
      return tokenService.create(UUID.randomUUID().toString(), user);
    } else {
      throw new IncorrectLoginException("Dados do login incorretos!");
    }
  }

  @Transactional
  private void deleteAllExpiredTokensByUserId(Integer userId) {
    tokenService.deleteAllExpiredTokensByUserId(userId);
  }

  @Transactional
  private boolean validateLoginPassword(UserLoginInput userLoginInput) {
    User user = userService.findByLogin(userLoginInput.getLogin());
    return passwordEncoder.matches(userLoginInput.getPassword(), user.getPassword());
  }

  private void verifyAndDeleteOldToken(Integer userId) {
    List<Token> allTokensByUserId = tokenService.findAllByUserIdOrderByExpirationTime(userId);
    if (allTokensByUserId.size() > 1) {
      tokenService.delete(allTokensByUserId.get(0));
    }
  }

}
