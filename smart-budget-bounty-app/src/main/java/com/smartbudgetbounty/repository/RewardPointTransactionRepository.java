package com.smartbudgetbounty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbudgetbounty.entity.RewardPointTransaction;

public interface RewardPointTransactionRepository
    extends
    JpaRepository<RewardPointTransaction, Long> {
    List<RewardPointTransaction> findByUserId(Long userId);
}
