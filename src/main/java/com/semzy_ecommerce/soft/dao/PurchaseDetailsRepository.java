package com.semzy_ecommerce.soft.dao;

import com.semzy_ecommerce.soft.entity.PurchaseDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseDetailsRepository extends JpaRepository<PurchaseDetails,Integer> {

    PurchaseDetails findByTransactionId(String transactionId);
    List<PurchaseDetails> findByUserId(int userId);
}
