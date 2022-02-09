package com.koltsov.cms.starter.service;

import com.koltsov.cms.starter.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultCrudServiceTest extends DefaultCrudService<TestModel, Long> {

    private static final Long ID = 1L;
    private static final Long UNKNOWN_ID = 2L;

    @Mock
    private JpaRepository<TestModel, Long> repository;

    @InjectMocks
    private DefaultCrudServiceTest defaultCrudService;

    @Override
    protected void updateFields(TestModel to, TestModel from) {
        // ignore
    }

    @Test
    void testGetAll() {
        List<TestModel> expected = List.of(testModel());
        when(repository.findAll()).thenReturn(expected);

        List<TestModel> actual = defaultCrudService.getAll();

        assertIterableEquals(expected, actual);
    }

    @Test
    void testGetPage() {
        Page<TestModel> expected = new PageImpl<>(List.of(testModel()));
        when(repository.findAll(any(Pageable.class))).thenReturn(expected);

        Page<TestModel> actual = defaultCrudService.getPage(Pageable.unpaged());

        assertIterableEquals(expected, actual);
    }

    @Test
    void testGetById() {
        when(repository.findById(UNKNOWN_ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> defaultCrudService.getById(UNKNOWN_ID));
    }

    @Test
    void testGetByIdNotFound() {
        TestModel expected = testModel();
        when(repository.findById(ID)).thenReturn(Optional.of(expected));

        TestModel actual = defaultCrudService.getById(ID);

        assertEquals(expected, actual);
    }

    @Test
    void testCreate() {
        TestModel testModel = testModel();
        when(repository.save(testModel)).thenAnswer(invocation -> invocation.getArgument(0));

        TestModel actual = defaultCrudService.create(testModel);

        assertEquals(testModel, actual);
    }

    @Test
    void testUpdate() {
        TestModel testModel = testModel();
        when(repository.findById(ID)).thenReturn(Optional.of(testModel));
        when(repository.save(testModel)).thenAnswer(invocation -> invocation.getArgument(0));

        TestModel actual = defaultCrudService.update(ID, testModel);

        assertEquals(testModel, actual);
    }

    @Test
    void testUpdateNotFound() {
        TestModel testModel = testModel();
        when(repository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> defaultCrudService.update(ID, testModel));
    }

    @Test
    void testUpdateWrongId() {
        TestModel testModel = testModel();

        assertThrows(ResponseStatusException.class, () -> defaultCrudService.update(UNKNOWN_ID, testModel));
    }

    @Test
    void testDelete() {
        TestModel testModel = testModel();
        when(repository.findById(ID)).thenReturn(Optional.of(testModel));
        doNothing().when(repository).delete(testModel);

        assertDoesNotThrow(() -> defaultCrudService.delete(ID));
    }

    @Test
    void testDeleteNotFound() {
        when(repository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> defaultCrudService.delete(ID));
    }

    private TestModel testModel() {
        return new TestModel(ID, "some-field");
    }
}
