package com.smartbudgetbounty.service.paymentmethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.entity.PaymentMethod;
import com.smartbudgetbounty.repository.PaymentMethodRepository;
import com.smartbudgetbounty.service.transfer.TransferServiceImpl;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private static final Logger logger = LoggerFactory.getLogger(TransferServiceImpl.class);

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository) {
        super();
        this.paymentMethodRepository = paymentMethodRepository;
    }

    // service methods

    // retrieve a PaymentMethod from PaymentMethodRepository
    // - to be called by other services
    @Override
    public PaymentMethod getById(Long id) {
        LogUtil.logStart(logger, "Getting PaymentMethod by id.");

        PaymentMethod paymentMethod = paymentMethodRepository.findById(id).orElseThrow(() -> {
            LogUtil.logError(logger, "Unable to find transferId: {}.", id);
            return new EntityNotFoundException("Unable to find transferId: " + id);
        });

        LogUtil.logEnd(logger, "Retrieved PaymentMethod: {}", paymentMethod);

        return paymentMethod;
    }
}
