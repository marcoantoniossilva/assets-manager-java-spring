package io.github.marcoantoniossilva.assets_manager.api.model;

public abstract class BaseEntityModel {

  private Integer id;

  protected BaseEntityModel() {
  }

  protected BaseEntityModel(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

}
