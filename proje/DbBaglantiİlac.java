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
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author User-Pc
 */
public class DbBaglantiİlac {
    
    private String kullaniciAdi = "root";
    private String parola = "";
    private String host = "localhost";
    private String dtb = "hasta";
    private int port = 3306;
    private Connection conn=null;

    public DbBaglantiİlac(){
        
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
    
    public void kaydet(String tcNo,String adSoyad,String ilac,int doz,String fiyat){
          
      //Hastanın ad soyadı,tc si,ilaç adı ,dozunu ve fiyatını database kaydeder.         
          String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dtb + "?useUnicode=true&characterEncoding=utf8";
           baglan();
           try {
             System.out.println("Başardık");
            this.conn=DriverManager.getConnection(url,kullaniciAdi,parola);
            Statement stm=conn.createStatement();
            String query="insert into ilacsatisi values ('"+ tcNo+"','"+ adSoyad+"','"+ ilac+"','"+ doz+"','"+ fiyat+"')";
            stm.executeUpdate(query);
            conn.close();
        } catch (Exception e) {
            System.out.println("Başarısız");
            e.printStackTrace();
        }
    }
    public void guncelle(String tc,String ad,String ilac,int doz){
      //İlaç adını güncelerek database tekarar yazar.  
        String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dtb + "?useUnicode=true&characterEncoding=utf8";
        baglan();
        try {
            System.out.println("Başardık");
            this.conn = DriverManager.getConnection(url, kullaniciAdi, parola);
            PreparedStatement uygula = conn.prepareStatement("update ilacsatisi set ilacAdı =?  where tc=?");

            uygula.setString(1, ilac);
            uygula.setString(2, tc);

            uygula.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Başarısız");
            e.printStackTrace();
        }
    }
    public void sil(String tc,String ad,String ilac,int doz){
        
//Tc sine bakarak silinmek istenen hastanın bilgilerini database den siler.
        String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dtb + "?useUnicode=true&characterEncoding=utf8";
        baglan();
        try {
            System.out.println("Başardık");
            this.conn = DriverManager.getConnection(url, kullaniciAdi, parola);
            PreparedStatement uygula = conn.prepareStatement("delete from ilacsatisi where tc=?");
            uygula.setString(1, tc);
            // uygula.setString(2, ad);
            
            uygula.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Başarısız");
            e.printStackTrace();
        }
    }
  
    
}
