/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl;

import gametrpl.usaha;
import gametrpl.property;
import gametrpl.kendaraan;
import gametrpl.utang;
import java.util.ArrayList;

/**
 *
 * @author ROG
 */
public class pemain {

    String pemain;
    int dana, penghasilan, turn,bulan=0;
    usaha[] usaha;
    ArrayList<kendaraan> kendaraan;
    ArrayList<property> property;
    utang utang;

    public pemain(String pemain, int turn) {
        this.turn = turn;
        this.pemain = pemain;
        dana = 1000000;
        penghasilan = 0;
        usaha = new usaha[4];
        utang=null;
        int[][] minMax = {
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0}};
        for (int i = 0; i < usaha.length; i++) {
            usaha[i] = new usaha("", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, minMax);
        }
        kendaraan = new ArrayList<kendaraan>();
        property = new ArrayList<property>();
    }

    public utang getUtang() {
        return utang;
    }

    public void setUtang(utang utang) {
        this.utang = utang;
    }

    public int getBulan() {
        return bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public pemain(pemain pemain, int turn) {
        this.pemain = pemain.getPemain();
        this.turn = turn;
        this.dana = pemain.getDana();
        this.penghasilan = pemain.getPenghasilan();
    }

    public String getPemain() {
        return pemain;
    }

    public void setPemain(String pemain) {
        this.pemain = pemain;
    }

    public int getDana() {
        return dana;
    }

    public void setDana(int dana) {
        this.dana = dana;
    }

    public int getPenghasilan() {
        return penghasilan;
    }

    public void setPenghasilan(int penghasilan) {
        this.penghasilan = penghasilan;
    }

    public void tambahDana(int dana) {
        this.dana += dana;
    }

    public usaha[] getUsaha() {
        return usaha;
    }

    public void setUsaha(usaha[] usaha) {
        this.usaha = usaha;
    }

    public usaha getLaundry() {
        return usaha[0];
    }

    public void setLaundry(usaha laundry) {
        usaha[0] = laundry;
    }

    public usaha getKedaiKopi() {
        return usaha[1];
    }

    public void setKedaiKopi(usaha kedaiKopi) {
        usaha[1] = kedaiKopi;
    }

    public usaha getPercetakan() {
        return usaha[2];
    }

    public void setPercetakan(usaha percetakan) {
        usaha[2] = percetakan;
    }

    public usaha getIndomaret() {
        return usaha[3];
    }

    public void setIndomaret(usaha indomaret) {
        usaha[3] = indomaret;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public ArrayList<kendaraan> getKendaraan() {
        return kendaraan;
    }

    public void setKendaraan(ArrayList<kendaraan> kendaraan) {
        this.kendaraan = kendaraan;
    }

    public void tambahKendaraan(kendaraan kendaraan) {
        this.kendaraan.add(kendaraan);
    }

    public int cariKendaraan(String nama) {
        int jumlah = 0;
        for (int i = 0; i < kendaraan.size(); i++) {
            if (kendaraan.get(i).getNama().equalsIgnoreCase(nama)) {
                jumlah += 1;
            }
        }
        return jumlah;
    }

    public ArrayList<property> getProperty() {
        return property;
    }

    public void setProperty(ArrayList<property> property) {
        this.property = property;
    }

    public void tambahProperty(property property) {
        this.property.add(property);
    }

    public int getJumlahKendaraan() {
        return kendaraan.size();
    }

    public int getJumlahProperty() {
        return property.size();
    }

    public int cariProperty(String nama) {
        int jumlah = 0;
        for (int i = 0; i < property.size(); i++) {
            if (property.get(i).getNama().equalsIgnoreCase(nama)) {
                jumlah += 1;
            }
        }
        return jumlah;
    }
    
    public void sitaKendaraan(int id) {
        for (kendaraan kendaraan : kendaraan) {
            if (kendaraan.getId()==id) {
                this.kendaraan.remove(kendaraan);
            }else{
                System.out.println("Data Tidak Ditemukan");
            }
         }
    }
    public void sitaProperty(int id) {
        for (property property : property) {
            if (property.getId()==id) {
                this.property.remove(property);
            }else{
                System.out.println("Data Tidak Ditemukan");
            }
         }
    }

}
