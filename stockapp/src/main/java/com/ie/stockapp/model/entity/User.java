package com.ie.stockapp.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "User")
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "USERID", length = 60)
    private Long userId;

    @Column(name = "USERNAME", length = 100, nullable = false)
    private String username;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserStock> userStocks;

    //can keep all other user details here or another detail table e.g lastLoginDate etc..
}
