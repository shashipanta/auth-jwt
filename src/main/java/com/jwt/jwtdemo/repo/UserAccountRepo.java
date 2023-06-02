package com.jwt.jwtdemo.repo;

import com.jwt.jwtdemo.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepo extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByEmail(String email);
}
