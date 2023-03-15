package br.com.mocktester.api.services.impl;

import br.com.mocktester.api.domain.People;
import br.com.mocktester.api.domain.dto.PeopleDTO;
import br.com.mocktester.api.repository.PeopleRepository;
import br.com.mocktester.api.services.exceptions.DataIntegratyViolationException;
import br.com.mocktester.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class PeopleServiceImplTest {

    public static final String NAME = "Gabriel";
    public static final Integer ID = 1;
    public static final String EMAIL = "gabriel@mail.com";
    public static final String PASS = "123";
    public static final String OBJECT_NOT_FOUND_MESSAGE = "Objeto não encontrado";
    public static final Integer INDEX = 0;
    public static final String EMAIL_ALREADY_EXISTENT = "Email já cadastrado";
    @InjectMocks
    private PeopleServiceImpl peopleService;

    @Mock
    private PeopleRepository peopleRepository;

    @Mock
    private ModelMapper mapper;

    private People people;
    private PeopleDTO peopleDTO;
    private Optional<People> optionalPeople;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startPeople();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(peopleRepository.findById(anyInt())).thenReturn(optionalPeople);

        People response = peopleService.findById(ID);

        assertNotNull(response);
        assertEquals(People.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());

    }

    @Test
    void whenFindByIdAnObjectNotFoundException() {
        when(peopleRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND_MESSAGE));

        try {
            peopleService.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND_MESSAGE, ex.getMessage());

        }
    }
    @Test
    void whenFindAllThenReturnAListOfPeople() {
        when(peopleRepository.findAll()).thenReturn(List.of(people));

        List<People> response = peopleService.findAllPeople();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(people.getClass(), response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASS, response.get(INDEX).getPassword());


    }

    @Test
    void whenCreatePeopleReturnSuccess() {
        when(peopleRepository.save(any())).thenReturn(people);

        People response = peopleService.create(peopleDTO);

        assertNotNull(response);
        assertEquals(People.class, response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASS, response.getPassword());
    }

    @Test
    void whenCreatePeopleReturnDataIntegrityViolationException() {
        when(peopleRepository.findByEmail(anyString())).thenReturn(optionalPeople);

        try {
            optionalPeople.get().setId(2);
            peopleService.create(peopleDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(EMAIL_ALREADY_EXISTENT, ex.getMessage());
        }


    }

    @Test
    void whenUpdatePeopleReturnSuccess() {
        when(peopleRepository.save(any())).thenReturn(people);

        People response = peopleService.update(peopleDTO);

        assertNotNull(response);
        assertEquals(People.class, response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASS, response.getPassword());
    }

    @Test
    void whenUpdatePeopleReturnDataIntegrityViolationException() {
        when(peopleRepository.findByEmail(anyString())).thenReturn(optionalPeople);

        try {
            optionalPeople.get().setId(2);
            peopleService.update(peopleDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals(EMAIL_ALREADY_EXISTENT, ex.getMessage());

        }


    }

    @Test
    void delete() {
    }

    private void startPeople() {
        people = new People(ID, NAME, EMAIL, PASS);
        peopleDTO = new PeopleDTO(ID, NAME, EMAIL, PASS);
        optionalPeople = Optional.of(new People(ID, NAME, EMAIL, PASS));
    }
}