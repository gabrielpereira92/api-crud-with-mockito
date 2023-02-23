package br.com.mocktester.api.resources;

import br.com.mocktester.api.domain.dto.PeopleDTO;
import br.com.mocktester.api.services.PeopleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/people")
@RequiredArgsConstructor
public class PeopleResource {

    public static final String ID = "/{id}";
    private final ModelMapper mapper;
    private final PeopleService peopleService;

    @GetMapping(value = ID)
    public ResponseEntity<PeopleDTO> findById(@PathVariable Integer id){

        return ResponseEntity.ok().body(mapper.map(peopleService.findById(id), PeopleDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<PeopleDTO>> findAllPeople(){
        List<PeopleDTO> peopleDTOS = peopleService.findAllPeople()
                .stream().map(x -> mapper.map(x , PeopleDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(peopleDTOS);
    }
    @PostMapping
    public ResponseEntity<PeopleDTO> create(@RequestBody PeopleDTO obj){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
                .buildAndExpand(peopleService.create(obj).getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping(value = ID)
    public ResponseEntity<PeopleDTO> update(@PathVariable Integer id, @RequestBody PeopleDTO obj)  {
        obj.setId(id);
        return ResponseEntity.ok().body(mapper.map(peopleService.update(obj), PeopleDTO.class));
    }
    @DeleteMapping(value = ID)
    public ResponseEntity<PeopleDTO> delete(@PathVariable Integer id){
        peopleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
