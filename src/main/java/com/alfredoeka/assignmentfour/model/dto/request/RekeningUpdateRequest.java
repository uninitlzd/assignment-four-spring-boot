package com.alfredoeka.assignmentfour.model.dto.request;

import com.alfredoeka.assignmentfour.entity.Karyawan;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RekeningUpdateRequest {
    @NotNull
    private Long id;

    private String nama;

    private String jenis;

    private String rekening;

    private String alamat;

    @NotNull
    private Karyawan karyawan;
}
