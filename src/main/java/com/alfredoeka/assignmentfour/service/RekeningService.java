package com.alfredoeka.assignmentfour.service;

import com.alfredoeka.assignmentfour.entity.Karyawan;
import com.alfredoeka.assignmentfour.entity.Rekening;
import com.alfredoeka.assignmentfour.model.APIResponse;
import com.alfredoeka.assignmentfour.model.dto.request.RekeningCreateRequest;
import com.alfredoeka.assignmentfour.model.dto.request.RekeningUpdateRequest;
import com.alfredoeka.assignmentfour.model.dto.response.RekeningResponse;
import com.alfredoeka.assignmentfour.repository.KaryawanRepository;
import com.alfredoeka.assignmentfour.repository.RekeningRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RekeningService {

    @Autowired
    private RekeningRepository rekeningRepository;

    @Autowired
    private KaryawanRepository karyawanRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    public Page<RekeningResponse> findAll(PageRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Page<Rekening> result = rekeningRepository.findAll(request);
        List<RekeningResponse> contactResponses = result.getContent().stream()
                .map(this::toRekeningResponse)
                .toList();

        return new PageImpl<>(contactResponses, pageable, result.getTotalElements());
    }

    public Optional<RekeningResponse> findById(Long id) {
        return Optional.ofNullable(toRekeningResponse(rekeningRepository.findById(id)));
    }

    @Transactional
    public RekeningResponse save(RekeningCreateRequest request) {
        Rekening rekening = modelMapper.map(request, Rekening.class);
        Karyawan karyawan = karyawanRepository.findById(request.getKaryawan().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST)
        );

        rekening.setKaryawan(karyawan);
        rekeningRepository.save(rekening);

        return modelMapper.map(rekening, RekeningResponse.class);
    }

    @Transactional
    public RekeningResponse update(RekeningUpdateRequest request) {
        Rekening rekening = modelMapper.map(request, Rekening.class);
        Karyawan karyawan = karyawanRepository.findById(request.getKaryawan().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST)
        );

        rekening.setKaryawan(karyawan);
        rekeningRepository.save(rekening);

        return modelMapper.map(rekening, RekeningResponse.class);
    }

    public APIResponse<String> deleteById(Long id) {
        rekeningRepository.deleteById(id);
        return APIResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .data("Sukses")
                .status("sukses")
                .build();
    }

    private RekeningResponse toRekeningResponse(Optional<Rekening> rekening) {
        return modelMapper.map(rekening.get(), RekeningResponse.class);
    }

    private RekeningResponse toRekeningResponse(Rekening rekening) {
        return modelMapper.map(rekening, RekeningResponse.class);
    }
}
