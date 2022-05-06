package io.github.marcoantoniossilva.assets_manager.api.exceptionhandler;

import io.github.marcoantoniossilva.assets_manager.domain.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  private MessageSource messageSource;

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers, HttpStatus status, WebRequest request) {

    List<Problem.Field> fields = new ArrayList<>();

    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      String name = ((FieldError) error).getField();
      String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

      fields.add(new Problem.Field(name, message));
    }

    Problem problem = new Problem();
    problem.setStatus(status.value());
    problem.setDateTime(LocalDateTime.now());
    problem.setTitle("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
    problem.setFields(fields);

    LOGGER.error("Um ou mais campos inválidos. ",ex);
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {

    HttpStatus status = HttpStatus.BAD_REQUEST;

    Problem problem = new Problem();
    problem.setStatus(status.value());
    problem.setDateTime(LocalDateTime.now());
    problem.setTitle(ex.getMessage());

    LOGGER.error("BusinessException disparado. ",ex);
    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(ExistentResourceException.class)
  public ResponseEntity<Object> handleExistentResourceException(ExistentResourceException ex, WebRequest request) {

    HttpStatus status = HttpStatus.BAD_REQUEST;

    Problem problem = new Problem();
    problem.setStatus(status.value());
    problem.setDateTime(LocalDateTime.now());
    problem.setTitle(ex.getMessage());

    LOGGER.error("Recurso já existente. ",ex);
    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFoundException(ResourceNotFoundException ex, WebRequest request) {

    HttpStatus status = HttpStatus.NOT_FOUND;

    Problem problem = new Problem();
    problem.setStatus(status.value());
    problem.setDateTime(LocalDateTime.now());
    problem.setTitle(ex.getMessage());

    LOGGER.error("Recurso não encontrado. ",ex);
    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(IncorrectLoginException.class)
  public ResponseEntity<Object> handleIncorrectLoginException(IncorrectLoginException ex, WebRequest request) {

    HttpStatus status = HttpStatus.UNAUTHORIZED;

    Problem problem = new Problem();
    problem.setStatus(status.value());
    problem.setDateTime(LocalDateTime.now());
    problem.setTitle(ex.getMessage());

    LOGGER.error("Login incorreto. ",ex);
    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(IdNullException.class)
  public ResponseEntity<Object> handleIdNullException(IdNullException ex, WebRequest request) {

    HttpStatus status = HttpStatus.BAD_REQUEST;

    Problem problem = new Problem();
    problem.setStatus(status.value());
    problem.setDateTime(LocalDateTime.now());
    problem.setTitle(ex.getMessage());

    LOGGER.error("Id Nulo. ",ex);
    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

}