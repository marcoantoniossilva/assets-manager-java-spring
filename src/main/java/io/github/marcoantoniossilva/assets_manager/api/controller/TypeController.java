package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.domain.model.Type;
import io.github.marcoantoniossilva.assets_manager.domain.repository.TypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {

  private final TypeRepository typeRepository;

  public TypeController(TypeRepository typeRepository) {
    this.typeRepository = typeRepository;
  }

  @GetMapping
  public List<Type> list() {
    return typeRepository.findAll();
  }

  @GetMapping("{typeId}")
  public ResponseEntity<Type> search(@PathVariable Integer typeId) {
    return typeRepository.findById(typeId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Type add(@RequestBody Type type) {
    return typeRepository.save(type);
  }

  @PutMapping("{typeId}")
  public ResponseEntity<Type> update(@PathVariable Integer typeId, @RequestBody Type type) {
    if (!typeRepository.existsById(typeId)) {
      return ResponseEntity.notFound().build();
    }
    type.setId(typeId);
    Type savedType = typeRepository.save(type);
    return ResponseEntity.ok(savedType);
  }

  @DeleteMapping("{typeId}")
  public ResponseEntity<Void> delete(@PathVariable Integer typeId) {
    if (!typeRepository.existsById(typeId)) {
      return ResponseEntity.notFound().build();
    }
    typeRepository.deleteById(typeId);
    return ResponseEntity.noContent().build();
  }

}
