package io.github.marcoantoniossilva.assets_manager.domain.service;

import io.github.marcoantoniossilva.assets_manager.domain.exception.BusinessException;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import io.github.marcoantoniossilva.assets_manager.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Transactional
  public User save(User user) {
    boolean loginInUse = userRepository.findByLogin(user.getLogin())
        .stream().anyMatch(existentUser -> !existentUser.equals(user));

    if(loginInUse) {
      throw new BusinessException("Já existe um usuário cadastrado com este login.");
    }
    return userRepository.save(user);
  }

}
