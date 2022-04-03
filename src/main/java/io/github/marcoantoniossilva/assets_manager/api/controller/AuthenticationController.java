package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.api.model.input.UserLoginInput;
import io.github.marcoantoniossilva.assets_manager.domain.model.Token;
import io.github.marcoantoniossilva.assets_manager.domain.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping("login")
  private Token auth(@RequestBody UserLoginInput userLoginInput) {
    return authenticationService.auth(userLoginInput);
  }

  @GetMapping("logout")
  private ResponseEntity<Void> logout(@RequestHeader(name = "Authorization") String token) {

    boolean existentToken = authenticationService.existsByStringToken(token);

    if (existentToken) {
      authenticationService.deleteByStringToken(token);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

}
