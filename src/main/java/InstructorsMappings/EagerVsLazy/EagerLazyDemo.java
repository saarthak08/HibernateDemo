package InstructorsMappings.EagerVsLazy;

import InstructorsMappings.OneToMany_Bi.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {

    public static void main(String[] args){

        SessionFactory factory=new Configuration()
                .configure("main/resources/hibernate.cfg.xml")
                .addAnnotatedClass(main.java.InstructorsMappings.OnetoOne.Instructor.class)
                .addAnnotatedClass(main.java.InstructorsMappings.OnetoOne.InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session=factory.getCurrentSession();
        try {
            int id=1;

            session.beginTransaction();

            main.java.InstructorsMappings.OnetoOne.Instructor tempInstructor=session.get(main.java.InstructorsMappings.OnetoOne.Instructor.class,id);
            System.out.println("Instructor: "+tempInstructor);

            System.out.println("Courses: " +tempInstructor.getCourses());

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
