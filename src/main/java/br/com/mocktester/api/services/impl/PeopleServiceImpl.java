package br.com.mocktester.api.services.impl;

import br.com.mocktester.api.domain.People;
import br.com.mocktester.api.repository.PeopleRepository;
import br.com.mocktester.api.services.PeopleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PeopleServiceImpl implements PeopleService {


    private PeopleRepository peopleRepository;

    @Override
    public People findById(Integer id) {

        Optional<People> obj = peopleRepository.findById(id);
        return obj.orElse(null);
    }
}
