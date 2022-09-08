package demo.csod.securitydemo.csod.spring_security.exception;

import org.modelmapper.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<String> handleUserExists(ResourceNotFound resourceNotFound){
        return new ResponseEntity<>("User does not exist",HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<String> handleUserExists(UserAlreadyExists userAlreadyExists){
        return new ResponseEntity<>("User already exists with the same emailId",HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleHttpMethodNotSupported(
            HttpRequestMethodNotSupportedException methodNotSupportedException, HttpServletRequest httpServletRequest){
        return new ResponseEntity<>(httpServletRequest.getMethod()+"method is not supported with following parameters"
                ,HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArguments(IllegalArgumentException illegalArgumentException){
        return new ResponseEntity<>("Some fields are not filled with valid data. please look into it.",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MappingException.class)
    public ResponseEntity<String> handleMapperExceptin(MappingException mappingException){
        return new ResponseEntity<>("Data cannot be null. Please provide some data",HttpStatus.BAD_REQUEST);
    }


}
