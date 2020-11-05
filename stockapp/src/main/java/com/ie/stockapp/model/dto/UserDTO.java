package com.ie.stockapp.model.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long userId;

    @NotEmpty
    private String username;

    private List<StockDTO> stockDTOS;


}
