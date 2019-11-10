package InstructorsMappings.OneToMany_Uni;

import InstructorsMappings.OneToMany_Bi.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
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

            Course tempCourse=new Course("Pacman");

            tempCourse.addReview(new Review("Great Course"));
            tempCourse.addReview(new Review("Not so good"));
            tempCourse.addReview(new Review("What a dumb course"));


            System.out.println("Saving the course");
            session.save(tempCourse);

            tempCourse.setTitle("Pacman Changed");
            session.detach(tempCourse);

            tempCourse.setTitle("Pacman Changed 2");

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
