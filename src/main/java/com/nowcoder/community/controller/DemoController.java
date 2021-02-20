package com.nowcoder.community.controller;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

/**
 * ClassName: DemoController
 * Description:
 * date: 2021/2/18 下午9:14
 *
 * @author yimingyu
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private DiscussPostMapper discussPostMapper;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello spring boot";
    }

    @GetMapping("/h1")
    public void h1(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println("name = "+name + " value = "+value);
        }
        System.out.println(request.getParameter("code"));
        response.setContentType("text/html;charset=utf-8");
        try (
                PrintWriter writer = response.getWriter();
                ){
            writer.write("<h1>牛客网</h1>");
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @RequestMapping(path = "/student", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(@RequestParam(name = "current", required = false, defaultValue = "1") int current,
                              @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some student";
    }

    @RequestMapping("/student/{id}")
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public String showStudent(Model model){
        model.addAttribute("name", "zhangsan");
        model.addAttribute("age", 21);
        return "teacher";
    }

    @GetMapping("/discuss")
    @ResponseBody
    public List<DiscussPost> discussList(){
        return discussPostMapper.findDiscussPosts(-1, 0, 9);
    }
}
