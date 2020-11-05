package com.ie.stockapp.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ie.stockapp.model.dto.StockDTO;
import com.ie.stockapp.model.dto.StockSearchDTO;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StockSearchParser implements Parser {

    @Value("${apikey}")
    private String apiKey;

    @Override
    public StringBuffer getResponseContent(String stockName) throws IOException {

        URL url = new URL(" https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=" + stockName + "&apikey=" + apiKey);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        return content;
    }

    @Override
    public List<StockSearchDTO> parseResponseForSearch(StringBuffer content) {
        List<StockSearchDTO> stockSearchDTOList = new ArrayList<>();
        JsonObject obj = new JsonParser().parse(content.toString()).getAsJsonObject();

        obj.get("bestMatches").getAsJsonArray().forEach(y -> {
            StockSearchDTO stockSearchDTO = StockSearchDTO.builder()
                    .symbol(y.getAsJsonObject().get("1. symbol").getAsString())
                    .stockName(y.getAsJsonObject().get("2. name").getAsString())
                    .type(y.getAsJsonObject().get("3. type").getAsString())
                    .region(y.getAsJsonObject().get("4. region").getAsString())
                    .marketOpen(y.getAsJsonObject().get("5. marketOpen").getAsString())
                    .marketClose(y.getAsJsonObject().get("6. marketClose").getAsString())
                    .currency(y.getAsJsonObject().get("8. currency").getAsString()).build();
            stockSearchDTOList.add(stockSearchDTO);
        });

        return stockSearchDTOList;
    }

    @Override
    public List<StockDTO> parseResponseForTimeseries(StringBuffer content) {
        return null;
    }
}
