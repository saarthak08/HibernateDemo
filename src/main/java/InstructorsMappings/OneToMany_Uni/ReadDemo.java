package InstructorsMappings.OneToMany_Uni;

import InstructorsMappings.OneToMany_Bi.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadDemo {
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

            session.beginTransaction();

            int theId=1;
            Course tempCourse=session.get(Course.class,theId);

            System.out.println(tempCourse);

            // commit transaction

            System.out.println(tempCourse.getReviews());

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
