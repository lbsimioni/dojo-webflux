package br.com.dojo.webflux.controllers;

import br.com.dojo.webflux.models.Course;
import br.com.dojo.webflux.services.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

// TODO: Adicionar Configurações
@ContextConfiguration(classes = CourseController.class)
@RunWith(SpringRunner.class)
@WebFluxTest(CourseController.class)
public class CourseControllerTest {

    // TODO: Adicionar testes para todas as rotas da controller
    @MockBean
    CourseService courseService;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void shouldSaveCourse() {
        final var request = Course.builder()
                .cost(new BigDecimal(10))
                .duration("10")
                .name("Introdução ao Pop")
                .build();

        final var response = request.toBuilder().id(1L).build();

        when(courseService.save(request)).thenReturn(Mono.just(response));

        this.webTestClient.post()
                .uri("/v1/course")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), Course.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo(response.getName())
                .jsonPath("$.duration").isEqualTo(response.getDuration())
                .jsonPath("$.id").isEqualTo(response.getId())
                .jsonPath("$.cost").isEqualTo(response.getCost());
    }

    @Test
    public void shouldFindCourseById() {
        final var response = Course.builder()
                .cost(new BigDecimal(10))
                .duration("10")
                .name("Introdução ao Pop")
                .id(1L)
                .build();

        when(courseService.findById(response.getId())).thenReturn(Mono.just(response));

        final var findByIdCourseResponseExchangeResult = this.webTestClient.get()
                .uri("/v1/course/" + response.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Course.class)
                .returnResult();

        final var responseBody = findByIdCourseResponseExchangeResult.getResponseBody();

        assertNotNull(responseBody);
        assertEquals(response.getId(), responseBody.getId());
        assertEquals(response.getName(), responseBody.getName());
        assertEquals(response.getDuration(), responseBody.getDuration());
        assertEquals(response.getCost(), responseBody.getCost());
    }

    @Test
    public void shouldFindAllCourse() {
        final var response = Course.builder()
                            .cost(new BigDecimal(10))
                            .duration("10")
                            .name("Introdução ao Pop")
                            .id(1L)
                            .build();

        when(courseService.findAll()).thenReturn(Flux.just(response));

        this.webTestClient.get()
        .uri("/v1/course")
        .exchange()
        .expectBodyList(Course.class)
        .consumeWith(findAllCourseResponseExchangeResult -> {
            final var courses = findAllCourseResponseExchangeResult.getResponseBody();

            assertNotNull(courses);
            assertFalse(courses.isEmpty());
            assertEquals(response.getId(), courses.get(0).getId());
            assertEquals(response.getName(), courses.get(0).getName());
            assertEquals(response.getCost(), courses.get(0).getCost());
            assertEquals(response.getDuration(), courses.get(0).getDuration());
        });
    }
}
