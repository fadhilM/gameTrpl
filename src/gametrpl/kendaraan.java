/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package gametrpl;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author ROG
 */
public class kendaraan {
    int harga,id=0;
    boolean tipe;
    String nama;
    int turn=0;
    
    public kendaraan(String nama, int harga,boolean tipe) {
        System.out.println(this.id);
        this.harga = harga;
        this.nama = nama;
        this.tipe = tipe;
    }
    
    public void randomId(){
        this.id = ThreadLocalRandom.current().nextInt(0,1000000  + 1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getHarga() {
        return harga;
    }
    
    public void setHarga(int harga) {
        this.harga = harga;
    }
    
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public int getTurn() {
        return turn;
    }
    
    public void setTurn(int turn) {
        this.turn = turn;
    }
    
    public boolean isTipe() {
        return tipe;
    }
    
    public void setTipe(boolean tipe) {
        this.tipe = tipe;
    }
    
    public double hargaJual(pemain pemain){
        double hargaJual=0,umurEkonomis,penyusutan;
        int sisa,tahun,nilaiResidu,jangkaWaktu;
        double persenPenyusutan=0;
        if (tipe) {
            persenPenyusutan =25;
            umurEkonomis = 4;
        }else{
            persenPenyusutan =12.5;
            umurEkonomis = 8;
        }
        nilaiResidu=harga*40/100;
        jangkaWaktu = pemain.getTurn()-turn;
        sisa=jangkaWaktu%12;
        tahun = (jangkaWaktu-sisa)/12;
        penyusutan= (((persenPenyusutan/100)*harga)-nilaiResidu)/umurEkonomis;
        hargaJual=harga+(penyusutan*tahun);
        return hargaJual;
    }
    
}
