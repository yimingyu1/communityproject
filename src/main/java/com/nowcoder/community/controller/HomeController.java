package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: HomeController
 * Description:
 * date: 2021/2/19 下午7:17
 *
 * @author yimingyu
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class HomeController {

    @Resource
    private DiscussPostService discussPostService;

    @Resource
    private UserService userService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Page page,
                               Model model) {
        System.out.println("---"+page);
        page.setRows(discussPostService.getDiscussPostCount(-1));
        List<DiscussPost> discussPosts = discussPostService.findDiscussPosts(-1, page.getOffSet(), page.getPageSize());

        page.setPath("/index");
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (discussPosts != null && discussPosts.size() > 0) {
            for (DiscussPost discussPost : discussPosts
            ) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", discussPost);
                User user = userService.getUserById(discussPost.getUserId());
                map.put("use", user);
                mapList.add(map);
            }
        }
        model.addAttribute("mapList", mapList);
        model.addAttribute("page", page);
        System.out.println(page);
        return "index";
    }
}
