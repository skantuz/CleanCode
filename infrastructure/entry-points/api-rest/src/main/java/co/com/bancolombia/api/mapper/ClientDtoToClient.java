package co.com.bancolombia.api.mapper;

import co.com.bancolombia.api.model.ClientDto;
import co.com.bancolombia.model.client.Client;

public class ClientDtoToClient {

    public static Client getClient(ClientDto clientDto){
        return Client.builder()
                .address(clientDto.getAddress())
                .id(clientDto.getId())
                .lastName(clientDto.getLastName())
                .phone(clientDto.getPhone())
                .name(clientDto.getName())
                .build();
    }
}
