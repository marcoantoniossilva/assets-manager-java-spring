package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.api.assembler.EquipmentTypeAssembler;
import io.github.marcoantoniossilva.assets_manager.api.model.EquipmentTypeDTO;
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
  public List<EquipmentTypeDTO> list() {
    return equipmentTypeAssembler.entityCollectionToDTOCollection(equipmentTypeService.findAll());
  }

  @GetMapping("{equipmentTypeId}")
  public ResponseEntity<EquipmentTypeDTO> search(@PathVariable Integer equipmentTypeId) {
    return equipmentTypeService.findById(equipmentTypeId)
        .map(equipmentType -> ResponseEntity.ok(equipmentTypeAssembler.entityToDTO(equipmentType)))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EquipmentTypeDTO add(@Valid @RequestBody EquipmentTypeDTO equipmentTypeDTO) {
    EquipmentType equipmentType = equipmentTypeAssembler.DTOToEntity(equipmentTypeDTO);
    EquipmentType savedEquipmentType = equipmentTypeService.save(equipmentType);
    return equipmentTypeAssembler.entityToDTO(savedEquipmentType);
  }

  @PutMapping("{equipmentTypeId}")
  public ResponseEntity<EquipmentTypeDTO> update(@PathVariable Integer equipmentTypeId,
                                                 @Valid @RequestBody EquipmentTypeDTO equipmentTypeDTO) {
    if (!equipmentTypeService.existsById(equipmentTypeId)) {
      return ResponseEntity.notFound().build();
    }
    equipmentTypeDTO.setId(equipmentTypeId);
    EquipmentType equipmentType = equipmentTypeAssembler.DTOToEntity(equipmentTypeDTO);
    EquipmentType savedEquipmentType = equipmentTypeService.save(equipmentType);

    return ResponseEntity.ok(equipmentTypeAssembler.entityToDTO(savedEquipmentType));
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
