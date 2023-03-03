/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author User-Pc
 */
public class DbBaglantiERecete {
     private String kullaniciAdi = "root";
    private String parola = "";
    private String host = "localhost";
    private String dtb = "hasta";
    private int port = 3306;
    private Connection conn=null;
    
    public DbBaglantiERecete(){
        
    }
       public void baglan(){
      try {
             System.out.println("Başardık");
             
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Mysql connector yok");
            e.printStackTrace();
        }
  }
       public void kaydet(String tc,String adSoyad,String fiyat){
          
 //Parametre olarak tc,ad soyad ve fiyatı kaydediyor.
        String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dtb + "?useUnicode=true&characterEncoding=utf8";
        baglan();
          try {
             System.out.println("Başardık");
            this.conn=DriverManager.getConnection(url,kullaniciAdi,parola);
            Statement stm=conn.createStatement();
            String query="insert into erecete values ('"+ tc+"','"+ adSoyad+"','"+ fiyat+"')";
            stm.executeUpdate(query);
            conn.close();
        } catch (Exception e) {
            System.out.println("Başarısız");
            e.printStackTrace();
        }
       }
}
