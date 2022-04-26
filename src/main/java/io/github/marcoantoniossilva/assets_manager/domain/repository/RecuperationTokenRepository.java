package io.github.marcoantoniossilva.assets_manager.domain.repository;

import io.github.marcoantoniossilva.assets_manager.domain.model.RecuperationToken;
import io.github.marcoantoniossilva.assets_manager.domain.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RecuperationTokenRepository extends JpaRepository<RecuperationToken, Integer> {

  void deleteAllByUserId(Integer userId);

  void deleteByToken(String stringToken);

  List<Token> findAllByUserId(Integer userId);

  Optional<RecuperationToken> findByToken(String token);

  List<RecuperationToken> findAllByUserIdOrderByExpirationTime(Integer userId);

  void deleteAllByExpirationTimeBefore(LocalDateTime localDateTime);

}
