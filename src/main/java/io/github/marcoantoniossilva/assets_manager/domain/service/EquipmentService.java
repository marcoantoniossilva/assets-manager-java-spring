package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.exception.BusinessException;
import io.github.marcoantoniossilva.assets_manager.domain.model.*;
import io.github.marcoantoniossilva.assets_manager.domain.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

  private final EquipmentRepository equipmentRepository;
  private final CompanyService companyService;
  private final UserService userService;
  private final TypeService typeService;
  private final SectorService sectorService;


  public EquipmentService(EquipmentRepository equipmentRepository1, CompanyService companyService, UserService userService, TypeService typeService, SectorService sectorService) {

    this.equipmentRepository = equipmentRepository1;
    this.companyService = companyService;
    this.userService = userService;
    this.typeService = typeService;
    this.sectorService = sectorService;
  }

  public List<Equipment> list() {
    return equipmentRepository.findAll();
  }

  public Optional<Equipment> findById(Integer equipmentId) {
    return equipmentRepository.findById(equipmentId);
  }

  @Transactional
  public Equipment save(Equipment equipment) {
    Company company = companyService
        .findById(equipment.getCompany().getId())
        .orElseThrow(() -> new BusinessException("Empresa não encontrada!"));

    User user = userService
        .findById(equipment.getUser().getId())
        .orElseThrow(() -> new BusinessException("Usuário não encontrado!"));

    Type type = typeService
        .findById(equipment.getType().getId())
        .orElseThrow(() -> new BusinessException("Tipo de equipamento não encontrado!"));

    Sector sector = sectorService
        .findById(equipment.getSector().getId())
        .orElseThrow(() -> new BusinessException("Setor não encontrado!"));

    equipment.setUser(user);
    equipment.setCompany(company);
    equipment.setType(type);
    equipment.setSector(sector);

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