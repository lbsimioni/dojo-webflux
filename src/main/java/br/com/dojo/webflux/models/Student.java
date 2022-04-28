package br.com.dojo.webflux.models;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter
@Builder
public class Student {

    private Long id;
    private String name;
    private Course course;
    private LocalDate birthDate;

}
