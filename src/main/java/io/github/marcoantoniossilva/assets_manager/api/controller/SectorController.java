package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.domain.model.Sector;
import io.github.marcoantoniossilva.assets_manager.domain.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sectors")
public class SectorController {

  @Autowired
  private SectorRepository sectorRepository;

  @GetMapping
  public List<Sector> list() {
    return sectorRepository.findAll();
  }

  @GetMapping("{sectorId}")
  public ResponseEntity<Sector> search(@PathVariable Integer sectorId) {
    return sectorRepository.findById(sectorId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Sector add(@RequestBody Sector sector) {
    return sectorRepository.save(sector);
  }

  @PutMapping("/{sectorId}")
  public ResponseEntity<Sector> update(@PathVariable Integer sectorId, @RequestBody Sector sector) {
    if (!sectorRepository.existsById(sectorId)) {
      return ResponseEntity.notFound().build();
    }
    sector.setId(sectorId);
    Sector savedSector = sectorRepository.save(sector);
    return ResponseEntity.ok(savedSector);
  }

  @DeleteMapping("/{sectorId}")
  public ResponseEntity<Void> delete(@PathVariable Integer sectorId) {
    if (!sectorRepository.existsById(sectorId)) {
      return ResponseEntity.notFound().build();
    }
    sectorRepository.deleteById(sectorId);
    return ResponseEntity.noContent().build();
  }


}
