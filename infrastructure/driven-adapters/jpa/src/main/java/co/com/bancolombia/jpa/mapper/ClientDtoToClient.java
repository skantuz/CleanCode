package co.com.bancolombia.jpa.mapper;

import co.com.bancolombia.jpa.dto.ClientsDto;
import co.com.bancolombia.model.client.Client;

public class ClientDtoToClient {

    public static Client getClient(ClientsDto clientsDto){
        return Client.builder()
                .active(clientsDto.getActive())
                .id(clientsDto.getId().toString())
                .name(clientsDto.getName())
                .lastName(clientsDto.getLastName())
                .phone(clientsDto.getPhone())
                .address(clientsDto.getAddress())
                .build();
    }
}
