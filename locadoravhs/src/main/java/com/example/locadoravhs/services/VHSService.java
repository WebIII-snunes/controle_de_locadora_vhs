package com.example.locadoravhs.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.locadoravhs.models.VHS;
import com.example.locadoravhs.repositories.VHSRepository;

@Service
public class VHSService {
    @Autowired
    VHSRepository vhsRepository;
    
    public List<VHS> findAll() {
        return vhsRepository.findAll();
    }

    public VHS save(VHS vhs) {

        vhs.setRegistrationDate(LocalDate.now());
        

        return vhsRepository.save(vhs);
    }

    public VHS findById(Long id) {
        
        return vhsRepository.findById(id).orElse(null);

    }
    public void deleteById(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id inválido");
        }

        VHS existingVHS = vhsRepository.findById(id).orElseThrow(() -> new RuntimeException("VHS não encontrado"));
    
        vhsRepository.deleteById(existingVHS.getId());
        
    }

     public VHS update(VHS updatedVHS) {
        
        if (updatedVHS == null || updatedVHS.getId() < 0) {
            throw new IllegalArgumentException("VHS inválido");
        }

        VHS existingVHS = vhsRepository.findById(updatedVHS.getId()).orElseThrow(() -> new RuntimeException("VHS não encontrado"));
        
        existingVHS.setTitle(updatedVHS.getTitle());
        existingVHS.setImageUrl(updatedVHS.getImageUrl());
        existingVHS.setDirector(updatedVHS.getDirector());
        existingVHS.setCategory(updatedVHS.getCategory());
        existingVHS.setRegistrationDate(updatedVHS.getRegistrationDate());
        existingVHS.setStatus(updatedVHS.getStatus());

        return vhsRepository.save(existingVHS);
    }
}

