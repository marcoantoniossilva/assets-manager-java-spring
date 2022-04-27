package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.exception.BusinessException;
import io.github.marcoantoniossilva.assets_manager.domain.model.EquipmentType;
import io.github.marcoantoniossilva.assets_manager.domain.repository.EquipmentTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentTypeService {
  
  private final EquipmentTypeRepository equipmentTypeRepository;

  public EquipmentTypeService(EquipmentTypeRepository equipmentTypeRepository) {
    this.equipmentTypeRepository = equipmentTypeRepository;
  }

  public List<EquipmentType> list() {
    return equipmentTypeRepository.findAll();
  }

  public Optional<EquipmentType> findById(Integer typeId) {
    return equipmentTypeRepository.findById(typeId);
  }

  public EquipmentType getById(Integer typeId) {
    Optional<EquipmentType> type = equipmentTypeRepository.findById(typeId);
    return type.orElseThrow(() -> new BusinessException("Tipo não encontrado com este id."));
  }

  @Transactional
  public EquipmentType save(EquipmentType EquipmentType) {
    return equipmentTypeRepository.save(EquipmentType);
  }

  public boolean existsById(Integer typeId) {
    return equipmentTypeRepository.existsById(typeId);
  }

  @Transactional
  public void deleteById(Integer typeId) {
    equipmentTypeRepository.deleteById(typeId);
  }
}
