package co.com.bancolombia.api.config;

import co.com.bancolombia.api.model.app_error.AppError;
import co.com.bancolombia.api.model.app_error.ErrorData;
import co.com.bancolombia.api.model.app_error.Meta;
import co.com.bancolombia.model.config.ExceptionClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Log4j2
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(ExceptionClient.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(ExceptionClient e, WebRequest request) {
        UUID messageId = UUID.randomUUID();
        List<ErrorData> errors = new ArrayList<>();
        errors.add(getError(e,request));
        AppError appError = AppError.builder().meta(Meta.builder()
                ._messageId(messageId.toString())
                ._requestDate(LocalDateTime.now())
                ._clientRequest(request.getRemoteUser())
                ._responseSize(1)
                .build())
                .errors(errors)
                .build();
        log.error(messageId+" "+e.getLog());
        return new ResponseEntity<>(appError,
                HttpStatus.valueOf(e.getStatus()));

    }

    private ErrorData getError(ExceptionClient e,WebRequest request){
        return ErrorData.builder()
                .code(e.getCode())
                .id(request.getSessionId())
                .href(request.getContextPath())
                .status(e.getStatus())
                .detail(e.getMessage())
                .title(e.getTitle())
                .build();
    }
}
