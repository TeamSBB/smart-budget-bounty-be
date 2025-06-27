package com.smartbudgetbounty.service.paymentmethod;

import com.smartbudgetbounty.entity.PaymentMethod;

public interface PaymentMethodService {
    // retrieve a PaymentMethod from PaymentMethodRepository
    // - to be called by other services
    PaymentMethod getById(Long id);
}
