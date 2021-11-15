package br.com.fagner.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.fagner.UnitTest;
import br.com.fagner.constants.Percentage;
import br.com.fagner.enums.TransferSchedulingType;
import br.com.fagner.exception.BusinessException;
import br.com.fagner.model.TransferScheduling;
import br.com.fagner.repository.TransferSchedulingRepository;


class TransferSchedulingServiceImplTest extends UnitTest {
	
	private static final int DESTINATION_ACCOUNT = 02225;
	private static final int SOURCE_ACCOUNT = 001220;
	private static final int ACCOUNT_ZERO = 0;
	private static final int RATE_TYPE_A = 3;
	private static final int RATE_TYPE_B = 12;
	private static final int VALUE_TRANSFER_TYPE_C = 100001;
	private static final int VALUE_TRANSFER_TYPE_C_START = 100000;
	private static final int VALUE_TRANSFER = 1000;
	private static final int VALUE_TRANSFER_ZERO = 0;
	private static final int FORTY_FIVE_DAYS = 45;
	private static final int THIRTY_FIVE_DAYS = 35;
	private static final int TWENTY_FIVE_DAYS = 25;
	private static final int FIFTEEN_DAYS = 15;
	
	@InjectMocks
	private TransferSchedulingServiceImpl transferSchedulingService;

	@Mock
	private TransferSchedulingRepository transferSchedulingRepository;
	
	@Test
	@DisplayName("Create Transfer Scheduling With Sucess")
	void createTransferScheduling_withSuccess() throws BusinessException {
		TransferScheduling transferScheduling = createTransferScheduling(LocalDate.now(), VALUE_TRANSFER);
		transferScheduling.setId(1L);

		when(transferSchedulingRepository.save(ArgumentMatchers.any(TransferScheduling.class))).thenReturn(transferScheduling);
		TransferScheduling created = transferSchedulingService.createTransferScheduling(transferScheduling);

		verify(transferSchedulingRepository, times(1)).save(transferScheduling);
        assertThat(created.getId()).isEqualTo(transferScheduling.getId());
	}
		
	@Test
	@DisplayName("Should Throw Business When Transfer Scheduling Null")
	void shouldThrow_businessException_when_transfer_scheduling_null() throws BusinessException {
		TransferScheduling transferScheduling  = null;		
		String message = this.messageSourceUtil.getMessage("business.informTransferScheduling");
		BusinessException exception = catchThrowableOfType(() ->transferSchedulingService.createTransferScheduling(transferScheduling), BusinessException.class);
		
        assertThat(exception.getMessage()).isNotBlank();
        assertThat(exception.getMessage()).isEqualTo(message);
	}
	
	@Test
	@DisplayName("Should Throw Business When Source Account Zero")
	void shouldThrow_businessException_when_source_account_zero() throws BusinessException {
		TransferScheduling transferScheduling  = createTransferScheduling(LocalDate.now(), VALUE_TRANSFER);		
		transferScheduling.setSourceAccount(ACCOUNT_ZERO);
		String message = this.messageSourceUtil.getMessage("business.informSourceAccount");
		BusinessException exception = catchThrowableOfType(() ->transferSchedulingService.createTransferScheduling(transferScheduling), BusinessException.class);
		
        assertThat(exception.getMessage()).isNotBlank();
        assertThat(exception.getMessage()).isEqualTo(message);
	}
	
	@Test
	@DisplayName("Should Throw Business When Destination Account Zero")
	void shouldThrow_businessException_when_destination_account_zero() throws BusinessException {
		TransferScheduling transferScheduling  = createTransferScheduling(LocalDate.now(), VALUE_TRANSFER);		
		transferScheduling.setDestinationAccount(ACCOUNT_ZERO);
		String message = this.messageSourceUtil.getMessage("business.informDestinationAccount");
		BusinessException exception = catchThrowableOfType(() ->transferSchedulingService.createTransferScheduling(transferScheduling), BusinessException.class);
		
        assertThat(exception.getMessage()).isNotBlank();
        assertThat(exception.getMessage()).isEqualTo(message);
	}
	
