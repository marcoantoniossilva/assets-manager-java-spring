package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.exception.BusinessException;
import io.github.marcoantoniossilva.assets_manager.domain.exception.ExistentResourceException;
import io.github.marcoantoniossilva.assets_manager.domain.exception.ResourceNotFoundException;
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
public class UserService extends BaseCrudService<User, Integer>{

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.tokenService = tokenService;
  }

  @Transactional
  public User save(User user) {
    Optional<User> userWithSameLogin = userRepository.findByLogin(user.getLogin());
    Optional<User> userWithSameEmail = userRepository.findByEmail(user.getEmail());
    boolean differentsUsersSameLogins = userWithSameLogin.stream()
        .anyMatch(existentUser -> !Objects.equals(existentUser.getId(), user.getId()));
    boolean differentsUsersSameEmails = userWithSameEmail.stream()
        .anyMatch(existentUser -> !Objects.equals(existentUser.getId(), user.getId()));

    if (differentsUsersSameLogins) { // Se estiver criando um usuário
      throw new ExistentResourceException("Já existe um usuário cadastrado com este login.");
    }
    if (differentsUsersSameEmails) { // Se estiver criando um usuário
      throw new ExistentResourceException("Já existe um usuário cadastrado com este e-mail.");
    }

    if (user.getId() == null) { // Criação de usuário
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    } else { // Edição de usuário
      user.setPassword(userRepository.getById(user.getId()).getPassword());
    }

    return userRepository.save(user);
  }

  public User getByLogin(String login) {
    Optional<User> user = userRepository.findByLogin(login);
    return user.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com este login."));
  }

  public Optional<User> findByToken(Token token) {
    return userRepository.findByTokens(token);
  }

  public Optional<User> findByStringToken(String stringToken) {
    Optional<Token> token = tokenService.findByToken(stringToken);

    if (token.isPresent()) {
      return userRepository.findByTokens(token.get());
    }
    return Optional.empty();
  }

  public User getUserByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com este email!"));
  }

  @Transactional
  public void alterPassword(Integer userId, String newPassword, String oldPassword) {
    User user = userRepository.getById(userId);

    if (!validateLoginPassword(oldPassword, user.getPassword())) {
      throw new BusinessException("A senha antiga não confere!");
    }
    userRepository.updatePasswordById(userId, passwordEncoder.encode(newPassword));
  }

  @Transactional
  public void setNewPassword(Integer userId, String newPassword) {
    userRepository.updatePasswordById(userId, passwordEncoder.encode(newPassword));
  }

  public boolean validateLoginPassword(String passwordInput, String userPassword) {
    return passwordEncoder.matches(passwordInput, userPassword);
  }

  protected UserRepository getRepository() {
    return this.userRepository;
  }

  protected String getEntityName(){
    return "User";
  }
}
