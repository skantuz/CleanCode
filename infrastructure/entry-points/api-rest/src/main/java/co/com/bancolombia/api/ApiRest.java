package co.com.bancolombia.api;
import co.com.bancolombia.api.mapper.ClientDtoToClient;
import co.com.bancolombia.api.mapper.ClientToClientDto;
import co.com.bancolombia.api.model.ClientDto;
import co.com.bancolombia.model.client.Client;
import co.com.bancolombia.usecase.client.ClientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ApiRest {
//    private final MyUseCase useCase;
    private final ClientUseCase clientUseCase;

    @GetMapping(value = "/client")
    public List<ClientDto> listClient(@PathParam("name") String name) {
        List<Client> clients;
        if(Objects.nonNull(name)){
            clients=clientUseCase.findByName(name);
        }else{
            clients=clientUseCase.listClient();
        }

        return clients.stream()
                .map(ClientToClientDto::getClientDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/client/{id}")
    public ClientDto getClientById(@PathVariable("id") String id){
        return ClientToClientDto.getClientDto(clientUseCase.getClient(id));
    }

    @PostMapping(value = "/client")
    public ClientDto postHello(@RequestBody ClientDto clientDto){
        Client client = ClientDtoToClient.getClient(clientDto);
        return ClientToClientDto.getClientDto(clientUseCase.setClient(client));
    }

    @DeleteMapping("/client/{id}")
    public ClientDto deleteClient(@PathVariable String id){
        Client client = clientUseCase.getClient(id);
        return ClientToClientDto.getClientDto(clientUseCase.deleteClient(client));
    }
}
