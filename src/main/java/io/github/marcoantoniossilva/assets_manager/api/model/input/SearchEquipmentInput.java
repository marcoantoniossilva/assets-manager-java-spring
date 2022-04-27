package io.github.marcoantoniossilva.assets_manager.api.model.input;

public class SearchEquipmentInput extends ListEquipmentsInput{

  private String searchTerm;
  private int page;
  private int size;

  public String getSearchTerm() {
    return searchTerm;
  }
  
}
