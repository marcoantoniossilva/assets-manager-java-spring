package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.api.assembler.EquipmentAssembler;
import io.github.marcoantoniossilva.assets_manager.api.assembler.NfeAssembler;
import io.github.marcoantoniossilva.assets_manager.api.model.EquipmentDTO;
import io.github.marcoantoniossilva.assets_manager.api.model.input.EquipmentInputDTO;
import io.github.marcoantoniossilva.assets_manager.common.LoggedUser;
import io.github.marcoantoniossilva.assets_manager.domain.exception.ResourceNotFoundException;
import io.github.marcoantoniossilva.assets_manager.domain.model.*;
import io.github.marcoantoniossilva.assets_manager.domain.model.enumeration.Status;
import io.github.marcoantoniossilva.assets_manager.domain.service.CompanyService;
import io.github.marcoantoniossilva.assets_manager.domain.service.EquipmentService;
import io.github.marcoantoniossilva.assets_manager.domain.service.EquipmentTypeService;
import io.github.marcoantoniossilva.assets_manager.domain.service.SectorService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
  private Page<EquipmentDTO> list(@PageableDefault Pageable pageable) {
    Page<Equipment> result = equipmentService.findAll(pageable);
    return equipmentAssembler.pageEntityToPageModel(result);
  }

  @GetMapping("search")
  public Page<EquipmentDTO> search(@RequestParam String searchTerm, @PageableDefault Pageable pageable) {
    Page<Equipment> result = equipmentService.search(searchTerm, pageable);
    return equipmentAssembler.pageEntityToPageModel(result);
  }

  @GetMapping("{equipmentId}")
  public ResponseEntity<EquipmentDTO> getById(@PathVariable Integer equipmentId) {
    return equipmentService.findById(equipmentId)
        .map(equipment -> ResponseEntity.ok(equipmentAssembler.entityToDTO(equipment)))
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("{equipmentId}/nfe")
  public ResponseEntity<Resource> searchNfe(@PathVariable Integer equipmentId) {
    Equipment equipment = equipmentService.findOrFailById(equipmentId);
    Nfe nfe = equipment.getNfe();

    if (nfe != null) {
      return ResponseEntity
          .ok()
          .header(HttpHeaders.CONTENT_TYPE, nfe.getType())
          .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", nfe.getFileName()))
          .body(new ByteArrayResource(nfe.getContent()));
    }
    throw new ResourceNotFoundException("Nota fiscal não encontrada para este equipamento.");
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  private EquipmentDTO add(@Valid @ModelAttribute EquipmentInputDTO equipmentInputDTO) {
    Integer companyId = equipmentInputDTO.getCompany();
    Integer sectorId = equipmentInputDTO.getSector();
    Integer equipmentTypeId = equipmentInputDTO.getEquipmentType();

    User user = LoggedUser.getUser();
    Company company = companyService.findOrFailById(companyId);
    Sector sector = sectorService.findOrFailById(sectorId);
    EquipmentType equipmentType = equipmentTypeService.findOrFailById(equipmentTypeId);
    Status status = equipmentInputDTO.getStatus();

    Equipment equipment = equipmentAssembler.DTOToEntity(equipmentInputDTO);
    equipment.setCompany(company);
    equipment.setUser(user);
    equipment.setEquipmentType(equipmentType);
    equipment.setSector(sector);
    equipment.setStatus(status);

    equipmentInputDTO.getNfe().ifPresent(multipartFile -> {
      Nfe nfe = nfeAssembler.multipartFileToEntity(multipartFile);
      equipment.setNfe(nfe);
    });

    Equipment savedEquipment = equipmentService.save(equipment);
    return equipmentAssembler.entityToDTO(savedEquipment);
  }

  @PutMapping("{equipmentId}")
  public ResponseEntity<EquipmentDTO> update(@Valid @ModelAttribute EquipmentInputDTO equipmentInputDTO, @PathVariable Integer equipmentId) {
    if (!equipmentService.existsById(equipmentId)) {
      return ResponseEntity.notFound().build();
    }

    equipmentInputDTO.setId(equipmentId);

    Integer companyId = equipmentInputDTO.getCompany();
    Integer sectorId = equipmentInputDTO.getSector();
    Integer equipmentTypeId = equipmentInputDTO.getEquipmentType();

    User user = LoggedUser.getUser();
    Company company = companyService.findOrFailById(companyId);
    Sector sector = sectorService.findOrFailById(sectorId);
    EquipmentType equipmentType = equipmentTypeService.findOrFailById(equipmentTypeId);
    Status status = equipmentInputDTO.getStatus();

    Equipment equipment = equipmentAssembler.DTOToEntity(equipmentInputDTO);
    equipment.setCompany(company);
    equipment.setUser(user);
    equipment.setEquipmentType(equipmentType);
    equipment.setSector(sector);
    equipment.setStatus(status);

    equipmentInputDTO.getNfe().ifPresent(multipartFile -> {
      Nfe nfe = nfeAssembler.multipartFileToEntity(multipartFile);
      equipment.setNfe(nfe);
    });

    Equipment savedEquipment = equipmentService.save(equipment);
    return ResponseEntity.ok(equipmentAssembler.entityToDTO(savedEquipment));
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
