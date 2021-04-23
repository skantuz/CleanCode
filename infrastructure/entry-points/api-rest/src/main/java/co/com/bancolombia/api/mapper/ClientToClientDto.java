package co.com.bancolombia.api.mapper;

import co.com.bancolombia.api.model.ClientDto;
import co.com.bancolombia.model.client.Client;

public class ClientToClientDto {
    public static ClientDto getClientDto(Client client){
        return new ClientDto(
                client.getId(), client.getName(), client.getLastName(), client.getAddress(), client.getPhone());
    }
}
