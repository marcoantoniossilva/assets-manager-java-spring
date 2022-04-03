package io.github.marcoantoniossilva.assets_manager.api.model.input;

import io.github.marcoantoniossilva.assets_manager.api.model.BaseEntityModel;
import io.github.marcoantoniossilva.assets_manager.domain.enumeration.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EquipmentInput extends BaseEntityModel {

  private UserIdInput user;

  private TypeIdInput type;

  private SectorIdInput sector;

  private CompanyIdInput company;

  private String description;

  private BigDecimal acquisitionValue;

  private LocalDate acquisitionDate;

  private String nfePath;

  private Status status;

  public EquipmentInput() {
  }

  public EquipmentInput(Integer id, UserIdInput user, TypeIdInput type, SectorIdInput sector, CompanyIdInput company, String description, BigDecimal acquisitionValue, LocalDate acquisitionDate, String nfePath, Status status) {
    super(id);
    this.user = user;
    this.type = type;
    this.sector = sector;
    this.company = company;
    this.description = description;
    this.acquisitionValue = acquisitionValue;
    this.acquisitionDate = acquisitionDate;
    this.nfePath = nfePath;
    this.status = status;
  }

  public UserIdInput getUser() {
    return user;
  }

  public void setUser(UserIdInput user) {
    this.user = user;
  }

  public TypeIdInput getType() {
    return type;
  }

  public void setType(TypeIdInput type) {
    this.type = type;
  }

  public SectorIdInput getSector() {
    return sector;
  }

  public void setSector(SectorIdInput sector) {
    this.sector = sector;
  }

  public CompanyIdInput getCompany() {
    return company;
  }

  public void setCompany(CompanyIdInput company) {
    this.company = company;
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

  public String getNfePath() {
    return nfePath;
  }

  public void setNfePath(String nfePath) {
    this.nfePath = nfePath;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
