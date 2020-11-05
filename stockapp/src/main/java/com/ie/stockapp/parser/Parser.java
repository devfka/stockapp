package com.ie.stockapp.parser;

import com.ie.stockapp.model.dto.StockDTO;
import com.ie.stockapp.model.dto.StockSearchDTO;

import java.io.IOException;
import java.util.List;

public interface Parser {
    StringBuffer getResponseContent(String stockName) throws IOException;

    public List<StockSearchDTO> parseResponseForSearch(StringBuffer content);

    public List<StockDTO> parseResponseForTimeseries(StringBuffer content);

}
