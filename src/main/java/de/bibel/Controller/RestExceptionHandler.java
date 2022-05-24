package de.bibel.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

  private static final Logger LOGGER = LogManager.getLogger(RestExceptionHandler.class);

  @ResponseBody
  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String parameterIsMissingHandler(MissingServletRequestParameterException ex) {
    LOGGER.warn(ex.getMessage());
    return ex.getMessage();
  }
}
