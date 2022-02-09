package com.koltsov.cms.starter.web.dto.order;

import com.koltsov.cms.starter.web.dto.cake.CakeDto;
import com.koltsov.cms.starter.web.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private CakeDto cake;
    private UserDto user;
    private LocalDateTime deliveryDate;
}
