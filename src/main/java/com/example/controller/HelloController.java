package com.example.controller;

import com.example.dao.UserMapper;

import com.example.model.User;

import java.text.SimpleDateFormat;
import java.util.*;

import com.example.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * Created by 讯 on 2017/4/10.
 */

/**
 * Created by Intellij IDEA.
 *
 * @Author LUOLIANG
 * @Date 2016/8/2
 * @Comment
 */
@Controller

public class HelloController {
    static int id = 2;
    private UserMapper userMapper;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)

    public String home() {

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody

    public String login(String username, String password, ModelMap model, HttpServletRequest request) {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        String stm = "com.example.dao.UserMapper.selectUser";
        User user = new User();
        user.setUname(username);
        user.setUpassword(password);
        if (sqlSession.selectOne(stm, user) != null) {
//            保存登录状态实现session共享

            request.getSession().setAttribute("currentuser", sqlSession.selectOne(stm, user));

            return "ok";

        }
        return "no";


    }

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public List<User> data() {
/*
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
/**
 * 映射sql的标识字符串，
 * com.edu.hpu.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
 * getAllUsers是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
 */
        String statement = "com.example.dao.UserMapper.selectByPrimaryKey";//映射sql的标识字符串
//执行查询操作，将查询结果自动封装成List<User>返回*/
        SqlSession session = MyBatisUtil.getSqlSession();
        UserMapper userDao = session.getMapper(UserMapper.class);
        User user = new User();
        user.setUname("iMbatis");
        user.setUpassword("iMbatis");

        List<User> list = new ArrayList<User>();
        list.add(userDao.selectByPrimaryKey(1));

//使用SqlSession执行完SQL之后需要关闭SqlSession
        session.close();
        return list;
    }

    /*
    注册 ，默认返回的注册成功还需要判定账号是否存在
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)

    public String registion() {

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String registion(String username, String password) {
        User user = new User();
        user.setUpassword(password);
        user.setUname(username);
        Date sqlDate = new java.sql.Date(new Date().getTime());
        user.setUregtime(sqlDate);
        System.out.println(sqlDate);
        user.setUid(++id);
        user.setUsex(1);
        user.setUhead("null");
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        String statement = "com.example.dao.UserMapper.insert";
        int i = sqlSession.insert(statement, user);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(i);
        return "ok";
    }
}
