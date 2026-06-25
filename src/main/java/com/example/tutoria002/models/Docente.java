package com.example.tutoria002.models;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "docentes", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tipoDocumento", "numeroDocumento"})
})
@Data 
@NoArgsConstructor
@AllArgsConstructor 
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) 
    private UUID id;

    @Column(nullable = false, length = 10)
    private String tipoDocumento; // Ej: "CC", "CE"

    @Column(nullable = false, length = 20)
    private String numeroDocumento;

    @Column(nullable = false, length = 50)
    private String nombres;

    @Column(nullable = false, length = 50)
    private String apellidos;

    @Column(nullable = false, length = 100)
    private String correo;

    @Column(length = 15)
    private String telefono;
}
