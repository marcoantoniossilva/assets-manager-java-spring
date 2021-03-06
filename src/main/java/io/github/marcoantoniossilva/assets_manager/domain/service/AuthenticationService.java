package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.api.model.input.UserLoginInputDTO;
import io.github.marcoantoniossilva.assets_manager.common.ApiPropertiesConfig;
import io.github.marcoantoniossilva.assets_manager.domain.exception.IncorrectLoginException;
import io.github.marcoantoniossilva.assets_manager.domain.model.Token;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AuthenticationService {

  private final UserService userService;
  private final TokenService tokenService;
  private final ApiPropertiesConfig apiPropertiesConfig;
  private final RecuperationTokenService recuperationTokenService;

  public AuthenticationService(UserService userService, TokenService tokenService,
                               RecuperationTokenService recuperationTokenService,
                               ApiPropertiesConfig apiPropertiesConfig) {
    this.userService = userService;
    this.tokenService = tokenService;
    this.recuperationTokenService = recuperationTokenService;
    this.apiPropertiesConfig = apiPropertiesConfig;
  }

  @Transactional
  public Token auth(UserLoginInputDTO userLoginInputDTO) {
    User user = userService.getByLogin(userLoginInputDTO.getLogin());
    String passwordInput = userLoginInputDTO.getPassword();
    String userPassword = user.getPassword();

    if (userService.validateLoginPassword(passwordInput, userPassword)) {
      tokenService.deleteAllExpiredTokens();
      recuperationTokenService.deleteAllExpiredTokens();
      recuperationTokenService.deleteAllByUserId(user.getId());

      verifyAndDeleteOldToken(user.getId());
      user.setLastAccess(LocalDateTime.now());
      userService.save(user);
      return tokenService.create(UUID.randomUUID().toString(), user);
    }
    throw new IncorrectLoginException("Dados do login incorretos!");
  }

  private void verifyAndDeleteOldToken(Integer userId) {
    List<Token> allTokensByUserId = tokenService.findAllByUserIdOrderByExpirationTime(userId);
    Integer maxTokensByUser = apiPropertiesConfig.getMaxTokensByUser();
    if (allTokensByUserId.size() >= maxTokensByUser) {
      tokenService.delete(allTokensByUserId.get(0));
    }
  }

}
