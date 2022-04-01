package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.exception.BusinessException;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import io.github.marcoantoniossilva.assets_manager.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public List<User> list() {
    return userRepository.findAll();
  }

  @Transactional
  public Optional<User> findById(Integer userId) {
    return userRepository.findById(userId);
  }

  @Transactional
  public User save(User user) {
    Optional<User> userWithSameLogin = userRepository.findByLogin(user.getLogin());
    boolean differentsUsersSameLogins = userWithSameLogin.stream()
        .anyMatch(existentUser -> !Objects.equals(existentUser.getId(), user.getId()));

    if (user.getId() == null) { // Criação de usuário
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    } else { // Edição de usuário
      user.setPassword(userRepository.getById(user.getId()).getPassword());
    }

    if (differentsUsersSameLogins) { // Se estiver criando um usuário
      throw new BusinessException("Já existe um usuário cadastrado com este login.");
    }
    return userRepository.save(user);
  }

  public boolean existsById(Integer userId) {
    return userRepository.existsById(userId);
  }

  public void deleteById(Integer userId) {
    userRepository.deleteById(userId);
  }

}
