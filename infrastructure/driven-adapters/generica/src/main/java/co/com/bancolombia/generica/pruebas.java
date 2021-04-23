package co.com.bancolombia.generica;

import co.com.bancolombia.model.client.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class pruebas {

    public static void main(String[] args) {
        List<Client> clients = ListClients.getClients();

        Optional<Client> first = clients.stream().filter(client -> client.getId().equals("13")).findFirst();

        if(first.isPresent()){
            System.out.printf(first.get().toString());
        }else {
            System.out.println("Registro no encontrado");
        }



    }
}
