package com.semzy_ecommerce.soft.dao;

import com.semzy_ecommerce.soft.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRepository extends JpaRepository<PasswordResetToken,Long> {
    PasswordResetToken findByToken(String token);
}
