package br.com.mocktester.api.resources;

import br.com.mocktester.api.domain.People;
import br.com.mocktester.api.domain.dto.PeopleDTO;
import br.com.mocktester.api.services.impl.PeopleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class PeopleResourceTest {

    public static final String NAME = "Gabriel";
    public static final Integer ID = 1;
    public static final String EMAIL = "gabriel@mail.com";
    public static final String PASS = "123";

    public static final String OBJECT_NOT_FOUND_MESSAGE = "Objeto não encontrado";

    public static final Integer INDEX = 0;

    public static final String EMAIL_ALREADY_EXISTENT = "Email já cadastrado";

    private People people;

    private PeopleDTO peopleDTO;

    @InjectMocks
    private PeopleResource peopleResource;

    @Mock
    private PeopleServiceImpl peopleService;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startPeople();

    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(peopleService.findById(anyInt())).thenReturn(people);
        when(mapper.map(any(),any())).thenReturn(peopleDTO);

        ResponseEntity<PeopleDTO> response = peopleResource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(PeopleDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASS, response.getBody().getPassword());



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