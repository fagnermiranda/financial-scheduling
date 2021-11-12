package br.com.fagner;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.fagner.util.MessageSourceUtil;
import br.com.fagner.util.MessageSourceUtilImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("tests")
@ContextConfiguration
public class UnitTest {
	
	@Mock
	protected MessageSourceUtil messageSourceUtil;	
	
	@Autowired
	protected MessageSourceUtilImpl messageSourceUtilImpl;
	
	@BeforeEach
	protected void setUp() {
		doAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			return messageSourceUtilImpl.getMessage((String)args[0]);
		}).when(messageSourceUtil).getMessage(anyString());
			
	}	
}
