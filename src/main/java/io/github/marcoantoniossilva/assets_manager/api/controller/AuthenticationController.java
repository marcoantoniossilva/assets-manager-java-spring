package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.api.model.input.UserAlterPasswordInput;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserLoginInput;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserNewPasswordInput;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserRecoverPasswordInput;
import io.github.marcoantoniossilva.assets_manager.domain.model.RecuperationToken;
import io.github.marcoantoniossilva.assets_manager.domain.model.Token;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import io.github.marcoantoniossilva.assets_manager.domain.service.AuthenticationService;
import io.github.marcoantoniossilva.assets_manager.domain.service.RecuperationTokenService;
import io.github.marcoantoniossilva.assets_manager.domain.service.TokenService;
import io.github.marcoantoniossilva.assets_manager.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;
  private final TokenService tokenService;
  private final UserService userService;
  private final RecuperationTokenService recuperationTokenService;

  public AuthenticationController(AuthenticationService authenticationService, TokenService tokenService,
                                  UserService userService, RecuperationTokenService recuperationTokenService) {
    this.authenticationService = authenticationService;
    this.tokenService = tokenService;
    this.userService = userService;
    this.recuperationTokenService = recuperationTokenService;
  }

  @PostMapping("login")
  private Token auth(@RequestBody UserLoginInput userLoginInput) {
    return authenticationService.auth(userLoginInput);
  }

  @GetMapping("logout")
  private ResponseEntity<Void> logout(@RequestHeader(name = "Authorization") String token) {
    tokenService.deleteByStringToken(token);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("change-password/{userId}")
  private ResponseEntity<Void> changePassword(@RequestBody UserAlterPasswordInput userAlterPasswordInput, @PathVariable Integer userId) {
    if (!userService.existsById(userId)) {
      return ResponseEntity.notFound().build();
    }
    String newPassword = userAlterPasswordInput.getNewPassword();
    String oldPassword = userAlterPasswordInput.getOldPassword();

    userService.alterPassword(userId, newPassword, oldPassword);
    return ResponseEntity.ok().build();
  }

  @PostMapping("recover-password")
  private ResponseEntity<Void> recoverPassword(@RequestBody UserRecoverPasswordInput userRecoverPasswordInput) {
    String email = userRecoverPasswordInput.getEmail();
    User user = userService.getUserByEmail(email);

    recuperationTokenService.deleteAllByUserId(user.getId());
    recuperationTokenService.create(UUID.randomUUID().toString(), user);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("new-password")
  private ResponseEntity<Void> newPassword(@RequestBody UserNewPasswordInput userNewPasswordInput) {
    String newPassword = userNewPasswordInput.getNewPassword();
    String token = userNewPasswordInput.getToken();

    Optional<RecuperationToken> recuperationToken = recuperationTokenService.findByToken(token);

    if (recuperationToken.isPresent()) {
      User user = recuperationToken.get().getUser();
      userService.setNewPassword(user.getId(), newPassword);
      recuperationTokenService.deleteAllByUserId(user.getId());
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

}
