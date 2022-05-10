package io.github.marcoantoniossilva.assets_manager.api.assembler;

import io.github.marcoantoniossilva.assets_manager.api.model.UserDTO;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserEditInputDTO;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserInputDTODTO;
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

  public UserDTO entityToDTO(User user) {
    return modelMapper.map(user, UserDTO.class);
  }

  public List<UserDTO> entityCollectionToDTOCollection(List<User> users) {
    return users.stream()
        .map(this::entityToDTO)
        .collect(Collectors.toList());
  }

  public User DTOToEntity(UserInputDTODTO userInputDTO) {
    return modelMapper.map(userInputDTO, User.class);
  }

  public User DTOToEntity(UserEditInputDTO userEditInputDTO) {
    return modelMapper.map(userEditInputDTO, User.class);
  }

}
