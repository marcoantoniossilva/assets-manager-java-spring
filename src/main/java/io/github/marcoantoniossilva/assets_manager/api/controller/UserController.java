package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.api.assembler.UserAssembler;
import io.github.marcoantoniossilva.assets_manager.api.model.UserModel;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserAlterPasswordInput;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserInput;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserNewPasswordInput;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserRecoverPasswordInput;
import io.github.marcoantoniossilva.assets_manager.common.LoggedUser;
import io.github.marcoantoniossilva.assets_manager.domain.model.RecuperationToken;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import io.github.marcoantoniossilva.assets_manager.domain.service.RecuperationTokenService;
import io.github.marcoantoniossilva.assets_manager.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final UserAssembler userAssembler;
  private final RecuperationTokenService recuperationTokenService;


  public UserController(UserService userService, UserAssembler userAssembler,
                        RecuperationTokenService recuperationTokenService) {
    this.userService = userService;
    this.userAssembler = userAssembler;
    this.recuperationTokenService = recuperationTokenService;
  }

  @GetMapping
  public List<UserModel> list() {
    List<User> users = userService.list();
    return userAssembler.toCollectionModel(users);
  }

  @GetMapping("{userId}")
  public ResponseEntity<UserModel> search(@PathVariable Integer userId) {
    return userService.findById(userId)
        .map(user ->
            ResponseEntity.ok(userAssembler.toModel(user))
        )
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("me")
  public UserModel searchLoggedUser() {
    User loggedUser = LoggedUser.getUser();
    return userAssembler.toModel(loggedUser);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserModel add(@RequestBody UserInput userInput) {
    User user = userAssembler.toEntity(userInput);

    User savedUser = userService.save(user);
    return userAssembler.toModel(savedUser);
  }

  @PutMapping("{userId}")
  public ResponseEntity<UserModel> update(@PathVariable Integer userId, @RequestBody UserInput userInput) {
    if (!userService.existsById(userId)) {
      return ResponseEntity.notFound().build();
    }
    userInput.setId(userId);
    User user = userAssembler.toEntity(userInput);
    User savedUser = userService.save(user);
    return ResponseEntity.ok(userAssembler.toModel(savedUser));
  }

  @DeleteMapping("{userId}")
  public ResponseEntity<Void> delete(@PathVariable Integer userId) {
    if (!userService.existsById(userId)) {
      return ResponseEntity.notFound().build();
    }
    userService.deleteById(userId);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("alter-password/{userId}")
  private ResponseEntity<Void> alterPassword(@RequestBody UserAlterPasswordInput userAlterPasswordInput, @PathVariable Integer userId) {
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

    recuperationTokenService.create(UUID.randomUUID().toString(), user);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("new-password")
  private ResponseEntity<Void> newPassword(@RequestBody UserNewPasswordInput userNewPasswordInput) {
    String newPassword = userNewPasswordInput.getNewPassword();
    String token = userNewPasswordInput.getToken();

    recuperationTokenService.deleteAllExpiredTokens();
    Optional<RecuperationToken> recuperationToken = recuperationTokenService.findByToken(token);

    if (recuperationToken.isPresent()) {
      User user = recuperationToken.get().getUser();
      userService.setNewPassword(user.getId(), newPassword);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

}
