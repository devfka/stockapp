package com.ie.stockapp.service;

import com.ie.stockapp.model.dto.StockDTO;
import com.ie.stockapp.model.dto.StockSearchDTO;
import com.ie.stockapp.parser.Parser;
import com.ie.stockapp.parser.ParserFactory;
import com.ie.stockapp.parser.StockSearchParser;
import com.ie.stockapp.parser.StockTimeSeriesParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DataStreamService {

    @Autowired
    private StockService stockService;

    @Scheduled(cron = "0 0/2 * 1/1 * ? *")
    public void streamTimeSeriesAPI() throws IOException {

        String stockName = "IBM"; //todo -> this shouldn't constant normally, basically we can fetch all stocks from DB and make the api call for each.

        Parser parser = ParserFactory.getParser(new StockTimeSeriesParser());

        StringBuffer content = parser.getResponseContent(stockName);

        List<StockDTO> stockDTOList = parser.parseResponseForTimeseries(content);

        stockService.updateStock(stockDTOList.get(0));
    }

    public List<StockSearchDTO> streamSearchStockAPI(String stockname) throws IOException {

        Parser parser = ParserFactory.getParser(new StockSearchParser());

        StringBuffer content = parser.getResponseContent(stockname);

        List<StockSearchDTO> stockDTOList = parser.parseResponseForSearch(content);

        return stockDTOList;
    }

}
