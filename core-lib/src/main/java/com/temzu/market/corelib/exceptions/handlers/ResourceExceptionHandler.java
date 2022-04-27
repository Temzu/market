package com.temzu.market.corelib.exceptions.handlers;

import com.temzu.market.corelib.exceptions.ResourceAlreadyExistsException;
import com.temzu.market.corelib.exceptions.ResourceException;
import com.temzu.market.corelib.exceptions.ResourceNotFoundException;
import com.temzu.market.corelib.models.MarketError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> catchResourceNotFoundException(ResourceException e) {
    return new ResponseEntity<>(new MarketError(e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<?> catchResourceAlreadyExistException(ResourceException e) {
    return new ResponseEntity<>(new MarketError(e.getMessage()), HttpStatus.CONFLICT);
  }
}
