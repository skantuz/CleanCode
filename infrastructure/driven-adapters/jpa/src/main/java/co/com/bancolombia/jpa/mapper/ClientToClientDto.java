package co.com.bancolombia.jpa.mapper;

import co.com.bancolombia.jpa.dto.ClientsDto;
import co.com.bancolombia.model.client.Client;

public class ClientToClientDto {
    public static ClientsDto getClientDto(Client client) {
        return new ClientsDto(
                client.getName(),client.getLastName(),client.getAddress(),client.getPhone());
    }
}
