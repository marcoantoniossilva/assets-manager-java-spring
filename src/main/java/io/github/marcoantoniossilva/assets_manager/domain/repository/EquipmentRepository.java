package io.github.marcoantoniossilva.assets_manager.domain.repository;

import io.github.marcoantoniossilva.assets_manager.domain.model.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

  Page<Equipment> findByDescriptionIsContainingIgnoreCase(String searchTerm, Pageable pageable);

  Page<Equipment> findAll(Pageable pageable);

}
