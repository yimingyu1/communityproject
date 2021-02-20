package com.nowcoder.community.controller;

import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.MailClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

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
public class RegisterController implements CommunityConstant {

    @Resource
    private UserService userService;

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(){
        return "site/register";
    }

    @PostMapping("/register")
    public String register(Model model, User user){
        Map<String, Object> map = userService.register(user);
        System.out.println(map);
        if (map.size() == 0){
            model.addAttribute("msg", "注册成功，我们已向您的邮箱发送了一封激活邮件，请尽快激活");
            model.addAttribute("target", "/index");
            return "site/operate-result";
        }else {
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));
            model.addAttribute("user", user);
            return "site/register";
        }
    }

    // http://localhost:8080/community/activation/0/b5289e56135b4d8a9e5c493c4f411ee2
    @GetMapping("/activation/{userId}/{code}")
    public String activation(Model model, @PathVariable("userId") int userId, @PathVariable("code") String code){
        int activationCode = userService.activation(userId, code);
        if (activationCode == ACTIVATION_REPEAT){
            model.addAttribute("msg", "您的账号已经是激活状态！");
            model.addAttribute("target", "/index");
        }else if (activationCode == ACTIVATION_SUCCESS){
            model.addAttribute("msg", "你的账号已激活成功，可以正常使用");
            model.addAttribute("target", "/login");
        }else {
            model.addAttribute("msg", "激活失败，激活码不正确！");
            model.addAttribute("target", "/index");
        }
        return "site/operate-result";
    }

    @GetMapping("/login")
    public String login(){
        return "site/login";
    }
}
