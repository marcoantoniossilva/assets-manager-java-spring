package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.api.assembler.SectorAssembler;
import io.github.marcoantoniossilva.assets_manager.api.model.SectorDTO;
import io.github.marcoantoniossilva.assets_manager.domain.model.Sector;
import io.github.marcoantoniossilva.assets_manager.domain.service.SectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sectors")
public class SectorController {

  private final SectorService sectorService;
  private final SectorAssembler sectorAssembler;

  public SectorController(SectorService sectorService, SectorAssembler sectorAssembler) {
    this.sectorService = sectorService;
    this.sectorAssembler = sectorAssembler;
  }

  @GetMapping
  public List<SectorDTO> list() {
    return sectorAssembler.entityCollectionToDTOCollection(sectorService.findAll());
  }

  @GetMapping("{sectorId}")
  public ResponseEntity<SectorDTO> search(@PathVariable Integer sectorId) {
    return sectorService.findById(sectorId)
        .map(sector -> ResponseEntity.ok(sectorAssembler.entityToDTO(sector)))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SectorDTO add(@Valid @RequestBody SectorDTO sectorDTO) {
    Sector sector = sectorAssembler.DTOToEntity(sectorDTO);
    Sector savedSector = sectorService.save(sector);
    return sectorAssembler.entityToDTO(savedSector);
  }

  @PutMapping("/{sectorId}")
  public ResponseEntity<SectorDTO> update(@PathVariable Integer sectorId,
                                          @Valid @RequestBody SectorDTO sectorDTO) {
    if (!sectorService.existsById(sectorId)) {
      return ResponseEntity.notFound().build();
    }
    sectorDTO.setId(sectorId);
    Sector sector = sectorAssembler.DTOToEntity(sectorDTO);
    Sector savedSector = sectorService.save(sector);

    return ResponseEntity.ok(sectorAssembler.entityToDTO(savedSector));
  }

  @DeleteMapping("/{sectorId}")
  public ResponseEntity<Void> delete(@PathVariable Integer sectorId) {
    if (!sectorService.existsById(sectorId)) {
      return ResponseEntity.notFound().build();
    }
    sectorService.deleteById(sectorId);
    return ResponseEntity.noContent().build();
  }


}
