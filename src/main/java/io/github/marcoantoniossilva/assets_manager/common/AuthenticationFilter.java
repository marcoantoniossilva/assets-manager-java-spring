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
import java.util.Optional;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

  private final TokenService tokenService;

  public AuthenticationFilter(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws
      ServletException, IOException {
    String authentication = request.getHeader("Authentication");
    if (authenticate(authentication)) {
      filterChain.doFilter(request, response);
    } else {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token de autenticação expirado ou incorreto!");
    }
  }

  private boolean authenticate(String authentication) {
    if (authentication == null || authentication.isEmpty() || authentication.isBlank()) {
      return false;
    }
    Optional<Token> token = tokenService.findByToken(authentication);
    return token.filter(savedToken -> savedToken.getExpirationTime().isAfter(LocalDateTime.now())).isPresent();
  }
}