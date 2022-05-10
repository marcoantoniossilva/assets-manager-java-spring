package io.github.marcoantoniossilva.assets_manager.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SectorDTO extends BaseEntityDTO {

  @NotBlank
  @Size(min = 5, max = 120)
  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
