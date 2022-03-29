package io.github.marcoantoniossilva.assets_manager.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "sectors")
public class Sector {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String description;

  public Sector() {
  }

  public Sector(String description) {
    this.description = description;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Sector{" +
        "id=" + id +
        ", description='" + description + '\'' +
        '}';
  }
}
