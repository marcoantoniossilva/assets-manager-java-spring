package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.exception.ResourceNotFoundException;
import io.github.marcoantoniossilva.assets_manager.domain.model.Sector;
import io.github.marcoantoniossilva.assets_manager.domain.repository.SectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SectorService {

  private final SectorRepository sectorRepository;

  public SectorService(SectorRepository sectorRepository) {
    this.sectorRepository = sectorRepository;
  }

  public List<Sector> list() {
    return sectorRepository.findAll();
  }

  public Optional<Sector> findById(Integer sectorId) {
    return sectorRepository.findById(sectorId);
  }

  public Sector getById(Integer sectorId) {
    Optional<Sector> sector = sectorRepository.findById(sectorId);
    return sector.orElseThrow(() -> new ResourceNotFoundException("Setor não encontrado com este id."));
  }

  @Transactional
  public Sector save(Sector sector) {
    return sectorRepository.save(sector);
  }

  public boolean existsById(Integer sectorId) {
    return sectorRepository.existsById(sectorId);
  }

  @Transactional
  public void deleteById(Integer sectorId) {
    sectorRepository.deleteById(sectorId);
  }
}
