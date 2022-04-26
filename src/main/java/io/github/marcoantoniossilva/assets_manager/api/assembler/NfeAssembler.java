package io.github.marcoantoniossilva.assets_manager.api.assembler;

import io.github.marcoantoniossilva.assets_manager.domain.exception.EntityNotFoundException;
import io.github.marcoantoniossilva.assets_manager.domain.model.Nfe;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class NfeAssembler {

  public Nfe multipartFileToEntity(MultipartFile nfe) {
    Nfe newNfe = new Nfe();
    newNfe.setFileName(nfe.getOriginalFilename());
    newNfe.setType(nfe.getContentType());
    try {
      newNfe.setContent(nfe.getBytes());
    } catch (IOException e) {
      throw new EntityNotFoundException("Nfe não anexada!");
    }
    return newNfe;
  }
}