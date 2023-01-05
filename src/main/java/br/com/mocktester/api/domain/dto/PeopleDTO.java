package br.com.mocktester.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeopleDTO {

    private Integer id;
    private String name;
    private String email;
    private String password;
}
