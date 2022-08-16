package com.asusoft.chatserver.exceptionhandler;

import com.asusoft.chatserver.exceptionhandler.exception.DuplicateSaveException;
import com.asusoft.chatserver.exceptionhandler.exception.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult illegalArgumentExHandler(IllegalArgumentException e) {
        log.info("[illegalArgumentExHandler] ex: ", e);
        return new ErrorResult(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResult loginException(LoginException e) {
        log.info("[LoginException] ex: ", e);
        return new ErrorResult(HttpStatus.NOT_FOUND.toString(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResult duplicateSaveException(DuplicateSaveException e) {
        log.info("[DuplicateSaveException] ex: ", e);
        return new ErrorResult(HttpStatus.NOT_FOUND.toString(), e.getMessage());
    }
}
