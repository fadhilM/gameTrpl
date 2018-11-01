/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl;
import gametrpl.kendaraan;
import gametrpl.property;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author ROG
 */
public class utang {
    double utang,sukubunga=9,tenor,angsuran,totalPembayaran;
    int turn,id;
    property property;
    kendaraan kendaraan;
    private static DecimalFormat df3 = new DecimalFormat(".###");
    boolean jaminan;
    
    public utang(kendaraan kendaraan, boolean jaminan){
        this.id = ThreadLocalRandom.current().nextInt(0,1000000  + 1);
        this.kendaraan=kendaraan;
        this.jaminan=jaminan;
    }
    
    public utang(property property,boolean jaminan){
        this.id = ThreadLocalRandom.current().nextInt(0,1000000  + 1);
        this.property=property;
        this.jaminan=jaminan;
    }

    public utang(double utang, double sukubunga, double tenor, int turn, property property) {
        this.utang = utang;
        this.sukubunga = sukubunga;
        this.tenor = tenor;
        this.turn = turn;
        this.property = property;
    }

    public utang(double utang, double sukubunga, double tenor, int turn, kendaraan kendaraan) {
        this.utang = utang;
        this.sukubunga = sukubunga;
        this.tenor = tenor;
        this.turn = turn;
        this.kendaraan = kendaraan;
    }
    
    public utang(){};

    public double getTotalPembayaran() {
        return totalPembayaran;
    }

    public void setTotalPembayaran(double totalPembayaran) {
        this.totalPembayaran = totalPembayaran;
    }

    public double getAngsuran() {
        return angsuran;
    }

    public void setAngsuran(double angsuran) {
        this.angsuran = angsuran;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isJaminan() {
        return jaminan;
    }

    public void setJaminan(boolean jaminan) {
        this.jaminan = jaminan;
    }
       
    public double getUtang() {
        return utang;
    }

    public void setUtang(double utang) {
        this.utang = utang;
        angsuran=hitungAngsuran(utang,tenor,sukubunga);
    }
    
    public void bayarUtang(){
        this.totalPembayaran-=angsuran;
        
    }
    
    public double bayarUtang(pemain pemain){
        utang-=angsuran;
        return pemain.getDana()-angsuran;
    }

    public double getSukubunga() {
        return sukubunga;
    }

    public void setSukubunga(double sukubunga) {
        this.sukubunga = sukubunga;
    }
    
    public double getBungaPerBulan(double sukuBunga,double pinjaman){
        return pinjaman*(sukuBunga/12)/100;
    }

    public double getTenor() {
        return tenor;
    }

    public void setTenor(double tenor) {
        this.tenor = tenor;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
    
    public double hitungAngsuran(double hutang,double tenor, double sukuBunga){
        double angsuran = ((hutang/tenor)+(getBungaPerBulan(sukuBunga, hutang)));
        angsuran = Double.parseDouble(df3.format(angsuran));
        return angsuran;
    }
    
    public void hitungBiayaPeminjaman(){
        totalPembayaran=angsuran*12;
    }
    
    public property getProperty() {
        return property;
    }

    public void setProperty(property property) {
        this.property = property;
    }

    public kendaraan getKendaraan() {
        return kendaraan;
    }

    public void setKendaraan(kendaraan kendaraan) {
        this.kendaraan = kendaraan;
    }
    
    
}
