package io.github.marcoantoniossilva.assets_manager.common;

import io.github.marcoantoniossilva.assets_manager.domain.model.Token;
import io.github.marcoantoniossilva.assets_manager.domain.service.TokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

  private final TokenService tokenService;
  private final List<String> PUBLIC_URLS = List.of("/auth");

  public AuthenticationFilter(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws
      ServletException, IOException {
    String authorization = request.getHeader("Authorization");
    if (authenticate(authorization) ||
        PUBLIC_URLS.stream()
            .anyMatch(url -> request.getRequestURL().toString().contains(url))) {
      filterChain.doFilter(request, response);
    } else {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
          "Token de autorização expirado ou incorreto!");
    }
  }

  private boolean authenticate(String authorization) {
    if (authorization == null || authorization.isEmpty() || authorization.isBlank()) {
      return false;
    }
    Optional<Token> token = tokenService.findByToken(authorization);
    return token.filter(savedToken -> savedToken.getExpirationTime().isAfter(LocalDateTime.now())).isPresent();
  }
}