package com.example.tutoria002.repositories;

import com.example.tutoria002.models.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.Optional;

@Repository
public interface IDocenteRepository extends JpaRepository<Docente, UUID> {
    
    Optional<Docente> findByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);
}