package br.com.mocktester.api.services.impl;

import br.com.mocktester.api.domain.People;
import br.com.mocktester.api.repository.PeopleRepository;
import br.com.mocktester.api.services.PeopleService;
import br.com.mocktester.api.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeopleServiceImpl implements PeopleService {

    private final PeopleRepository peopleRepository;

    @Override
    public People findById(Integer id) {

        Optional<People> obj = peopleRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public List<People> findAllPeople() {
        return peopleRepository.findAll();
    }
}
