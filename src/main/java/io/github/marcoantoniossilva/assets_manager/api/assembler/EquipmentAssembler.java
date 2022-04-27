package io.github.marcoantoniossilva.assets_manager.api.assembler;

import io.github.marcoantoniossilva.assets_manager.api.model.EquipmentModel;
import io.github.marcoantoniossilva.assets_manager.api.model.input.EquipmentInput;
import io.github.marcoantoniossilva.assets_manager.domain.model.Equipment;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EquipmentAssembler {

  private ModelMapper modelMapper;

  public EquipmentAssembler(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public EquipmentModel toModel(Equipment equipment) {
    EquipmentModel equipmentModel = modelMapper.map(equipment, EquipmentModel.class);

    if (equipment.getNfe() != null) {
      URI imageUri = ServletUriComponentsBuilder.fromCurrentContextPath()
          .path("equipments/{id}/nfe").buildAndExpand(equipment.getId()).toUri();
      equipmentModel.setNfeUri(imageUri);
    }
    return equipmentModel;
  }

  public List<EquipmentModel> toCollectionModel(List<Equipment> equipments) {
    return equipments.stream()
        .map(this::toModel)
        .collect(Collectors.toList());
  }

  public Equipment toEntity(EquipmentInput equipmentInput) {
    return modelMapper.map(equipmentInput, Equipment.class);
  }

  public Page<EquipmentModel> pageEntityToPageModel(Page<Equipment> result) {
    return result.map(this::toModel);
  }
}
