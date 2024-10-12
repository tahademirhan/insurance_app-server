package com.insurance_app.insurance_server.core.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ResourceBundleUtil {

    private static MessageSource messageSource;

    public ResourceBundleUtil(MessageSource messageSource) {
        ResourceBundleUtil.messageSource = messageSource;
    }

    public static String getValue(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }

    public static String getValue(String code, Object... params) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, params, locale);
    }

}
