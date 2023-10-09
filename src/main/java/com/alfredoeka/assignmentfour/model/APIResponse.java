package com.alfredoeka.assignmentfour.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse<T> {
    private T data;

    @Builder.Default
    private Integer code = HttpStatus.OK.value();

    @Builder.Default
    private String status = HttpStatus.OK.getReasonPhrase();
}
