package com.chengyu.core.service.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {
	@Bean
    public DefaultKaptcha producer(){

        /*Properties properties = new Properties();
        properties.put("kaptcha.border", "yes");
        properties.put("kaptcha.border.color", "black");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "20");
        properties.put("kaptcha.textproducer.char.length","4");
        properties.put("kaptcha.image.height","50");
        properties.put("kaptcha.textproducer.font.size","40");
        properties.put("kaptcha.textproducer.char.string","0123456789");
 
        properties.put("kaptcha.noise.impl","com.google.code.kaptcha.impl.DefaultNoise");
        properties.put("kaptcha.noise.color","white");
        properties.put("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.WaterRipple");

        properties.put("kaptcha.background.impl","com.google.code.kaptcha.impl.DefaultBackground");
        properties.put("kaptcha.background.clear.from","white");
        properties.put("kaptcha.background.clear.to","white");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);*/

        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.border.color", "black");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "10");
        properties.put("kaptcha.textproducer.char.length","4");
        properties.put("kaptcha.image.height","50");
        properties.put("kaptcha.textproducer.font.size","40");
        properties.put("kaptcha.textproducer.char.string","0123456789");

        properties.put("kaptcha.noise.impl","com.google.code.kaptcha.impl.DefaultNoise");
        properties.put("kaptcha.noise.color","white");
        properties.put("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.WaterRipple");

        properties.put("kaptcha.background.impl","com.google.code.kaptcha.impl.DefaultBackground");
        properties.put("kaptcha.background.clear.from","241,245,248");
        properties.put("kaptcha.background.clear.to","241,245,248");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}

