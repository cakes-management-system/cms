package com.koltsov.cms.starter.service;

import com.koltsov.cms.starter.data.IdAble;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestModel implements IdAble<Long> {
    private Long id;
    private String field;
}
