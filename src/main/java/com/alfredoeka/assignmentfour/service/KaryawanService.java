package com.alfredoeka.assignmentfour.service;

import com.alfredoeka.assignmentfour.entity.Karyawan;
import com.alfredoeka.assignmentfour.model.dto.request.KaryawanUpdateRequest;
import com.alfredoeka.assignmentfour.repository.KaryawanRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class KaryawanService {

    @Autowired
    private KaryawanRepository karyawanRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    public Page<Karyawan> findAll(PageRequest pageRequest) {
        return karyawanRepository.findAll(pageRequest);
    }

    public Optional<Karyawan> findById(Long id) {
        return karyawanRepository.findById(id);
    }

    @Transactional
    public Karyawan save(Karyawan karyawan) {

        return karyawanRepository.save(karyawan);
    }

    @Transactional
    public Karyawan update(KaryawanUpdateRequest request) {
        if (!request.getId().equals(request.getDetailKaryawan().getId())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id and detailKaryawan.id have to be identical");

        Karyawan karyawan = modelMapper.map(request, Karyawan.class);
        return karyawanRepository.save(karyawan);
    }

    public void deleteById(Long id) {
        karyawanRepository.deleteById(id);
    }
}
