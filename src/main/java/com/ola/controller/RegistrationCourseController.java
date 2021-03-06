package com.ola.controller;

import com.ola.registration.model.dao.CourseDAO;
import com.ola.registration.model.dao.ScheduleDAO;
import com.ola.registration.model.dao.StudentDAO;
import com.ola.registration.model.dao.daoimpl.CourseDAOImpl;
import com.ola.registration.model.dao.daoimpl.ScheduleDAOImpl;
import com.ola.registration.model.dao.daoimpl.StudentDAOImpl;
import com.ola.registration.model.entity.Course;
import com.ola.registration.model.entity.Schedule;
import com.ola.registration.model.entity.Student;

import java.util.Date;
import java.util.List;

public class RegistrationCourseController {

       private ScheduleDAO   scheduleDAO ;
       private StudentDAO    studentDAO  ;
       private CourseDAO     courseDAO   ;

    public RegistrationCourseController(String url,String userName , String password) {

        this.scheduleDAO=new ScheduleDAOImpl(url,userName,password);
        this.courseDAO=new CourseDAOImpl(url,userName,password);
        this.studentDAO=new StudentDAOImpl(url,userName,password);
    }

     public void register(String studentId,String courseId){

         Student student=studentDAO.findById(studentId);
         Course  course=courseDAO.findCourseById(courseId);


           if(student == null){

             throw new RuntimeException("Student "+ studentId+ " not exist");
         }

           if(course == null){

               throw new RuntimeException("Course "+ courseId+ " not exist");
           }

          Date  date= course.getStartingDate();

           if (new Date().compareTo(date)>= 1){

               throw new RuntimeException("StartingDate"+date+"not exist");
           }

           double capacity=course.getCapacity();

          List<Student> register= scheduleDAO.findByCourseId(courseId);

          if (capacity <= register.size()){

              throw new RuntimeException("full Capacity");
          }

          boolean isRegister=scheduleDAO.isRegister(studentId,courseId);

          if (isRegister){

              throw new RuntimeException(" Student "+studentId+ " already registered in that course ");
          }

           String scheduleId=scheduleDAO.scheduleIdCreator();

           scheduleDAO.saveNewSchedule(new Schedule(scheduleId,studentId,courseId));

     }

}
