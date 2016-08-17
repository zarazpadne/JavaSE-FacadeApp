/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dziekanatapp;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author k
 */
@Entity
@Table(name = "STUDENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByNrIndeksu", query = "SELECT s FROM Student s WHERE s.nrIndeksu = :nrIndeksu"),
    @NamedQuery(name = "Student.findByImie", query = "SELECT s FROM Student s WHERE s.imie = :imie"),
    @NamedQuery(name = "Student.findByNazwisko", query = "SELECT s FROM Student s WHERE s.nazwisko = :nazwisko"),
    @NamedQuery(name = "Student.findByAdres", query = "SELECT s FROM Student s WHERE s.adres = :adres"),
    @NamedQuery(name = "Student.findByMiasto", query = "SELECT s FROM Student s WHERE s.miasto = :miasto"),
    @NamedQuery(name = "Student.findByIdkursu", query = "SELECT s FROM Student s WHERE s.idkursu = :idkursu")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NR_INDEKSU")
    private Integer nrIndeksu;
    @Basic(optional = false)
    @Column(name = "IMIE")
    private String imie;
    @Basic(optional = false)
    @Column(name = "NAZWISKO")
    private String nazwisko;
    @Column(name = "ADRES")
    private String adres;
    @Column(name = "MIASTO")
    private String miasto;
    @Column(name = "IDKURSU")
    private Integer idkursu;
    private Kurs kurs;
    private static final String dbURL1 = "jdbc:derby://localhost:1527/studentsDB";

    public Student() {
    }

    public Student(Integer nrIndeksu) {
        try {
            Connection connection = DriverManager.getConnection(dbURL1,"app","app"); 
            Statement s = connection.createStatement();
            String sql = "SELECT imie, nazwisko, adres, miasto, idkursu FROM student WHERE nr_indeksu = " + nrIndeksu;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                this.nrIndeksu = nrIndeksu;
                this.imie = rs.getString(1);
                this.nazwisko = rs.getString(2);
                this.adres = rs.getString(3);
                this.miasto = rs.getString(4);
                this.idkursu = rs.getInt(5);
                this.kurs = new Kurs(idkursu);
            } 
            s.close();
        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    public Student(Integer nrIndeksu, String imie, String nazwisko, String adres, String miasto) {
        this.nrIndeksu = nrIndeksu;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
        this.miasto = miasto;
    }

    public Integer getNrIndeksu() {
        return nrIndeksu;
    }

    public void setNrIndeksu(Integer nrIndeksu) {
        this.nrIndeksu = nrIndeksu;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }
    
    public Kurs getKurs() {
        return kurs;
    }

    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
        this.idkursu = kurs.getIdKurs();
    }

    public Integer getIdkursu() {
        return idkursu;
    }

    public void setIdkursu(Integer idkursu) {
        this.idkursu = idkursu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nrIndeksu != null ? nrIndeksu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.nrIndeksu == null && other.nrIndeksu != null) || (this.nrIndeksu != null && !this.nrIndeksu.equals(other.nrIndeksu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nrIndeksu + ", " + this.imie + ", " + this.nazwisko + ", " +
                this.getKurs().getUczelnia()+ ", " + this.getKurs().getWydzial() + ", "+ this.getKurs().getKierunek() +
                ", " + this.getAdres() + ", " + this.getMiasto();
    }
}
