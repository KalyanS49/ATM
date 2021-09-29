/**
 * 
 */
package com.example.atm.model.request;

/**
 * @author KALYAN
 *
 */
public class WithdrawalReq {
	private Double amount;
	private Long customerId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
