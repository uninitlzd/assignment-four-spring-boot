package com.alfredoeka.assignmentfour.controller;

import com.alfredoeka.assignmentfour.entity.KaryawanTraining;
import com.alfredoeka.assignmentfour.model.APIResponse;
import com.alfredoeka.assignmentfour.model.dto.request.KaryawanTrainingCreateRequest;
import com.alfredoeka.assignmentfour.model.dto.request.KaryawanTrainingUpdateRequest;
import com.alfredoeka.assignmentfour.service.KaryawanTrainingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/v1/idstar/karyawan-training")
public class KaryawanTrainingController {

    @Autowired
    private KaryawanTrainingService service;

    @GetMapping("/list")
    public APIResponse<Page<KaryawanTraining>> findAll(
            @RequestParam(defaultValue = "0") final int page,
            @RequestParam(defaultValue = "5") final int size
    ) {
        return APIResponse.<Page<KaryawanTraining>>builder()
                .data(service.findAll(PageRequest.of(page, size)))
                .build();
    }

    @GetMapping("/{id}")
    public APIResponse<Optional<KaryawanTraining>> findById(@PathVariable Long id) {
        Optional<KaryawanTraining> karyawanTraining = Optional.ofNullable(
                service.findById(id).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                )
        );

        return APIResponse.<Optional<KaryawanTraining>>builder()
                .data(karyawanTraining)
                .build();
    }

    // create a karyawan
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping("/save")
    public APIResponse<KaryawanTraining> create(@Valid @RequestBody KaryawanTrainingCreateRequest karyawanTraining) {
        return APIResponse.<KaryawanTraining>builder().data(service.save(karyawanTraining))
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .build();
    }

    // update a karyawan
    @PutMapping("/update")
    public APIResponse<KaryawanTraining> update(@Valid @RequestBody KaryawanTrainingUpdateRequest request) {
        return APIResponse.<KaryawanTraining>builder().data(service.update(request))
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    // delete a karyawan training
    @ResponseStatus(HttpStatus.OK) // 204
    @DeleteMapping("/{id}")
    public APIResponse<String> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return APIResponse.<String>builder().data("Sukses")
                .code(HttpStatus.OK.value())
                .status("sukses")
                .build();
    }

}