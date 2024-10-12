package com.insurance_app.insurance_server.core;

import com.insurance_app.insurance_server.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Locale;

public class UserContext {

    private static final ThreadLocal<Locale> userLocale = new InheritableThreadLocal<>();

    private UserContext() {
    }

    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public static Locale getUserLocale() {
        Locale locale = userLocale.get();
        if (null == locale) {
            locale = new Locale("tr", "TR");
            setUserLocale(locale);
        }

        return locale;
    }

    public static void setUserLocale(Locale locale) {
        userLocale.set(locale);
    }

    public static boolean isEnglish() {
        return getUserLocale().getLanguage().equals("en");
    }

    public static String getValueByLocale(String en, String tr) {
        return isEnglish() ? en : tr;
    }

    public static void clearUserLocale() {
        userLocale.remove();
    }
}
