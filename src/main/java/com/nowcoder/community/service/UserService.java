package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * ClassName: UserService
 * Description:
 * date: 2021/2/19 下午6:52
 *
 * @author yimingyu
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class UserService implements CommunityConstant {

    @Resource
    private UserMapper userMapper;

    @Resource
    private TemplateEngine templateEngine;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Resource
    private MailClient mailClient;


    public User getUserById(int id){
        return userMapper.findUserById(id);
    }

    public Map<String, Object> register(User user){
        if (user == null){
            throw new IllegalArgumentException("参数不能为空");
        }
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(user.getUserName())){
            map.put("usernameMsg", "用户名不能为空");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())){
            map.put("passwordMsg", "密码不能为空");
            return map;
        }
        if (StringUtils.isBlank(user.getEmail())){
            map.put("emailMsg", "邮箱不能为空");
            return map;
        }
        User u = userMapper.findUserByName(user.getUserName());
        if (null != u){
            map.put("usernameMsg", "用户名已存在");
            return map;
        }
        u = userMapper.findUserByEmail(user.getEmail());
        if (null != u){
            map.put("emailMsg", "邮箱已存在");
            return map;
        }
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(CommunityUtil.generateUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.saveUser(user);
        System.out.println(user.getId());
        user = userMapper.findUserByName(user.getUserName());
        //发送激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        String url = this.domain + this.contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
        System.out.println("url = "+url);
        context.setVariable("url", url);
        String process = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "激活账号", process);
        return map;
    }

    public int activation(int userId, String code){
        User user = userMapper.findUserById(userId);
        if (user.getStatus() == 1){
            return ACTIVATION_REPEAT;
        }
        if (user.getActivationCode().equals(code)){
            userMapper.updateStatus(userId, 1);
            return ACTIVATION_SUCCESS;
        }else{
            return ACTIVATION_FAIL;
        }

    }
}
