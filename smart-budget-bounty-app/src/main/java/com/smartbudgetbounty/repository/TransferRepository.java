package com.smartbudgetbounty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbudgetbounty.entity.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long>{

}
