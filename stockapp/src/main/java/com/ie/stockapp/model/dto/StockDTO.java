package com.ie.stockapp.model.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    private Long stockId;

    @NotEmpty
    private String stockName;

    private BigDecimal currentPrice;

    private BigDecimal highestPriceInLast5Min;

    private BigDecimal lowestPriceInLast5Min;

    private Long volume;

}
