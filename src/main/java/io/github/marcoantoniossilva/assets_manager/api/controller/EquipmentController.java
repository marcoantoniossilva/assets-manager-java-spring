package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.api.assembler.EquipmentAssembler;
import io.github.marcoantoniossilva.assets_manager.api.model.EquipmentModel;
import io.github.marcoantoniossilva.assets_manager.api.model.input.EquipmentInput;
import io.github.marcoantoniossilva.assets_manager.domain.model.Equipment;
import io.github.marcoantoniossilva.assets_manager.domain.service.EquipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipments")
public class EquipmentController {

  private final EquipmentAssembler equipmentAssembler;
  private final EquipmentService equipmentService;

  public EquipmentController(EquipmentAssembler equipmentAssembler, EquipmentService equipmentService) {
    this.equipmentAssembler = equipmentAssembler;
    this.equipmentService = equipmentService;
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

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  private EquipmentModel add(@RequestBody EquipmentInput equipmentInput) {
    Equipment equipment = equipmentAssembler.toEntity(equipmentInput);
    Equipment savedEquipment = equipmentService.save(equipment);
    return equipmentAssembler.toModel(savedEquipment);
  }

  @PutMapping("{equipmentId}")
  public ResponseEntity<EquipmentModel> update(@RequestBody EquipmentInput equipmentInput, @PathVariable Integer equipmentId) {
    if (!equipmentService.existsById(equipmentId)) {
      return ResponseEntity.notFound().build();
    }
    equipmentInput.setId(equipmentId);
    Equipment equipment = equipmentAssembler.toEntity(equipmentInput);
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
