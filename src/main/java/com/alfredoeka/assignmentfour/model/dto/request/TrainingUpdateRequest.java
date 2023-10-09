package com.alfredoeka.assignmentfour.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingUpdateRequest {
    @NotNull(message = "Please provide id")
    private Long id;

    @NotNull
    private String tema;

    @NotNull
    private String pengajar;
}
