package co.com.bancolombia.generica;

import co.com.bancolombia.model.client.Client;
import co.com.bancolombia.model.client.gateways.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientStatic implements ClientRepository {

    private List<Client> clients;

    public ClientStatic() {
        clients=ListClients.getClients();
    }

    @Override
    public Client getClient(String id) {
        Optional<Client> clientOptional = clients.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst();
        if(clientOptional.isPresent()){
            return clientOptional.get();
        }else{
            throw new RuntimeException("CLiente No Existe");
        }
    }

    @Override
    public List<Client> findByName(String name){
        List<Client> result = clients.stream()
                .filter(client -> client.getName().equals(name))
                .collect(Collectors.toList());
        if(result.size()>0) {
            return result;
        }else{
            StringBuilder msg =new StringBuilder();
            msg.append("CLiente con nombre ");
            msg.append(name);
            msg.append("No Existe");
            throw new RuntimeException(msg.toString());
        }

    }

    @Override
    public List<Client> listClients() {
        return clients;
    }

    @Override
    public Client setClient(Client client) {
        clients.add(client);
        return client;
    }
}
