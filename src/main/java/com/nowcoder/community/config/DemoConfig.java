package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * ClassName: DemoConfig
 * Description:
 * date: 2021/2/18 下午10:58
 *
 * @author yimingyu
 * @version 1.0
 * @since JDK 1.8
 */
@Configuration
public class DemoConfig {

    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
