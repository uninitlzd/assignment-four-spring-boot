package com.alfredoeka.assignmentfour.service;

import com.alfredoeka.assignmentfour.entity.Karyawan;
import com.alfredoeka.assignmentfour.entity.KaryawanTraining;
import com.alfredoeka.assignmentfour.entity.Training;
import com.alfredoeka.assignmentfour.model.dto.request.KaryawanTrainingCreateRequest;
import com.alfredoeka.assignmentfour.model.dto.request.KaryawanTrainingUpdateRequest;
import com.alfredoeka.assignmentfour.repository.KaryawanRepository;
import com.alfredoeka.assignmentfour.repository.KaryawanTrainingRepository;
import com.alfredoeka.assignmentfour.repository.TrainingRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KaryawanTrainingService {

    @Autowired
    private KaryawanTrainingRepository karyawanTrainingRepository;

    @Autowired
    private KaryawanRepository karyawanRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    public Page<KaryawanTraining> findAll(PageRequest pageRequest) {
        return karyawanTrainingRepository.findAll(pageRequest);
    }

    public Optional<KaryawanTraining> findById(Long id) {
        return karyawanTrainingRepository.findById(id);
    }

    @Transactional
    public KaryawanTraining save(KaryawanTrainingCreateRequest karyawanTrainingRequest) {
        KaryawanTraining newData = new KaryawanTraining();
        Karyawan karyawan = karyawanRepository.findById(karyawanTrainingRequest.getKaryawan().getId())
                .orElseThrow(() -> new EntityNotFoundException("Karyawan not found"));
        Training training = trainingRepository.findById(karyawanTrainingRequest.getTraining().getId())
                .orElseThrow(() -> new EntityNotFoundException("Training not found"));

        newData.setKaryawan(karyawan);
        newData.setTraining(training);
        newData.setTanggal(karyawanTrainingRequest.getTanggal());

        return karyawanTrainingRepository.save(newData);
    }

    @Transactional
    public KaryawanTraining update(KaryawanTrainingUpdateRequest request) {
        KaryawanTraining existing = karyawanTrainingRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("KaryawanTraining not found"));
        Karyawan karyawan = karyawanRepository.findById(request.getKaryawan().getId())
                .orElseThrow(() -> new EntityNotFoundException("Karyawan not found"));
        Training training = trainingRepository.findById(request.getTraining().getId())
                .orElseThrow(() -> new EntityNotFoundException("Training not found"));

        existing.setKaryawan(karyawan);
        existing.setTraining(training);
        existing.setTanggal(request.getTanggal());

        return karyawanTrainingRepository.save(existing);
    }

    public void deleteById(Long id) {
        karyawanTrainingRepository.deleteById(id);
    }
}
