package io.github.marcoantoniossilva.assets_manager.domain.repository;

import io.github.marcoantoniossilva.assets_manager.domain.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Integer> {

}
