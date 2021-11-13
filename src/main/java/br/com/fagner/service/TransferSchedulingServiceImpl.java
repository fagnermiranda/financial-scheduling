package br.com.fagner.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fagner.enums.TransferSchedulingType;
import br.com.fagner.exception.BusinessException;
import br.com.fagner.model.TransferScheduling;
import br.com.fagner.repository.TransferSchedulingRepository;
import br.com.fagner.util.MessageSourceUtil;

@Service
public class TransferSchedulingServiceImpl implements TransferSchedulingService {
	
    @Autowired
    private MessageSourceUtil messageSourceUtil;
    
    @Autowired
    private TransferSchedulingRepository transferSchedulingRepository;

	@Override
	public TransferScheduling createTransferScheduling(TransferScheduling transferScheduling) throws BusinessException {
		beforeCreateTransferScheduling(transferScheduling);
		transferSchedulingRepository.save(transferScheduling);
		return transferScheduling;
	}
	
	public TransferScheduling beforeCreateTransferScheduling(TransferScheduling transferScheduling) throws BusinessException {
		TransferSchedulingType transferSchedulingType = getTransferSchedulingType(transferScheduling);
		transferScheduling.setTransferSchedulingType(transferSchedulingType);
		transferScheduling.setRate(calculateRateScheduling(transferScheduling));
		return transferScheduling;
	}
		
	@Override
	public double calculateRateScheduling(TransferScheduling transferScheduling) throws BusinessException {
		double rate = 0;
		long days = this.getDays(transferScheduling);
		TransferSchedulingType transferSchedulingType = transferScheduling.getTransferSchedulingType();
		if (transferSchedulingType != null) {
			rate = transferSchedulingType.calculateRate(days, transferScheduling.getValueTransfer());
		}
		validateRateScheduling(rate);
		return rate;
	}

	public void validateRateScheduling(double rate) throws BusinessException {
		if (isNotRate(rate)) {
			throw new BusinessException(this.messageSourceUtil.getMessage("business.notRate"));
		}
	}

	public TransferSchedulingType getTransferSchedulingType(TransferScheduling transferScheduling) {
		LocalDate today = LocalDate.now();
		long days = getDays(transferScheduling);
		double valueTransfer = transferScheduling.getValueTransfer();
		if (isToday(transferScheduling, today)) {
			return TransferSchedulingType.A;
		}
		if (isTypeB(days)) {
			return TransferSchedulingType.B;
		}
		if (isTypeC(days, valueTransfer)) {
			return TransferSchedulingType.C;
		}
		return null;
	}

	private long getDays(TransferScheduling transferScheduling) {
		return ChronoUnit.DAYS.between(transferScheduling.getDateTransfer(), transferScheduling.getDateScheduling());
	}
		
	private boolean isNotRate(double rate) {
		return rate == 0;
	}
	
	private boolean isTypeC(long days, double valueTransfer) {
		return (days > 10 && days <= 40) || (days > 40 && valueTransfer > 100000);
	}

	private boolean isTypeB(long days) {
		return days >= 1 && days <= 10;
	}

	private boolean isToday(TransferScheduling transferScheduling, LocalDate today) {
		return transferScheduling.getDateScheduling().isEqual(today);
	}

}
