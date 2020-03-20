package com.ola.controller;

import com.ola.registration.model.dao.LoginDAO;
import com.ola.registration.model.dao.StudentDAO;
import com.ola.registration.model.dao.daoimpl.LoginDAOImpl;
import com.ola.registration.model.dao.daoimpl.StudentDAOImpl;
import com.ola.registration.model.entity.Course;
import com.ola.registration.model.entity.Login;
import com.ola.registration.model.entity.Student;

import java.time.LocalDateTime;
import java.util.List;

public class LoginController {

    private StudentDAO studentDAO;
    private LoginDAO   loginDAO;

    public LoginController(String url,String userName,String password) {

        this.studentDAO=new StudentDAOImpl(url,userName,password);
        this.loginDAO=new LoginDAOImpl(url,userName,password);
    }

    public String isSignUp(String studentId,String password){

        Student student=studentDAO.findById(studentId);

         if(student==null){

             throw new RuntimeException("student "+studentId+"Not Found please Check ID Or SignUp ");
         }

         boolean isAuthenticate=studentDAO.authenticateUser(studentId,password);

         if (!isAuthenticate){

             throw new RuntimeException("password for student"+studentId+"not Correct");
         }


         if (loginDAO.isLogged(studentId)){

             throw new RuntimeException("you are logged in ");
         }

          String token= token();

          loginDAO.save(new Login(studentId,token,LocalDateTime.now()));

          String user=student.getUserType();


        return token+"\t"+user;
    }

      private String token() {

        return String.valueOf(1000 +(int)(Math.random()*(9999-1000+1)));
    }

    public String deleteLoginStudent(String adminId,String studentId){

        Student user=studentDAO.findById(adminId);

        if (user==null || !("admin".equals(user.getUserType())) ){

            throw new RuntimeException("you not have permission to delete ");
        }

        Student student=studentDAO.findById(studentId);

        if (student==null ){

            throw new RuntimeException("student or courseId not found ");
        }

        loginDAO.deleteLogged(studentId);

        return "student logged deleted ";

    }
    public List<Login> showLoginStudent(String adminId){

        Student user=studentDAO.findById(adminId);

        if (user==null || !("admin".equals(user.getUserType())) ){

            throw new RuntimeException("you not have permission to delete ");
        }

         List<Login> listLogin=loginDAO.showLogin();

        return listLogin;
    }
}
