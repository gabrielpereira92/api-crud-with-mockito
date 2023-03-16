package br.com.mocktester.api.resources;

import br.com.mocktester.api.domain.People;
import br.com.mocktester.api.domain.dto.PeopleDTO;
import br.com.mocktester.api.services.impl.PeopleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PeopleResourceTest {

    public static final String NAME = "Gabriel";
    public static final Integer ID = 1;
    public static final String EMAIL = "gabriel@mail.com";
    public static final String PASS = "123";

    private People people;

    private PeopleDTO peopleDTO;

    @InjectMocks
    private PeopleResource resource;

    @Mock
    private PeopleServiceImpl service;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startPeople();

    }

    @Test
    void findById() {
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
    }
}