package co.com.bancolombia.api.model.app_error;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
public class AppError {
    private Meta meta;
    private List<ErrorData> errors;
}
