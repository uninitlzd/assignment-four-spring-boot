package com.alfredoeka.assignmentfour.repository;

import com.alfredoeka.assignmentfour.entity.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface KaryawanRepository extends JpaRepository<Karyawan, Long> {
    @Query("select k from Karyawan k join fetch k.detailKaryawan")
    Page<Karyawan> findAll(final Pageable pageable);
    @Query("SELECT k FROM Karyawan k WHERE k.id = ?1")
    Optional<Karyawan> findByIdSelectIdAndName(Long id);
}
