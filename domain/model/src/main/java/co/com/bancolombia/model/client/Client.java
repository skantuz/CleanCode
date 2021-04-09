package co.com.bancolombia.model.client;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Client {
    private String id;
    private String name;
    private String lastName;
    private String address;
    private String phone;
}
