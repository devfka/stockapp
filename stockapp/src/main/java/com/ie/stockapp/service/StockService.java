package com.ie.stockapp.service;

import com.ie.stockapp.enums.ActiveIndicator;
import com.ie.stockapp.enums.ProcessType;
import com.ie.stockapp.exception.ResourceNotFoundException;
import com.ie.stockapp.model.entity.Stock;
import com.ie.stockapp.model.entity.User;
import com.ie.stockapp.model.entity.UserStock;
import com.ie.stockapp.repository.StockRepository;
import com.ie.stockapp.repository.UserStockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StockService {

    private StockRepository stockRepository;

    private UserStockRepository userStockRepository;

    private UserService userService;

    public StockService(StockRepository stockRepository, UserStockRepository userStockRepository, UserService userService) {
        this.stockRepository = stockRepository;
        this.userStockRepository = userStockRepository;
        this.userService = userService;
    }

    public List<Stock> getAllStocks() {
        return this.stockRepository.findAll();
    }

    public Optional<Stock> getStock(Long stockId) {
        return this.stockRepository.findById(stockId);
    }

    @Transactional
    public void followStock(String username, Long stockId) throws ResourceNotFoundException, DataIntegrityViolationException {

        User user = userService.getUserByUserName(username);
        Optional<Stock> stock = getStock(stockId);
        UserStock userStock = null;

        if (!stock.isPresent()) {
            throw new ResourceNotFoundException(String.valueOf(stockId));
        }

        userStock = UserStock.builder().stock(stock.get()).user(user).build();

        this.userStockRepository.save(userStock);
    }

    @Transactional
    public void followUnFollowStock(String username, Long stockId, ProcessType processType) throws ResourceNotFoundException, DataIntegrityViolationException {

        User user = userService.getUserByUserName(username);
        Optional<Stock> stock = getStock(stockId);
        UserStock userStock = null;

        if (!stock.isPresent()) {
            throw new ResourceNotFoundException(String.valueOf(stockId));
        }

        if (processType.equals(ProcessType.FOLLOW)) {
            userStock = UserStock.builder().stock(stock.get()).user(user).activeIndicator(ActiveIndicator.ACTIVE.getValue()).build();
        } else {
            userStock = this.userStockRepository.findAllByUserUsernameAndStock_StockId(username, stockId);
            userStock.setActiveIndicator(ActiveIndicator.INACTIVE.getValue());
        }

        this.userStockRepository.save(userStock);
    }

    // TODO: 10/30/2020 - exception handling
}
