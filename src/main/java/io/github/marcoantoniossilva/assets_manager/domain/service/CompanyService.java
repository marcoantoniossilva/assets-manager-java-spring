package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.exception.BusinessException;
import io.github.marcoantoniossilva.assets_manager.domain.model.Company;
import io.github.marcoantoniossilva.assets_manager.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {

  @Autowired
  private CompanyRepository companyRepository;

  @Transactional
  public Company save(Company company) {
    boolean cnpjInUse = companyRepository.findByCnpj(company.getCnpj())
        .stream().anyMatch(existentCompany -> !existentCompany.equals(company));

    if (cnpjInUse) {
      throw new BusinessException("Já existe uma empresa com este CNPJ!");
    }
    return companyRepository.save(company);
  }

}
