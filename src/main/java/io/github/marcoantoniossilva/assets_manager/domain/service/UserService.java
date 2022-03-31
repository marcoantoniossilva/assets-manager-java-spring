package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.exception.BusinessException;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import io.github.marcoantoniossilva.assets_manager.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Transactional
  public User save(User user) {

    Optional<User> userWithSameLogin = userRepository.findByLogin(user.getLogin());
    boolean differentsUsersSameLogins = userWithSameLogin.stream()
        .anyMatch(existentUser -> !Objects.equals(existentUser.getId(), user.getId()));


    if (differentsUsersSameLogins) { // Se estiver criando um usuário
      throw new BusinessException("Já existe um usuário cadastrado com este login.");
    }
    return userRepository.save(user);
  }

}
