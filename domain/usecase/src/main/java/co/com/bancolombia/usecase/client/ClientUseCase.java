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

    public List<Client> listClient(Integer page,Integer pageSize,String sort){
        return clientRepository.listClients(page,pageSize,sort);
    }

    public List<Client> findByName(String name){
        return clientRepository.findByName(name);
    }

    public Client setClient(Client client){
        return clientRepository.setClient(client);
    }

    public Client deleteClient(Client client){
        return clientRepository.deleteClient(client);
    }
}
