package InstructorsMappings.ManyToMany;

import InstructorsMappings.OneToMany_Bi.Course;
import InstructorsMappings.OneToMany_Uni.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentDemo {
    public static void main(String[] args){
        SessionFactory factory=new Configuration()
                .configure("main/resources/hibernate.cfg.xml")
                .addAnnotatedClass(main.java.InstructorsMappings.OnetoOne.Instructor.class)
                .addAnnotatedClass(main.java.InstructorsMappings.OnetoOne.InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(main.java.student.Student.class)
                .buildSessionFactory();

        Session session=factory.getCurrentSession();
        try {
            // start a transaction
            session.beginTransaction();
            // save the student objects

            Course tempCourse=new Course("Pacman");


            System.out.println("Saving the course");
            session.save(tempCourse);

            main.java.student.Student tempStudent1=new main.java.student.Student("John","Doe","johndoe@gmail.com");
            main.java.student.Student tempStudent2=new main.java.student.Student("Mary","Walker","marywalker@gmail.com");
            main.java.student.Student tempStudent3=new main.java.student.Student("Bonita","Applebaum","bonitaapplebaum@gmail.com");

            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);
            tempCourse.addStudent(tempStudent3);

            System.out.println("Saving students...");

            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Successful" + session.getProperties());
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        finally {

            session.close();
            factory.close();
        }
    }
}
