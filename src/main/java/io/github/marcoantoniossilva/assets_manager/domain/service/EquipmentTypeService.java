package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.model.EquipmentType;
import io.github.marcoantoniossilva.assets_manager.domain.repository.EquipmentTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipmentTypeService extends BaseCrudService<EquipmentType, Integer>{
  
  private final EquipmentTypeRepository equipmentTypeRepository;

  public EquipmentTypeService(EquipmentTypeRepository equipmentTypeRepository) {
    this.equipmentTypeRepository = equipmentTypeRepository;
  }

  protected EquipmentTypeRepository getRepository() {
    return this.equipmentTypeRepository;
  }
}
