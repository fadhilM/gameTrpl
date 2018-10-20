/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl;

/**
 *
 * @author ROG
 */
public class kendaraan {
    int harga;
    boolean tipe;
    String nama;
    int turn=0;

    public kendaraan(String nama, int harga) {
        this.harga = harga;
        this.nama = nama;
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
    
}
