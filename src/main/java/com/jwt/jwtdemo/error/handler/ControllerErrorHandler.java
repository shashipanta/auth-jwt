package com.jwt.jwtdemo.error.handler;

import com.jwt.jwtdemo.dto.response.ErrorResponse;
import com.jwt.jwtdemo.error.codes.ErrorCodes;
import com.jwt.jwtdemo.error.exception.BaseException;
import com.jwt.jwtdemo.error.exception.impl.BadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerErrorHandler.class);

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(Exception e, HttpServletRequest request) {
        log.info("Bad request from client");
        BaseException exception =
                new BadRequestException(ErrorCodes.BAD_REQUEST, "Validation failed pleasse check your data and try again");
        this.addPathToErrorResponse(exception, request);
        return ResponseEntity.status(exception.getHttpStatus()).body(exception.getErrorResponse());
    }

    private void addPathToErrorResponse(BaseException baseException, HttpServletRequest request) {
        baseException.setPath(request.getRequestURI());
    }


}
