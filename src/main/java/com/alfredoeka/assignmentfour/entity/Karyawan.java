package com.alfredoeka.assignmentfour.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@SQLDelete(sql = "UPDATE karyawan SET deleted_date = NOW() where id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "deleted_date is null")
public class Karyawan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;

    private String alamat;

    private LocalDate dob;

    private String status;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant updatedDate;

    private Instant deletedDate;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "detail_karyawan_id", referencedColumnName = "id")
    private DetailKaryawan detailKaryawan;
}
