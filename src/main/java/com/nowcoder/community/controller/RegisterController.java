package com.nowcoder.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ClassName: RegisterController
 * Description:
 * date: 2021/2/20 下午6:28
 *
 * @author yimingyu
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class RegisterController {

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(){
        return "site/register";
    }
}
