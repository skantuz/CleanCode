package co.com.bancolombia.jpa.repository;

import co.com.bancolombia.jpa.dto.ClientsDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientDtoRepository extends CrudRepository<ClientsDto, Long> {

    List<ClientsDto> findByNameStartingWith(String name);

}
