package com.smartbudgetbounty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbudgetbounty.entity.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{

}
