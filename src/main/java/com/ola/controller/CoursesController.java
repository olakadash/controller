package com.ola.controller;

import com.ola.registration.model.dao.CourseDAO;
import com.ola.registration.model.dao.daoimpl.CourseDAOImpl;
import com.ola.registration.model.entity.Course;

import java.util.Date;
import java.util.List;

public class CoursesController {

    private CourseDAO courseDAO;

    public CoursesController(String url,String userName,String password) {

        this.courseDAO=new CourseDAOImpl(url,userName,password);

    }

    public List<Course> showCourse(){

        List<Course> listOfCourses=courseDAO.listOfCourse();

        for(int i=0;i<listOfCourses.size();i++){

            if (new Date().compareTo(listOfCourses.get(i).getStartingDate())>=1){

                listOfCourses.remove(i);
            }
        }

        return listOfCourses;
    }

    public Course showCourseByName(String courseName){

        Course course=courseDAO.findCourseByName(courseName);

        if ((new Date().compareTo(course.getStartingDate())>=1)){

            throw new RuntimeException("Course not exist ");
        }

        return course;
    }
}
