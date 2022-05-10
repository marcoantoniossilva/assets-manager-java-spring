package io.github.marcoantoniossilva.assets_manager.api.assembler;

import io.github.marcoantoniossilva.assets_manager.api.model.SectorDTO;
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

  public SectorDTO entityToDTO(Sector sector) {
    return modelMapper.map(sector, SectorDTO.class);
  }

  public List<SectorDTO> entityCollectionToDTOCollection(List<Sector> sectors) {
    return sectors.stream()
        .map(this::entityToDTO)
        .collect(Collectors.toList());
  }

  public Sector DTOToEntity(SectorDTO sectorDTO) {
    return modelMapper.map(sectorDTO, Sector.class);
  }

}
