package br.com.dojo.webflux.services;

import br.com.dojo.webflux.exceptions.StudentNotFoundException;
import br.com.dojo.webflux.models.Student;
import br.com.dojo.webflux.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class StudentService {

    @Autowired
    private final CourseService courseService;

    @Autowired
    private final StudentRepository studentRepository;

    public Mono<Student> save(final Student student) {
        return this.courseService.save(student.getCourse())
                .flatMap(savedCourse -> {
                    student.setCourse(savedCourse);
                    return this.studentRepository.save(student);
                });
    }

    public Mono<Student> findById(final Long id) {
        return this.studentRepository.findById(id)
                .switchIfEmpty(Mono.error(StudentNotFoundException::new));
    }

    public Flux<Student> findAll() {
        return this.studentRepository.findAll();
    }
}
