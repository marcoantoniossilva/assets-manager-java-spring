package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.exception.IdNullException;
import io.github.marcoantoniossilva.assets_manager.domain.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class BaseCrudService<ENTITY, ID> {

  protected abstract JpaRepository<ENTITY, ID> getRepository();

  protected abstract String getEntityName();

  public List<ENTITY> findAll() {
    return this.getRepository().findAll();
  }

  public Page<ENTITY> findAll(Pageable pageable) {
    return this.getRepository().findAll(pageable);
  }

  @Transactional
  public ENTITY save(ENTITY entity) {
    return this.getRepository().save(entity);
  }

  public Optional<ENTITY> findById(ID id) {
    return this.getRepository().findById(id);
  }

  public ENTITY findOrFailById(ID id) {
    if (id == null) {
      throw new IdNullException(String.format("O Id de %s não pode ser nulo!", this.getEntityName()));
    }
    Optional<ENTITY> entity = this.getRepository().findById(id);
    return entity.orElseThrow(() -> new ResourceNotFoundException(String.format("%s não encontrado com o ID: %s.", this.getEntityName(), id)));
  }

  public boolean existsById(ID id) {
    return this.getRepository().existsById(id);
  }

  @Transactional
  public void deleteById(ID id) {
    this.getRepository().deleteById(id);
  }
}
