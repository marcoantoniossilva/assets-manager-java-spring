package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.model.Equipment;
import io.github.marcoantoniossilva.assets_manager.domain.repository.EquipmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService extends BaseCrudService<Equipment, Integer> {

  private final EquipmentRepository equipmentRepository;

  public EquipmentService(EquipmentRepository equipmentRepository) {
    this.equipmentRepository = equipmentRepository;
  }

  public Page<Equipment> search(String searchTerm, Pageable pageable) {
    return equipmentRepository.findByDescriptionIsContainingIgnoreCase(searchTerm, pageable);
  }

  protected EquipmentRepository getRepository() {
    return this.equipmentRepository;
  }
}