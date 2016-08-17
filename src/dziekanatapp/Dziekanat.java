package dziekanatapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dziekanat {  
    private Student student;
    private Kurs kurs;
    
    private static String dbURL1 = "jdbc:derby://localhost:1527/studentsDB";
    private static String dbURL2 = "jdbc:derby://localhost:1527/wydzialyDB";
    
    private ArrayList<Student> studentsList = new ArrayList<Student>();

    public Dziekanat() {
        getStudentsFromDB();
    }

    
    public void getStudentsFromDB() {
        int nrIndeksu = -1;
        try {
            Connection connection = DriverManager.getConnection(dbURL1,"app","app"); 
            Statement s = connection.createStatement();
            String sql = "SELECT nr_indeksu FROM student";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                nrIndeksu = rs.getInt(1);
                Student student = new Student(nrIndeksu);
                studentsList.add(student);
            } 
            s.close();
        } catch (Exception except) {
            except.printStackTrace();
        }
    }
    
    public ArrayList<Student> getStudents() {
        return studentsList;
    }
}
