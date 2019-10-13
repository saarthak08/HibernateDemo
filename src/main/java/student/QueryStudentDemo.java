package main.java.student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration().configure("main/resources/hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {
            //use the session object to save Java object

            //start a transaction
            session.beginTransaction();

            //Query Students
            List<Student> students=session.createQuery("from Student").getResultList();
            displayName(students);

            //query & display students with last name: "Gupta"
            students=(session.createQuery("from Student s where s.lastName='Gupta'").getResultList());
            displayName(students);

            //query : lastName="Gupta" or firstName="John"
            students=session.createQuery("from Student s where" +
                                        " s.lastName='Gupta' OR s.firstName='John'").getResultList();
            displayName(students);

            //query : email like gmail.com
            students=session.createQuery("from Student s where s.email LIKE  '%gmail.com'").getResultList();
            displayName(students);


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");


        } finally {
            factory.close();
        }
    }

    private static void displayName(List<Student> students) {
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
