package br.com.souza.hexagonal_arch.todoapi.config.handler;


import br.com.souza.hexagonal_arch.todoapi.config.handler.exceptions.ErrorCodeException;
import br.com.souza.hexagonal_arch.todoapi.config.handler.dto.CustomErrorResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Resource
    private MessageSource messageSource;

    @ExceptionHandler({ErrorCodeException.class})
    private ResponseEntity<Object> handleCodigoDeErro(Exception e, WebRequest request) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setError(e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        log.error(e.getMessage());

        return handleExceptionInternal(e, error, headers, HttpStatus.BAD_REQUEST, request);
    }
}

