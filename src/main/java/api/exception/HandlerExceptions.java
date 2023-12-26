package api.exception;

import api.exception.handlers.HandlerEntityNotFoundException;
import api.exception.handlers.HandlerError;
import api.rest.response.ResponseError;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j(topic = "Handles")
public class HandlerExceptions extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logError(ex);
        log.error("HTTP method not supported review the method");
        ResponseError responseError =new ResponseError("Method not valid",ex.getMessage(),HttpStatus.BAD_REQUEST);
        return handleExceptionInternal(ex,responseError,headers,HttpStatus.BAD_REQUEST,request);
    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logError(ex);
        log.error("Error in the request check its formatting");
        Object msg = null;
        if (ex.getCause() instanceof InvalidFormatException exception){
            msg = exception.getValue();
        }
        ResponseError responseError =new ResponseError("Invalid format",ex.getMessage(),HttpStatus.BAD_REQUEST);
        return handleExceptionInternal(ex,responseError,headers,HttpStatus.BAD_REQUEST,request);
    }
    @ExceptionHandler(HandlerEntityNotFoundException.class)
    public ResponseEntity<Object> handlerEntityNotFoundExecption(HandlerEntityNotFoundException ex){
        logError(ex);
        log.error("Entity does not exist or cannot be found, check the entity inserted");
        return builderResponse(ex.getMessage(),"Entity not found",HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HandlerError.class)
    public ResponseEntity<Object> handlerError(HandlerError ex){
        logError(ex);
        log.error("some error was found in the api");
        return builderResponse(ex.getMessage(),"API error",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionGeneric(Exception ex){
        logError(ex);
        log.error("some error was found in the api");
        return builderResponse(ex.getMessage(),"API error",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private static void logError(Exception ex){
        log.error(ex.getClass().getName(),ex);
        log.error(ex.getClass().getName(),ex.getMessage());
        log.error(ex.getClass().getName(),ex.getLocalizedMessage());
    }
    private ResponseEntity<Object> builderResponse(String error, String msg, HttpStatus status){
        ResponseError responseError = new ResponseError(error,msg,status);
        return ResponseEntity.status(status).body(responseError);
    }
}
