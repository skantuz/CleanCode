package co.com.bancolombia.generica;

import co.com.bancolombia.model.client.Client;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class pruebas {

    public static void main(String[] args) {
        String name = "Pepito";
        LocalDateTime start = LocalDateTime.now();
        StringBuilder msg = new StringBuilder();
        msg.append("numeros");
        for(int i =0; i<10000;i++){
            msg.append(i);
        }
        System.out.println(msg.toString());
        System.out.println("Duracion: "+ChronoUnit.MILLIS.between(start,LocalDateTime.now()));


    }
}
