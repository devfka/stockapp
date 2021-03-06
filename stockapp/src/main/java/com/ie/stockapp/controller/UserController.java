package com.ie.stockapp.controller;

import com.ie.stockapp.mapper.UserMapper;
import com.ie.stockapp.model.dto.UserDTO;
import com.ie.stockapp.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Gets User info - including portfolio ", response = List.class)
    @GetMapping(value = "/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUserDetailsByUserName(@PathVariable() String username) {
        UserDTO userDTO = UserMapper.userToUserDTO(this.userService.getUserByUserName(username));
        return ResponseEntity.ok(userDTO);
    }
}
