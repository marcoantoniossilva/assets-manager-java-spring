package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.domain.model.EquipmentType;
import io.github.marcoantoniossilva.assets_manager.domain.service.EquipmentTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment-types")
public class EquipmentTypeController {

  private final EquipmentTypeService equipmentTypeService;

  public EquipmentTypeController(EquipmentTypeService equipmentTypeService) {
    this.equipmentTypeService = equipmentTypeService;
  }

  @GetMapping
  public List<EquipmentType> list() {
    return equipmentTypeService.findAll();
  }

  @GetMapping("{equipmentTypeId}")
  public ResponseEntity<EquipmentType> search(@PathVariable Integer equipmentTypeId) {
    return equipmentTypeService.findById(equipmentTypeId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EquipmentType add(@RequestBody EquipmentType equipmentType) {
    return equipmentTypeService.save(equipmentType);
  }

  @PutMapping("{equipmentTypeId}")
  public ResponseEntity<EquipmentType> update(@PathVariable Integer equipmentTypeId, @RequestBody EquipmentType equipmentType) {
    if (!equipmentTypeService.existsById(equipmentTypeId)) {
      return ResponseEntity.notFound().build();
    }
    equipmentType.setId(equipmentTypeId);
    EquipmentType savedEquipmentType = equipmentTypeService.save(equipmentType);
    return ResponseEntity.ok(savedEquipmentType);
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
