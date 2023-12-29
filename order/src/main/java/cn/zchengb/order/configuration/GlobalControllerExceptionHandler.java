package cn.zchengb.order.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResult handleRuntimeException(Exception e) {
        log.error("handleRuntimeException: " + e.getMessage(), e);
        return new ErrorResult("INTERNAL_SERVER_ERROR", e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResult handleBadRequestException(IllegalArgumentException e) {
        log.error("handleBadRequestException: " + e.getMessage(), e);
        return new ErrorResult("ILLEGAL_ARGUMENT", e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResult handleNullBadRequestException(NullPointerException e) {
        log.error("handleBadRequestException: " + e.getMessage(), e);
        return new ErrorResult("ILLEGAL_ARGUMENT", e.getMessage());
    }
}
