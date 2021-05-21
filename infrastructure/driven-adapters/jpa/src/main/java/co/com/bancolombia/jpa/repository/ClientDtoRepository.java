package co.com.bancolombia.jpa.repository;

import co.com.bancolombia.jpa.dto.ClientsDto;
import co.com.bancolombia.model.client.Client;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientDtoRepository extends CrudRepository<ClientsDto, Long> {

    Optional<ClientsDto> findById(Integer id);

    List<ClientsDto> findByNameStartingWith(String name);

    List<ClientsDto> findAll(Pageable pageable);

}
