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
public class asset {

    int harga, id = 0;
    String nama;
    int turn = 0;
    
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
      
    public void randomId(){
        this.id = ThreadLocalRandom.current().nextInt(0,1000000  + 1);
    }
}
