package br.com.fagner.service;

import br.com.fagner.exception.BusinessException;
import br.com.fagner.model.TransferScheduling;

public interface TransferSchedulingService {

	public TransferScheduling createTransferScheduling(TransferScheduling transferScheduling) throws BusinessException;

	public double calculateRateScheduling(TransferScheduling transferScheduling) throws BusinessException;
}
