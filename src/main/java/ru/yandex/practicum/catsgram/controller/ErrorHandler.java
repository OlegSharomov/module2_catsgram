package ru.yandex.practicum.catsgram.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.catsgram.exception.*;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({PostNotFoundException.class, UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse<String> handlePostAndUserNotFound(RuntimeException e) {
        return new ErrorResponse<>(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse<String> handleUserExist(UserAlreadyExistException e) {
        return new ErrorResponse<>(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse<String> handleInvalidEmail(InvalidEmailException e) {
        return new ErrorResponse<>(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse<String> handleException(IncorrectParameterException e) {
        return new ErrorResponse<>(String.format("Ошибка с полем %s", e.getParameter()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse<String> handleOtherExceptions(Throwable e) {
        return new ErrorResponse<>("Произошла непредвиденная ошибка.");
    }

}

class ErrorResponse<String> {
    private final String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}