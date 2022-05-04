package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.common.UpdateUtils;
import io.github.marcoantoniossilva.assets_manager.domain.model.Sector;
import io.github.marcoantoniossilva.assets_manager.domain.service.SectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sectors")
public class SectorController {

  private final SectorService sectorService;

  public SectorController(SectorService sectorService) {
    this.sectorService = sectorService;
  }

  @GetMapping
  public List<Sector> list() {
    return sectorService.findAll();
  }

  @GetMapping("{sectorId}")
  public ResponseEntity<Sector> search(@PathVariable Integer sectorId) {
    return sectorService.findById(sectorId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Sector add(@RequestBody Sector sector) {
    return sectorService.save(sector);
  }

  @PutMapping("/{sectorId}")
  public ResponseEntity<Sector> update(@PathVariable Integer sectorId, @RequestBody Sector sector) {
    if (!sectorService.existsById(sectorId)) {
      return ResponseEntity.notFound().build();
    }
    sector.setId(sectorId);
    Sector existentSector = sectorService.findOrFailById(sectorId);
    UpdateUtils.copyNonNullProperties(sector, existentSector);
    Sector savedSector = sectorService.save(existentSector);
    return ResponseEntity.ok(savedSector);
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
