package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import io.github.marcoantoniossilva.assets_manager.domain.repository.UserRepository;
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

  @GetMapping
  public List<User> list() {
    return userRepository.findAll();
  }

  @GetMapping("{userId}")
  public ResponseEntity<User> search(@PathVariable Integer userId) {
    return userRepository.findById(userId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public User add(@RequestBody User user) {
    return userRepository.save(user);
  }

  @PutMapping("{userId}")
  public ResponseEntity<User> update(@PathVariable Integer userId, @RequestBody User user) {
    if (!userRepository.existsById(userId)) {
      return ResponseEntity.notFound().build();
    }
    user.setId(userId);
    User savedUser = userRepository.save(user);
    return ResponseEntity.ok(savedUser);

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
