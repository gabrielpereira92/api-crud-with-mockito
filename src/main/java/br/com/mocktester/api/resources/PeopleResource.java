package br.com.mocktester.api.resources;

import br.com.mocktester.api.domain.People;
import br.com.mocktester.api.services.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/people")
@RequiredArgsConstructor
public class PeopleResource {
    private final PeopleService peopleService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<People> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(peopleService.findById(id));
    }
}
