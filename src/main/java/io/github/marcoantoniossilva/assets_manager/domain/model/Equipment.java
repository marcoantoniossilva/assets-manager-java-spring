package io.github.marcoantoniossilva.assets_manager.domain.model;

import io.github.marcoantoniossilva.assets_manager.domain.enumeration.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "equipments")
public class Equipment extends BaseEntity{

  private String description;

  @Column(name = "acquisition_value")
  private BigDecimal acquisitionValue;

  @Column(name = "acquisition_date")
  private LocalDate acquisitionDate;

  @Enumerated(EnumType.STRING)
  @Type(type = "io.github.marcoantoniossilva.assets_manager.common.EnumTypePostgreSql")
  private Status status;

  @ManyToOne
  private Company company;

  @ManyToOne
  private User user;

  @ManyToOne
  private io.github.marcoantoniossilva.assets_manager.domain.model.Type type;

  @ManyToOne
  private Sector sector;

  @Embedded
  private Nfe nfe;

  public Equipment() {
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

  public Nfe getNfe() {
    return nfe;
  }

  public void setNfe(Nfe nfe) {
    this.nfe = nfe;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public io.github.marcoantoniossilva.assets_manager.domain.model.Type getType() {
    return type;
  }

  public void setType(io.github.marcoantoniossilva.assets_manager.domain.model.Type type) {
    this.type = type;
  }

  public Sector getSector() {
    return sector;
  }

  public void setSector(Sector sector) {
    this.sector = sector;
  }

  @Override
  public String toString() {
    return "Equipment{" +
        "description='" + description + '\'' +
        ", acquisitionValue=" + acquisitionValue +
        ", acquisitionDate=" + acquisitionDate +
        ", status=" + status +
        ", company=" + company +
        ", user=" + user +
        ", type=" + type +
        ", sector=" + sector +
        ", nfe=" + nfe +
        '}';
  }
}
