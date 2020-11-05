package com.ie.stockapp.controller;

import com.ie.stockapp.enums.ProcessType;
import com.ie.stockapp.exception.ResourceNotFoundException;
import com.ie.stockapp.mapper.StockMapper;
import com.ie.stockapp.model.dto.StockDTO;
import com.ie.stockapp.model.dto.StockSearchDTO;
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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @ApiOperation(value = "Gets All stocks available", response = List.class)
    @GetMapping(value = "/getAllStocks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockDTO>> getAllStocks() {

        List<StockDTO> stockDTOS = StockMapper.stocksToStockDTOs(this.stockService.getAllStocks());

        return new ResponseEntity<>(stockDTOS, HttpStatus.OK);

    }

    @ApiOperation(value = "Follow or Unfollow a stock", response = List.class)
    @PostMapping(value = "/followUnFollowStock/{stockname}/{processType}")
    public ResponseEntity<?> followUnFollowStock(@PathVariable("stockname") String stockname,
                                                 @PathVariable("processType") String processType, HttpServletRequest request) throws ResourceNotFoundException {

        Principal principal = request.getUserPrincipal(); // get user name fron spring security

        this.stockService.followUnFollowStock(principal.getName(), stockname, ProcessType.valueOf(processType.toUpperCase()));

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @ApiOperation(value = "Search stocks ", response = List.class)
    @GetMapping(value = "/searchStock/{stockname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockSearchDTO>> searchStock(@PathVariable("stockname") String stockname) throws IOException {

        List<StockSearchDTO> stockSearchDTOList = this.stockService.searchStock(stockname);

        return new ResponseEntity<>(stockSearchDTOList, HttpStatus.OK);

    }

}
