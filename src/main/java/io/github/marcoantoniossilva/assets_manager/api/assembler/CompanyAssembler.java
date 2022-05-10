package io.github.marcoantoniossilva.assets_manager.api.assembler;

import io.github.marcoantoniossilva.assets_manager.api.model.CompanyDTO;
import io.github.marcoantoniossilva.assets_manager.domain.model.Company;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
  public class CompanyAssembler {

  private ModelMapper modelMapper;

  public CompanyAssembler(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public CompanyDTO entityToDTO(Company company) {
    return modelMapper.map(company, CompanyDTO.class);
  }

  public Company DTOToEntity(CompanyDTO companyDTO) {
    return modelMapper.map(companyDTO, Company.class);
  }

  public List<CompanyDTO> entityCollectionToDTOCollection(List<Company> companies) {
    return companies.stream()
        .map(this::entityToDTO)
        .collect(Collectors.toList());
  }

}
