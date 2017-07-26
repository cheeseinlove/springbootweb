package com.example.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Created by 讯 on 2017/7/26.
 */
@Controller
public class AdminController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String homepage() {
        return "admin";
    }
}