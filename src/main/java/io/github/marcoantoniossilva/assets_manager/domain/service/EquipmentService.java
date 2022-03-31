package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.exception.BusinessException;
import io.github.marcoantoniossilva.assets_manager.domain.model.*;
import io.github.marcoantoniossilva.assets_manager.domain.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EquipmentService {

  private final EquipmentRepository equipmentRepository;
  private final CompanyRepository companyRepository;
  private final UserRepository userRepository;
  private final SectorRepository sectorRepository;
  private final TypeRepository typeRepository;

  public EquipmentService(EquipmentRepository equipmentRepository, CompanyRepository companyRepository, UserRepository userRepository, SectorRepository sectorRepository, TypeRepository typeRepository) {
    this.equipmentRepository = equipmentRepository;
    this.companyRepository = companyRepository;
    this.userRepository = userRepository;
    this.sectorRepository = sectorRepository;
    this.typeRepository = typeRepository;
  }

  @Transactional
  public Equipment save(Equipment equipment) {
    Company company = companyRepository
        .findById(equipment.getCompany().getId())
        .orElseThrow(() -> new BusinessException("Empresa não encontrada!"));

    User user = userRepository
        .findById(equipment.getUser().getId())
        .orElseThrow(() -> new BusinessException("Usuário não encontrado!"));

    Type type = typeRepository
        .findById(equipment.getType().getId())
        .orElseThrow(() -> new BusinessException("Tipo de equipamento não encontrado!"));

    Sector sector = sectorRepository
        .findById(equipment.getSector().getId())
        .orElseThrow(() -> new BusinessException("Setor não encontrado!"));

    equipment.setUser(user);
    equipment.setCompany(company);
    equipment.setType(type);
    equipment.setSector(sector);

    return equipmentRepository.save(equipment);
  }
}