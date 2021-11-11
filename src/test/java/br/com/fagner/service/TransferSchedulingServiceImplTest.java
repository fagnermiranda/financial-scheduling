package br.com.fagner.service;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import br.com.fagner.UnitTest;
import br.com.fagner.enums.TransferSchedulingType;
import br.com.fagner.model.Account;
import br.com.fagner.model.TransferScheduling;
import br.com.fagner.service.impl.TransferSchedulingServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;


class TransferSchedulingServiceImplTest extends UnitTest {
	
	@InjectMocks
	private TransferSchedulingServiceImpl transferSchedulingService;
	
	@Test
	@DisplayName("Calculete Rate Scheduling Type A With Sucess")
	void calculateRateSchedulingTypeA_withSuccess() {
		TransferScheduling transferScheduling = createTransferScheduling(TransferSchedulingType.A, LocalDate.now(), 500);
		transferScheduling.setRate(transferSchedulingService.calculateRateScheduling(transferScheduling));

		double expectedRate = 3 + (transferScheduling.getValueTransfer() * 0.03);
        assertThat(transferScheduling.getRate()).isEqualTo(expectedRate);
	}

	@Test
	@DisplayName("Calculete Rate Scheduling Type B With Sucess")
	void calculateRateSchedulingTypeB_withSuccess() {
		LocalDate today = LocalDate.now();
		LocalDate dateScheduling =  today.plusDays(9);
		TransferScheduling transferScheduling = createTransferScheduling(TransferSchedulingType.B, dateScheduling, 1000);
		transferScheduling.setRate(transferSchedulingService.calculateRateScheduling(transferScheduling));

		Period period = Period.between(today, dateScheduling);
		double expectedRate = (12 * period.getDays());
        assertThat(transferScheduling.getRate()).isEqualTo(expectedRate);
	}

	@Test
	@DisplayName("Calculete Rate Scheduling Type C With Sucess")
	void calculateRateSchedulingTypeC_withSuccess() {
		TransferScheduling transferSchedulingGreater10Until20  = createTransferScheduling(TransferSchedulingType.C, LocalDate.now().plusDays(15), 1000);
		transferSchedulingGreater10Until20.setRate(transferSchedulingService.calculateRateScheduling(transferSchedulingGreater10Until20));

		TransferScheduling transferSchedulingGreater20Until30  = createTransferScheduling(TransferSchedulingType.C, LocalDate.now().plusDays(25), 1000);
		transferSchedulingGreater20Until30.setRate(transferSchedulingService.calculateRateScheduling(transferSchedulingGreater20Until30));

		TransferScheduling transferSchedulingGreater30Until40  = createTransferScheduling(TransferSchedulingType.C, LocalDate.now().plusDays(35), 1000);
		transferSchedulingGreater30Until40.setRate(transferSchedulingService.calculateRateScheduling(transferSchedulingGreater30Until40));
		
		TransferScheduling transferSchedulingGreater40  = createTransferScheduling(TransferSchedulingType.C, LocalDate.now().plusDays(45), 100001);
		transferSchedulingGreater40.setRate(transferSchedulingService.calculateRateScheduling(transferSchedulingGreater40));
		
		double expectedRateGreater10Until20 = (transferSchedulingGreater10Until20.getValueTransfer() * 0.08);
        assertThat(transferSchedulingGreater10Until20.getRate()).isEqualTo(expectedRateGreater10Until20);
        
        double expectedRateGreater20Until30 = (transferSchedulingGreater20Until30.getValueTransfer() * 0.06);
        assertThat(transferSchedulingGreater20Until30.getRate()).isEqualTo(expectedRateGreater20Until30);
        
        double expectedRateGreater30Until40 = (transferSchedulingGreater30Until40.getValueTransfer() * 0.04);
        assertThat(transferSchedulingGreater30Until40.getRate()).isEqualTo(expectedRateGreater30Until40);
        
        double expectedRateGreater40 = (transferSchedulingGreater40.getValueTransfer() * 0.02);
        assertThat(transferSchedulingGreater40.getRate()).isEqualTo(expectedRateGreater40);
        assertThat(transferSchedulingGreater40.getValueTransfer()).isGreaterThan(100000);
	}

	private TransferScheduling createTransferScheduling(TransferSchedulingType transferSchedulingType,
			LocalDate dateScheduling, double valueTransfer) {
		Account sourceAccount = new Account();
		sourceAccount.setId(1L);
		sourceAccount.setCode(001220);

		Account destinationAccount = new Account();
		destinationAccount.setId(2L);
		destinationAccount.setCode(02225);

		LocalDate today = LocalDate.now();
		TransferScheduling transferScheduling = new TransferScheduling();
		transferScheduling.setTransferSchedulingType(transferSchedulingType);
		transferScheduling.setDateScheduling(dateScheduling);
		transferScheduling.setDateTransfer(today);
		transferScheduling.setValueTransfer(valueTransfer);
		transferScheduling.setSourceAccount(sourceAccount);
		transferScheduling.setDestinationAccount(destinationAccount);
		return transferScheduling;
	}

}
