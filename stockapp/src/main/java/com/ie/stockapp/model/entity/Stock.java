package com.ie.stockapp.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "Stock")
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_seq")
    @SequenceGenerator(name = "stock_seq", sequenceName = "stock_seq", allocationSize = 1)
    @Column(name = "STOCKID", length = 60)
    private Long stockId;

    @Column(name = "STOCKNAME", length = 100, nullable = false)
    private String stockName;

    @Column(name = "CURRENTPRICE")
    private BigDecimal currentPrice;

    @Column(name = "HIGHESTPRICEINLAST5MIN")
    private BigDecimal highestPriceInLast5Min;

    @Column(name = "LOWESTPRICEINLAST5MIN")
    private BigDecimal lowestPriceInLast5Min;

    @Column(name = "VOLUME")
    private Long volume;

    @OneToMany(mappedBy = "stock", fetch = FetchType.EAGER)
    private List<UserStock> userStocks;

}
