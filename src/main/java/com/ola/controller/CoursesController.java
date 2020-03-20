package com.ola.controller;

import com.ola.registration.model.dao.CourseDAO;
import com.ola.registration.model.dao.StudentDAO;
import com.ola.registration.model.dao.daoimpl.CourseDAOImpl;
import com.ola.registration.model.dao.daoimpl.StudentDAOImpl;
import com.ola.registration.model.entity.BuildCourseBuilderConstructor;
import com.ola.registration.model.entity.Course;
import com.ola.registration.model.entity.Student;
import com.ola.utils.Constants;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CoursesController {

    private CourseDAO courseDAO;
    private StudentDAO studentDAO;

    public CoursesController(String url,String userName,String password) {

        this.courseDAO=new CourseDAOImpl(url,userName,password);
        this.studentDAO=new StudentDAOImpl(url,userName,password);

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

    public String deleteCourseByAdmin(String adminId,String courseId){

        Student user=studentDAO.findById(adminId);

        if (user==null || !("admin".equals(user.getUserType())) ){

            throw new RuntimeException("you not have permission to delete ");
        }

        Course course=courseDAO.findCourseById(courseId);


        if ( course==null ){

            throw new RuntimeException(" courseId not found ");
        }

        courseDAO.deleteCourseById(courseId);

        return " course deleted ";

    }

    public String saveNewCourse(Map<String,String> params ){

        Student user=studentDAO.findById(params.get(Constants.USER_ID.getValue()));

        if (user==null || !("admin".equals(user.getUserType())) ){

            throw new RuntimeException("you not have permission to delete ");
        }

   Course course=courseDAO.findCourseById(params.get(Constants.COURSE_ID.getValue()));

        if (course!=null){

            throw new RuntimeException ("Course  already exist ");

        }

        if (params.get(Constants.COURSE_Name.getValue())==null){

            throw new RuntimeException("can not save null value courseName ");
        }

        if (params.get(Constants.INSTRUCTOR.getValue())== null){

            throw new RuntimeException("can not save null value instructor ");

        }

        if (params.get(Constants.COURSE_CODE.getValue())==null){

            throw new RuntimeException("can not save null value courseCode ");
        }
        if (params.get(Constants.CAPACITY.getValue())==null){

            throw new RuntimeException("can not save null value capacity ");
        }
        if (params.get(Constants.STARTING_DATE.getValue())==null){

            throw new RuntimeException("can not save null value startingDate ");
        }
        if (params.get(Constants.DURATION.getValue())==null){

            throw new RuntimeException("can not save null value duration ");
        }
        if (params.get(Constants.HOURS.getValue())==null){

            throw new RuntimeException("can not save null value hours ");
        }

        courseDAO.save(course(params));

        return "successfully registered ";

    }

    private Course course(Map<String ,String> params){

            return   BuildCourseBuilderConstructor.buildCourseFromUserInput(params.get(Constants.COURSE_ID.getValue()),
                    params.get(Constants.COURSE_Name.getValue()),params.get(Constants.INSTRUCTOR.getValue()),
                    params.get(Constants.COURSE_CODE.getValue()),Double.valueOf(params.get(Constants.CAPACITY.getValue())),
                    java.sql.Date.valueOf(params.get(Constants.STARTING_DATE.getValue())),params.get(Constants.DURATION.getValue())
                    ,params.get(Constants.HOURS.getValue()));

    }
}
