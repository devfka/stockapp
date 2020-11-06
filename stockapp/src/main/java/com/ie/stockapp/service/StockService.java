package com.ie.stockapp.service;

import com.ie.stockapp.enums.ActiveIndicator;
import com.ie.stockapp.enums.ProcessType;
import com.ie.stockapp.exception.ResourceNotFoundException;
import com.ie.stockapp.model.dto.StockDTO;
import com.ie.stockapp.model.dto.StockSearchDTO;
import com.ie.stockapp.model.entity.Stock;
import com.ie.stockapp.model.entity.User;
import com.ie.stockapp.model.entity.UserStock;
import com.ie.stockapp.repository.StockRepository;
import com.ie.stockapp.repository.UserStockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserStockRepository userStockRepository;

    @Autowired
    private DataStreamService dataStreamService;

    @Autowired
    private UserService userService;

    public List<Stock> getAllStocks() {
        return this.stockRepository.findAll();
    }

    public Optional<Stock> getStock(Long stockId) {
        return this.stockRepository.findById(stockId);
    }

    @Transactional
    public void updateStock(List<StockDTO> stockDTO) {

        List<Stock> stocksToBeSaved = new ArrayList<>();

        stockDTO.forEach(x ->
        {
            Stock stock = this.stockRepository.findByStockName(x.getStockName());
            stock.setCurrentPrice(x.getCurrentPrice());
            stock.setHighestPriceInLast5Min(x.getHighestPriceInLast5Min());
            stock.setLowestPriceInLast5Min(x.getLowestPriceInLast5Min());
            stock.setVolume(x.getVolume());
            stocksToBeSaved.add(stock);
        });


        this.stockRepository.saveAll(stocksToBeSaved);
    }

    @Transactional
    public void followUnFollowStock(String username, String stockname, ProcessType processType) throws ResourceNotFoundException, DataIntegrityViolationException {

        User user = userService.getUserByUserName(username);
        Stock stock = this.stockRepository.findByStockName(stockname);
        UserStock userStock = null;

        if (user == null || stock == null) {
            throw new ResourceNotFoundException("Could not be found");
        }

        if (processType.equals(ProcessType.FOLLOW)) {
            userStock = UserStock.builder().stock(stock).user(user).activeIndicator(ActiveIndicator.ACTIVE.getValue()).build();
        } else {
            userStock = this.userStockRepository.findAllByUserUsernameAndStock_StockId(username, stock.getStockId());
            userStock.setActiveIndicator(ActiveIndicator.INACTIVE.getValue());
        }

        this.userStockRepository.save(userStock);
    }

    public List<StockSearchDTO> searchStock(String stockName) throws IOException {

        return this.dataStreamService.streamSearchStockAPI(stockName);
    }

}
