package main.java.student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory=new Configuration().configure("main/resources/hibernate.cfg.xml")
                .addAnnotatedClass(main.java.student.Student.class)
                .buildSessionFactory();

        //create session
        Session session=factory.getCurrentSession();

        try{
            //start a transaction
            session.beginTransaction();

            int studentId=1;

            //retrieve student based on the id: primary key
            System.out.println("Getting Student with id: "+studentId);
            main.java.student.Student myStudent=session.get(main.java.student.Student.class,studentId);

            System.out.println("Get complete: "+myStudent);

            //Deleting Student
            System.out.println("Deleting Student...");
            session.delete(myStudent);

            //Deleting student via HQL
            session.createQuery("delete Student where id=2")
                    .executeUpdate();

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");


        }
        finally {
            factory.close();
        }
    }
}
