package io.github.marcoantoniossilva.assets_manager.domain.repository;

import io.github.marcoantoniossilva.assets_manager.domain.model.Token;
import io.github.marcoantoniossilva.assets_manager.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByLogin(String login);

  Optional<User> findByTokens(Token token);

  @Modifying
  @Query("update User u set u.password = :new_password where u.id = :id")
  void updatePasswordById(@Param(value = "id") Integer userId, @Param(value = "new_password") String newPassword);

}
