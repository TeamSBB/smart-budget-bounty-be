package com.smartbudgetbounty.dto.transfer;

import com.smartbudgetbounty.dto.generic.SortRequest;

import java.time.Instant;
import java.util.List;

public class SearchTransferRequestDto {
    private String recipientName;
    private Long paymentMethodId;
    private Instant startSearchDate;
    private int page;
    private int size;
    private List<SortRequest> sorts;

    public SearchTransferRequestDto(String recipientName, Long paymentMethodId, Instant startSearchDate, int page, int size, List<SortRequest> sorts) {
        this.recipientName = recipientName;
        this.paymentMethodId = paymentMethodId;
        this.startSearchDate = startSearchDate;
        this.page = page;
        this.size = size;
        this.sorts = sorts;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<SortRequest> getSorts() {
        return sorts;
    }

    public void setSorts(List<SortRequest> sorts) {
        this.sorts = sorts;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Instant getStartSearchDate() {
        return startSearchDate;
    }

    public void setStartSearchDateDate(Instant startSearchDate) {
        this.startSearchDate = startSearchDate;
    }
}
