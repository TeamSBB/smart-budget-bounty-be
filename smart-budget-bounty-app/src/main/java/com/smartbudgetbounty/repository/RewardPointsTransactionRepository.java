package com.smartbudgetbounty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbudgetbounty.entity.RewardPointsTransaction;

public interface RewardPointsTransactionRepository
    extends
    JpaRepository<RewardPointsTransaction, Long> {
    List<RewardPointsTransaction> findByUserId(Long userId);
}
