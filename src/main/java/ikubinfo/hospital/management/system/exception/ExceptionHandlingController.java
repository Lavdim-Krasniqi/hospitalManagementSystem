package ikubinfo.hospital.management.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlingController {

  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException exception) {
    Map<String, String> errorMessage = new HashMap<>();
    errorMessage.put("error message", exception.getMessage());
    errorMessage.put("error number", "" + HttpStatus.NOT_FOUND.value());
    errorMessage.put("error path", exception.getStackTrace()[0].toString());
    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
  }
}
