package com.koltsov.cms.starter.web.dto.cake;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CakeDto {
    private Long id;
    private String name;
}
