package co.com.bancolombia.generica;

import co.com.bancolombia.model.client.Client;

import java.util.ArrayList;
import java.util.List;

public class ListClients {

    public static List<Client> getClients(){
        List<Client> clients;
        clients= new ArrayList<>();
        clients.add(Client.builder().name("pepito").id("1234").build());
        clients.add(Client.builder().id("123").name("Julanito de Tal").build());
        clients.add(Client.builder().id("54321").build());
        return getClients();
    }
}
