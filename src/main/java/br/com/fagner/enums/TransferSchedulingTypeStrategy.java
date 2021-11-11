package br.com.fagner.enums;

public interface TransferSchedulingTypeStrategy {
	
	public double calculateRate(int days, double valueTransfer);

}
