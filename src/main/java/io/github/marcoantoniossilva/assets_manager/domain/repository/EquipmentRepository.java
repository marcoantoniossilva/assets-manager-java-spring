package io.github.marcoantoniossilva.assets_manager.domain.repository;

import io.github.marcoantoniossilva.assets_manager.domain.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
}
