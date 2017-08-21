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
}