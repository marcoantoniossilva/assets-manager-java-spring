package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.api.assembler.EquipmentTypeAssembler;
import io.github.marcoantoniossilva.assets_manager.api.model.EquipmentTypeModel;
import io.github.marcoantoniossilva.assets_manager.domain.model.EquipmentType;
import io.github.marcoantoniossilva.assets_manager.domain.service.EquipmentTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/equipment-types")
public class EquipmentTypeController {

  private final EquipmentTypeService equipmentTypeService;
  private final EquipmentTypeAssembler equipmentTypeAssembler;

  public EquipmentTypeController(EquipmentTypeService equipmentTypeService, EquipmentTypeAssembler equipmentTypeAssembler) {
    this.equipmentTypeService = equipmentTypeService;
    this.equipmentTypeAssembler = equipmentTypeAssembler;
  }

  @GetMapping
  public List<EquipmentTypeModel> list() {
    return equipmentTypeAssembler.toCollectionModel(equipmentTypeService.findAll());
  }

  @GetMapping("{equipmentTypeId}")
  public ResponseEntity<EquipmentTypeModel> search(@PathVariable Integer equipmentTypeId) {
    return equipmentTypeService.findById(equipmentTypeId)
        .map(equipmentType -> ResponseEntity.ok(equipmentTypeAssembler.toModel(equipmentType)))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EquipmentTypeModel add(@Valid @RequestBody EquipmentTypeModel equipmentTypeModel) {
    EquipmentType equipmentType = equipmentTypeAssembler.toEntity(equipmentTypeModel);
    EquipmentType savedEquipmentType = equipmentTypeService.save(equipmentType);
    return equipmentTypeAssembler.toModel(savedEquipmentType);
  }

  @PutMapping("{equipmentTypeId}")
  public ResponseEntity<EquipmentTypeModel> update(@PathVariable Integer equipmentTypeId,
                                                   @Valid @RequestBody EquipmentTypeModel equipmentTypeModel) {
    if (!equipmentTypeService.existsById(equipmentTypeId)) {
      return ResponseEntity.notFound().build();
    }
    equipmentTypeModel.setId(equipmentTypeId);
    EquipmentType equipmentType = equipmentTypeAssembler.toEntity(equipmentTypeModel);
    EquipmentType savedEquipmentType = equipmentTypeService.save(equipmentType);

    return ResponseEntity.ok(equipmentTypeAssembler.toModel(savedEquipmentType));
  }

  @DeleteMapping("{equipmentTypeId}")
  public ResponseEntity<Void> delete(@PathVariable Integer equipmentTypeId) {
    if (!equipmentTypeService.existsById(equipmentTypeId)) {
      return ResponseEntity.notFound().build();
    }
    equipmentTypeService.deleteById(equipmentTypeId);
    return ResponseEntity.noContent().build();
  }

}
