package com.ie.stockapp.model.dto;


import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long userId;

    private String username;

    private List<StockDTO> stockDTOS;

    // TODO: 10/30/2020 - add some validation to the fields
}
