package br.com.mocktester.api.services;

import br.com.mocktester.api.domain.People;

public interface PeopleService {

    People findById(Integer id);

}
