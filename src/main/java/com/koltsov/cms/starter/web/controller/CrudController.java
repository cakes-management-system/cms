package com.koltsov.cms.starter.web.controller;

import com.koltsov.cms.starter.mapper.GenericMapper;
import com.koltsov.cms.starter.service.CrudService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public abstract class CrudController<T, ID, D, C> {

    @Autowired
    private CrudService<T, ID> crudService;

    @Autowired
    private GenericMapper<T, D, C> mapper;

    @GetMapping
    public Page<D> getPage(@ParameterObject Pageable pageable) {
        return crudService.getPage(pageable)
                .map(mapper::toDto);
    }

    @GetMapping("{id}")
    public D getById(@PathVariable ID id) {
        T found = crudService.getById(id);
        return mapper.toDto(found);
    }

    @PostMapping
    public D create(@RequestBody C createDto) {
        T t = mapper.toNewEntity(createDto);
        T created = crudService.create(t);
        return mapper.toDto(created);
    }

    @PutMapping("{id}")
    public D update(@PathVariable ID id, @RequestBody D dto) {
        T t = mapper.toEntity(dto);
        T updated = crudService.update(id, t);
        return mapper.toDto(updated);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable ID id) {
        crudService.delete(id);
    }
}
