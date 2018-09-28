/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl;
import gametrpl.usaha;
/**
 *
 * @author ROG
 */
public class pemain {
    String pemain;
    int dana,penghasilan,turn;
    usaha[] usaha;

    public pemain(String pemain,int turn) {
        this.turn = turn;
        this.pemain = pemain;
        dana = 5000;
        penghasilan = 0;
        usaha = new usaha[4];
        for (int i = 0; i < usaha.length; i++) {
            int[] a={0,0};
            usaha[i] = new usaha("",0,0,0,0,0,0,0,0,0,0,a,a,a);     
        }
    }
    
    public pemain(pemain pemain, int turn){
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
    
    public void tambahDana(int dana){
        this.dana += dana;
    }
    
    public usaha[] getUsaha(){
        return usaha;
    }

    public void setUsaha(usaha[] usaha) {
        this.usaha = usaha;
    }
    
    public usaha getLaundry(){
        return usaha[0];
    }
    
    public void setLaundry(usaha laundry){
        usaha[0]=laundry;
    }
    
    public usaha getKedaiKopi(){
        return usaha[1];
    }
    
    public void setKedaiKopi(usaha kedaiKopi){
        usaha[1]=kedaiKopi;
    }
    
    public usaha getPercetakan(){
        return usaha[2];
    }
    
    public void setPercetakan(usaha percetakan){
        usaha[2]= percetakan;
    }
    
    public usaha getIndomaret(){
        return usaha[3];
    }
    
    public void setIndomaret(usaha indomaret){
        usaha[3] = indomaret;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
    
    
}
