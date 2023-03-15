package br.com.mocktester.api.services.impl;

import br.com.mocktester.api.domain.People;
import br.com.mocktester.api.domain.dto.PeopleDTO;
import br.com.mocktester.api.repository.PeopleRepository;
import br.com.mocktester.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest
class PeopleServiceImplTest {

    public static final String NAME = "Gabriel";
    public static final Integer ID = 1;
    public static final String EMAIL = "gabriel@mail.com";
    public static final String PASS = "123";
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
        when(peopleRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));

        try {
            peopleService.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }
    @Test
    void findAllPeople() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
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