package com.example.locadoravhs.services;

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
}

