package com.rtuit.backend.controller;

import com.rtuit.backend.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class CrudController<T, ID> {

    protected final CrudService<T, ID> service;

    @PostMapping
    public T create(@RequestBody T entity) {
        return service.save(entity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        Optional<T> result = service.findById(id);
        return result.map(ResponseEntity::ok).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no entity with id" + id)
        );
    }

    @GetMapping
    public List<T> getAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
        T result = service.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no entity with id" + id)
        );
        service.save(result);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ID id) {
        Optional<T> result = service.findById(id);
        if (result.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody T entity) {
        service.delete(entity);
        return ResponseEntity.noContent().build();
    }
}
