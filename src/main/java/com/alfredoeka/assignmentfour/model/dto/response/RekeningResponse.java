package com.alfredoeka.assignmentfour.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RekeningResponse {
    private Long id;

    private String jenis;

    private String nama;

    private String rekening;

    private Instant createdDate;

    private Instant updatedDate;

    private Instant deletedDate;

    private Karyawan karyawan;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Karyawan {
        private Long id;
        private String nama;
    }
}
