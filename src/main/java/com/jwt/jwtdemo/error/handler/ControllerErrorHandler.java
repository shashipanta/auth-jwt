package com.jwt.jwtdemo.error.handler;

import com.jwt.jwtdemo.dto.response.ErrorResponse;
import com.jwt.jwtdemo.error.codes.ErrorCodes;
import com.jwt.jwtdemo.error.exception.BaseException;
import com.jwt.jwtdemo.error.exception.impl.BadRequestException;
import com.jwt.jwtdemo.error.exception.impl.ForbiddenException;
import com.jwt.jwtdemo.error.exception.impl.InternalServerException;
import com.jwt.jwtdemo.error.exception.impl.TokenExpiredException;
import com.jwt.jwtdemo.validation.BaseValidation;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ControllerErrorHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerErrorHandler.class);

//    @ExceptionHandler({BadRequestException.class})
//    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatusCode status,
//                                                         WebRequest request) {
//        BaseValidation<?> validation = new BaseValidation<>(ex);
//        BaseException exception = new BadRequestException(ErrorCodes.BAD_REQUEST,
//                "Validation Failed. Please check the data and try again.", validation.getErrors());
//        return new ResponseEntity<>(exception.getErrorResponse(), exception.getHttpStatus());
//    }
//
//    @ExceptionHandler({Exception.class})
//    public ResponseEntity<ErrorResponse> handleBadRequestException(Exception e, HttpServletRequest request, BindException ex) {
//        log.info("Bad request from client : {}", request);
//
//        if(e instanceof BadRequestException) e = (BadRequestException) e;
//        BaseValidation<?> validation = new BaseValidation<>(ex);
//        BaseException exception =
//                new BadRequestException(ErrorCodes.BAD_REQUEST, "Validation failed pleasse check your data and try again", validation.getErrors());
//        this.addPathToErrorResponse(exception, request);
//        return ResponseEntity.status(exception.getHttpStatus()).body(exception.getErrorResponse());
//    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(BindException ex, HttpHeaders headers, HttpStatusCode status,
                                                                   WebRequest request) {


        log.info("Bad request from client : {}", request);
        BaseValidation<?> validation = new BaseValidation<>(ex);
        BaseException exception = new BadRequestException(ErrorCodes.BAD_REQUEST,
                "Validation Failed. Please check the data and try again.", validation.getErrors());
        return ResponseEntity.status(exception.getHttpStatus()).body(exception.getErrorResponse());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(Exception e, HttpServletRequest request){
        log.info("Access Denied for authentication type : {}", request.getAuthType());
        BaseException exception = new ForbiddenException(ErrorCodes.ACCESS_DENIED, e.getMessage() );
        this.addPathToErrorResponse(exception, request);
        return ResponseEntity.status(exception.getHttpStatus()).body(exception.getErrorResponse());
    }

    @ExceptionHandler({ExpiredJwtException.class, TokenExpiredException.class})
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(Exception e, HttpServletRequest request){
        BaseException exception = new TokenExpiredException(ErrorCodes.TOKEN_EXPIRED, e.getMessage());
        this.addPathToErrorResponse(exception, request);
        return ResponseEntity.status(exception.getHttpStatus()).body(exception.getErrorResponse());
    }

//    @ExceptionHandler({BaseException.class})
//    public ResponseEntity<ErrorResponse> handleCustomException(Exception e, HttpServletRequest request){
//        log.error("Caught error on base controller : {}", e);
//
//        BaseException baseException;
//        if(e instanceof TokenExpiredException){
//            baseException = (TokenExpiredException) e;
//        } else if(e instanceof BaseException){
//            baseException = (BaseException) e;
//        } else {
//            baseException = new InternalServerException(ErrorCodes.INTERNAL_SERVER_ERROR, "Oops! Something went wrong. Try again");
//        }
//
//        this.addPathToErrorResponse(baseException, request);
//        return ResponseEntity.status(baseException.getHttpStatus()).body(baseException.getErrorResponse());
//    }

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        log.error("Exception caught on base controller ", e);
        BaseException baseException;
        if (e instanceof BaseException) baseException = (BaseException) e;
        else
            baseException = new InternalServerException(ErrorCodes.INTERNAL_SERVER_ERROR, "Something went wrong. Please try again later.");
        this.addPathToErrorResponse(baseException, request);
        return ResponseEntity.status(baseException.getHttpStatus()).body(baseException.getErrorResponse());
    }

    private void addPathToErrorResponse(BaseException baseException, HttpServletRequest request) {
        baseException.setPath(request.getRequestURI());
    }


}
