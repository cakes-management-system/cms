package com.koltsov.cms.starter.mapper;

public interface GenericMapper<E, D, C> {
    D toDto(E entity);

    E toEntity(D dto);

    E toNewEntity(C createDto);
}
