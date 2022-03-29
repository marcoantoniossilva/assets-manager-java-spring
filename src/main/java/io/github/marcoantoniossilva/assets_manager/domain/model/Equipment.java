package io.github.marcoantoniossilva.assets_manager.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "equipments")
public class Equipment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String description;

  @Column(name = "acquisition_value")
  private BigDecimal acquisitionValue;

  @Column(name = "acquisition_date")
  private LocalDate acquisitionDate;

  @Column(name = "nfe_path")
  private String nfePath;

  private Status status;

  @ManyToOne
  private Company company;

  @ManyToOne
  private User user;

  @ManyToOne
  private Type type;

  @ManyToOne
  private Sector sector;

  public Equipment() {
  }

  public Equipment(String description, BigDecimal acquisitionValue, LocalDate acquisitionDate, String nfePath, Status status, Company company, User user, Type type, Sector sector) {
    this.description = description;
    this.acquisitionValue = acquisitionValue;
    this.acquisitionDate = acquisitionDate;
    this.nfePath = nfePath;
    this.status = status;
    this.company = company;
    this.user = user;
    this.type = type;
    this.sector = sector;
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

  @Override
  public String toString() {
    return "Equipment{" +
        "id=" + id +
        ", description='" + description + '\'' +
        ", acquisitionValue=" + acquisitionValue +
        ", acquisitionDate=" + acquisitionDate +
        ", nfePath='" + nfePath + '\'' +
        ", status=" + status +
        ", company=" + company +
        ", user=" + user +
        ", type=" + type +
        ", sector=" + sector +
        '}';
  }
}
