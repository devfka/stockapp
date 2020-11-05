package com.ie.stockapp.service;

import com.ie.stockapp.exception.ResourceNotFoundException;
import com.ie.stockapp.model.entity.Stock;
import com.ie.stockapp.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class StockServiceTest {

    @InjectMocks
    StockService stockService;

    @Mock
    StockRepository stockRepository;

    @Test
    public void testGetAllStocks() throws ResourceNotFoundException {
        //Given
        // Given
        Stock stock = Stock.builder().stockId((long) 1).stockName("IBM").currentPrice(new BigDecimal(123)).highestPriceInLast5Min(new BigDecimal(124)).volume((100L)).build();
        List<Stock> stocks = new ArrayList<>();
        stocks.add(stock);

        //When
        Mockito.when(stockRepository.findAll())
                .thenReturn(stocks);

        //Then
        List<Stock> allStocks = stockService
                .getAllStocks();

        //Assertions
        assertNotNull(allStocks);
        assertEquals("IBM", allStocks.get(0).getStockName());
        //and others


        //verification
        verify(stockRepository).findAll();
    }
}