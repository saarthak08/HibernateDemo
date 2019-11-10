package main.java.student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Create3StudentsDemo {
    public static void main(String[] args){

        //create Session Factory
        SessionFactory factory=new Configuration()
                .configure("main/resources/hibernate.cfg.xml")
                .addAnnotatedClass(main.java.student.Student.class)
                .buildSessionFactory();

        //create Session
        Session session=factory.getCurrentSession();

        try {
            //use the session object to save Java object

            //create 3 student objects
            System.out.println("Creating 3 student objects..");
            main.java.student.Student tempStudent1=new main.java.student.Student("John","Doe","johndoe@gmail.com");
            main.java.student.Student tempStudent2=new main.java.student.Student("Mary","Walker","marywalker@gmail.com");
            main.java.student.Student tempStudent3=new main.java.student.Student("Bonita","Applebum","bonitaapplebaum@gmail.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
