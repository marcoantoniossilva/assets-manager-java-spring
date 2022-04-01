package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.exception.BusinessException;
import io.github.marcoantoniossilva.assets_manager.domain.model.Company;
import io.github.marcoantoniossilva.assets_manager.domain.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CompanyService {

  private final CompanyRepository companyRepository;

  public CompanyService(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  @Transactional
  public List<Company> list() {
    return companyRepository.findAll();
  }

  @Transactional
  public Optional<Company> findById(Integer companyId) {
    return companyRepository.findById(companyId);
  }

  @Transactional
  public Company save(Company company) {

    Optional<Company> companyWithSameCnpj = companyRepository.findByCnpj(company.getCnpj());

    boolean differentCompaniesSameCnpj = companyWithSameCnpj.stream()
        .anyMatch(existentCompany -> !Objects.equals(existentCompany.getId(), company.getId()));

    if (differentCompaniesSameCnpj) { // Se estiver criando uma empresa
      throw new BusinessException("Já existe uma empresa com este CNPJ!");
    }
    return companyRepository.save(company);
  }

  @Transactional
  public boolean existsById(Integer companyId) {
    return companyRepository.existsById(companyId);
  }

  @Transactional
  public void deleteById(Integer companyId) {
    companyRepository.deleteById(companyId);
  }

}