	@Test
	@DisplayName("Should Throw Business When Value Transfer Zero")
	void shouldThrow_businessException_when_value_transfer_zero() throws BusinessException {
		TransferScheduling transferScheduling  = createTransferScheduling(LocalDate.now(), VALUE_TRANSFER_ZERO);		
		String message = this.messageSourceUtil.getMessage("business.informValueTransfer");
		BusinessException exception = catchThrowableOfType(() ->transferSchedulingService.createTransferScheduling(transferScheduling), BusinessException.class);
		
        assertThat(exception.getMessage()).isNotBlank();
        assertThat(exception.getMessage()).isEqualTo(message);
	}
	
	@Test
	@DisplayName("Should Throw Business When Date Scheduling Null")
	void shouldThrow_businessException_when_date_scheduling_null() throws BusinessException {
		TransferScheduling transferScheduling  = createTransferScheduling(null, VALUE_TRANSFER);		
		String message = this.messageSourceUtil.getMessage("business.informDateScheduling");
		BusinessException exception = catchThrowableOfType(() ->transferSchedulingService.createTransferScheduling(transferScheduling), BusinessException.class);
		
        assertThat(exception.getMessage()).isNotBlank();
        assertThat(exception.getMessage()).isEqualTo(message);
	}
	
	@Test
	@DisplayName("Should Throw Business When Date Transfer Null")
	void shouldThrow_businessException_when_date_transfer_null() throws BusinessException {
		TransferScheduling transferScheduling  = createTransferScheduling(LocalDate.now(), VALUE_TRANSFER);
		transferScheduling.setDateTransfer(null);
		String message = this.messageSourceUtil.getMessage("business.informDateTransfer");
		BusinessException exception = catchThrowableOfType(() ->transferSchedulingService.createTransferScheduling(transferScheduling), BusinessException.class);
		
        assertThat(exception.getMessage()).isNotBlank();
        assertThat(exception.getMessage()).isEqualTo(message);
	}
	
	@Test
	@DisplayName("Should Throw Business Exception When Doesnt Exist Rate To Before Day")
	void shouldThrow_businessException_when_doesnt_exist_rate_to_before_day() throws BusinessException {
		TransferScheduling transferScheduling  = createTransferScheduling(LocalDate.now().plusDays(-1), VALUE_TRANSFER);		
		String message = this.messageSourceUtil.getMessage("business.notRate");
		BusinessException exception = catchThrowableOfType(() ->transferSchedulingService.createTransferScheduling(transferScheduling), BusinessException.class);
		
        assertThat(exception.getMessage()).isNotBlank();
        assertThat(exception.getMessage()).isEqualTo(message);
	}
	
	@Test
	@DisplayName("Calculete Rate Scheduling Type A With Sucess")
	void calculateRateSchedulingTypeA_withSuccess() throws BusinessException {
		TransferScheduling transferScheduling = createTransferScheduling(LocalDate.now(), VALUE_TRANSFER);
		transferScheduling.setRate(transferSchedulingService.calculateRateScheduling(transferScheduling));

		double expectedRate = RATE_TYPE_A + (transferScheduling.getValueTransfer() * Percentage.THREE);
        assertThat(transferScheduling.getRate()).isEqualTo(expectedRate);
	}

	@Test
	@DisplayName("Calculete Rate Scheduling Type B With Sucess")
	void calculateRateSchedulingTypeB_withSuccess() throws BusinessException {
		LocalDate today = LocalDate.now();
		LocalDate dateScheduling =  today.plusDays(9);
		TransferScheduling transferScheduling = createTransferScheduling(dateScheduling, 1000);
		transferScheduling.setRate(transferSchedulingService.calculateRateScheduling(transferScheduling));

		Period period = Period.between(today, dateScheduling);
		double expectedRate = (RATE_TYPE_B * period.getDays());
        assertThat(transferScheduling.getRate()).isEqualTo(expectedRate);
	}

