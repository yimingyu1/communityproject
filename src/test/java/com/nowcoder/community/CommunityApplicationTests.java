package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextAware;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class CommunityApplicationTests {

    @Resource
    private SimpleDateFormat simpleDateFormat;

    @Resource
    private UserMapper userMapper;

    @Resource
    private DiscussPostMapper discussPostMapper;

    @Resource
    private MailClient mailClient;

    @Resource
    private TemplateEngine templateEngine;

    @Test
    void contextLoads() {
        System.out.println(simpleDateFormat.format(new Date()));
    }

    @Test
    public void test1(){



    }

    @Test
    public void test2(){
        DiscussPost discussPost = new DiscussPost();
        discussPost.setUserId(2);
        discussPost.setTitle("你好2021");
        discussPost.setContent("再见2020，你好2021，愿你灿烂如阳光，此生明媚不忧伤。要开心快乐过好每一天，提笔，写下这一年的总结。在红尘的渡口，写一页心情；在想念的季节，看一场雪落。从曾经的懵懂无知，到现在的成熟，时间教会了我们许多东西，也让我们失去了很多东西。");
        discussPost.setType(0);
        discussPost.setStatus(1);
        discussPost.setCreateTime(new Date());
        discussPost.setCommentCount(0);
        discussPost.setScore(0);
        for (int i = 0; i < 20; i++){
            discussPostMapper.saveDiscussPost(discussPost);
        }
        User user = new User();
        user.setUserName("张飞");
        user.setPassword("123456");
        user.setSalt("axiba");
        user.setEmail("1231231@qq.com");
        user.setType(0);
        user.setActivationCode("12138");
        user.setHeaderUrl("http://fqweasdffasdfasdfas.jpg");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        for (int i =0; i < 20; i++){
            userMapper.saveUser(user);
        }

    }

    @Test
    public void test3(){
        Context context = new Context();
        context.setVariable("username", "sunnday");
        String process = templateEngine.process("/mail/demo", context);
        mailClient.sendMail("371044760@qq.com", "你好  我是javaMailSender", process);
        System.out.println(process);
    }





}
