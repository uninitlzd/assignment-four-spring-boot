package com.alfredoeka.assignmentfour.controller;

import com.alfredoeka.assignmentfour.model.APIResponse;
import com.alfredoeka.assignmentfour.model.dto.request.RekeningCreateRequest;
import com.alfredoeka.assignmentfour.model.dto.request.RekeningUpdateRequest;
import com.alfredoeka.assignmentfour.model.dto.response.RekeningResponse;
import com.alfredoeka.assignmentfour.service.RekeningService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/v1/idstar/rekening")
public class RekeningController {

    @Autowired
    private RekeningService service;

    @GetMapping("/list")
    public APIResponse<Page<RekeningResponse>> findAll(
            @RequestParam(defaultValue = "0") final int page,
            @RequestParam(defaultValue = "5") final int size
    ) {
        return APIResponse.<Page<RekeningResponse>>builder()
                .data(service.findAll(PageRequest.of(page, size)))
                .status("sukses")
                .build();
    }

    @GetMapping("/{id}")
    public APIResponse<Optional<RekeningResponse>> findById(@PathVariable Long id) {
        Optional<RekeningResponse> rekening = Optional.ofNullable(
                service.findById(id).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                )
        );

        return APIResponse.<Optional<RekeningResponse>>builder()
                .data(rekening)
                .status("sukses")
                .build();
    }

    // create a rekening
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping("/save")
    public APIResponse<RekeningResponse> create(@Valid @RequestBody RekeningCreateRequest request) {
        return APIResponse.<RekeningResponse>builder().data(service.save(request))
                .code(HttpStatus.CREATED.value())
                .status("sukses")
                .build();
    }

    // update a rekening
    @PutMapping("/update")
    public APIResponse<RekeningResponse> update(@RequestBody RekeningUpdateRequest request) {
        return APIResponse.<RekeningResponse>builder().data(service.update(request))
                .code(HttpStatus.OK.value())
                .status("sukses")
                .build();
    }

    // delete a rekening
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public APIResponse<String> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

}