package com.ie.stockapp.controller;

import com.ie.stockapp.exception.ResourceNotFoundException;
import com.ie.stockapp.model.dto.StockDTO;
import com.ie.stockapp.model.entity.Stock;
import com.ie.stockapp.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class StockControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @InjectMocks
    private StockController stockController;

    @InjectMocks
    private MockHttpServletRequest request;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stockController).build();
    }

    @Test
    public void testGetAllStocks() throws ResourceNotFoundException {
        // Given
        Stock stock = Stock.builder().stockId((long) 1).stockName("IBM").currentPrice(new BigDecimal(123)).highestPriceInLast5Min(new BigDecimal(124)).volume((100L)).build();
        List<Stock> stocks = new ArrayList<>();
        stocks.add(stock);

        // When
        Mockito.when(stockService.getAllStocks())
                .thenReturn(stocks);

        // Then
        ResponseEntity<List<StockDTO>> result = stockController.getAllStocks();

        // Assertions
        assertNotNull(result);
        assertEquals(1, result.getBody().iterator().next().getStockId());
        assertEquals("IBM", result.getBody().iterator().next().getStockName());
        assertEquals(new BigDecimal(124), result.getBody().iterator().next().getHighestPriceInLast5Min());
        assertEquals(new BigDecimal(123), result.getBody().iterator().next().getCurrentPrice());
        assertEquals(100L, result.getBody().iterator().next().getVolume());

        // verification
        verify(stockService).getAllStocks();
    }

}