package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.model.Sector;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
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

  @Transactional
  public List<Sector> list() {
    return sectorRepository.findAll();
  }

  @Transactional
  public Optional<Sector> findById(Integer sectorId) {
    return sectorRepository.findById(sectorId);
  }

  @Transactional
  public Sector save(Sector sector) {
    return sectorRepository.save(sector);
  }

  @Transactional
  public boolean existsById(Integer sectorId) {
    return sectorRepository.existsById(sectorId);
  }

  @Transactional
  public void deleteById(Integer sectorId) {
    sectorRepository.deleteById(sectorId);
  }
}
