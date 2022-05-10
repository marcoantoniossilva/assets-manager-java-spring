package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.api.assembler.UserAssembler;
import io.github.marcoantoniossilva.assets_manager.api.model.UserDTO;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserEditInputDTO;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserInputDTODTO;
import io.github.marcoantoniossilva.assets_manager.common.LoggedUser;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import io.github.marcoantoniossilva.assets_manager.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final UserAssembler userAssembler;

  public UserController(UserService userService, UserAssembler userAssembler) {
    this.userService = userService;
    this.userAssembler = userAssembler;
  }

  @GetMapping
  public List<UserDTO> list() {
    List<User> users = userService.findAll();
    return userAssembler.entityCollectionToDTOCollection(users);
  }

  @GetMapping("{userId}")
  public ResponseEntity<UserDTO> search(@PathVariable Integer userId) {
    return userService.findById(userId)
        .map(user ->
            ResponseEntity.ok(userAssembler.entityToDTO(user))
        )
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("me")
  public UserDTO searchLoggedUser() {
    User loggedUser = LoggedUser.getUser();
    return userAssembler.entityToDTO(loggedUser);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDTO add(@Valid @RequestBody UserInputDTODTO userInputDTO) {
    User user = userAssembler.DTOToEntity(userInputDTO);

    User savedUser = userService.save(user);
    return userAssembler.entityToDTO(savedUser);
  }

  @PutMapping("{userId}")
  public ResponseEntity<UserDTO> update(@PathVariable Integer userId,
                                        @Valid @RequestBody UserEditInputDTO userEditInputDTO) {
    if (!userService.existsById(userId)) {
      return ResponseEntity.notFound().build();
    }

    userEditInputDTO.setId(userId);
    User user = userAssembler.DTOToEntity(userEditInputDTO);

    User savedUser = userService.save(user);
    return ResponseEntity.ok(userAssembler.entityToDTO(savedUser));
  }

  @DeleteMapping("{userId}")
  public ResponseEntity<Void> delete(@PathVariable Integer userId) {
    if (!userService.existsById(userId)) {
      return ResponseEntity.notFound().build();
    }
    userService.deleteById(userId);
    return ResponseEntity.noContent().build();
  }

}
