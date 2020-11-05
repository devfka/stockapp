package com.ie.stockapp.repository;

import com.ie.stockapp.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findByStockName(@Param("stockname") String stockname);
}
