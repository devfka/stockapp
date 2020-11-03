package com.ie.stockapp.controller;

import com.ie.stockapp.enums.ProcessType;
import com.ie.stockapp.exception.ResourceNotFoundException;
import com.ie.stockapp.mapper.StockMapper;
import com.ie.stockapp.model.dto.StockDTO;
import com.ie.stockapp.service.StockService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @ApiOperation(value = "Gets All stocks available", response = List.class)
    @GetMapping(value = "/stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockDTO>> getAllStocks() {

        List<StockDTO> stockDTOS = StockMapper.stocksToStockDTOs(this.stockService.getAllStocks());

        return new ResponseEntity<>(stockDTOS, HttpStatus.OK);

    }

    @ApiOperation(value = "Follow or Unfollow a stock", response = List.class)
    @PostMapping(value = "/followUnFollowStock/{username}/{stockId}/{processType}")
    public ResponseEntity<?> followUnFollowStock(@PathVariable("username") String username, @PathVariable("stockId") Long stockId,
                                                 @PathVariable("processType") String processType) throws ResourceNotFoundException {

        this.stockService.followUnFollowStock(username, stockId, ProcessType.valueOf(processType.toUpperCase()));

        return new ResponseEntity<>(HttpStatus.CREATED);

        // TODO: 11/3/2020 burada, illegal argument exception dondur. enum case senstive degil
    }

    // TODO: 11/2/2020 - make validation of variables
    // TODO: 11/3/2020 - user'r direk spring security'den cekebiliyor muyuz ? parametre gecmeyiz boyleikle ?
    // TODO: 11/3/2020 - follow vs unfollow'u input dto haline cevir, path variable yerine
    // TODO: 11/3/2020 - logger ekle
    // TODO: 11/3/2020 follow ve unfollow endpoint parametresini degistirebilirsin stockid -> stockname
    // TODO: 11/3/2020, user ayni stock'u takip ettiginde hata dondur!

}
