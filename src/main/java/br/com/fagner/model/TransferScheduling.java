package br.com.fagner.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.fagner.enums.TransferSchedulingType;

@Entity(name = "transfer_scheduling")
public class TransferScheduling implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id_transfer_scheduling")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    @Column(name = "source_account")
	private int sourceAccount;
	
    @Column(name = "destination_account")
	private int destinationAccount; 
	
    @Column(name = "rate")
	private double rate;

    @Column(name = "value_transfer")
	private double valueTransfer; 

    @Column(name = "date_transfer")
	private LocalDate dateTransfer;
	
    @Column(name = "date_scheduling")
	private LocalDate dateScheduling;
	
	private TransferSchedulingType transferSchedulingType;
	
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

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
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
