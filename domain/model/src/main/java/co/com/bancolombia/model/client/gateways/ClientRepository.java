package co.com.bancolombia.model.client.gateways;

import co.com.bancolombia.model.client.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ClientRepository {

    Client getClient(String id);

    List<Client> findByName(String name);

    List<Client> listClients();

    Client setClient(Client client);

    Client deleteClient(Client client);
}
