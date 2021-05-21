package co.com.bancolombia.jpa.repository;

import co.com.bancolombia.jpa.dto.ClientsDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientDtoRepository extends CrudRepository<ClientsDto, Long> {

    List<ClientsDto> findByNameStartingWith(String name);

    List<ClientsDto> findAll(Pageable pageable);

}
