package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User getUserById(int id){
        return userMapper.findUserById(id);
    }
}
