package com.alfredoeka.assignmentfour.repository;

import com.alfredoeka.assignmentfour.entity.Rekening;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RekeningRepository extends JpaRepository<Rekening, Long> {
    @Query("select a from Rekening a")
    Page<Rekening> findAll(final Pageable pageable);
}
