package dziekanatapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DziekanatFasada {
    private static final String dbURL1 = "jdbc:derby://localhost:1527/studentsDB";
    private static final String dbURL2 = "jdbc:derby://localhost:1527/wydzialyDB";

    public DziekanatFasada() {
    }
    
    public void addStudent(Student student){
        try {
            Connection conn1 = DriverManager.getConnection(dbURL1,"app","app"); 
            Connection conn2 = DriverManager.getConnection(dbURL2,"app","app");
            Statement s1 = conn1.createStatement();
            Statement s2 = conn2.createStatement();
            String sql = "insert into kurs(uczelnia, wydzial, kierunek) values('" + student.getKurs().getUczelnia() + "', '" + student.getKurs().getWydzial()+
                    "', '"+ student.getKurs().getKierunek() + "')";
            s2.executeUpdate(sql, 1);
            int id = 0;
            ResultSet rs = s2.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            student.setIdkursu(id);
            sql = "insert into student(nr_indeksu, imie, nazwisko, adres, miasto, idkursu) values(" +
                    student.getNrIndeksu()+", '" +student.getImie()+"', '"+student.getNazwisko()+"', '" +student.getAdres()+ 
                    "', '"+ student.getMiasto()+ "', " + id +")";
            s1.executeUpdate(sql, 1);
            s1.close();
            s2.close();
        } catch (Exception except) {
            except.printStackTrace();
        }
    }
}
