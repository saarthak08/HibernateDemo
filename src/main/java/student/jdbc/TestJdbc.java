package main.java.student.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args){
        //Testing JDBC Connection
        String jdbcUrl="jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
        String user="Saarthak";
        String pass="testing";

        try{
            System.out.println("Connection to database: "+jdbcUrl);
            Connection myConnection=
                    DriverManager.getConnection(jdbcUrl,user,pass);
            System.out.println("Connection Successful!!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
