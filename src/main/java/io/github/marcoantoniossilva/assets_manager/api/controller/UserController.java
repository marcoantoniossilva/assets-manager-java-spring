package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.api.assembler.UserAssembler;
import io.github.marcoantoniossilva.assets_manager.api.model.UserModel;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import io.github.marcoantoniossilva.assets_manager.domain.repository.UserRepository;
import io.github.marcoantoniossilva.assets_manager.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private UserAssembler userAssembler;

  @GetMapping
  public List<UserModel> list() {
    List<User> users = userRepository.findAll();
    return userAssembler.toCollectionModel(users);
  }

  @GetMapping("{userId}")
  public ResponseEntity<UserModel> search(@PathVariable Integer userId) {
    return userRepository.findById(userId)
        .map(user ->
            ResponseEntity.ok(userAssembler.toModel(user))
        )
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserModel add(@RequestBody User user) {
    return userAssembler.toModel(userService.save(user));
  }

  @PutMapping("{userId}")
  public ResponseEntity<UserModel> update(@PathVariable Integer userId, @RequestBody User user) {
    if (!userRepository.existsById(userId)) {
      return ResponseEntity.notFound().build();
    }
    user.setId(userId);
    User savedUser = userService.save(user);
    return ResponseEntity.ok(userAssembler.toModel(savedUser));
  }

  @DeleteMapping("{userId}")
  public ResponseEntity<Void> delete(@PathVariable Integer userId) {
    if (!userRepository.existsById(userId)) {
      return ResponseEntity.notFound().build();
    }
    userRepository.deleteById(userId);
    return ResponseEntity.noContent().build();
  }


}
