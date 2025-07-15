package com.smartbudgetbounty.dto.schedulepayment;

import java.util.ArrayList;
import java.util.List;

public class SearchSchedulePaymentResponseDto {

    private List<SchedulePaymentResponseDto> data = new ArrayList<>();
    private Integer totalPages;

    public SearchSchedulePaymentResponseDto(List<SchedulePaymentResponseDto> data, Integer totalPages) {
        this.data = data;
        this.totalPages = totalPages;
    }

    public List<SchedulePaymentResponseDto> getData() {
        return data;
    }

    public void setData(List<SchedulePaymentResponseDto> data) {
        this.data = data;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}