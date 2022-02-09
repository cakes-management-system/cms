package com.koltsov.cms.starter.web.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderCreateDto {
    private Long cakeId;
    private Long userId;
    private LocalDateTime deliveryDate;
}
