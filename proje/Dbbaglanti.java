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
public class Dbbaglanti {
    private String kullaniciAdi = "root";
    private String parola = "";
    private String host = "localhost";
    private String dtb = "hasta";
    private int port = 3306;
    private Connection conn=null;
  

    public Dbbaglanti() {
       
    }
    
    
     public void temizle(String tc,String adSoyad) {  
       //Burada database ile connection kurarak istediğimiz kişiyi tc sine göre siliyoruz.
       
     
        String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dtb + "?useUnicode=true&characterEncoding=utf8";
        baglan();  
           try {
             System.out.println("Başardık");
            this.conn=DriverManager.getConnection(url,kullaniciAdi,parola);
            PreparedStatement uygula=conn.prepareStatement("delete from sistemegiris where tc=?");
            uygula.setString(1, tc);
          // uygula.setString(2, ad);
           //uygula.setString(3, date);
           uygula.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Başarısız");
            e.printStackTrace();
        }
    }       
    public void kaydolKaydet(String tc,String adSoyad,String cinsiyet,String dogumTarihi){
        //Hastayı database kaydederken connection kuruyoruz ve insert keyword ünü kullanarak database ekliyoruz.
       //
       
        String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dtb + "?useUnicode=true&characterEncoding=utf8";
    
    baglan(); 
        try {
             System.out.println("Başardık");
            this.conn=DriverManager.getConnection(url,kullaniciAdi,parola);
            Statement stm=conn.createStatement();
            String query="insert into hasta values ('"+ tc+"','"+ adSoyad+"','"+ cinsiyet+"','"+ dogumTarihi+"')";
            stm.executeUpdate(query);
            conn.close();
        } catch (Exception e) {
            System.out.println("Başarısız");
            e.printStackTrace();
        }
    }
    
  public void girisKaydet(String tcNo,String adSoyad,String eReceteNo){
         //İnsert keyword ünü kullanarak bilgileri database ekleyebiliyoruz. 
      
      String url="jdbc:mysql://" + this.host + ":" + "/" + this.dtb + "?useUnicode=true&characterEncoding=utf8";
       baglan();
         try {
             System.out.println("Başardık");
            this.conn=DriverManager.getConnection(url,kullaniciAdi,parola);
            Statement stm=conn.createStatement();
            String query="insert into sistemegiris values ('"+ tcNo+"','"+ adSoyad+"','"+ eReceteNo+"')";
            stm.executeUpdate(query);
            conn.close();
        } catch (Exception e) {
            System.out.println("Başarısız");
            e.printStackTrace();
        }
  }  
      public boolean buHastaVarMi(String tc){
      //Bu metotun amacı hasta daha önce kayıt edilmişse bir daha kaydetmemektir.
      //Databasede kişinin kayıtlı olup olmadığına bakıyor.Eğer kayıtlıysa uyarı veriyor.
          boolean buHastaVarMi=false;
         String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dtb + "?useUnicode=true&characterEncoding=utf8";
    
     baglan();
          try {
              int hastaSayisi=0;
              String query="select * from hasta where tc like ?";
              this.conn=DriverManager.getConnection(url,kullaniciAdi,parola);
              PreparedStatement pst=conn.prepareStatement(query);
              pst.setString(1,tc);
              ResultSet rs=pst.executeQuery();
              while(rs.next()){
                  hastaSayisi++;
              }
              if(hastaSayisi==0){
                 buHastaVarMi=false; 
              }else{
                  buHastaVarMi=true;
              }
          } catch (Exception e) {
              System.out.println("Hatalı");
          }
          return buHastaVarMi;
          
      }
      
      
       public void baglan(){
     //Metotlarda kodların daha kısa olması için bağlantı kurulmasını sağlayan metotdur.
           
           try {
             System.out.println("Başardık");
             
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Mysql connector yok");
            e.printStackTrace();
        }
  }
       public void ismiXIleBaşlayanHastalar(String x, ArrayList hastalar) {
       //Bu metotu hasta araması için yaptım.Database de ad ve soyadına göre arama yapıyor.
       //Aradıktan sonra hastanın ad ve soyadını hastalar listesine ekliyor.
           
           hastalar.clear();
         String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dtb + "?useUnicode=true&characterEncoding=utf8";
    
     baglan();
        try {
            this.conn=DriverManager.getConnection(url,kullaniciAdi,parola);
            String sorgu = "Select * From hasta where Ad_Soyad like ?";
            PreparedStatement pst=conn.prepareStatement(sorgu);
          
            pst.setString(1, x + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                hastalar.add(rs.getString("Ad_Soyad") + ": " + rs.getString("tc"));
            }

        } catch (SQLException ex) {
            System.out.println("ismiXIleBaşlayanOgrenciler metodu başarısız.");
        //    Logger.getLogger(Baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public boolean giristekiKisiAyniMi(String tc){
   //Burada da aynı şekilde hasta daha önce kaydedilmişse bizi uyarıyor.
      boolean kisiAyniMi=false;
      String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dtb + "?useUnicode=true&characterEncoding=utf8";
    
     baglan();
      try {
              int hastaSayisi=0;
              String query="select * from sistemegiris where tc like ?";
              this.conn=DriverManager.getConnection(url,kullaniciAdi,parola);
              PreparedStatement pst=conn.prepareStatement(query);
              pst.setString(1,tc);
              ResultSet rs=pst.executeQuery();
              while(rs.next()){
                  hastaSayisi++;
              }
              if(hastaSayisi==0){
                 kisiAyniMi=false; 
              }else{
                  kisiAyniMi=true;
              }
          } catch (Exception e) {
              System.out.println("Hatalı");
          }
          return kisiAyniMi;
  } 

   
}
