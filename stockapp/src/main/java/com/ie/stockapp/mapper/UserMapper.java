package com.ie.stockapp.mapper;

import com.ie.stockapp.model.dto.StockDTO;
import com.ie.stockapp.model.dto.UserDTO;
import com.ie.stockapp.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserMapper {

    public static UserDTO userToUserDTO(User user) {

        return UserDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .stockDTOS(extractUserStocksFromUser(user)).build();
    }

    public static List<StockDTO> extractUserStocksFromUser(User user) {

        List<StockDTO> stockDTOS = new ArrayList<>();

        user.getUserStocks().forEach(userStock ->
        {
            stockDTOS.add(StockMapper.stockToStockDTO(userStock.getStock()));
        });

        return stockDTOS;
    }

    public static List<UserDTO> usersToUsersDTO(List<User> users) {
        return users.stream().filter(Objects::nonNull)
                .map(UserMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

}
