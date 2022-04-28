package br.com.dojo.webflux.controllers;

import br.com.dojo.webflux.models.Student;
import br.com.dojo.webflux.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/students")
@AllArgsConstructor
public class StudentController {

    @Autowired
    private final StudentService studentService;

    @PostMapping
    public Mono<Student> save(@RequestBody final Student student) {
        return studentService.save(student);
    }

    @GetMapping("/{id}")
    public Mono<Student> findById(@PathVariable final Long id) {
        return this.studentService.findById(id);
    }

    @GetMapping
    public Flux<Student> findAll() {
        return this.studentService.findAll();
    }
}
