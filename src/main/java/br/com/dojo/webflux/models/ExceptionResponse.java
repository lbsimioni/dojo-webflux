package br.com.dojo.webflux.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    @Builder.Default
    private ZonedDateTime timestamp = ZonedDateTime.now();
    private String path;
    private Integer status;
    private String message;
}
