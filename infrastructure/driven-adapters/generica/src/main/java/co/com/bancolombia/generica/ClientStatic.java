package co.com.bancolombia.generica;

import co.com.bancolombia.model.client.Client;
import co.com.bancolombia.model.client.gateways.ClientRepository;
import co.com.bancolombia.model.config.ExceptionClient;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
public class ClientStatic implements ClientRepository {

    private final List<Client> clients;
    public ClientStatic() {
        clients = ListClients.getClients();
    }

    @Override
    public Client getClient(String id) {
        log.info("id".concat(id));
        Optional<Client> clientOptional = clients.stream()
                .filter(client -> client.getId().equals(id) && client.getActive())
                .findFirst();
        if (clientOptional.isPresent()) {
            return clientOptional.get();
        } else {
            throw new ExceptionClient("Cliente con id ".concat(id).concat("no existe"),
                    "Cliente No Existe",
                    404,
                    "El CLiente no existe","EC404");
        }
    }

    @Override
    public List<Client> findByName(String name) {
        log.info("nombre a buscar ".concat(name));
        List<Client> result = new ArrayList<>();
        clients.stream()
                .filter(client -> name.equalsIgnoreCase(client.getName())).forEach(result::add);
        if (result.size() > 0) {
            return result;
        } else {
            StringBuilder msg = new StringBuilder();
            msg.append("CLiente con nombre ");
            msg.append(name);
            msg.append("No Existe");
            throw new RuntimeException(msg.toString());
        }

    }

    @Override
    public List<Client> listClients(Integer page,Integer pageSize,String sort) {
        return clients.stream()
                .filter(Client::getActive)
                .collect(Collectors.toList());
    }

    @Override
    public Client setClient(Client client) {
        clients.add(client);
        return client;
    }

    @Override
    public Client deleteClient(Client client) {
        clients.stream().filter(c -> c.getId().equals(client.getId()))
                .forEach(clientEdit -> clientEdit.setActive(Boolean.FALSE));
        return client;
    }
}
