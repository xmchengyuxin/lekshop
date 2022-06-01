package com.chengyu.core.i18n;

import com.chengyu.core.utils.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class MyLocaleResolverConfig implements LocaleResolver {

    private static final String PATH_PARAMETER = "lang";

    private static final String PATH_PARAMETER_SPLIT = "_";

    private static final String LANG_SESSION = "lang_session";

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getHeader(PATH_PARAMETER);
        Locale locale = request.getLocale();
        if (StringUtils.isNotBlank(lang)) {
            String[] split = lang.split(PATH_PARAMETER_SPLIT);
            locale = new Locale(split[0], split[1]);

            HttpSession session = request.getSession();
            session.setAttribute(LANG_SESSION, locale);
        }else{
            HttpSession session = request.getSession();
            Locale localeInSession = (Locale) session.getAttribute(LANG_SESSION);
            if (localeInSession != null){
                locale = localeInSession;
            }
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
