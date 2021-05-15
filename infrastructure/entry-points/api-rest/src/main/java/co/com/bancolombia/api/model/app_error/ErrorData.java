package co.com.bancolombia.api.model.app_error;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class ErrorData {
        private String id;
        private String href;
        private Integer status;
        private String code;
        private String title;
        private String detail;
}
