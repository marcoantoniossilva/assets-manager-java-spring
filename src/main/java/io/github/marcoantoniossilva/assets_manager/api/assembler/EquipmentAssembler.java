package io.github.marcoantoniossilva.assets_manager.api.assembler;

import io.github.marcoantoniossilva.assets_manager.api.model.EquipmentDTO;
import io.github.marcoantoniossilva.assets_manager.api.model.input.EquipmentInputDTO;
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

  public EquipmentDTO entityToDTO(Equipment equipment) {
    EquipmentDTO equipmentDTO = modelMapper.map(equipment, EquipmentDTO.class);

    if (equipment.getNfe() != null) {
      URI imageUri = ServletUriComponentsBuilder.fromCurrentContextPath()
          .path("equipments/{id}/nfe").buildAndExpand(equipment.getId()).toUri();
      equipmentDTO.setNfeUri(imageUri);
    }
    return equipmentDTO;
  }

  public List<EquipmentDTO> entityCollectionToDTOCollection(List<Equipment> equipments) {
    return equipments.stream()
        .map(this::entityToDTO)
        .collect(Collectors.toList());
  }

  public Equipment DTOToEntity(EquipmentInputDTO equipmentInputDTO) {
    return modelMapper.map(equipmentInputDTO, Equipment.class);
  }

  public Page<EquipmentDTO> pageEntityToPageModel(Page<Equipment> result) {
    return result.map(this::entityToDTO);
  }
}
