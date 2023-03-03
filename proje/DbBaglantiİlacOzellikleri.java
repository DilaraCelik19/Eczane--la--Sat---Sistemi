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
public class DbBaglantiİlacOzellikleri {
     private String kullaniciAdi = "root";
    private String parola = "";
    private String host = "localhost";
    private String dtb = "hasta";
    private int port = 3306;
    private Connection conn=null;
    
    public DbBaglantiİlacOzellikleri(){
        
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
       
   public void kaydet(String ilacinAdi, String ilacKullanimi,String stok,String ilacKodu){
     //İlacın adını ,kullanımını,stok sayısını ve kodunu database yazar. 
       String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dtb + "?useUnicode=true&characterEncoding=utf8";
       baglan();
         try {
             System.out.println("Başardık");
            this.conn=DriverManager.getConnection(url,kullaniciAdi,parola);
            Statement stm=conn.createStatement();
            String query="insert into ilacözellikleri values ('"+ ilacinAdi+"','"+ ilacKullanimi+"','"+ stok+"','"+ ilacKodu+"')";
            stm.executeUpdate(query);
            conn.close();
        } catch (Exception e) {
            System.out.println("Başarısız");
            e.printStackTrace();
        }
   }
   public void sil( String ilacAdi, String kullanim,String stok,String ilacKodu){
      
     //İlacı silmek istiyorsa  ilac koduna bakarak databaseden delete keyword ü yardımıyla siler.   
        String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dtb + "?useUnicode=true&characterEncoding=utf8";
       baglan();
        try {
             System.out.println("Başardık");
            this.conn=DriverManager.getConnection(url,kullaniciAdi,parola);
            PreparedStatement uygula=conn.prepareStatement("delete from ilacözellikleri where ilacKodu=?");
            uygula.setString(1, ilacKodu);
          
           uygula.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Başarısız");
            e.printStackTrace();
        }
   }
}
