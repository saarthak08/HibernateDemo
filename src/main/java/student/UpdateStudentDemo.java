package main.java.student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory=new Configuration().configure("main/resources/hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session=factory.getCurrentSession();

        try{
            //start a transaction
            session.beginTransaction();

            int studentId=1;

            //retrieve student based on the id: primary key
            System.out.println("Getting Student with id: "+studentId);
            Student myStudent=session.get(Student.class,studentId);

            System.out.println("Get complete: "+myStudent);

            //Updating Student
            System.out.println("Updating Student...");
            myStudent.setFirstName("Scooby");

            //update Email for all students
            System.out.println("Updating all students...");
            session.createQuery("update Student set email='foo@gmail.com'")
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
