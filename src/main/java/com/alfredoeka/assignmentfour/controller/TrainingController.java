package com.alfredoeka.assignmentfour.controller;

import com.alfredoeka.assignmentfour.entity.Training;
import com.alfredoeka.assignmentfour.model.APIResponse;
import com.alfredoeka.assignmentfour.model.dto.request.TrainingRequest;
import com.alfredoeka.assignmentfour.model.dto.request.TrainingUpdateRequest;
import com.alfredoeka.assignmentfour.service.TrainingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/v1/idstar/training")
public class TrainingController {

    @Autowired
    private TrainingService service;

    @GetMapping("/list")
    public APIResponse<Page<Training>> findAll(
            @RequestParam(defaultValue = "0") final int page,
            @RequestParam(defaultValue = "5") final int size
    ) {
        return APIResponse.<Page<Training>>builder()
                .data(service.findAll(PageRequest.of(page, size)))
                .status("sukses")
                .build();
    }

    @GetMapping("/{id}")
    public APIResponse<Optional<Training>> findById(@PathVariable Long id) {
        Optional<Training> training = Optional.ofNullable(
                service.findById(id).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                )
        );

        return APIResponse.<Optional<Training>>builder()
                .data(training)
                .status("sukses")
                .build();
    }

    // create a rekening
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping("/save")
    public APIResponse<Training> create(@Valid @RequestBody TrainingRequest request) {
        return APIResponse.<Training>builder().data(service.save(request))
                .code(HttpStatus.CREATED.value())
                .status("sukses")
                .build();
    }

    // update a rekening
    @PutMapping("/update")
    public APIResponse<Training> update(@Valid @RequestBody TrainingUpdateRequest request) {
        return APIResponse.<Training>builder().data(service.update(request))
                .code(HttpStatus.OK.value())
                .status("sukses")
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public APIResponse<String> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

}