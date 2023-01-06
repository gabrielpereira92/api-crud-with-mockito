package br.com.mocktester.api.services.impl;

import br.com.mocktester.api.domain.People;
import br.com.mocktester.api.domain.dto.PeopleDTO;
import br.com.mocktester.api.repository.PeopleRepository;
import br.com.mocktester.api.services.PeopleService;
import br.com.mocktester.api.services.exceptions.DataIntegratyViolationException;
import br.com.mocktester.api.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeopleServiceImpl implements PeopleService {

    private final ModelMapper mapper;
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

    @Override
    public People create(PeopleDTO obj) {
        findByEmail(obj);
        //O Mapper tem a função de transformar um Objeto x em um Dto dele
        People map = mapper.map(obj, People.class);
        return peopleRepository.save(map);
    }

    @Override
    public People update(PeopleDTO obj) {
        findByEmail(obj);
        People map = mapper.map(obj, People.class);
        return peopleRepository.save(map);
    }

    private void findByEmail(PeopleDTO obj){
        Optional<People> people = peopleRepository.findByEmail(obj.getEmail());
        if (people.isPresent() && !people.get().getId().equals(obj.getId())) {
            throw new DataIntegratyViolationException("Email já cadastrado");
        }
    }
}
