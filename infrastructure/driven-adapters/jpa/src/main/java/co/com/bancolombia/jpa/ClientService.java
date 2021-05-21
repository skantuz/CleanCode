package co.com.bancolombia.jpa;

import co.com.bancolombia.jpa.dto.ClientsDto;
import co.com.bancolombia.jpa.mapper.ClientDtoToClient;
import co.com.bancolombia.jpa.mapper.ClientToClientDto;
import co.com.bancolombia.jpa.repository.ClientDtoRepository;
import co.com.bancolombia.model.client.Client;
import co.com.bancolombia.model.client.gateways.ClientRepository;
import co.com.bancolombia.model.config.ExceptionClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public class ClientService implements ClientRepository {

    private final ClientDtoRepository clientDtoRepository;

    @Override
    public Client getClient(String id) {
        Optional<ClientsDto> clientsDto = clientDtoRepository.findById(Long.getLong(id));
        if (clientsDto.isPresent()) {
            return ClientDtoToClient.getClient(clientsDto.get());
        }
        return getClient("ID ".concat(id));
    }

    private void getException(String message) {
        throw new ExceptionClient("Cliente con ".concat(message).concat(" no existe"),
                "Cliente No Existe",
                404,
                "El CLiente no existe", "EC404");
    }

    @Override
    public List<Client> findByName(String name) {
        return clientDtoRepository.findByNameStartingWith(name).stream()
                .map(ClientDtoToClient::getClient)
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> listClients(Integer page, Integer pageSize, String sort) {

        Iterable<ClientsDto> clientsDto = clientDtoRepository.findAll(PageRequest.of(page,pageSize,Sort.by(sort)));
        return StreamSupport.stream(clientsDto.spliterator(), false)
                .filter(ClientsDto::getActive)
                .map(ClientDtoToClient::getClient)
                .collect(Collectors.toList());
    }

    @Override
    public Client setClient(Client client) {

        return ClientDtoToClient.getClient(clientDtoRepository.save(ClientToClientDto.getClientDto(client)));
    }

    @Override
    public Client deleteClient(Client client) {
        Optional<ClientsDto> clientsDto = clientDtoRepository.findById(Long.getLong(client.getId()));
        if (clientsDto.isPresent()) {
            ClientsDto dto = clientsDto.get();
            dto.setActive(false);
            return ClientDtoToClient.getClient(clientDtoRepository.save(dto));
        }
        throw new ExceptionClient("CLiente No existe" + client, "Cliente No existe",
                204, "Este cliente no existe", "EC-204");
    }
}
