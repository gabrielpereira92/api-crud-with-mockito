package br.com.mocktester.api.repository;

import br.com.mocktester.api.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Integer> {


}
