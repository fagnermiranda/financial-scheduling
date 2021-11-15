package br.com.fagner.exception;

public class FinancialSchedulingErrors {
	private String error;

	public FinancialSchedulingErrors(BusinessException ex) {
		this.error = ex.getMessage();
	}

	public String getError() {
		return error;
	}

}