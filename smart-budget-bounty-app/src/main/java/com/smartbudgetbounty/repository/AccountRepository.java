package com.smartbudgetbounty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbudgetbounty.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	Account findByUserId(Long id);
}
