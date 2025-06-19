package com.smartbudgetbounty.dto.schedulepayment;

public class CreateSchedulePaymentDtoResponse {
	private String billName;

	public CreateSchedulePaymentDtoResponse(String billName) {
        this.billName = billName;
    }

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}
 
    // Getters and Setters
 
}
