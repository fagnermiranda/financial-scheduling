package br.com.fagner.service;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fagner.enums.TransferSchedulingType;
import br.com.fagner.exception.BusinessException;
import br.com.fagner.model.TransferScheduling;
import br.com.fagner.util.MessageSourceUtil;

@Service
public class TransferSchedulingServiceImpl implements TransferSchedulingService {
	
    @Autowired
    private MessageSourceUtil messageSourceUtil;

	@Override
	public double calculateRateScheduling(TransferScheduling transferScheduling) throws BusinessException {
		long daysBetween = ChronoUnit.DAYS.between(transferScheduling.getDateTransfer(), transferScheduling.getDateScheduling());
		TransferSchedulingType transferSchedulingType = transferScheduling.getTransferSchedulingType();
		double rate = transferSchedulingType.calculateRate(daysBetween, transferScheduling.getValueTransfer());
		validateRateScheduling(rate);
		return rate;
	}

	public void validateRateScheduling(double rate) throws BusinessException {
		if (isNotRate(rate)) {
			throw new BusinessException(this.messageSourceUtil.getMessage("business.notRate"));
		}
	}

	private boolean isNotRate(double rate) {
		return rate == 0;
	}

}
