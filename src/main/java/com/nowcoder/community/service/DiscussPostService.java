package com.nowcoder.community.service;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: DiscussPostService
 * Description:
 * date: 2021/2/19 下午6:49
 *
 * @author yimingyu
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class DiscussPostService {

    @Resource
    private DiscussPostMapper discussPostMapper;

    public List<DiscussPost> findDiscussPosts(int userId, int start, int end){
        return discussPostMapper.findDiscussPosts(userId, start, end);
    }

    public int getDiscussPostCount(int userId){
        return discussPostMapper.getDiscussPostCount(userId);
    }

}
