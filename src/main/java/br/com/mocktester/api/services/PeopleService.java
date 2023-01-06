package br.com.mocktester.api.services;

import br.com.mocktester.api.domain.People;

import java.util.List;

public interface PeopleService {

    People findById(Integer id);

    List<People> findAllPeople();

}
