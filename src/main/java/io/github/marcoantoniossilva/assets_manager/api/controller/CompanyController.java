package io.github.marcoantoniossilva.assets_manager.api.controller;


import io.github.marcoantoniossilva.assets_manager.domain.model.Company;
import io.github.marcoantoniossilva.assets_manager.domain.repository.CompanyRepository;
import io.github.marcoantoniossilva.assets_manager.domain.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

  @Autowired
  private CompanyRepository companyRepository;
  @Autowired
  private CompanyService companyService;

  @GetMapping
  public List<Company> list() {
    return companyRepository.findAll();
  }

  @GetMapping("{companyId}")
  public ResponseEntity<Company> search(@PathVariable Integer companyId) {
    return companyRepository.findById(companyId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Company add(@RequestBody Company company) {
    return companyService.save(company);
  }

  @PutMapping("{companyId}")
  public ResponseEntity<Company> update(@PathVariable Integer companyId, @RequestBody Company company) {
    if (!companyRepository.existsById(companyId)) {
      return ResponseEntity.notFound().build();
    }
    company.setId(companyId);
    Company savedCompany = companyService.save(company);
    return ResponseEntity.ok(savedCompany);
  }

  @DeleteMapping("{companyId}")
  public ResponseEntity<Void> delete(@PathVariable Integer companyId) {
    if (!companyRepository.existsById(companyId)) {
      return ResponseEntity.notFound().build();
    }
    companyRepository.deleteById(companyId);
    return ResponseEntity.noContent().build();
  }
}
