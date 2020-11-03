package com.ie.stockapp.repository;

import com.ie.stockapp.model.entity.UserStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStockRepository extends JpaRepository<UserStock, Long> {

    UserStock findAllByUserUsernameAndStock_StockId(String username, Long stockId);
}
