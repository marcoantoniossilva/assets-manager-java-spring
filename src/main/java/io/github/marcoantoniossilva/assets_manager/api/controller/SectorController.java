package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.api.assembler.SectorAssembler;
import io.github.marcoantoniossilva.assets_manager.api.model.SectorModel;
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
  public List<SectorModel> list() {
    return sectorAssembler.toCollectionModel(sectorService.findAll());
  }

  @GetMapping("{sectorId}")
  public ResponseEntity<SectorModel> search(@PathVariable Integer sectorId) {
    return sectorService.findById(sectorId)
        .map(sector -> ResponseEntity.ok(sectorAssembler.toModel(sector)))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SectorModel add(@Valid @RequestBody SectorModel sectorModel) {
    Sector sector = sectorAssembler.toEntity(sectorModel);
    Sector savedSector = sectorService.save(sector);
    return sectorAssembler.toModel(savedSector);
  }

  @PutMapping("/{sectorId}")
  public ResponseEntity<SectorModel> update(@PathVariable Integer sectorId,
                                            @Valid @RequestBody SectorModel sectorModel) {
    if (!sectorService.existsById(sectorId)) {
      return ResponseEntity.notFound().build();
    }
    sectorModel.setId(sectorId);
    Sector sector = sectorAssembler.toEntity(sectorModel);
    Sector savedSector = sectorService.save(sector);

    return ResponseEntity.ok(sectorAssembler.toModel(savedSector));
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
