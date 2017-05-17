package com.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 讯 on 2017/5/14.
 */
public class test {
    public static void main(String args[]){
        HttpServletRequest request = null;

        HttpSession session= request.getSession();
        String userinfo=session.getId();
    }
}
