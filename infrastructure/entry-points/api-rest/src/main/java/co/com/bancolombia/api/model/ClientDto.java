package co.com.bancolombia.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String id;
    private String name;
    private String lastName;
    private String address;
    private String phone;
}
