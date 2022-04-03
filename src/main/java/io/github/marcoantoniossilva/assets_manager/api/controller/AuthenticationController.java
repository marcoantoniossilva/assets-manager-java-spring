package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.api.model.input.UserLoginInput;
import io.github.marcoantoniossilva.assets_manager.domain.model.Token;
import io.github.marcoantoniossilva.assets_manager.domain.service.AuthenticationService;
import io.github.marcoantoniossilva.assets_manager.domain.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;
  private final TokenService tokenService;

  public AuthenticationController(AuthenticationService authenticationService, TokenService tokenService) {
    this.authenticationService = authenticationService;
    this.tokenService = tokenService;
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

}
