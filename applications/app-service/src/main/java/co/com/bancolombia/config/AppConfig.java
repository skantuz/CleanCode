package co.com.bancolombia.config;

import co.com.bancolombia.jpa.ClientService;
import co.com.bancolombia.jpa.repository.ClientDtoRepository;
import co.com.bancolombia.model.client.gateways.ClientRepository;
import co.com.bancolombia.usecase.client.ClientUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ClientRepository getClientRepository(ClientDtoRepository clientDtoRepository) {
        return new ClientService(clientDtoRepository);
    }

    @Bean
    public ClientUseCase clientUseCase(ClientRepository clientRepository) {
        return new ClientUseCase(clientRepository);
    }
}
