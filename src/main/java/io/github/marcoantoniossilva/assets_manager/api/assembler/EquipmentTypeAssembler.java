package io.github.marcoantoniossilva.assets_manager.api.assembler;

import io.github.marcoantoniossilva.assets_manager.api.model.EquipmentTypeDTO;
import io.github.marcoantoniossilva.assets_manager.domain.model.EquipmentType;
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

  public EquipmentTypeDTO entityToDTO(EquipmentType equipmentType) {
    return modelMapper.map(equipmentType, EquipmentTypeDTO.class);
  }

  public List<EquipmentTypeDTO> entityCollectionToDTOCollection(List<EquipmentType> equipmentTypes) {
    return equipmentTypes.stream()
        .map(this::entityToDTO)
        .collect(Collectors.toList());
  }

  public EquipmentType DTOToEntity(EquipmentTypeDTO equipmentTypeDTO) {
    return modelMapper.map(equipmentTypeDTO, EquipmentType.class);
  }

}
