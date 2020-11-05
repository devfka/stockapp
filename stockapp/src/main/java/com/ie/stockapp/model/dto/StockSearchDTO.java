package com.ie.stockapp.model.dto;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockSearchDTO {

    private String symbol;

    private String stockName;

    private String type;

    private String region;

    private String marketOpen;

    private String marketClose;

    private String currency;

}
