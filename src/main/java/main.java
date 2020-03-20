import com.ola.controller.*;
import com.ola.registration.model.entity.Course;
import com.ola.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {

    public static void main(String[] args) {

        RegistrationCourseController controller=new RegistrationCourseController("jdbc:mysql://localhost:3306/student1?useSSL=false","root", "root@JEA");

        //controller.register("3","1");

        LoginController controller1=new LoginController("jdbc:mysql://localhost:3306/student1?useSSL=false","root", "root@JEA");

        controller1.isSignUp("1","123");

        CoursesController coursesController=new CoursesController("jdbc:mysql://localhost:3306/student1?useSSL=false","root", "root@JEA");
            /*List<Course> courses=coursesController.showCourse();

            for (int i=0;i<courses.size();i++){


                System.out.println(courses.get(i).toString());
            }*/

        Map<String,String> serviceParam=new HashMap<String, String>();

        serviceParam.put(Constants.STUDENT_ID.getValue(),"0");
        serviceParam.put(Constants.FIRST_NAME.getValue()," ");
        serviceParam.put(Constants.LAST_NAME.getValue(),"ka");
        serviceParam.put(Constants.EMAIL.getValue(),"al@l1");
        serviceParam.put(Constants.PASSWORD.getValue(),"123");

        StudentController controller2=new StudentController("jdbc:mysql://localhost:3306/student1?useSSL=false","root","root@JEA");

        //System.out.println(controller2.signUpClient(serviceParam));

        LogoutController controller3=new LogoutController("jdbc:mysql://localhost:3306/student1?useSSL=false","root","root@JEA");

        //controller3.logoutUser("1");

        AuthenticationController authenticationController=new AuthenticationController("jdbc:mysql://localhost:3306/student1?useSSL=false","root","root@JEA"
        );

        //System.out.println(authenticationController.isAuthenticate("9888","1"));

        ScheduleController scheduleController=new ScheduleController("jdbc:mysql://localhost:3306/student1?useSSL=false","root", "root@JEA"
        );
      /*   List<Course> courses=scheduleController.showMyCourse("1");

         for(int i=0; i<courses.size();i++){

             System.out.println(courses.get(i));
         }*/

      //scheduleController.deleteScheduleByUser("3","2");
    }
}
