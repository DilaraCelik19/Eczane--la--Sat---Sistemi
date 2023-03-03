/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

/**
 *
 * @author User-Pc
 */
public class Hasta {
    private String tcNo;
    private String adSoyad;
    private String dogumTarihi;
    private String cinsiyet;

    public Hasta() {
    }
   

    public Hasta(String tcNo, String adSoyad, String dogumTarihi, String cinsiyet) {
        this.tcNo = tcNo;
        this.adSoyad = adSoyad;
        this.dogumTarihi = dogumTarihi;
        this.cinsiyet = cinsiyet;
       
    }

    public String getTcNo() {
        return tcNo;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public String getDogumTarihi() {
        return dogumTarihi;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

   

    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public void setDogumTarihi(String dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

   
}
