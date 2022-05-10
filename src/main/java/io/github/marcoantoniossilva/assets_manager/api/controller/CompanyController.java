package io.github.marcoantoniossilva.assets_manager.api.controller;


import io.github.marcoantoniossilva.assets_manager.api.assembler.CompanyAssembler;
import io.github.marcoantoniossilva.assets_manager.api.model.CompanyDTO;
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
  public List<CompanyDTO> list() {
    return companyAssembler.entityCollectionToDTOCollection(companyService.findAll());
  }

  @GetMapping("{companyId}")
  public ResponseEntity<CompanyDTO> search(@PathVariable Integer companyId) {
    return companyService.findById(companyId)
        .map(company ->
            ResponseEntity.ok(companyAssembler.entityToDTO(company))
        )
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CompanyDTO add(@Valid @RequestBody CompanyDTO companyDTO) {
    Company company = companyAssembler.DTOToEntity(companyDTO);
    Company savedCompany = companyService.save(company);
    return companyAssembler.entityToDTO(savedCompany);
  }

  @PutMapping("{companyId}")
  public ResponseEntity<CompanyDTO> update(@PathVariable Integer companyId, @Valid @RequestBody CompanyDTO companyDTO) {
    if (!companyService.existsById(companyId)) {
      return ResponseEntity.notFound().build();
    }
    companyDTO.setId(companyId);

    Company company = companyAssembler.DTOToEntity(companyDTO);
    Company savedCompany = companyService.save(company);
    return ResponseEntity.ok(companyAssembler.entityToDTO(savedCompany));
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
