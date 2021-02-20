package com.nowcoder.community.dao;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.javassist.runtime.Desc;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * ClassName: DiscussPostMapper
 * Description:
 * date: 2021/2/19 下午3:38
 *
 * @author yimingyu
 * @version 1.0
 * @since JDK 1.8
 */
@Mapper
public interface DiscussPostMapper {

    @SelectProvider(type = DiscussPostMapperProvider.class, method = "findDiscussPosts")
    List<DiscussPost> findDiscussPosts(@Param("userId") int userId,
                                       @Param("start") int start,
                                       @Param("end") int end);


    @SelectProvider(type = DiscussPostMapperProvider.class, method = "getDiscussPostCount")
    int getDiscussPostCount(@Param("userId") int userId);


    class DiscussPostMapperProvider{
        public String findDiscussPosts(int userId, int start, int end){
            return new SQL(){
                {
                   SELECT("*");
                   FROM("discuss_post");
                   WHERE("status != 2");
                   if (userId >= 0){
                       WHERE("user_id = #{userId}");
                   }
                   ORDER_BY("type desc");
                   ORDER_BY("create_time desc");

                   LIMIT("#{start}, #{end}");
                }
            }.toString();
        }

        public String getDiscussPostCount(int userId){
            return new SQL(){
                {
                    SELECT("count(id)");
                    FROM("discuss_post");
                    WHERE("status != 2");
                    if (userId != -1){
                        WHERE("user_id = #{userId}");
                    }
                }
            }.toString();
        }
    }

    @Insert("insert into discuss_post(user_id, title, content, type, status, create_time, comment_count, score)" +
            "values(#{userId}, #{title}, #{content}, #{type}, #{status}, #{createTime}, #{commentCount}, #{score})")
    int saveDiscussPost(DiscussPost discussPost);
}
