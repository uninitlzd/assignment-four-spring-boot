package com.alfredoeka.assignmentfour.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KaryawanUpdateRequest {
    @NotNull
    private Long id;

    private String nama;

    private String alamat;

    private LocalDate dob;

    private String status;

    private DetailKaryawanUpdateRequest detailKaryawan;
}
