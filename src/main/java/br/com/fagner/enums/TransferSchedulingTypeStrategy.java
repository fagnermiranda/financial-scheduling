package br.com.fagner.enums;

public interface TransferSchedulingTypeStrategy {
	
	public double calculateRate(long days, double valueTransfer);

}
