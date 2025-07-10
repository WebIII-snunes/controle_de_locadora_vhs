package com.example.locadoravhs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.locadoravhs.models.VHS;

@Repository
public interface VHSRepository extends JpaRepository<VHS, Long> {}