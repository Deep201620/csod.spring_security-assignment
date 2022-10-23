package demo.csod.securitydemo.csod.spring_security.exception;

import org.modelmapper.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import javax.servlet.http.HttpServletRequest;
import static demo.csod.securitydemo.csod.spring_security.exception.IErrorMessage.*;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<String> handleUserNotExists(ResourceNotFound resourceNotFound){
        return new ResponseEntity<>(USER_NOT_EXIST,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<String> handleUserExists(UserAlreadyExists userAlreadyExists){
        return new ResponseEntity<>( USER_EXISTS,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleHttpMethodNotSupported(
            HttpRequestMethodNotSupportedException methodNotSupportedException, HttpServletRequest httpServletRequest){
        return new ResponseEntity<>(httpServletRequest.getMethod()+METHOD_NOT_SUPPORTED
                ,HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArguments(IllegalArgumentException illegalArgumentException){
        return new ResponseEntity<>(CHECK_FIELDS,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MappingException.class)
    public ResponseEntity<String> handleMapperExceptin(MappingException mappingException){
        return new ResponseEntity<>(NULL_FIELDS,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<String> handleInternalServerError
            (HttpServerErrorException.InternalServerError internalServerError){
        return new ResponseEntity<>(SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
