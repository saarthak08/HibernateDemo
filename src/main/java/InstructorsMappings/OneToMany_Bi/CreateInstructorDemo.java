package InstructorsMappings.OneToMany_Bi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
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

            main.java.InstructorsMappings.OnetoOne.Instructor tempInstructor=new main.java.InstructorsMappings.OnetoOne.Instructor("Alien","Telusko","teluskolearning@gmail.com");
            main.java.InstructorsMappings.OnetoOne.InstructorDetail tempDetail=new main.java.InstructorsMappings.OnetoOne.InstructorDetail("http://youtube.com/telusko","Programming");
            tempInstructor.setInstructorDetail(tempDetail);
            // start a transaction
            session.beginTransaction();
            // save the student objects

            main.java.InstructorsMappings.OnetoOne.Instructor tempInstructor1=session.get(main.java.InstructorsMappings.OnetoOne.Instructor.class,id);
            Course tempCourse1=new Course("Air Guitar - The Ultimate Guide");
            Course tempCourse2=new Course("The Pinball Masterclass");

            tempInstructor1.add(tempCourse1);
            tempInstructor1.add(tempCourse2);

            session.save(tempCourse1);
            session.save(tempCourse2);
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

            session.close();
            factory.close();
        }
    }
}
