package com.example.tutoria002.controllers;

import com.example.tutoria002.models.Docente;
import com.example.tutoria002.services.DocenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/docentes")
@Tag(name = "Docente Controller", description = "API REST para la gestión de Docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping
    @Operation(summary = "Obtener todos los docentes creados")
    public List<Docente> getAllDocentes() {
        return docenteService.getAll();
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo docente")
    public ResponseEntity<Docente> createDocente(@RequestBody Docente docente) {
        Docente nuevoDocente = docenteService.create(docente);
        return new ResponseEntity<>(nuevoDocente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un docente existente por su ID")
    public ResponseEntity<Docente> updateDocente(@PathVariable UUID id, @RequestBody Docente docente) {
        Docente docenteActualizado = docenteService.update(id, docente);
        return ResponseEntity.ok(docenteActualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un docente por su ID")
    public ResponseEntity<Void> deleteDocente(@PathVariable UUID id) {
        docenteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}