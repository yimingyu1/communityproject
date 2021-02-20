package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * ClassName: UserMapper
 * Description:
 * date: 2021/2/19 下午1:45
 *
 * @author yimingyu
 * @version 1.0
 * @since JDK 1.8
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User findUserById(int id);

    @Select("select * from user where username = #{name}")
    User findUserByName(String name);

    @Select("select * from user where email = #{email}")
    User findUserByEmail(String email);

    @Insert("insert into user(username, password, salt, email, type, status, activation_code, header_url, create_time, update_time) values" +
            "(#{userName}, #{password}, #{salt}, #{email}, #{type}, #{status}, #{activationCode}, #{headerUrl}, #{createTime}, #{updateTime})")
    int saveUser(User user);

    @Update("update user set status = #{status} where id = #{id}")
    int updateStatus(int id, int status);

    @Update("update user set headerUrl = #{headerUrl} where id = #{id}")
    int updateHeader(int id, String headerUrl);

    @Update("update user set password = #{password} where id = #{id}")
    int updatePassword(int id, String password);
}
