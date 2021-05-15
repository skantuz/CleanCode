package co.com.bancolombia.api.model.app_error;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
public class Meta {
    private String _messageId;
    private String _version;
    private LocalDateTime _requestDate;
    private Integer _responseSize;
    private String _clientRequest;
    private JsonNode additionalProp1;
}
