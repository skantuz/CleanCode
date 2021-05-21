package co.com.bancolombia.jpa.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "propio", name = "clients")
@NoArgsConstructor
public class ClientsDto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "propio.client_id_seq")
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    private String address;
    private String phone;
    private Boolean active = true;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public ClientsDto(String name, String lastName, String address, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }
}
