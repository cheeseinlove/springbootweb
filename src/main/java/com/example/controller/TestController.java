package com.example.controller;

import com.example.dao.StudentDao;
import com.example.model.Reply;
import com.example.model.Student;
import com.example.model.Topic;
import com.example.model.User;
import com.example.util.MyBatisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 讯 on 2017/4/7.
 */
@Controller

public class TestController {
static  int i = 3;
static  int rid = 1;

/*成电校园

 */
    @RequestMapping(value = "/campus",method = RequestMethod.GET)
    public String hello(Model model,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
//        分页插件pagehelper使用
      PageHelper.startPage(pageNum, pageSize);
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        String stm="com.example.dao.TopicMapper.select";
        List<Topic>list=sqlSession.selectList(stm);
        System.out.print("list.size--------- "+ list.size());
        PageInfo<Topic> p=new PageInfo<Topic>(list);
        //获得当前页
        model.addAttribute("pageNum", p.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize",p.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", p.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages",p.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage",p.isIsLastPage());
        System.out.print("-----pagesize:"+p.getPageSize()+"\n");
        System.out.print("-----pagenum:"+p.getPageNum()+"\n");
     model.addAttribute("datalist",list);
        model.addAttribute("page",p);


        return "campus";
    }
//    发帖
    @RequestMapping(value = "/campus",method = RequestMethod.POST)
    @ResponseBody
    public String topicList(String title, String content, Model model,HttpServletRequest request){
if (request.getSession().getAttribute("currentuser")==null){
    System.out.print("----------------"+ request.getSession().getAttribute("currentuser"));
    return "wrong";
}
User cuurrentuser= (User) request.getSession().getAttribute("currentuser");
        Topic topic=new Topic();
        topic.setTid(i++);
        topic.setTsid(1);
        Date sqlDate = new java.sql.Date(new Date().getTime());
        topic.setPublishtime(sqlDate);
        topic.setContent(content);
        topic.setTuid(cuurrentuser.getUid());
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
    public String viewtopic(@PathVariable(value = "tid")int tid ,Model model,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize){
    PageHelper.startPage(pageNum, pageSize);
    String addrep="com.example.dao.ReplyMapper.selectall";
    SqlSession sqlSession=MyBatisUtil.getSqlSession();
    List<Reply>replies=sqlSession.selectList(addrep,tid);//筛选回复对应的帖子
    String gettopic="com.example.dao.TopicMapper.selectByPrimaryKey";
    Topic topic=sqlSession.selectOne(gettopic,tid);
  int uid=topic.getTuid();//找到发帖人
    model.addAttribute("topic",topic);
//    model.addAttribute("content",topic.getContent());
String getuser="com.example.dao.UserMapper.selectByPrimaryKey";
User user=sqlSession.selectOne(getuser,uid);
model.addAttribute("author",user.getUname());


    System.out.print("-----replies:"+replies.size()+"\n");

    PageInfo<Reply> p=new PageInfo<Reply>(replies);

    //获得当前页
    model.addAttribute("pageNum", p.getPageNum());
    //获得一页显示的条数

    model.addAttribute("pageSize",p.getPageSize());
    //是否是第一页
    model.addAttribute("isFirstPage", p.isIsFirstPage());
    //获得总页数
    model.addAttribute("totalPages",p.getPages());
    //是否是最后一页
    model.addAttribute("isLastPage",p.isIsLastPage());
    System.out.print("-----pagesize:"+p.getPageSize()+"\n");
    System.out.print("-----pagenum:"+p.getPageNum()+"\n");
model.addAttribute("replylist",replies);
model.addAttribute("page",p);

    return "topic";
}
@RequestMapping(value ="/campus/{tid}/topic",method = RequestMethod.POST)
@ResponseBody
    public String viewtopic(@PathVariable(value = "tid")int tid,Model model,String content,HttpServletRequest request){
    if (request.getSession().getAttribute("currentuser")==null){
        System.out.print("----------------"+ request.getSession().getAttribute("currentuser"));
        return "wrong";
    }
    User cuurrentuser= (User) request.getSession().getAttribute("currentuser");
    SqlSession sqlSession=MyBatisUtil.getSqlSession();
//回复一次回复数加一
        String findtopic="com.example.dao.TopicMapper.selectByPrimaryKey";
        Topic topic=sqlSession.selectOne(findtopic,tid);
        topic.setReplycount(rid);
        sqlSession.update("com.example.dao.TopicMapper.updateByPrimaryKeySelective",topic);
        sqlSession.commit();

    Reply reply=new Reply();
    reply.setContent(content);
    reply.setTid(tid);
    reply.setRid(rid++);
reply.setUid(cuurrentuser.getUid());
    Date sqlDate = new java.sql.Date(new Date().getTime());
    reply.setTime(sqlDate);

    String addreply="com.example.dao.ReplyMapper.insertSelective";
    sqlSession.insert(addreply,reply);
    String addrep="com.example.dao.ReplyMapper.selectall";
    List<Reply>replies=sqlSession.selectList(addrep);
    model.addAttribute("replylist",replies);
    sqlSession.commit();
//    System.out.println("passed"+content);
        return "ok";


}


    }

