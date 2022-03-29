package io.github.marcoantoniossilva.assets_manager.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "companies")
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "corporate_name")
  private String corporateName;

  @Column(name = "fantasy_name")
  private String fantasyName;

  private String cnpj;

  public Company() {
  }

  public Company(String corporateName, String fantasyName, String cnpj) {
    this.corporateName = corporateName;
    this.fantasyName = fantasyName;
    this.cnpj = cnpj;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCorporateName() {
    return corporateName;
  }

  public void setCorporateName(String corporateName) {
    this.corporateName = corporateName;
  }

  public String getFantasyName() {
    return fantasyName;
  }

  public void setFantasyName(String fantasyName) {
    this.fantasyName = fantasyName;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  @Override
  public String toString() {
    return "Company{" +
        "id=" + id +
        ", corporateName='" + corporateName + '\'' +
        ", fantasyName='" + fantasyName + '\'' +
        ", cnpj='" + cnpj + '\'' +
        '}';
  }
}
