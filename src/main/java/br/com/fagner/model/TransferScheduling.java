package br.com.fagner.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.fagner.enums.TransferSchedulingType;
import lombok.Data;

@Data
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
	
}
