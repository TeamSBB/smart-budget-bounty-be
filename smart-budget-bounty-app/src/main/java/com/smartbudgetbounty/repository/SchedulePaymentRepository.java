package com.smartbudgetbounty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbudgetbounty.entity.SchedulePayment;


public interface SchedulePaymentRepository extends JpaRepository<SchedulePayment, Long>{

}
