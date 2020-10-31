package com.ie.stockapp.service;

import com.ie.stockapp.mapper.StockMapper;
import com.ie.stockapp.model.dto.StockDTO;
import com.ie.stockapp.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StockService {

    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockDTO> getAllStocks() {
        return StockMapper.stocksToStockDTOs(this.stockRepository.findAll());
    }

    // TODO: 10/30/2020 - exception handling
}
