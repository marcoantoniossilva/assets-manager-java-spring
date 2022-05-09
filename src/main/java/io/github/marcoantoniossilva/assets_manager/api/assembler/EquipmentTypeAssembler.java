package io.github.marcoantoniossilva.assets_manager.api.assembler;

import io.github.marcoantoniossilva.assets_manager.api.model.EquipmentTypeModel;
import io.github.marcoantoniossilva.assets_manager.api.model.UserModel;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserEditInput;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserInput;
import io.github.marcoantoniossilva.assets_manager.domain.model.EquipmentType;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
  public class EquipmentTypeAssembler {

  private ModelMapper modelMapper;

  public EquipmentTypeAssembler(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public EquipmentTypeModel toModel(EquipmentType equipmentType) {
    return modelMapper.map(equipmentType, EquipmentTypeModel.class);
  }

  public List<EquipmentTypeModel> toCollectionModel(List<EquipmentType> equipmentTypes) {
    return equipmentTypes.stream()
        .map(this::toModel)
        .collect(Collectors.toList());
  }

  public EquipmentType toEntity(EquipmentTypeModel equipmentTypeModel) {
    return modelMapper.map(equipmentTypeModel, EquipmentType.class);
  }

}
