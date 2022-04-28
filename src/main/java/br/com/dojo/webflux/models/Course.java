package br.com.dojo.webflux.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder(toBuilder = true)
public class Course {

    private Long id;
    private String name;
    private String duration;
    private BigDecimal cost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name) && Objects.equals(duration, course.duration) && Objects.equals(cost, course.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration, cost);
    }
}
