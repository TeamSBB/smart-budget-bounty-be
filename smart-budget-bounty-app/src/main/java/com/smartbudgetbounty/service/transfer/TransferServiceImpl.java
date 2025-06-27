package com.smartbudgetbounty.service.transfer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartbudgetbounty.dto.transfer.CreateTransferDtoRequest;
import com.smartbudgetbounty.dto.transfer.TransferResponseDto;
import com.smartbudgetbounty.entity.PaymentMethod;
import com.smartbudgetbounty.entity.RewardPointsTransaction;
import com.smartbudgetbounty.entity.Transfer;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.repository.TransferRepository;
import com.smartbudgetbounty.service.paymentmethod.PaymentMethodService;
import com.smartbudgetbounty.service.rewardpointstransaction.RewardPointsTransactionService;
import com.smartbudgetbounty.service.user.UserService;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TransferServiceImpl implements TransferService {

    private static final Logger logger = LoggerFactory.getLogger(TransferServiceImpl.class);

    private final UserService userService;
    private final TransferRepository transferRepository;
    private final PaymentMethodService paymentMethodService;
    private final RewardPointsTransactionService pointsTransactionService;

    public TransferServiceImpl(
        UserService userService,
        TransferRepository transferRepository,
        PaymentMethodService paymentMethodService,
        RewardPointsTransactionService pointsTransactionService
    ) {
        super();
        this.userService = userService;
        this.transferRepository = transferRepository;
        this.paymentMethodService = paymentMethodService;
        this.pointsTransactionService = pointsTransactionService;
    }

    // helper methods

    // save Transfer to TransferRepository
    private Transfer save(Transfer transfer) {
        LogUtil.logStart(logger, "Saving Transfer.");

        transfer = transferRepository.save(transfer);

        LogUtil.logEnd(logger, "Saved Transfer: {}", transfer);

        return transfer;
    }

    // convert Transfer to TransferResponseDto
    private TransferResponseDto toTransferResponseDto(Transfer transfer) {
        return new TransferResponseDto(
            transfer.getId(),
            transfer.getRecipientName(),
            transfer.getFromPaynowPhoneNumber(),
            transfer.getToPaynowPhoneNumber(),
            transfer.getTransferDate(),
            transfer.getBeneficiaryName(),
            transfer.getFromAccountNumber(),
            transfer.getToAccountNumber(),
            transfer.getTransactionAmount(),
            transfer.getCreatedAt(),
            transfer.getPaymentMethod().getId(),
            transfer.getRemarks()
        );
    }

    // convert a list of Transfers to a list of TransferResponseDtos
    private List<TransferResponseDto> toTransferResponseDtos(
        List<Transfer> transfers
    ) {
        ArrayList<TransferResponseDto> transferResponseDtos = new ArrayList<TransferResponseDto>();

        for (Transfer transfer : transfers) {
            transferResponseDtos.add(
                toTransferResponseDto(transfer)
            );
        }

        return transferResponseDtos;
    }

    // service methods

    // create a Transfer and associated RewardPointsTransaction
    // persist Transfer, which persists RewardPointsTransaction via cascade
    // - to be called by TransferController
    @Override
    public TransferResponseDto create(CreateTransferDtoRequest request) {
        LogUtil.logStart(logger, "Creating Transfer.");

        // retrieve User from repository
        User user = userService.getById(request.getUserId());

        // retrieve PaymentMethod from repository
        PaymentMethod paymentMethod = paymentMethodService.getById(request.getPaymentMethodId());

        // create Transfer
        Transfer transfer = new Transfer(
            request.getTransactionAmount(),
            Instant.now(),
            request.getRecipientName(),
            request.getFromPaynowPhoneNumber(),
            request.getToPaynowPhoneNumber(),
            request.getFromAccountNumber(),
            request.getToAccountNumber(),
            request.getBeneficiaryName(),
            request.getRemarks(),
            request.getTransferDate() != null ? request.getTransferDate() : Instant.now(),
            user,
            paymentMethod
        );

        // create and persist RewardPointsTransaction
        RewardPointsTransaction pointsTransaction = pointsTransactionService.createEarn(
            user,
            transfer
        );

        // set the relationship from Transfer to RewardPointsTransaction
        transfer.setPointsTransaction(pointsTransaction);

        // persist Transfer, which persists RewardPointsTransaction via cascade
        transfer = save(transfer);

        LogUtil.logEnd(logger, "Created Transfer: {}", transfer);

        return toTransferResponseDto(transfer);
    }

    // retrieve a Transfer from TransferRepository
    // - to be called by other services
    @Override
    public Transfer getById(Long id) {
        LogUtil.logStart(logger, "Retrieving Transfer by id.");

        Transfer transfer = transferRepository.findById(id).orElseThrow(() -> {
            LogUtil.logError(logger, "Unable to find transferId: {}.", id);
            return new EntityNotFoundException("Unable to find transferId: " + id);
        });

        LogUtil.logEnd(logger, "Retrieved Transfer: {}", transfer);

        return transfer;
    }

    // retrieve a Transfer from TransferRepository as a TransferResponseDto
    // - to be called by TransferController
    @Override
    public TransferResponseDto getDtoById(Long id) {
        Transfer transfer = getById(id);
        TransferResponseDto transferResponseDto = toTransferResponseDto(transfer);
        return transferResponseDto;
    }

    // retrieve a user's list of Transfers from TransferRepository
    // - to be called by other services
    @Override
    public List<Transfer> getByUserId(Long userId) {
        LogUtil.logStart(logger, "Retrieving Transfers by id.");

        User user = userService.getById(userId);
        List<Transfer> transfers = user.getTransfers();

        LogUtil.logEnd(logger, "Retrieved Transfers: {}", transfers);

        return transfers;
    }

    // retrieve a user's list of Transfers from TransferRepository as a list of TransferResponseDtos
    // - to be called by TransferController
    @Override
    public List<TransferResponseDto> getDtosByUserId(Long userId) {
        List<Transfer> transfers = getByUserId(userId);
        List<TransferResponseDto> transferResponseDtos = toTransferResponseDtos(transfers);
        return transferResponseDtos;
    }
}
