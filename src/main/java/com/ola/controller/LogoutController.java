package com.ola.controller;

import com.ola.registration.model.dao.LoginDAO;
import com.ola.registration.model.dao.StudentDAO;
import com.ola.registration.model.dao.daoimpl.LoginDAOImpl;
import com.ola.registration.model.dao.daoimpl.StudentDAOImpl;
import com.ola.registration.model.entity.Student;

public class LogoutController {

    private LoginDAO loginDAO;
    private StudentDAO studentDAO;

    public LogoutController(String url,String userName,String password) {

        this.loginDAO=new LoginDAOImpl(url,userName,password);

        this.studentDAO=new StudentDAOImpl(url,userName,password);

    }

    public String logoutUser(String userId){

        Student student=studentDAO.findById(userId);

        if (student==null){

            throw new RuntimeException("user with id "+userId+"not found");
        }

         loginDAO.deleteLogged(userId);


        return "you are logged out";

    }
}
