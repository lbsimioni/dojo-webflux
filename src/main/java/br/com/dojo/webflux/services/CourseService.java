package br.com.dojo.webflux.services;

import br.com.dojo.webflux.models.Course;
import br.com.dojo.webflux.repositories.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CourseService {

    @Autowired
    private final CourseRepository courseRepository;

    public Mono<Course> save(final Course course) {
        return this.courseRepository.save(course);
    }

    public Mono<Course> findById(final Long id) {
        return this.courseRepository.findById(id);
    }

    public Flux<Course> findAll() {
        return this.courseRepository.findAll();
    }

}
