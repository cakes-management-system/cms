package com.koltsov.cms.starter.helper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TestModelHelper {

    public static final Long ID = 1L;
    public static final Long UNKNOWN_ID = 2L;

    public static TestModel testModel() {
        return new TestModel(ID, "some-field");
    }

    public static TestModelDto testModelDto() {
        return new TestModelDto(ID, "some-field");
    }

    public static TestModelCreateDto testModelCreateDto() {
        return new TestModelCreateDto("some-field");
    }
}
