package org.b2bsalesportal.userservice.advisor;

import org.b2bsalesportal.userservice.exception.AlreadyExistsException;
import org.b2bsalesportal.userservice.exception.NotFoundException;
import org.b2bsalesportal.userservice.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404,"Error",e), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<StandardResponse> handleAlreadyExistsException(AlreadyExistsException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(409,"Error", e.getMessage()), HttpStatus.CONFLICT
        );
    }
}
