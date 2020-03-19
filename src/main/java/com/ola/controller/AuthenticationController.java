package com.ola.controller;

import com.ola.registration.model.dao.LoginDAO;
import com.ola.registration.model.dao.StudentDAO;
import com.ola.registration.model.dao.daoimpl.LoginDAOImpl;
import com.ola.registration.model.dao.daoimpl.StudentDAOImpl;
import com.ola.registration.model.entity.Student;

public class AuthenticationController {

    private  LoginDAO loginDAO;


    public AuthenticationController(String url,String userName,String password) {

        this.loginDAO=new LoginDAOImpl(url,userName,password);


    }

    public boolean isAuthenticate(String token,String studentId){


       boolean isAliveUser= loginDAO.isAlive(token,studentId);

       if (isAliveUser){

           return true;
       }else {


           loginDAO.deleteLogged(studentId);

           return false;
       }
    }
}
