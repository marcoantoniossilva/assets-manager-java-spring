package io.github.marcoantoniossilva.assets_manager.api.controller;


import io.github.marcoantoniossilva.assets_manager.api.assembler.CompanyAssembler;
import io.github.marcoantoniossilva.assets_manager.api.model.CompanyModel;
import io.github.marcoantoniossilva.assets_manager.domain.model.Company;
import io.github.marcoantoniossilva.assets_manager.domain.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

  private final CompanyService companyService;
  private final CompanyAssembler companyAssembler;

  public CompanyController(CompanyService companyService, CompanyAssembler companyAssembler) {
    this.companyService = companyService;
    this.companyAssembler = companyAssembler;
  }

  @GetMapping
  public List<CompanyModel> list() {
    return companyAssembler.toCollectionModel(companyService.findAll());
  }

  @GetMapping("{companyId}")
  public ResponseEntity<CompanyModel> search(@PathVariable Integer companyId) {
    return companyService.findById(companyId)
        .map(company ->
            ResponseEntity.ok(companyAssembler.toModel(company))
        )
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CompanyModel add(@Valid @RequestBody CompanyModel companyModel) {
    Company company = companyAssembler.toEntity(companyModel);
    Company savedCompany = companyService.save(company);
    return companyAssembler.toModel(savedCompany);
  }

  @PutMapping("{companyId}")
  public ResponseEntity<CompanyModel> update(@PathVariable Integer companyId,@Valid @RequestBody CompanyModel companyModel) {
    if (!companyService.existsById(companyId)) {
      return ResponseEntity.notFound().build();
    }
    companyModel.setId(companyId);

    Company company = companyAssembler.toEntity(companyModel);
    Company savedCompany = companyService.save(company);
    return ResponseEntity.ok(companyAssembler.toModel(savedCompany));
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
