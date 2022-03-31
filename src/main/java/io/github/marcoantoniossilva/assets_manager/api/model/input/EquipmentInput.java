package io.github.marcoantoniossilva.assets_manager.api.model.input;

import io.github.marcoantoniossilva.assets_manager.api.model.BaseEntityDTO;
import io.github.marcoantoniossilva.assets_manager.domain.enumeration.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EquipmentInput extends BaseEntityDTO {

  private UserIdInput userIdInput;

  private TypeIdInput typeIdInput;

  private SectorIdInput sectorIdInput;

  private CompanyIdInput companyIdInput;

  private String description;

  private BigDecimal acquisitionValue;

  private LocalDate acquisitionDate;

  private String nfePath;

  private Status status;

  public EquipmentInput() {
  }

  public EquipmentInput(Integer id, UserIdInput userIdInput, TypeIdInput typeIdInput, SectorIdInput sectorIdInput, CompanyIdInput companyIdInput, String description, BigDecimal acquisitionValue, LocalDate acquisitionDate, String nfePath, Status status) {
    super(id);
    this.userIdInput = userIdInput;
    this.typeIdInput = typeIdInput;
    this.sectorIdInput = sectorIdInput;
    this.companyIdInput = companyIdInput;
    this.description = description;
    this.acquisitionValue = acquisitionValue;
    this.acquisitionDate = acquisitionDate;
    this.nfePath = nfePath;
    this.status = status;
  }

  public UserIdInput getUserIdInput() {
    return userIdInput;
  }

  public void setUserIdInput(UserIdInput userIdInput) {
    this.userIdInput = userIdInput;
  }

  public TypeIdInput getTypeIdInput() {
    return typeIdInput;
  }

  public void setTypeIdInput(TypeIdInput typeIdInput) {
    this.typeIdInput = typeIdInput;
  }

  public SectorIdInput getSectorIdInput() {
    return sectorIdInput;
  }

  public void setSectorIdInput(SectorIdInput sectorIdInput) {
    this.sectorIdInput = sectorIdInput;
  }

  public CompanyIdInput getCompanyIdInput() {
    return companyIdInput;
  }

  public void setCompanyIdInput(CompanyIdInput companyIdInput) {
    this.companyIdInput = companyIdInput;
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
