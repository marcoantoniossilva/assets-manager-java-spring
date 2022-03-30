package io.github.marcoantoniossilva.assets_manager.domain.repository;

import io.github.marcoantoniossilva.assets_manager.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

  Optional<Company> findByCnpj(String cnpj);

}
