package com.ie.stockapp.controller;

import com.ie.stockapp.model.dto.StockDTO;
import com.ie.stockapp.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "stock", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }


    @GetMapping(value = "/getStocks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockDTO>> getRecipes() {
        return new ResponseEntity<>(this.stockService.getAllStocks(), HttpStatus.OK);

    }
}
