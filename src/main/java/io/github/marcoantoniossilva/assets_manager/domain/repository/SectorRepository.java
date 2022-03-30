package io.github.marcoantoniossilva.assets_manager.domain.repository;

import io.github.marcoantoniossilva.assets_manager.domain.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, Integer> {
}
