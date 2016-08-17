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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "KURS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kurs.findAll", query = "SELECT k FROM Kurs k"),
    @NamedQuery(name = "Kurs.findByIdKurs", query = "SELECT k FROM Kurs k WHERE k.idKurs = :idKurs"),
    @NamedQuery(name = "Kurs.findByUczelnia", query = "SELECT k FROM Kurs k WHERE k.uczelnia = :uczelnia"),
    @NamedQuery(name = "Kurs.findByWydzial", query = "SELECT k FROM Kurs k WHERE k.wydzial = :wydzial"),
    @NamedQuery(name = "Kurs.findByKierunek", query = "SELECT k FROM Kurs k WHERE k.kierunek = :kierunek")})
public class Kurs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_KURS")
    private Integer idKurs;
    @Basic(optional = false)
    @Column(name = "UCZELNIA")
    private String uczelnia;
    @Basic(optional = false)
    @Column(name = "WYDZIAL")
    private String wydzial;
    @Column(name = "KIERUNEK")
    private String kierunek;
    private static String dbURL2 = "jdbc:derby://localhost:1527/wydzialyDB";

    public Kurs() {
    }

    public Kurs(Integer idKurs) {
        try {
            Connection connection = DriverManager.getConnection(dbURL2,"app","app"); 
            Statement s = connection.createStatement();
            String sql = "SELECT uczelnia, wydzial, kierunek FROM kurs WHERE id_kurs = " + idKurs;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                this.uczelnia = rs.getString(1);
                this.wydzial = rs.getString(2);
                this.kierunek = rs.getString(3);
                this.idKurs = idKurs;
            } 
            s.close();
        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    public Kurs(String uczelnia, String wydzial, String kierunek) {
        this.uczelnia = uczelnia;
        this.wydzial = wydzial;
        this.kierunek = kierunek;
    }
    
    public Integer getIdKurs() {
        return idKurs;
    }

    public void setIdKurs(Integer idKurs) {
        this.idKurs = idKurs;
    }

    public String getUczelnia() {
        return uczelnia;
    }

    public void setUczelnia(String uczelnia) {
        this.uczelnia = uczelnia;
    }

    public String getWydzial() {
        return wydzial;
    }

    public void setWydzial(String wydzial) {
        this.wydzial = wydzial;
    }

    public String getKierunek() {
        return kierunek;
    }

    public void setKierunek(String kierunek) {
        this.kierunek = kierunek;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKurs != null ? idKurs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kurs)) {
            return false;
        }
        Kurs other = (Kurs) object;
        if ((this.idKurs == null && other.idKurs != null) || (this.idKurs != null && !this.idKurs.equals(other.idKurs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dziekanatapp.Kurs[ idKurs=" + idKurs + " ]";
    }
    
}
