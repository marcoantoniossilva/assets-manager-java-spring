package io.github.marcoantoniossilva.assets_manager.domain.model;

import javax.persistence.*;

@Embeddable
public class Nfe{

  @Column(name = "nfe_file_name")
  private String fileName;
  @Column(name = "nfe_file_content")
  private byte[] content;
  @Column(name = "nfe_file_type")
  private String type;

  public Nfe() {
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}