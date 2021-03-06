package br.com.fagner.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageSourceUtilImpl implements MessageSourceUtil {

	@Autowired
	private MessageSource messageSource;

	public String getMessage(String code) throws NoSuchMessageException {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(code, null, locale);
	}
}
