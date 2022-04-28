package br.com.dojo.webflux.repositories;

import br.com.dojo.webflux.models.Course;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

@Repository
public class CourseRepository {

    private static final Set<Course> courses = new HashSet<>();

    public Mono<Course> save(final Course course) {
        course.setId(courses.isEmpty() ? 1L : courses.size() + 1);
        if (courses.add(course)) {
            return Mono.just(course);
        }

        return Mono.justOrEmpty(courses.stream().filter(c -> c.equals(course)).findFirst());
    }

    public Mono<Course> findById(final Long id) {
        return Mono.justOrEmpty(courses.stream().filter(c -> c.getId().equals(id)).findFirst());
    }

    public Flux<Course> findAll() {
        return Flux.fromIterable(courses);
    }

}
