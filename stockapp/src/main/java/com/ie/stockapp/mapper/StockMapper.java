package com.ie.stockapp.mapper;

import com.ie.stockapp.model.dto.StockDTO;
import com.ie.stockapp.model.entity.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class StockMapper {

    public static StockDTO stockToStockDTO(Stock stock) {
        return StockDTO.builder()
                .stockId(stock.getStockId())
                .stockName(stock.getStockName())
                .currentPrice(stock.getCurrentPrice())
                .highestPriceInLast5Min(stock.getHighestPriceInLast5Min())
                .lowestPriceInLast5Min(stock.getLowestPriceInLast5Min())
                .volume(stock.getVolume())
                .build();
    }

    public static List<StockDTO> stocksToStockDTOs(List<Stock> stocks) {
        return stocks.stream().filter(Objects::nonNull)
                .map(StockMapper::stockToStockDTO)
                .collect(Collectors.toList());
    }

}
