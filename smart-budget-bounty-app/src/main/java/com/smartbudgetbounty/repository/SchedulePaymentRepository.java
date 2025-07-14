package com.smartbudgetbounty.repository;
 
import com.smartbudgetbounty.entity.SchedulePayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
 
@Repository
public interface SchedulePaymentRepository extends JpaRepository<SchedulePayment, Long> {
 
    List<SchedulePayment> findByStatusIgnoreCase(String status);
 
    Page<SchedulePayment> findByStatusIgnoreCase(String status, Pageable pageable);
 
    Page<SchedulePayment> findByPaymentMethodIgnoreCase(String paymentMethod, Pageable pageable);
 
    Page<SchedulePayment> findByStatusIgnoreCaseAndPaymentMethodIgnoreCase(String status, String paymentMethod, Pageable pageable);
}