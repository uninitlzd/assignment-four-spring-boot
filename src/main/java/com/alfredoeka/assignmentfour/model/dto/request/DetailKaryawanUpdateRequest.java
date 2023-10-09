package com.alfredoeka.assignmentfour.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailKaryawanUpdateRequest {
    @NotNull
    private Long id;

    private String nik;

    private String npwp;
}
