package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.exception.BusinessException;
import io.github.marcoantoniossilva.assets_manager.domain.model.Token;
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

  public List<User> list() {
    return userRepository.findAll();
  }

  public Optional<User> findById(Integer userId) {
    return userRepository.findById(userId);
  }

  @Transactional
  public User save(User user) {
    Optional<User> userWithSameLogin = userRepository.findByLogin(user.getLogin());
    boolean differentsUsersSameLogins = userWithSameLogin.stream()
        .anyMatch(existentUser -> !Objects.equals(existentUser.getId(), user.getId()));

    if (differentsUsersSameLogins) { // Se estiver criando um usuário
      throw new BusinessException("Já existe um usuário cadastrado com este login.");
    }

    if (user.getId() == null) { // Criação de usuário
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    } else { // Edição de usuário
      user.setPassword(userRepository.getById(user.getId()).getPassword());
    }

    return userRepository.save(user);
  }

  public User findByLogin(String login) {
    Optional<User> user = userRepository.findByLogin(login);
    return user.orElseThrow(() -> new BusinessException("Usuário não encontrado com este login."));
  }

  public Optional<User> findByToken(Token token) {
    return userRepository.findByTokens(token);
  }

  public boolean existsById(Integer userId) {
    return userRepository.existsById(userId);
  }

  @Transactional
  public void deleteById(Integer userId) {
    userRepository.deleteById(userId);
  }

}
