package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.api.assembler.EquipmentAssembler;
import io.github.marcoantoniossilva.assets_manager.api.assembler.NfeAssembler;
import io.github.marcoantoniossilva.assets_manager.api.model.EquipmentModel;
import io.github.marcoantoniossilva.assets_manager.api.model.input.EquipmentInput;
import io.github.marcoantoniossilva.assets_manager.common.LoggedUser;
import io.github.marcoantoniossilva.assets_manager.domain.exception.ResourceNotFoundException;
import io.github.marcoantoniossilva.assets_manager.domain.model.*;
import io.github.marcoantoniossilva.assets_manager.domain.model.enumeration.Status;
import io.github.marcoantoniossilva.assets_manager.domain.service.CompanyService;
import io.github.marcoantoniossilva.assets_manager.domain.service.EquipmentService;
import io.github.marcoantoniossilva.assets_manager.domain.service.EquipmentTypeService;
import io.github.marcoantoniossilva.assets_manager.domain.service.SectorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipments")
public class EquipmentController {

  private final EquipmentTypeService equipmentTypeService;
  private final NfeAssembler nfeAssembler;
  private final SectorService sectorService;
  private final CompanyService companyService;
  private final EquipmentService equipmentService;
  private final EquipmentAssembler equipmentAssembler;

  public EquipmentController(EquipmentAssembler equipmentAssembler, EquipmentService equipmentService,
                             EquipmentTypeService equipmentTypeService, NfeAssembler nfeAssembler,
                             SectorService sectorService, CompanyService companyService) {
    this.equipmentAssembler = equipmentAssembler;
    this.equipmentService = equipmentService;
    this.equipmentTypeService = equipmentTypeService;
    this.nfeAssembler = nfeAssembler;
    this.sectorService = sectorService;
    this.companyService = companyService;
  }

  @GetMapping
  private Page<EquipmentModel> list(@PageableDefault Pageable pageable) {
    Page<Equipment> result = equipmentService.findAll(pageable);
    return equipmentAssembler.pageEntityToPageModel(result);
  }

  @GetMapping("search")
  public Page<EquipmentModel> search(@RequestParam String searchTerm, @PageableDefault Pageable pageable) {
    Page<Equipment> result = equipmentService.search(searchTerm, pageable);
    return equipmentAssembler.pageEntityToPageModel(result);
  }

  @GetMapping("{equipmentId}")
  public ResponseEntity<EquipmentModel> getById(@PathVariable Integer equipmentId) {
    return equipmentService.findById(equipmentId)
        .map(equipment -> ResponseEntity.ok(equipmentAssembler.toModel(equipment)))
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("{equipmentId}/nfe")
  public ResponseEntity<byte[]> searchNfe(@PathVariable Integer equipmentId) {
    Equipment equipment = equipmentService.findOrFailById(equipmentId);
    Nfe nfe = equipment.getNfe();

    if (nfe != null) {
      return ResponseEntity
          .ok()
          .contentType(MediaType.valueOf(nfe.getType()))
          .body(nfe.getContent());
    }
    throw new ResourceNotFoundException("Nota fiscal não encontrada para este equipamento.");
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  private EquipmentModel add(@ModelAttribute EquipmentInput equipmentInput) {
    Integer companyId = equipmentInput.getCompany();
    Integer sectorId = equipmentInput.getSector();
    Integer equipmentTypeId = equipmentInput.getEquipmentType();

    User user = LoggedUser.getUser();
    Company company = companyService.findOrFailById(companyId);
    Sector sector = sectorService.findOrFailById(sectorId);
    EquipmentType equipmentType = equipmentTypeService.findOrFailById(equipmentTypeId);
    Status status = equipmentInput.getStatus();

    Equipment equipment = equipmentAssembler.toEntity(equipmentInput);
    equipment.setCompany(company);
    equipment.setUser(user);
    equipment.setEquipmentType(equipmentType);
    equipment.setSector(sector);
    equipment.setStatus(status);

    equipmentInput.getNfe().ifPresent(multipartFile -> {
      Nfe nfe = nfeAssembler.multipartFileToEntity(multipartFile);
      equipment.setNfe(nfe);
    });

    Equipment savedEquipment = equipmentService.save(equipment);
    return equipmentAssembler.toModel(savedEquipment);
  }

  @PutMapping("{equipmentId}")
  public ResponseEntity<EquipmentModel> update(@ModelAttribute EquipmentInput equipmentInput, @PathVariable Integer equipmentId) {
    if (!equipmentService.existsById(equipmentId)) {
      return ResponseEntity.notFound().build();
    }

    equipmentInput.setId(equipmentId);

    Integer companyId = equipmentInput.getCompany();
    Integer sectorId = equipmentInput.getSector();
    Integer equipmentTypeId = equipmentInput.getEquipmentType();

    User user = LoggedUser.getUser();
    Company company = companyService.findOrFailById(companyId);
    Sector sector = sectorService.findOrFailById(sectorId);
    EquipmentType equipmentType = equipmentTypeService.findOrFailById(equipmentTypeId);
    Status status = equipmentInput.getStatus();

    Equipment equipment = equipmentAssembler.toEntity(equipmentInput);
    equipment.setCompany(company);
    equipment.setUser(user);
    equipment.setEquipmentType(equipmentType);
    equipment.setSector(sector);
    equipment.setStatus(status);

    equipmentInput.getNfe().ifPresent(multipartFile -> {
      Nfe nfe = nfeAssembler.multipartFileToEntity(multipartFile);
      equipment.setNfe(nfe);
    });

    Equipment savedEquipment = equipmentService.save(equipment);
    return ResponseEntity.ok(equipmentAssembler.toModel(savedEquipment));
  }

  @DeleteMapping("{equipmentId}")
  public ResponseEntity<Void> delete(@PathVariable Integer equipmentId) {
    if (!equipmentService.existsById(equipmentId)) {
      return ResponseEntity.notFound().build();
    }
    equipmentService.deleteById(equipmentId);
    return ResponseEntity.noContent().build();
  }


}
