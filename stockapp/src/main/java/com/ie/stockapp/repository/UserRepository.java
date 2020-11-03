package com.ie.stockapp.repository;

import com.ie.stockapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    User findByUsername (@Param("username") String username);

    User findByUsername(String username);
}
