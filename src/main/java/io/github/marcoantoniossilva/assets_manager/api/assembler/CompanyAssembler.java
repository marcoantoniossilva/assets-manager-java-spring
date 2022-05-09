package io.github.marcoantoniossilva.assets_manager.api.assembler;

import io.github.marcoantoniossilva.assets_manager.api.model.CompanyModel;
import io.github.marcoantoniossilva.assets_manager.api.model.UserModel;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserEditInput;
import io.github.marcoantoniossilva.assets_manager.api.model.input.UserInput;
import io.github.marcoantoniossilva.assets_manager.domain.model.Company;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
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

  public CompanyModel toModel(Company company) {
    return modelMapper.map(company, CompanyModel.class);
  }

  public Company toEntity(CompanyModel companyModel) {
    return modelMapper.map(companyModel, Company.class);
  }

  public List<CompanyModel> toCollectionModel(List<Company> companies) {
    return companies.stream()
        .map(this::toModel)
        .collect(Collectors.toList());
  }

}
