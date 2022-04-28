package br.com.dojo.webflux.repositories;

import br.com.dojo.webflux.models.Student;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private static final List<Student> students = new ArrayList<>();

    public Mono<Student> save(final Student student) {
        student.setId(students.isEmpty() ? 1L : students.size() + 1);
        students.add(student);

        return Mono.just(student);
    }

    public Mono<Student> findById(final Long id) {
        return Mono.justOrEmpty(students.stream().filter(s -> s.getId().equals(id)).findFirst());
    }

    public Flux<Student> findAll() {
        return Flux.fromIterable(students);
    }

}
