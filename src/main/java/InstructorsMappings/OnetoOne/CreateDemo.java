
import InstructorsMappings.OneToMany_Bi.Course;
import InstructorsMappings.OneToMany_Uni.Review;
import main.java.InstructorsMappings.OnetoOne.Instructor;
import main.java.InstructorsMappings.OnetoOne.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {

        SessionFactory factory=new Configuration()
                .configure("main/resources/hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(main.java.student.Student.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session=factory.getCurrentSession();
        try {

            Instructor tempInstructor=new Instructor("Chad","Darby","luv2code@gmail.com");
            InstructorDetail tempDetail=new InstructorDetail("http://youtube.com/luv2code","Coding");
            tempInstructor.setInstructorDetail(tempDetail);
            // start a transaction
            session.beginTransaction();
            // save the student objects
            session.save(tempInstructor);
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
            factory.close();
        }
    }
}
