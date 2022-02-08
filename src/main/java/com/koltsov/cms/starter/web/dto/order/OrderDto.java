package com.koltsov.cms.starter.web.dto.order;

import com.koltsov.cms.starter.web.dto.cake.CakeDto;
import com.koltsov.cms.starter.web.dto.user.UserDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;
    private CakeDto cake;
    private UserDto user;
    private LocalDateTime deliveryDate;
}
