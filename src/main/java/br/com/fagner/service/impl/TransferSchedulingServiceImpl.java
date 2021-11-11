package br.com.fagner.service.impl;

import java.time.Period;

import org.springframework.stereotype.Service;

import br.com.fagner.enums.TransferSchedulingType;
import br.com.fagner.model.TransferScheduling;
import br.com.fagner.service.TransferSchedulingService;

@Service
public class TransferSchedulingServiceImpl implements TransferSchedulingService {

	@Override
	public double calculateRateScheduling(TransferScheduling transferScheduling) {
		Period period = Period.between(transferScheduling.getDateTransfer(), transferScheduling.getDateScheduling());
		TransferSchedulingType transferSchedulingType = transferScheduling.getTransferSchedulingType();
		return transferSchedulingType.calculateRate(period.getDays(), transferScheduling.getValueTransfer());
	}

}
