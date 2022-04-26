package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.exception.BusinessException;
import io.github.marcoantoniossilva.assets_manager.domain.model.Type;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import io.github.marcoantoniossilva.assets_manager.domain.repository.TypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
  
  private final TypeRepository typeRepository;

  public TypeService(TypeRepository typeRepository) {
    this.typeRepository = typeRepository;
  }

  public List<Type> list() {
    return typeRepository.findAll();
  }

  public Optional<Type> findById(Integer typeId) {
    return typeRepository.findById(typeId);
  }

  public Type getById(Integer typeId) {
    Optional<Type> type = typeRepository.findById(typeId);
    return type.orElseThrow(() -> new BusinessException("Tipo não encontrado com este id."));
  }

  @Transactional
  public Type save(Type Type) {
    return typeRepository.save(Type);
  }

  public boolean existsById(Integer typeId) {
    return typeRepository.existsById(typeId);
  }

  @Transactional
  public void deleteById(Integer typeId) {
    typeRepository.deleteById(typeId);
  }
}
