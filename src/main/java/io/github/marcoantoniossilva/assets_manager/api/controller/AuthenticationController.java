package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.api.model.input.UserAlterPasswordInputDTO;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserLoginInputDTO;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserNewPasswordInputDTO;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserRecoverPasswordInputDTO;
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

import javax.validation.Valid;
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
  private Token auth(@Valid @RequestBody UserLoginInputDTO userLoginInputDTO) {
    return authenticationService.auth(userLoginInputDTO);
  }

  @GetMapping("logout")
  private ResponseEntity<Void> logout(@RequestHeader(name = "Authorization") String token) {
    tokenService.deleteByStringToken(token);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("change-password/{userId}")
  private ResponseEntity<Void> changePassword(@Valid @RequestBody UserAlterPasswordInputDTO userAlterPasswordInputDTO, @PathVariable Integer userId) {
    if (!userService.existsById(userId)) {
      return ResponseEntity.notFound().build();
    }
    String newPassword = userAlterPasswordInputDTO.getNewPassword();
    String oldPassword = userAlterPasswordInputDTO.getOldPassword();

    userService.alterPassword(userId, newPassword, oldPassword);
    return ResponseEntity.ok().build();
  }

  @PostMapping("recover-password")
  private ResponseEntity<Void> recoverPassword(@Valid @RequestBody UserRecoverPasswordInputDTO userRecoverPasswordInputDTO) {
    String email = userRecoverPasswordInputDTO.getEmail();
    User user = userService.getUserByEmail(email);

    recuperationTokenService.deleteAllByUserId(user.getId());
    recuperationTokenService.create(UUID.randomUUID().toString(), user);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("new-password")
  private ResponseEntity<Void> newPassword(@Valid @RequestBody UserNewPasswordInputDTO userNewPasswordInputDTO) {
    String newPassword = userNewPasswordInputDTO.getNewPassword();
    String token = userNewPasswordInputDTO.getToken();

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
