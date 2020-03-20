package com.ola.controller;

import com.ola.registration.model.dao.StudentDAO;
import com.ola.registration.model.dao.daoimpl.StudentDAOImpl;
import com.ola.registration.model.entity.BuildStudentBuilderConstructor;
import com.ola.registration.model.entity.Student;
import com.ola.utils.Constants;

import java.time.Year;
import java.util.Map;

public class StudentController {

    private StudentDAO studentDAO;

    public StudentController(String url,String userName,String password) {

        this.studentDAO=new StudentDAOImpl(url,userName,password);

    }

    public String signUpClient(Map<String ,String> param){

        Student student=studentDAO.findById(param.get(Constants.STUDENT_ID.getValue()));

        if (student!=null){

            throw new RuntimeException("Student with id :- "+Constants.STUDENT_ID.getValue()+"already registered");
        }

        if (param.get(Constants.FIRST_NAME.getValue())==null ){

            throw new RuntimeException("can not save null value firstName ");
        }
        if (param.get(Constants.LAST_NAME.getValue())==null){

            throw new RuntimeException("can not save null value LastName ");
        }
        Student studentEmail=studentDAO.findByEmail(param.get(Constants.EMAIL.getValue()));

        if (studentEmail!=null){

            throw new RuntimeException(" email used or null ");
        }
        if (param.get(Constants.PASSWORD.getValue())==null){

            throw new RuntimeException("can not save null value password ");
        }

        param.put(Constants.USER_TYPE.getValue(),"student");
         studentDAO.save(student(param));

        return "successfully registered ";


    }

    private Student student(Map<String,String> param){

      return   BuildStudentBuilderConstructor.buildStudentFromUserInput(param.get(Constants.STUDENT_ID.getValue()),
              param.get(Constants.FIRST_NAME.getValue()),param.get(Constants.LAST_NAME.getValue()), param.get(Constants.EMAIL.getValue())
                      ,param.get(Constants.PASSWORD.getValue()), String.valueOf(Year.now()),param.get(Constants.USER_TYPE.getValue()));

    }

    public String deleteStudentByAdmin(String adminId,String studentId){

        Student user=studentDAO.findById(adminId);

        if (user==null || !("admin".equals(user.getUserType())) ){

            throw new RuntimeException("you not have permission to delete ");
        }

        Student student=studentDAO.findById(studentId);

        if (student==null ){

            throw new RuntimeException("student or courseId not found ");
        }

        studentDAO.deleteStudentById(studentId);

        return "Student deleted ";
    }
}
