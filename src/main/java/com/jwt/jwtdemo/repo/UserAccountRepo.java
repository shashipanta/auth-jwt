package com.jwt.jwtdemo.repo;

import com.jwt.jwtdemo.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepo extends JpaRepository<UserAccount, Long> {
}
