package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.api.assembler.EquipmentAssembler;
import io.github.marcoantoniossilva.assets_manager.api.assembler.NfeAssembler;
import io.github.marcoantoniossilva.assets_manager.api.model.EquipmentModel;
import io.github.marcoantoniossilva.assets_manager.api.model.input.EquipmentInput;
import io.github.marcoantoniossilva.assets_manager.common.LoggedUser;
import io.github.marcoantoniossilva.assets_manager.domain.enumeration.Status;
import io.github.marcoantoniossilva.assets_manager.domain.model.*;
import io.github.marcoantoniossilva.assets_manager.domain.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipments")
public class EquipmentController {

  private final TypeService typeService;
  private final NfeAssembler nfeAssembler;
  private final SectorService sectorService;
  private final CompanyService companyService;
  private final EquipmentService equipmentService;
  private final EquipmentAssembler equipmentAssembler;

  public EquipmentController(EquipmentAssembler equipmentAssembler, EquipmentService equipmentService,
                             TypeService typeService, NfeAssembler nfeAssembler,
                             SectorService sectorService, CompanyService companyService) {
    this.equipmentAssembler = equipmentAssembler;
    this.equipmentService = equipmentService;
    this.typeService = typeService;
    this.nfeAssembler = nfeAssembler;
    this.sectorService = sectorService;
    this.companyService = companyService;
  }

  @GetMapping
  private List<EquipmentModel> list() {
    return equipmentAssembler.toCollectionModel(equipmentService.list());
  }

  @GetMapping("{equipmentId}")
  public ResponseEntity<EquipmentModel> search(@PathVariable Integer equipmentId) {
    return equipmentService.findById(equipmentId)
        .map(equipment -> ResponseEntity.ok(equipmentAssembler.toModel(equipment)))
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("{equipmentId}/nfe")
  public ResponseEntity<byte[]> searchNfe(@PathVariable Integer equipmentId){
    Equipment equipment = equipmentService.getById(equipmentId);
    Nfe nfe = equipment.getNfe();

    return ResponseEntity
        .ok()
        .contentType(MediaType.valueOf(nfe.getType()))
        .body(nfe.getContent());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  private EquipmentModel add(@ModelAttribute EquipmentInput equipmentInput) {
    Integer companyId = equipmentInput.getCompany();
    Integer sectorId = equipmentInput.getSector();
    Integer typeId = equipmentInput.getType();

    User user = LoggedUser.getUser();
    Company company = companyService.getById(companyId);
    Sector sector = sectorService.getById(sectorId);
    Type type = typeService.getById(typeId);
    Status status = Enum.valueOf(Status.class, equipmentInput.getStatus());

    Equipment equipment = equipmentAssembler.toEntity(equipmentInput);
    equipment.setCompany(company);
    equipment.setUser(user);
    equipment.setType(type);
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
  public ResponseEntity<EquipmentModel> update(@RequestBody EquipmentInput equipmentInput, @PathVariable Integer equipmentId) {
    if (!equipmentService.existsById(equipmentId)) {
      return ResponseEntity.notFound().build();
    }
    equipmentInput.setId(equipmentId);

    Integer companyId = equipmentInput.getCompany();
    Integer sectorId = equipmentInput.getSector();
    Integer typeId = equipmentInput.getType();

    User user = LoggedUser.getUser();
    Company company = companyService.getById(companyId);
    Sector sector = sectorService.getById(sectorId);
    Type type = typeService.getById(typeId);
    Status status = Enum.valueOf(Status.class, equipmentInput.getStatus());

    Equipment equipment = equipmentAssembler.toEntity(equipmentInput);
    equipment.setCompany(company);
    equipment.setUser(user);
    equipment.setType(type);
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
