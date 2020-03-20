package com.ola.controller;

import com.ola.registration.model.dao.CourseDAO;
import com.ola.registration.model.dao.ScheduleDAO;
import com.ola.registration.model.dao.StudentDAO;
import com.ola.registration.model.dao.daoimpl.CourseDAOImpl;
import com.ola.registration.model.dao.daoimpl.ScheduleDAOImpl;
import com.ola.registration.model.dao.daoimpl.StudentDAOImpl;
import com.ola.registration.model.entity.Course;
import com.ola.registration.model.entity.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleController {

    private ScheduleDAO scheduleDAO;
    private StudentDAO  studentDAO;
    private CourseDAO   courseDAO;

    public ScheduleController(String url,String userName,String password) {

        this.scheduleDAO=new ScheduleDAOImpl(url,userName,password);
        this.studentDAO=new StudentDAOImpl(url,userName,password);
        this.courseDAO=new CourseDAOImpl(url,userName,password);
    }

    public List<Course> showMyCourse(String studentId){

        Student student=studentDAO.findById(studentId);

        if (student==null){

            throw new RuntimeException("student with id "+studentId+"not found ");
        }

       List<Course> courses=scheduleDAO.findByStudentId(studentId);

        return courses;

    }

    public String deleteScheduleByUser(String studentId,String courseId ){

        Student student=studentDAO.findById(studentId);

        if (student==null){

            throw new RuntimeException("student with id "+studentId+"not found ");
        }

        Course course=courseDAO.findCourseById(courseId);

        if (course==null){

            throw new RuntimeException("course with id "+courseId+"not found");
        }

        Date date=course.getStartingDate();

         date.setDate(date.getDate()+5);


        if (new Date().compareTo(date)>=1){

            throw new RuntimeException(" time limit for withdrawal is over ");

        }


        scheduleDAO.deleteSchedule(studentId,courseId);

        return "course deleted ";
    }

    public String deleteScheduleByAdmin(String adminId,String studentId, String courseId){

        Student user=studentDAO.findById(adminId);

        if (user==null || !("admin".equals(user.getUserType())) ){

            throw new RuntimeException("you not have permission to delete ");
        }

        Student student=studentDAO.findById(studentId);

        Course course=courseDAO.findCourseById(courseId);

        if (student==null || course==null ){

            throw new RuntimeException("student or courseId not found ");
        }

        scheduleDAO.deleteSchedule(studentId,courseId);

        return "course deleted ";
    }
}
