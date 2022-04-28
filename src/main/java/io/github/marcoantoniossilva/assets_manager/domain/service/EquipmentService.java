package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.exception.ResourceNotFoundException;
import io.github.marcoantoniossilva.assets_manager.domain.model.Equipment;
import io.github.marcoantoniossilva.assets_manager.domain.repository.EquipmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EquipmentService {

  private final EquipmentRepository equipmentRepository;


  public EquipmentService(EquipmentRepository equipmentRepository) {

    this.equipmentRepository = equipmentRepository;
  }

  public Page<Equipment> findAll(Pageable pageable) {
    return equipmentRepository.findAll(pageable);
  }

  public Page<Equipment> search(String searchTerm, PageRequest pageRequest) {
    return equipmentRepository.findByDescriptionIsContainingIgnoreCase(searchTerm, pageRequest);
  }

  public Equipment getById(Integer equipmentId) {
    Optional<Equipment> equipment = equipmentRepository.findById(equipmentId);
    return equipment.orElseThrow(() -> new ResourceNotFoundException("Equipamento não encontrado com este id."));
  }

  public Optional<Equipment> findById(Integer equipmentId) {
    return equipmentRepository.findById(equipmentId);
  }

  @Transactional
  public Equipment save(Equipment equipment) {
    return equipmentRepository.save(equipment);
  }

  public boolean existsById(Integer equipmentId) {
    return equipmentRepository.existsById(equipmentId);
  }

  @Transactional
  public void deleteById(Integer equipmentId) {
    equipmentRepository.deleteById(equipmentId);
  }
}