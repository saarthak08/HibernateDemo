package InstructorsMappings.OneToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorCourseDemo {

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

            Course tempCourse=session.get(Course.class,id);

            session.delete(tempCourse);
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
