package br.com.fagner.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.fagner.enums.TransferSchedulingType;

@Entity(name = "transfer-scheduling")
public class TransferScheduling implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Account sourceAccount;
	
	private Account destinationAccount; 
	
	private double valueTransfer; 

	private LocalDate dateTransfer;
	
	private LocalDate dateScheduling;
	
	private TransferSchedulingType transferSchedulingType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(Account sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public Account getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(Account destinationAccount) {
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

	public TransferSchedulingType getTransferSchedulingType() {
		return transferSchedulingType;
	}

	public void setTransferSchedulingType(TransferSchedulingType transferSchedulingType) {
		this.transferSchedulingType = transferSchedulingType;
	}
	
}
