package main.java.student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory=new Configuration().configure("main/resources/hibernate.cfg.xml")
                                    .addAnnotatedClass(main.java.student.Student.class)
                                    .buildSessionFactory();

        //create session
        Session session=factory.getCurrentSession();

        try {
            //use the session object to save Java object

            //create a student object
            System.out.println("Creating a Student..");
            main.java.student.Student tempStudent=new main.java.student.Student("Saarthak","Gupta","saarthakgupta08@gmail.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the student...");
            session.save(tempStudent);

            //commit transaction
            session.getTransaction().commit();

            //find out the student's primary key
            System.out.println("Saved student. Generated ID: "+tempStudent.getId());

            //now get a session and start transaction
            session=factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id: primary key
            System.out.println("Getting Student with id: "+tempStudent.getId());
            main.java.student.Student myStudent=session.get(main.java.student.Student.class,tempStudent.getId());

            System.out.println("Get complete: "+myStudent);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");


        }
        finally {
            factory.close();
        }
    }
}
