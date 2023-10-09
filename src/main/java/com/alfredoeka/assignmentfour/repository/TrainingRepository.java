package com.alfredoeka.assignmentfour.repository;

import com.alfredoeka.assignmentfour.entity.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    Page<Training> findAll(final Pageable pageable);
}
