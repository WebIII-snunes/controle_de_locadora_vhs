package com.example.locadoravhs.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;

@Entity
@Data
public class VHS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    
    @Lob
    private String imageUrl;  //Caminho ou URL da imagem (opcional)
    private String director;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    private LocalDate registrationDate;
    
    @Enumerated(EnumType.STRING)
    private TapeStatus status;
}
