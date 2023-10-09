package com.alfredoeka.assignmentfour.repository;

import com.alfredoeka.assignmentfour.entity.KaryawanTraining;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KaryawanTrainingRepository extends JpaRepository<KaryawanTraining, Long> {
    Page<KaryawanTraining> findAll(final Pageable pageable);
    Optional<KaryawanTraining> findById(Long id);
}
