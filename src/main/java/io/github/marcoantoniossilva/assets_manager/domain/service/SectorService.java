package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.model.Sector;
import io.github.marcoantoniossilva.assets_manager.domain.repository.SectorRepository;
import org.springframework.stereotype.Service;

@Service
public class SectorService extends BaseCrudService<Sector, Integer>{

  private final SectorRepository sectorRepository;

  public SectorService(SectorRepository sectorRepository) {
    this.sectorRepository = sectorRepository;
  }

  protected SectorRepository getRepository() {
    return this.sectorRepository;
  }

}
