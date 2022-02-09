package com.koltsov.cms.starter.web.controller;

import com.koltsov.cms.starter.helper.TestModelCreateDto;
import com.koltsov.cms.starter.helper.TestModelDto;
import com.koltsov.cms.starter.mapper.GenericMapper;
import com.koltsov.cms.starter.service.CrudService;
import com.koltsov.cms.starter.helper.TestModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.koltsov.cms.starter.helper.TestModelHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrudControllerTest extends CrudController<TestModel, Long, TestModelDto, TestModelCreateDto> {

    @Mock
    private CrudService<TestModel, Long> crudService;
    @Mock
    private GenericMapper<TestModel, TestModelDto, TestModelCreateDto> mapper;

    @InjectMocks
    private CrudControllerTest crudController;

    @Test
    void testGetPage() {
        Pageable pageable = Pageable.unpaged();
        TestModel testModel = testModel();
        TestModelDto testModelDto = testModelDto();
        Page<TestModel> entityPage = new PageImpl<>(List.of(testModel));
        Page<TestModelDto> expected = new PageImpl<>(List.of(testModelDto));

        when(crudService.getPage(pageable)).thenReturn(entityPage);
        when(mapper.toDto(testModel)).thenReturn(testModelDto);

        Page<TestModelDto> actual = crudController.getPage(pageable);

        assertIterableEquals(expected, actual);
    }

    @Test
    void testGetById() {
        TestModel testModel = testModel();
        TestModelDto testModelDto = testModelDto();

        when(crudService.getById(ID)).thenReturn(testModel);
        when(mapper.toDto(testModel)).thenReturn(testModelDto);

        TestModelDto actual = crudController.getById(ID);

        assertEquals(testModelDto, actual);
    }

    @Test
    void testCreate() {
        TestModel testModel = testModel();
        TestModelDto testModelDto = testModelDto();
        TestModelCreateDto testModelCreateDto = testModelCreateDto();

        when(mapper.toNewEntity(testModelCreateDto)).thenReturn(testModel);
        when(crudService.create(testModel)).thenReturn(testModel);
        when(mapper.toDto(testModel)).thenReturn(testModelDto);

        TestModelDto actual = crudController.create(testModelCreateDto);

        assertEquals(testModelDto, actual);
    }

    @Test
    void testUpdate() {
        TestModel testModel = testModel();
        TestModelDto testModelDto = testModelDto();

        when(mapper.toEntity(testModelDto)).thenReturn(testModel);
        when(crudService.update(ID, testModel)).thenReturn(testModel);
        when(mapper.toDto(testModel)).thenReturn(testModelDto);

        TestModelDto actual = crudController.update(ID, testModelDto);

        assertEquals(testModelDto, actual);
    }

    @Test
    void testDelete() {
        doNothing().when(crudService).delete(ID);

        assertDoesNotThrow(() -> crudController.delete(ID));
    }
}