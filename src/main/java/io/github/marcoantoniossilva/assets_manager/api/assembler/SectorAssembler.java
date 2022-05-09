package io.github.marcoantoniossilva.assets_manager.api.assembler;

import io.github.marcoantoniossilva.assets_manager.api.model.SectorModel;
import io.github.marcoantoniossilva.assets_manager.domain.model.Sector;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
  public class SectorAssembler {

  private ModelMapper modelMapper;

  public SectorAssembler(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public SectorModel toModel(Sector sector) {
    return modelMapper.map(sector, SectorModel.class);
  }

  public List<SectorModel> toCollectionModel(List<Sector> sectors) {
    return sectors.stream()
        .map(this::toModel)
        .collect(Collectors.toList());
  }

  public Sector toEntity(SectorModel sectorModel) {
    return modelMapper.map(sectorModel, Sector.class);
  }

}
