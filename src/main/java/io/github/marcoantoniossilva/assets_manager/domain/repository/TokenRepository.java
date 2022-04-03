package io.github.marcoantoniossilva.assets_manager.domain.repository;

import io.github.marcoantoniossilva.assets_manager.domain.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  void deleteAllByUserIdAndExpirationTimeBefore(Integer userId, LocalDateTime localDateTime);

  void deleteByToken(String stringToken);

  boolean existsByToken(String stringToken);

  List<Token> findAllByUserIdOrderByExpirationTime(Integer userId);

  Optional<Token> findByToken(String token);

}
