package com.ie.stockapp.model.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "USERSTOCK")
@Entity
@Where(clause = "activeIndicator = 'Y'")
public class UserStock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userstock_seq")
    @SequenceGenerator(name = "userstock_seq", sequenceName = "userstock_seq", allocationSize = 1)
    @Column(name = "USERSTOCKID")
    private Long userStockId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STOCKID")
    private Stock stock;

    @Column(name = "ACTIVEINDICATOR")
    private String activeIndicator;

}
