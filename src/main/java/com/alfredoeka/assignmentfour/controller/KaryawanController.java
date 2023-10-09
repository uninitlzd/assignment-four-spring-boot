package com.alfredoeka.assignmentfour.controller;

import com.alfredoeka.assignmentfour.entity.Karyawan;
import com.alfredoeka.assignmentfour.model.APIResponse;
import com.alfredoeka.assignmentfour.model.dto.request.KaryawanUpdateRequest;
import com.alfredoeka.assignmentfour.service.KaryawanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/v1/idstar/karyawan")
public class KaryawanController {

    @Autowired
    private KaryawanService service;

    @GetMapping("/list")
    public APIResponse<Page<Karyawan>> findAll(
            @RequestParam(defaultValue = "0") final int page,
            @RequestParam(defaultValue = "5") final int size
    ) {
        return APIResponse.<Page<Karyawan>>builder()
                .data(service.findAll(PageRequest.of(page, size)))
                .build();
    }

    @GetMapping("/{id}")
    public APIResponse<Optional<Karyawan>> findById(@PathVariable Long id) {
        Optional<Karyawan> karyawan = Optional.ofNullable(
                service.findById(id).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                )
        );

        return APIResponse.<Optional<Karyawan>>builder()
                .data(karyawan)
                .build();
    }

    // create a karyawan
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping("/save")
    public APIResponse<Karyawan> create(@RequestBody Karyawan karyawan) {
        return APIResponse.<Karyawan>builder().data(service.save(karyawan))
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .build();
    }

    // update a karyawan
    @PutMapping("/update")
    public APIResponse<Karyawan> update(@Valid @RequestBody KaryawanUpdateRequest request) {
        return APIResponse.<Karyawan>builder().data(service.update(request))
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    // delete a karyawan
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