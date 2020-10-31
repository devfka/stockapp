package com.ie.stockapp.model.dto;


import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    private Long stockId;

    private String stockName;

    private BigDecimal currentPrice;

    private BigDecimal highestPriceInLast5Min;

    private BigDecimal lowestPriceInLast5Min;

    private Long volume;

    // TODO: 10/30/2020 - add some validation to the fields
}
