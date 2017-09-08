package com.latyshonak.web.controllers;


import com.latyshonak.dao.hibernate.HibernateUtil;
import com.latyshonak.entity.Users;
import com.latyshonak.utils.PreValidation;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AutorizationController {

    @RequestMapping(value = "/Autorization", method = RequestMethod.POST)
    public String AutorizationPost(HttpSession session, HttpServletRequest request) {
        if(PreValidation.checkAutorizationLogin(request.getParameter("autorization-login"), request.getParameter("autorization-password"))){
         session.setAttribute("Autorization", "true");
         session.setAttribute("Login", request.getParameter("autorization-login"));
        }
        else {
            session.setAttribute("Autorization", "false");
        }
        return "redirect:Autorization";
    }

    @RequestMapping(value = "/Autorization", method = RequestMethod.GET)
    public String AutorizationGet(HttpSession session) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        List<Users> list = (hibernateSession.createQuery("from Users where login='" + session.getAttribute("Login") + "'").list());
        if(list.get(0).getRole() == 1 ) {
            session.setAttribute("Role", 1);
            return "redirect:ModeratorsRoom";
        }
        else {
            session.setAttribute("Role", 0);
            return "redirect:index";
        }
    }

}