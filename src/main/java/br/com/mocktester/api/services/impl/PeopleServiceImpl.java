package br.com.mocktester.api.services.impl;

import br.com.mocktester.api.domain.People;
import br.com.mocktester.api.domain.dto.PeopleDTO;
import br.com.mocktester.api.repository.PeopleRepository;
import br.com.mocktester.api.services.PeopleService;
import br.com.mocktester.api.services.exceptions.DataIntegrityViolationException;
import br.com.mocktester.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PeopleRepository peopleRepository;

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
        People people = mapper.map(obj, People.class);
        return peopleRepository.save(people);
    }

    @Override
    public People update(PeopleDTO obj) {
        findByEmail(obj);
        People people = mapper.map(obj, People.class);
        return peopleRepository.save(people);
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        peopleRepository.deleteById(id);
    }

    private void findByEmail(PeopleDTO obj){
        Optional<People> people = peopleRepository.findByEmail(obj.getEmail());
        if (people.isPresent() && !people.get().getId().equals(obj.getId())) {
            throw new DataIntegrityViolationException("Email já cadastrado");
        }
    }
}
