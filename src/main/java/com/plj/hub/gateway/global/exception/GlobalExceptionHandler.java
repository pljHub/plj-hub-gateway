package com.plj.hub.gateway.global.exception;

import com.plj.hub.gateway.global.dto.ResponseDto;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PljHubException.class)
    public ResponseEntity<ResponseDto<String>> handlePljHubException(PljHubException e) {
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.getStatus();
        String message = errorCode.getMessage();
        return ResponseEntity
                .status(status)
                .body(ResponseDto.error(message));
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ResponseDto<String>> handleJwtException(JwtException e) {
        String message = Arrays.stream(e.getStackTrace()).findFirst().toString();
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ResponseDto.error(message));
    }

}
