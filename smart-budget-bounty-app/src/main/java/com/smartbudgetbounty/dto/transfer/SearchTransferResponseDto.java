package com.smartbudgetbounty.dto.transfer;

import java.util.ArrayList;
import java.util.List;

public class SearchTransferResponseDto {
    private List<SearchTransferDto> data = new ArrayList<>();
    private Integer totalPages;

    public SearchTransferResponseDto(List<SearchTransferDto> data, Integer totalPages) {
        this.data = data;
        this.totalPages = totalPages;
    }

    public List<SearchTransferDto> getData() {
        return data;
    }

    public void setData(List<SearchTransferDto> data) {
        this.data = data;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
