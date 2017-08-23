package com.example.controller;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 讯 on 2017/7/26.
 */
@Controller
public class AdminController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String homepage() {
        return "admin";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String wrong403() {
        return "403";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String wrong404() {
        return "404";
    }

    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public String wrong500() {
        return "500";
    }

    @RequestMapping(value = "/503", method = RequestMethod.GET)
    public String wrong503() {
        return "503";
    }

    @RequestMapping(value = "/faq", method = RequestMethod.GET)
    public String faqs() {
        return "faq";
    }

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help() {
        return "help";
    }

    @RequestMapping(value = "/contopic", method = RequestMethod.GET)
    public String ControlTopic() {
        return "contopic";
    }

    @RequestMapping(value = "/{sid}/topiclist", method = RequestMethod.GET)
    public String ControlTopics(@PathVariable(value = "sid") int sid, Model model) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        String stm = "com.example.dao.TopicMapper.selectBySid";
        List<Topic> list = sqlSession.selectList(stm, sid);

//        model.addAttribute("datalist", list);//同一板块的帖子
        List<String> alist = new ArrayList<String>();
        Map<Topic,String>map=new HashMap<>();
        list.forEach(a -> {
            int uid = a.getTuid();

            String getuser = "com.example.dao.UserMapper.selectByPrimaryKey";
            User user = sqlSession.selectOne(getuser, uid);
            map.put(a,user.getUname());
            alist.add(user.getUname());
//            model.addAttribute("author", user.getUname());
        });
        model.addAttribute("datalist",map);

////model.addAttribute("list",alist);
        return "topiclist";
    }
    @RequestMapping(value = "/{sid}/topiclist", method = RequestMethod.POST)
    @ResponseBody
    public String deletetopic(String topicid){
        int id=Integer.parseInt(topicid);

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        String stm = "com.example.dao.TopicMapper.deleteByPrimaryKey";
//

        sqlSession.delete(stm,id);
        sqlSession.commit();
        return "ok";
    }

}