package io.github.marcoantoniossilva.assets_manager.api.model;

import io.github.marcoantoniossilva.assets_manager.domain.enumeration.Status;
import io.github.marcoantoniossilva.assets_manager.domain.model.Sector;
import io.github.marcoantoniossilva.assets_manager.domain.model.Type;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EquipmentModel extends BaseEntityModel {

  private String description;

  private BigDecimal acquisitionValue;

  private LocalDate acquisitionDate;

  private String nfePath;

  private Status status;

  private UserResumeModel user;

  private Type type;

  private Sector sector;

  private CompanyResumeModel company;

  public EquipmentModel() {
  }

  public EquipmentModel(Integer id, String description, BigDecimal acquisitionValue, LocalDate acquisitionDate, String nfePath, Status status, UserResumeModel userResumeModel, Type type, Sector sector, CompanyResumeModel company) {
    super(id);
    this.description = description;
    this.acquisitionValue = acquisitionValue;
    this.acquisitionDate = acquisitionDate;
    this.nfePath = nfePath;
    this.status = status;
    this.user = userResumeModel;
    this.type = type;
    this.sector = sector;
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

  public UserResumeModel getUser() {
    return user;
  }

  public void setUser(UserResumeModel user) {
    this.user = user;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
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
