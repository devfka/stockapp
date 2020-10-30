package com.ie.stockapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name= "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", length = 60)
    private Long userId;

    @Column(name = "userName", length = 100, nullable = false)
    private String userName;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserStock> userStocks;

    //can keep all other user details here or another detail table e.g lastLoginDate etc..
}

// TODO: 10/30/2020 - password icin field ekle ve hash seklinde tut