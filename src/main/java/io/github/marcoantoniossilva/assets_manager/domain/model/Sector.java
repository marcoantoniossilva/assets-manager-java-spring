package io.github.marcoantoniossilva.assets_manager.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "sectors")
public class Sector extends BaseEntity{

  private String description;

  public Sector() {
  }

  public Sector(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
