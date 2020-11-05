package com.ie.stockapp.parser;

public class ParserFactory {

    public static Parser getParser(Parser parser) {
        if (parser instanceof StockSearchParser) {
            return new StockSearchParser();
        }
        if (parser instanceof StockTimeSeriesParser) {
            return new StockTimeSeriesParser();
        }
        return new StockTimeSeriesParser();
    }
}
