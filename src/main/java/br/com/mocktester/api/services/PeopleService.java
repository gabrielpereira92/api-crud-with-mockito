package br.com.mocktester.api.services;

import br.com.mocktester.api.domain.People;
import br.com.mocktester.api.domain.dto.PeopleDTO;

import java.util.List;

public interface PeopleService {

    People findById(Integer id);

    List<People> findAllPeople();

    People create(PeopleDTO obj);

    People update(PeopleDTO obj);

}
