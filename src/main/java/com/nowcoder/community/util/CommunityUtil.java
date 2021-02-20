package com.nowcoder.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * ClassName: CommunityUtil
 * Description:
 * date: 2021/2/20 下午7:20
 *
 * @author yimingyu
 * @version 1.0
 * @since JDK 1.8
 */
public class CommunityUtil {

    /**
     * 生成随机数
     * @return
     */
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * md5加密
     * @param key   设置的密码 + 随机字符串
     * @return
     */
    public static String md5(String key){
        if (StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
