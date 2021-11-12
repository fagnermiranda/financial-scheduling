package br.com.fagner.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class TransferSchedulingDTO  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private int sourceAccount;
	
	private int destinationAccount; 
	
	private double valueTransfer; 

	private LocalDate dateTransfer;
	
	private LocalDate dateScheduling;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(int sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public int getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(int destinationAccount) {
		this.destinationAccount = destinationAccount;
	}

	public double getValueTransfer() {
		return valueTransfer;
	}

	public void setValueTransfer(double valueTransfer) {
		this.valueTransfer = valueTransfer;
	}

	public LocalDate getDateTransfer() {
		return dateTransfer;
	}

	public void setDateTransfer(LocalDate dateTransfer) {
		this.dateTransfer = dateTransfer;
	}

	public LocalDate getDateScheduling() {
		return dateScheduling;
	}

	public void setDateScheduling(LocalDate dateScheduling) {
		this.dateScheduling = dateScheduling;
	}

}
