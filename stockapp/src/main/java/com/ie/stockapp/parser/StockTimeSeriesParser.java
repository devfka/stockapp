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

public class StockTimeSeriesParser implements Parser {

    @Value("${apikey}")
    private String apiKey;

    @Override
    public StringBuffer getResponseContent(String stockName) throws IOException {
        URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + stockName + "&interval=1min&apikey=" + apiKey);
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
        return null;
    }

    @Override
    public List<StockDTO> parseResponseForTimeseries(StringBuffer content) {
        List<StockDTO> stockDTOList = new ArrayList<>();
        JsonObject obj = new JsonParser().parse(content.toString()).getAsJsonObject();
        obj.getAsJsonObject("Time Series (1min)").entrySet().iterator().forEachRemaining(x -> {

            StockDTO stockDTO = StockDTO.builder()
                    .currentPrice(x.getValue().getAsJsonObject().get("4. close").getAsBigDecimal())
                    .lowestPriceInLast5Min(x.getValue().getAsJsonObject().get("3. low").getAsBigDecimal())
                    .highestPriceInLast5Min(x.getValue().getAsJsonObject().get("2. high").getAsBigDecimal())
                    .volume(x.getValue().getAsJsonObject().get("5. volume").getAsLong())
                    .stockName(obj.getAsJsonObject("Meta Data").get("2. Symbol").getAsString()).build();
            stockDTOList.add(stockDTO);
        });

        return stockDTOList;
    }

}
