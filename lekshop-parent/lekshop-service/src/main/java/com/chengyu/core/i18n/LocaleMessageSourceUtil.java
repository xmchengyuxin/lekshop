package com.chengyu.core.i18n;

import com.chengyu.core.utils.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

@Component
public class LocaleMessageSourceUtil {
    @Resource
    private MessageSource messageSource;

    public String getMessage(String code) {
        return getMessage(code, null);
    }

    /**
     *
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @return
     */
    public String getMessage(String code, Object[] args){
        return getMessage(code, args, "");
    }

    /**
     *
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @param defaultMessage : 没有设置key的时候的默认值.
     * @return
     */
    public String getMessage(String code,Object[] args,String defaultMessage){
        //这里使用比较方便的方法，不依赖request.
        Locale locale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage(code, args, defaultMessage, locale);
        if(StringUtils.isNotBlank(msg)){
            return msg;
        }else{
            return code;
        }
    }
}
