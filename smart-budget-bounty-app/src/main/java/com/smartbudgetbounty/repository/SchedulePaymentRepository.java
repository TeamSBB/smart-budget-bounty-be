package com.smartbudgetbounty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbudgetbounty.entity.SchedulePayment;


public interface SchedulePaymentRepository extends JpaRepository<SchedulePayment, Long>{

	List<SchedulePayment> findByUserId(Long userID);
}
