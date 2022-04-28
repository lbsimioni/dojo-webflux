package br.com.dojo.webflux.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter
public class Student {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;
    private Course course;
    private LocalDate birthDate;

}
