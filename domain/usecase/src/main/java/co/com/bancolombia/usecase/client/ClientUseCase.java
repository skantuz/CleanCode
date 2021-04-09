package co.com.bancolombia.usecase.client;

import co.com.bancolombia.model.client.Client;
import co.com.bancolombia.model.client.gateways.ClientRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ClientUseCase {

    private final ClientRepository clientRepository;

    public Client getClient(String id){
        return  clientRepository.getClient(id);
    }

    public List<Client> listClient(){
        return clientRepository.listClients();
    }

    public Client setClient(Client client){
        return clientRepository.setClient(client);
    }
}
