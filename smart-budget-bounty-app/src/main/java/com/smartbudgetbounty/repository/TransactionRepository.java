package com.smartbudgetbounty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbudgetbounty.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
