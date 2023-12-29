package cn.zchengb.order.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
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
    private static final String ILLEGAL_ARGUMENT = "ILLEGAL_ARGUMENT";
    private final ObjectMapper objectMapper;

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
        return new ErrorResult(ILLEGAL_ARGUMENT, e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResult handleNullBadRequestException(NullPointerException e) {
        log.error("handleBadRequestException: " + e.getMessage(), e);
        return new ErrorResult(ILLEGAL_ARGUMENT, e.getMessage());
    }

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResult handleFeignException(FeignException e) {
        var errorMessage = extractErrorMessage(e);
        return new ErrorResult(ILLEGAL_ARGUMENT, errorMessage);
    }

    private String extractErrorMessage(FeignException e) {
        try {
            if (e.responseBody().isPresent()) {
                var responseBody = new String(e.responseBody().get().array());
                var jsonNode = objectMapper.readTree(responseBody);
                return jsonNode.path("message").asText();
            }
            return e.getMessage();
        } catch (Exception error) {
            log.error("Failed to extract error message from FeignException", error);
            return error.getMessage();
        }
    }
}
