package com.smartbudgetbounty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbudgetbounty.entity.RewardVoucher;

public interface RewardVoucherRepository extends JpaRepository<RewardVoucher, Long> {
    List<RewardVoucher> findByUserId(Long userId);
}
