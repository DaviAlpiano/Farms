package com.betrybe.agrix.advice;

import com.betrybe.agrix.service.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Global controller advice.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

  /**
   * Handle exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler
  public ResponseEntity<String> handleException(NotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ex.getMessage());
  }
}
