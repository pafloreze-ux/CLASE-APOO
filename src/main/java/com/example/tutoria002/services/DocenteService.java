package com.example.tutoria002.services;

import com.example.tutoria002.models.Docente;
import com.example.tutoria002.repositories.IDocenteRepository;
import com.example.tutoria002.exceptions.ResourceNotFoundException;
import com.example.tutoria002.exceptions.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class DocenteService {

    @Autowired
    private IDocenteRepository docenteRepository;

    
    public List<Docente> getAll() {
        return docenteRepository.findAll();
    }

    

    public Docente create(Docente docente) {
        
        docenteRepository.findByTipoDocumentoAndNumeroDocumento(docente.getTipoDocumento(), docente.getNumeroDocumento())
                .ifPresent(d -> {
                    throw new BadRequestException("Ya existe un docente registrado con ese Tipo y Número de documento.");
                });
        return docenteRepository.save(docente);
    }
    
    public Docente update(UUID id, Docente docenteDetails) {
        return docenteRepository.findById(id)
                .map(docente -> {
                    
                    docente.setTipoDocumento(docenteDetails.getTipoDocumento());
                    docente.setNumeroDocumento(docenteDetails.getNumeroDocumento());
                    docente.setNombres(docenteDetails.getNombres());
                    docente.setApellidos(docenteDetails.getApellidos());
                    docente.setCorreo(docenteDetails.getCorreo());
                    docente.setTelefono(docenteDetails.getTelefono());
                    return docenteRepository.save(docente);
                })
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar. Docente no encontrado con el ID: " + id));
    }

    
    public void delete(UUID id) {
        if (!docenteRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Docente no encontrado con el ID: " + id);
        }
        docenteRepository.deleteById(id);
    }
}