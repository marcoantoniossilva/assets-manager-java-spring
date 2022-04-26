package io.github.marcoantoniossilva.assets_manager.api.controller;

import io.github.marcoantoniossilva.assets_manager.common.UpdateUtils;
import io.github.marcoantoniossilva.assets_manager.domain.model.Type;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import io.github.marcoantoniossilva.assets_manager.domain.service.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {

  private final TypeService typeService;

  public TypeController(TypeService typeService) {
    this.typeService = typeService;
  }

  @GetMapping
  public List<Type> list() {
    return typeService.list();
  }

  @GetMapping("{typeId}")
  public ResponseEntity<Type> search(@PathVariable Integer typeId) {
    return typeService.findById(typeId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Type add(@RequestBody Type type) {
    return typeService.save(type);
  }

  @PutMapping("{typeId}")
  public ResponseEntity<Type> update(@PathVariable Integer typeId, @RequestBody Type type) {
    if (!typeService.existsById(typeId)) {
      return ResponseEntity.notFound().build();
    }
    type.setId(typeId);
    Type existentType = typeService.getById(typeId);
    UpdateUtils.copyNonNullProperties(type, existentType);
    Type savedType = typeService.save(existentType);
    return ResponseEntity.ok(savedType);
  }

  @DeleteMapping("{typeId}")
  public ResponseEntity<Void> delete(@PathVariable Integer typeId) {
    if (!typeService.existsById(typeId)) {
      return ResponseEntity.notFound().build();
    }
    typeService.deleteById(typeId);
    return ResponseEntity.noContent().build();
  }

}
