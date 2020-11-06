package com.ie.stockapp.service;

import com.ie.stockapp.model.dto.StockDTO;
import com.ie.stockapp.model.dto.StockSearchDTO;
import com.ie.stockapp.model.entity.Stock;
import com.ie.stockapp.parser.Parser;
import com.ie.stockapp.parser.ParserFactory;
import com.ie.stockapp.parser.StockSearchParser;
import com.ie.stockapp.parser.StockTimeSeriesParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DataStreamService {

    @Autowired
    private StockService stockService;

    @Scheduled(cron = "0 */5 * ? * *")
    public void streamTimeSeriesAPI() {

        List<Stock> stocks = this.stockService.getAllStocks();

        Parser parser = ParserFactory.getParser(new StockTimeSeriesParser());
        List<StockDTO> stockDTOList = new ArrayList<>();

        log.info("Streaming started");

        stocks.forEach(stock -> {
            StringBuffer content = null;
            try {
                content = parser.getResponseContent(stock.getStockName());
            } catch (IOException e) {
                log.error("An error occured during stream: " + stock.getStockName());
            }

            List<StockDTO> stockTimeSeries = parser.parseResponseForTimeseries(content);

            stockDTOList.add(stockTimeSeries.get(0));
        });

        stockService.updateStock(stockDTOList);

    }

    public List<StockSearchDTO> streamSearchStockAPI(String stockname) throws IOException {

        Parser parser = ParserFactory.getParser(new StockSearchParser());

        StringBuffer content = parser.getResponseContent(stockname);

        List<StockSearchDTO> stockDTOList = parser.parseResponseForSearch(content);

        return stockDTOList;
    }

}
