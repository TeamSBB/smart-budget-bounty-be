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
import com.smartbudgetbounty.entity.Transfer;
import com.smartbudgetbounty.entity.User;
import com.smartbudgetbounty.repository.PaymentMethodRepository;
import com.smartbudgetbounty.repository.TransferRepository;
import com.smartbudgetbounty.service.rewardpointstransaction.RewardPointsTransactionService;
import com.smartbudgetbounty.service.user.UserService;
import com.smartbudgetbounty.util.LogUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TransferServiceImpl implements TransferService {

    private static final Logger logger = LoggerFactory.getLogger(TransferServiceImpl.class);

    private final UserService userService;
    private final TransferRepository transferRepository;
    private final PaymentMethodRepository paymentMethodRepo;
    private final RewardPointsTransactionService pointsTransactionService;

    public TransferServiceImpl(
            UserService userService,
            TransferRepository transferRepository,
            PaymentMethodRepository paymentMethodRepo,
            RewardPointsTransactionService pointsTransactionService) {
        super();
        this.userService = userService;
        this.transferRepository = transferRepository;
        this.paymentMethodRepo = paymentMethodRepo;
        this.pointsTransactionService = pointsTransactionService;
    }

    // helper methods

    // convert Transfer toTransferResponseDto
    private TransferResponseDto toTransferResponseDto(Transfer transfer) {
        return new TransferResponseDto(
                transfer.getId(),
                transfer.getTransactionAmount(),
                transfer.getRecipientName(),
                transfer.getPaymentMethod().getId(),
                transfer.getCreatedAt(),
                transfer.getTransferDate(),
                transfer.getPaynowPhoneNumber(),
                transfer.getAccountNumber(),
                transfer.getRemarks(),
                transfer.getBankName(),
                transfer.getBeneficiaryName());
    }

    // convert a list of Transfers to a list of TransferResponseDtos
    private List<TransferResponseDto> toTransferResponseDtos(
            List<Transfer> transfers) {
        ArrayList<TransferResponseDto> transferResponseDtos = new ArrayList<TransferResponseDto>();

        for (Transfer transfer : transfers) {
            transferResponseDtos.add(
                    toTransferResponseDto(transfer));
        }

        return transferResponseDtos;
    }

    // service methods

    // create and persist Transfer
    // create and persist RewardPointsTransaction
    // - to be called by TransferController
    @Override
    public TransferResponseDto create(CreateTransferDtoRequest request) {
        LogUtil.logStart(logger, "Creating Transfer.");

        // get User from repository
        User user = userService.getById(request.getUserId());

        // get PaymentMethod from repository
        PaymentMethod paymentMethod = paymentMethodRepo.findById(
                request.getPaymentMethodId()).orElseThrow(() -> {
                    LogUtil.logError(logger, "Unable to find paymentId: {}.", request.getPaymentMethodId());
                    return new EntityNotFoundException(
                            "Unable to find paymentId: " + request.getPaymentMethodId());
                });

        // create and persist Transfer
        Instant now = Instant.now();
        Transfer transfer = transferRepository.save(
                new Transfer(
                        request.getTransactionAmount(),
                        now,
                        request.getRecipientName(),
                        paymentMethod,
                        request.getPaynowPhoneNumber(),
                        request.getAccountNumber(),
                        request.getRemarks(),
                        request.getBankName(),
                        request.getBeneficiaryName(),
                        request.getTransferDate() != null ? request.getTransferDate() : Instant.now(),
                        user));

        // create and persist RewardPointsTransaction
        transfer = pointsTransactionService.createEarn(user, transfer);

        LogUtil.logEnd(logger, "Created Transfer: {}", transfer);

        return toTransferResponseDto(transfer);
    }

    // retrieve Transfer from TransferRepository
    // - to be called by other services
    @Override
    public Transfer getById(Long id) {
        LogUtil.logStart(logger, "Getting Transfer by id.");

        Transfer transfer = transferRepository.findById(id).orElseThrow(() -> {
            LogUtil.logError(logger, "Unable to find transferId: {}.", id);
            return new EntityNotFoundException("Unable to find transferId: " + id);
        });

        LogUtil.logEnd(logger, "Retrieved Transfer: {}", transfer);

        return transfer;
    }

    // retrieve Transfer from TransferRepository and return it as a
    // TransferResponseDto
    // - to be called by TransferController
    @Override
    public TransferResponseDto getDtoById(Long id) {
        Transfer transfer = getById(id);
        return toTransferResponseDto(transfer);
    }

    // retrieve a user's Transfers from TransferRepository and return it as a
    // TransferResponseDto
    // - to be called by TransferController
    @Override
    public List<TransferResponseDto> getDtosByUserId(Long userId) {
        LogUtil.logStart(logger, "Getting Transfers by id.");

        // get User from repository
        User user = userService.getById(userId);

        // convert Transfers to TransferResponseDtos
        List<TransferResponseDto> transfers = toTransferResponseDtos(
                user.getTransfers());

        LogUtil.logEnd(logger, "Retrieved Transfers: {}", transfers);

        return transfers;
    }
}
