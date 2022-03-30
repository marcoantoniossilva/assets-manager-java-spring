package io.github.marcoantoniossilva.assets_manager.api.assembler;

import io.github.marcoantoniossilva.assets_manager.api.model.UserModel;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserInput;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
  public class UserAssembler {

  private ModelMapper modelMapper;

  public UserAssembler(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public UserModel toModel(User user) {
    return modelMapper.map(user, UserModel.class);
  }

  public List<UserModel> toCollectionModel(List<User> users) {
    return users.stream()
        .map(this::toModel)
        .collect(Collectors.toList());
  }

  public User toEntity(UserInput userInput) {
    return modelMapper.map(userInput, User.class);
  }

}
