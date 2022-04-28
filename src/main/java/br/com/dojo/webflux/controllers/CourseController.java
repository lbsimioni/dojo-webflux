package br.com.dojo.webflux.controllers;

import br.com.dojo.webflux.models.Course;
import br.com.dojo.webflux.services.CourseService;
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
@RequestMapping("/v1/course")
@AllArgsConstructor
public class CourseController {

    @Autowired
    private final CourseService courseService;

    @PostMapping
    public Mono<Course> save(@RequestBody final Course student) {
        return courseService.save(student);
    }

    @GetMapping("/{id}")
    public Mono<Course> findById(@PathVariable final Long id) {
        return this.courseService.findById(id);
    }

    @GetMapping
    public Flux<Course> findAll() {
        return this.courseService.findAll();
    }

}
