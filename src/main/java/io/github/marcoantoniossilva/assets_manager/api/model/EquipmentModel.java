package io.github.marcoantoniossilva.assets_manager.api.model;

import io.github.marcoantoniossilva.assets_manager.domain.model.enumeration.Status;
import io.github.marcoantoniossilva.assets_manager.domain.model.Sector;
import io.github.marcoantoniossilva.assets_manager.domain.model.EquipmentType;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;

public class EquipmentModel extends BaseEntityModel {

  private String description;

  private BigDecimal acquisitionValue;

  private LocalDate acquisitionDate;

  private URI nfeUri;

  private Status status;

  private UserResumeModel user;

  private EquipmentType equipmentType;

  private Sector sector;

  private CompanyResumeModel company;

  public EquipmentModel() {
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getAcquisitionValue() {
    return acquisitionValue;
  }

  public void setAcquisitionValue(BigDecimal acquisitionValue) {
    this.acquisitionValue = acquisitionValue;
  }

  public LocalDate getAcquisitionDate() {
    return acquisitionDate;
  }

  public void setAcquisitionDate(LocalDate acquisitionDate) {
    this.acquisitionDate = acquisitionDate;
  }

  public URI getNfeUri() {
    return nfeUri;
  }

  public void setNfeUri(URI nfeUri) {
    this.nfeUri = nfeUri;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public UserResumeModel getUser() {
    return user;
  }

  public void setUser(UserResumeModel user) {
    this.user = user;
  }

  public EquipmentType getType() {
    return equipmentType;
  }

  public void setType(EquipmentType equipmentType) {
    this.equipmentType = equipmentType;
  }

  public Sector getSector() {
    return sector;
  }

  public void setSector(Sector sector) {
    this.sector = sector;
  }

  public CompanyResumeModel getCompany() {
    return company;
  }

  public void setCompany(CompanyResumeModel company) {
    this.company = company;
  }
}
