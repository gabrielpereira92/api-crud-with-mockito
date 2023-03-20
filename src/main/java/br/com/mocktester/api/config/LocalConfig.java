package br.com.mocktester.api.config;


import br.com.mocktester.api.domain.People;
import br.com.mocktester.api.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("local")
@Configuration
@RequiredArgsConstructor
public class LocalConfig {

    private final PeopleRepository peopleRepository;

    @Bean
    public void startDb(){
        People people1 = new People(null, "Gabriel", "gabriel@mail.com", "123");
        People people2 = new People(null, "Julianny", "julianny@mail.com", "123");


        peopleRepository.saveAll(List.of(people1,people2));
    }
}
