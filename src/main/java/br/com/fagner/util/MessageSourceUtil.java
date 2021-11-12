package br.com.fagner.util;

import org.springframework.context.NoSuchMessageException;

public interface MessageSourceUtil {
	public String getMessage(String code) throws NoSuchMessageException;
}
