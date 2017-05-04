package com.example.controller;

import com.example.dao.StudentDao;
import com.example.model.Student;
import com.example.model.Topic;
import com.example.model.User;
import com.example.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 讯 on 2017/4/7.
 */
@Controller
public class TestController {
static  int i = 3;
/*成电校园

 */
    @RequestMapping(value = "/campus",method = RequestMethod.GET)
    public String hello(Model model) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        String stm="com.example.dao.TopicMapper.select";
        List<Topic>list=sqlSession.selectList(stm);
        model.addAttribute("datalist",list);

        return "campus";
    }
    @RequestMapping(value = "/campus",method = RequestMethod.POST)
@ResponseBody
    public String topicList(String title,String content,Model model){
        Topic topic=new Topic();
        topic.setTid(i++);
        topic.setTsid(1);
        Date sqlDate = new java.sql.Date(new Date().getTime());
        topic.setPublishtime(sqlDate);
        topic.setContent(content);
        topic.setTitle(title);
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        String statement = "com.example.dao.TopicMapper.insert";
        String stm="com.example.dao.TopicMapper.select";
        int i=sqlSession.insert(statement,topic);
        sqlSession.commit();
        List<Topic>list=sqlSession.selectList(stm);
        model.addAttribute("datalist",list);

        sqlSession.close();
       return  "ok";
    }
    /*
    根据tid找到发帖人
     */

@RequestMapping(value ="/campus/{tid}/topic",method = RequestMethod.GET)
    public String viewtopic(@PathVariable(value = "tid")int tid ,Model model){
    SqlSession sqlSession=MyBatisUtil.getSqlSession();
    String gettopic="com.example.dao.TopicMapper.selectByPrimaryKey";
    Topic topic=sqlSession.selectOne(gettopic,tid);
int uid=topic.getTuid();//找到发帖人
    model.addAttribute("topic",topic);
String getuser="com.example.dao.UserMapper.selectByPrimaryKey";
User user=sqlSession.selectOne(getuser,uid);
model.addAttribute("author",user.getUname());
    return "topic";
}


    }

