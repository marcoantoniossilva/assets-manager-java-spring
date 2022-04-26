package io.github.marcoantoniossilva.assets_manager.api.controller;


import io.github.marcoantoniossilva.assets_manager.common.UpdateUtils;
import io.github.marcoantoniossilva.assets_manager.domain.model.Company;
import io.github.marcoantoniossilva.assets_manager.domain.model.Sector;
import io.github.marcoantoniossilva.assets_manager.domain.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

  private final CompanyService companyService;

  public CompanyController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @GetMapping
  public List<Company> list() {
    return companyService.list();
  }

  @GetMapping("{companyId}")
  public ResponseEntity<Company> search(@PathVariable Integer companyId) {
    return companyService.findById(companyId)
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
    if (!companyService.existsById(companyId)) {
      return ResponseEntity.notFound().build();
    }
    company.setId(companyId);
    Company existentCompany = companyService.getById(companyId);
    UpdateUtils.copyNonNullProperties(company, existentCompany);
    Company savedCompany = companyService.save(existentCompany);
    return ResponseEntity.ok(savedCompany);
  }

  @DeleteMapping("{companyId}")
  public ResponseEntity<Void> delete(@PathVariable Integer companyId) {
    if (!companyService.existsById(companyId)) {
      return ResponseEntity.notFound().build();
    }
    companyService.deleteById(companyId);
    return ResponseEntity.noContent().build();
  }
}
