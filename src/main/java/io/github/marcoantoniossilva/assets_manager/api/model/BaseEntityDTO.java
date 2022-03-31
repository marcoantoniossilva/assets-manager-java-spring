package io.github.marcoantoniossilva.assets_manager.api.model;

public abstract class BaseEntityDTO {

  private Integer id;

  protected BaseEntityDTO() {
  }

  protected BaseEntityDTO(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

}
