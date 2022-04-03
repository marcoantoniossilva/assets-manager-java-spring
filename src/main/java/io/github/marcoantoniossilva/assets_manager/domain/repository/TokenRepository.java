package io.github.marcoantoniossilva.assets_manager.domain.repository;

import io.github.marcoantoniossilva.assets_manager.domain.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  void deleteAllByUserIdAndExpirationTimeBefore(Integer userId, LocalDateTime localDateTime);

  List<Token> findAllByUserIdOrderByExpirationTime(Integer userId);
}