	@Test
	@DisplayName("Calculete Rate Scheduling Type C With Sucess")
	void calculateRateSchedulingTypeC_withSuccess() throws BusinessException {
		TransferScheduling transferSchedulingGreater10Until20  = createTransferScheduling(LocalDate.now().plusDays(FIFTEEN_DAYS), VALUE_TRANSFER);
		transferSchedulingGreater10Until20.setRate(transferSchedulingService.calculateRateScheduling(transferSchedulingGreater10Until20));

		TransferScheduling transferSchedulingGreater20Until30  = createTransferScheduling(LocalDate.now().plusDays(TWENTY_FIVE_DAYS), VALUE_TRANSFER);
		transferSchedulingGreater20Until30.setRate(transferSchedulingService.calculateRateScheduling(transferSchedulingGreater20Until30));

		TransferScheduling transferSchedulingGreater30Until40  = createTransferScheduling(LocalDate.now().plusDays(THIRTY_FIVE_DAYS), VALUE_TRANSFER);
		transferSchedulingGreater30Until40.setRate(transferSchedulingService.calculateRateScheduling(transferSchedulingGreater30Until40));
		
		TransferScheduling transferSchedulingGreater40  = createTransferScheduling(LocalDate.now().plusDays(FORTY_FIVE_DAYS), VALUE_TRANSFER_TYPE_C);
		transferSchedulingGreater40.setRate(transferSchedulingService.calculateRateScheduling(transferSchedulingGreater40));
		
		double expectedRateGreater10Until20 = (transferSchedulingGreater10Until20.getValueTransfer() * Percentage.EIGHT);
        assertThat(transferSchedulingGreater10Until20.getRate()).isEqualTo(expectedRateGreater10Until20);
        
        double expectedRateGreater20Until30 = (transferSchedulingGreater20Until30.getValueTransfer() * Percentage.SIX);
        assertThat(transferSchedulingGreater20Until30.getRate()).isEqualTo(expectedRateGreater20Until30);
        
        double expectedRateGreater30Until40 = (transferSchedulingGreater30Until40.getValueTransfer() * Percentage.FOUR);
        assertThat(transferSchedulingGreater30Until40.getRate()).isEqualTo(expectedRateGreater30Until40);
        
        double expectedRateGreater40 = (transferSchedulingGreater40.getValueTransfer() * Percentage.TWO);
        assertThat(transferSchedulingGreater40.getRate()).isEqualTo(expectedRateGreater40);
        assertThat(transferSchedulingGreater40.getValueTransfer()).isGreaterThan(VALUE_TRANSFER_TYPE_C_START);
	}
	
	@Test
	@DisplayName("Should Throw Business Exception When Doesnt Exist Rate To Type C")
	void shouldThrow_businessException_when_doesnt_exist_rate_to_typeC() throws BusinessException {
		TransferScheduling transferSchedulingGreater40  = createTransferScheduling(LocalDate.now().plusDays(FORTY_FIVE_DAYS), VALUE_TRANSFER);		
		String message = this.messageSourceUtil.getMessage("business.notRate");
		BusinessException exception = catchThrowableOfType(() ->transferSchedulingService.calculateRateScheduling(transferSchedulingGreater40), BusinessException.class);
		
        assertThat(exception.getMessage()).isNotBlank();
        assertThat(exception.getMessage()).isEqualTo(message);
	}

	TransferScheduling createTransferScheduling(LocalDate dateScheduling, double valueTransfer) {
		LocalDate today = LocalDate.now();
		TransferScheduling transferScheduling = new TransferScheduling();
		transferScheduling.setSourceAccount(SOURCE_ACCOUNT);
		transferScheduling.setDestinationAccount(DESTINATION_ACCOUNT);
		transferScheduling.setDateScheduling(dateScheduling);
		transferScheduling.setDateTransfer(today);
		transferScheduling.setValueTransfer(valueTransfer);
		TransferSchedulingType transferSchedulingType = transferSchedulingService.getTransferSchedulingType(transferScheduling);
		transferScheduling.setTransferSchedulingType(transferSchedulingType);
		return transferScheduling;
	}

}
