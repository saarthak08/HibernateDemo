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

            int theId=5;
            Course tempCourse=session.get(Course.class,theId);


            System.out.println(tempCourse);

            // commit transaction

            System.out.println("Reviews: "+tempCourse.getReviews());

            session.getTransaction().commit();

            session.close();

            session=factory.getCurrentSession();

            session.beginTransaction();
            int theId1=5;
            Course tempCourse1=session.get(Course.class,theId1);


            System.out.println(tempCourse1);

            // commit transaction

            System.out.println("Reviews: "+tempCourse1.getReviews());

            System.out.println("Successful" + session.getProperties());
            session.getTransaction().commit();

        } catch (Exception e) {
            if (session.getTransaction() != null) {
                System.out.println(e.getMessage());
                //session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        finally {

            session.close();
            factory.close();
        }
    }
}
