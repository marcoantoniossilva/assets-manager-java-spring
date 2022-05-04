package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.exception.ExistentResourceException;
import io.github.marcoantoniossilva.assets_manager.domain.exception.ResourceNotFoundException;
import io.github.marcoantoniossilva.assets_manager.domain.model.Company;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import io.github.marcoantoniossilva.assets_manager.domain.repository.CompanyRepository;
import io.github.marcoantoniossilva.assets_manager.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CompanyService extends BaseCrudService<Company, Integer> {

  private final CompanyRepository companyRepository;

  public CompanyService(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  @Transactional
  public Company save(Company company) {

    Optional<Company> companyWithSameCnpj = companyRepository.findByCnpj(company.getCnpj());

    boolean differentCompaniesSameCnpj = companyWithSameCnpj.stream()
        .anyMatch(existentCompany -> !Objects.equals(existentCompany.getId(), company.getId()));

    if (differentCompaniesSameCnpj) { // Se estiver criando uma empresa
      throw new ExistentResourceException("Já existe uma empresa com este CNPJ!");
    }
    return companyRepository.save(company);
  }

  protected CompanyRepository getRepository() {
    return this.companyRepository;
  }

  protected String getEntityName(){
    return "Company";
  }
}
