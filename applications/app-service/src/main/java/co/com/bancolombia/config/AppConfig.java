package co.com.bancolombia.config;

import co.com.bancolombia.jpa.ClientService;
import co.com.bancolombia.jpa.repository.ClientDtoRepository;
import co.com.bancolombia.jwt.JwtService;
import co.com.bancolombia.model.client.gateways.ClientRepository;
import co.com.bancolombia.model.jwttoken.gateways.JwtTokenRepository;
import co.com.bancolombia.usecase.client.ClientUseCase;
import co.com.bancolombia.usecase.jwttoken.JwtTokenUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
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
    @Bean
    public JwtTokenRepository jwtTokenRepository(@Value("${jwt.issuer}")String issuer,
                                                 @Value("${jwt.secret}")String secret,
                                                 ObjectMapper mapper){
        return new JwtService(secret,issuer,mapper);
    }
    @Bean
    public JwtTokenUseCase jwtTokenUseCase(JwtTokenRepository jwtTokenRepository){
        return new JwtTokenUseCase(jwtTokenRepository);
    }
}
