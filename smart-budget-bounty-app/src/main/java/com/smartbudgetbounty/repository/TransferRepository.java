package com.smartbudgetbounty.repository;

import com.smartbudgetbounty.entity.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    @Query("""
            	SELECT t FROM Transfer t
            	WHERE t.user.id = :userId 
            	AND (
            		:recipientName IS NULL OR
            		:recipientName = '' OR
            		LOWER(t.recipientName) LIKE LOWER(CONCAT(:recipientName, '%'))
            	)
            	AND (
            		:paymentMethodId IS NULL OR
            		:paymentMethodId = t.paymentMethod.id
            	)		
            	AND (
            		:startDate IS NULL OR
            		:startDate <= t.createdAt
            	)
            """)
    public Page<Transfer> searchTransfers(
            @Param("userId") Long userId,
            @Param("recipientName") String recipientName,
            @Param("paymentMethodId") Long paymentMethodId,
            @Param("startDate") Instant startDate,
            Pageable pageable);
}
