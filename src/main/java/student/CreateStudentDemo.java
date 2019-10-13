package main.java.student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {
    public static void main(String[] args){


        //create Session Factory
        SessionFactory factory=new Configuration()
                                    .configure("main/resources/hibernate.cfg.xml")
                                    .addAnnotatedClass(Student.class)
                                    .buildSessionFactory();

        //create Session
        Session session=factory.getCurrentSession();

        try {
            //use the session object to save Java object

            //create a student object
            System.out.println("Creating a Student..");
            Student tempStudent=new Student("Paul","Walker","paulwalker@gmail.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the student...");
            session.save(tempStudent);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
