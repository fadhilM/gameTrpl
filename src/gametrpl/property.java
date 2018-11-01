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
public class property {

    String nama;
    int harga, id;
    int turn;

    public property(String nama, int harga) {
        this.id = ThreadLocalRandom.current().nextInt(0, 1000000 + 1);
        this.nama = nama;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public double hargaJual(pemain pemain) {
        double hargaJual = 0, umurEkonomis, penyusutan;
        int sisa, tahun, nilaiResidu, jangkaWaktu;
        double persenPenyusutan = 0;
        persenPenyusutan = 5;
        umurEkonomis = 20;
        nilaiResidu = harga * 80 / 100;
        jangkaWaktu = pemain.getTurn() - turn;
        sisa = jangkaWaktu % 12;
        tahun = (jangkaWaktu - sisa) / 12;
        penyusutan = (((persenPenyusutan / 100) * harga) - nilaiResidu) / umurEkonomis;
        hargaJual = harga + (penyusutan * tahun);
        return hargaJual;
    }

}
