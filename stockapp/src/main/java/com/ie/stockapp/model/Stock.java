package com.ie.stockapp.model;

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
@Table(name= "stock")
public class Stock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockId", length = 60)
    private Long stockId;

    @Column(name = "stockName", length = 100, nullable = false)
    private String stockName;

    @Column(name = "currentPrice")
    private BigDecimal currentPrice;

    @Column(name = "currentPrice")
    private BigDecimal highestPriceIn5Min;

    @Column(name = "currentPrice")
    private BigDecimal lowestPriceIn5Min;

    @Column(name = "volume")
    private Long volume;

    @OneToMany(mappedBy = "stock", fetch = FetchType.EAGER)
    private List<UserStock> userStocks;

}
