package br.com.mocktester.api.repository;

import br.com.mocktester.api.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<People, Integer> {

    Optional<People> findByEmail(String email);
}
