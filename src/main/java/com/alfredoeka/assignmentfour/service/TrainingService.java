package com.alfredoeka.assignmentfour.service;

import com.alfredoeka.assignmentfour.entity.Training;
import com.alfredoeka.assignmentfour.model.APIResponse;
import com.alfredoeka.assignmentfour.model.dto.request.TrainingRequest;
import com.alfredoeka.assignmentfour.model.dto.request.TrainingUpdateRequest;
import com.alfredoeka.assignmentfour.repository.TrainingRepository;
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
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    public Page<Training> findAll(PageRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Page<Training> result = trainingRepository.findAll(request);
        List<Training> contactResponses = result.getContent().stream()
                .toList();

        return new PageImpl<>(contactResponses, pageable, result.getTotalElements());
    }

    public Optional<Training> findById(Long id) {
        return trainingRepository.findById(id);
    }

    @Transactional
    public Training save(TrainingRequest request) {
        Training training = modelMapper.map(request, Training.class);

        trainingRepository.save(training);

        return modelMapper.map(training, Training.class);
    }

    @Transactional
    public Training update(TrainingUpdateRequest request) {
        trainingRepository.findById(request.getId())
            .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "id not exists")
            );

        Training training = modelMapper.map(request, Training.class);
        return trainingRepository.save(training);
    }

    public APIResponse<String> deleteById(Long id) {
        trainingRepository.deleteById(id);
        return APIResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .data("Sukses")
                .status("sukses")
                .build();
    }
}
