package co.com.bancolombia.api;

import co.com.bancolombia.api.mapper.ClientDtoToClient;
import co.com.bancolombia.api.mapper.ClientToClientDto;
import co.com.bancolombia.api.model.ClientDto;
import co.com.bancolombia.api.model.Login;
import co.com.bancolombia.api.service.UserDetail;
import co.com.bancolombia.model.client.Client;
import co.com.bancolombia.usecase.client.ClientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ApiRest {
    //    private final MyUseCase useCase;
    private final ClientUseCase clientUseCase;
    private final UserDetail userDetail;

    @GetMapping(value = "/client")
    public List<ClientDto> listClient(@PathParam("name") String name,
                                      @PathParam("page") Integer page,
                                      @PathParam("sort") String sort,
                                      @RequestHeader Map<String, Object> headers) {
        userDetail.checkAuth(headers,"ADMIN");
        if (Objects.isNull(page)) {
            page = 0;
        }
        if (Objects.isNull(sort)) {
            sort = "createdAt";
        }
        List<Client> clients;
        if (Objects.nonNull(name)) {
            clients = clientUseCase.findByName(name);
        } else {
            clients = clientUseCase.listClient(page, 10, sort);
        }

        return clients.stream()
                .map(ClientToClientDto::getClientDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/client/{id}")
    public ClientDto getClientById(@PathVariable("id") String id, @RequestHeader Map<String, Object> headers) {
        userDetail.checkAuth(headers,"ADMIN");
        return ClientToClientDto.getClientDto(clientUseCase.getClient(id));

    }

    @PostMapping(value = "/client")
    public ClientDto postHello(@RequestBody ClientDto clientDto, @RequestHeader Map<String, Object> headers) {
        userDetail.checkAuth(headers,"ADMIN");
        Client client = ClientDtoToClient.getClient(clientDto);
        return ClientToClientDto.getClientDto(clientUseCase.setClient(client));
    }

    @PostMapping("/auth")
    public Map<String, String> authorization(@RequestBody Login login) {
        return Map.of("token", userDetail.auth(login));
    }

    @DeleteMapping("/client/{id}")
    public ClientDto deleteClient(@PathVariable String id, @RequestHeader Map<String, Object> headers) {
        userDetail.checkAuth(headers,"ADMIN");
        Client client = clientUseCase.getClient(id);
        return ClientToClientDto.getClientDto(clientUseCase.deleteClient(client));
    }
}
