package io.github.marcoantoniossilva.assets_manager.common;

import io.github.marcoantoniossilva.assets_manager.domain.model.Token;
import io.github.marcoantoniossilva.assets_manager.domain.service.TokenService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Component
public class AuthenticationFilter extends OncePerRequestFilter {

  private final TokenService tokenService;
  private static final Map<String, HttpMethod> PUBLIC_URLS = new HashMap<>();

  static {
    PUBLIC_URLS.put("/users", HttpMethod.POST);
    PUBLIC_URLS.put("/login", HttpMethod.POST);
    PUBLIC_URLS.put("/api-docs", HttpMethod.GET);
    PUBLIC_URLS.put("/new-password", HttpMethod.POST);
    PUBLIC_URLS.put("/swagger-ui/index.html", HttpMethod.GET);
    PUBLIC_URLS.put("/recover-password", HttpMethod.POST);
  }

  public AuthenticationFilter(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
  ) throws ServletException, IOException {
    String authorization = request.getHeader("Authorization");
    if (isPublicUrl(request) || authenticate(authorization)) {
      filterChain.doFilter(request, response);
    } else {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token de autorização expirado ou incorreto!");
    }
  }

  private boolean isPublicUrl(HttpServletRequest request) {
    String currentUrl = request.getRequestURL().toString();
    HttpMethod currentMethod = HttpMethod.valueOf(request.getMethod());
    return PUBLIC_URLS.entrySet().stream()
        .anyMatch(entry -> {
          HttpMethod method = entry.getValue();
          String url = entry.getKey();
          return currentMethod.equals(method) && currentUrl.endsWith(url);
        });
  }


  private boolean authenticate(String authorization) {
    if (authorization == null || authorization.isEmpty() || authorization.isBlank()) {
      return false;
    }
    Optional<Token> token = tokenService.findByToken(authorization);
    return token.filter(savedToken -> savedToken.getExpirationTime().isAfter(LocalDateTime.now())).isPresent();
  }
}