package io.github.marcoantoniossilva.assets_manager.api.assembler;

import io.github.marcoantoniossilva.assets_manager.api.model.EquipmentModel;
import io.github.marcoantoniossilva.assets_manager.api.model.input.EquipmentInput;
import io.github.marcoantoniossilva.assets_manager.domain.model.Equipment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipmentAssembler {

  private ModelMapper modelMapper;

  public EquipmentAssembler(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public EquipmentModel toModel(Equipment equipment) {
    return modelMapper.map(equipment, EquipmentModel.class);
  }

  public List<EquipmentModel> toCollectionModel(List<Equipment> equipments) {
    return equipments.stream()
        .map(this::toModel)
        .collect(Collectors.toList());
  }

  public Equipment toEntity(EquipmentInput equipmentInput) {
    return modelMapper.map(equipmentInput, Equipment.class);
  }
}
