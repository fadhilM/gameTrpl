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
public class usaha {
    String namaUsaha;
    int modal, penghasilan, operasional;
    
    int uPenghasilan, uOperasional, bPenghasilan, bOperasional, bSpesial;
    boolean boostP=false,boostO=false,boostS=false;
    
    int persenUp,persenO;
    int tempP,tempO,tempS;
    int p,o,s;
    int min,max;

    public usaha(String namaUsaha, int modal, int penghasilan, int operasional, int uPenghasilan, int uOperasional, int bPenghasilan, int bOperasional, int bSpesial, int persenUp, int persenO, int p, int o, int s, int min, int max) {
        this.namaUsaha = namaUsaha;
        this.modal = modal;
        this.penghasilan = penghasilan;
        this.operasional = operasional;
        this.uPenghasilan = uPenghasilan;
        this.uOperasional = uOperasional;
        this.bPenghasilan = bPenghasilan;
        this.bOperasional = bOperasional;
        this.bSpesial = bSpesial;
        this.persenUp = persenUp;
        this.persenO = persenO;
        this.p = p;
        tempP = p;
        this.o = o;
        tempO = o;
        this.s = s;
        tempS = s;
        this.min = min;
        this.max = max;
    }

    public int getPersenUp() {
        return persenUp;
    }

    public void setPersenUp(int persenUp) {
        this.persenUp = persenUp;
    }

    public int getPersenO() {
        return persenO;
    }

    public void setPersenO(int persenO) {
        this.persenO = persenO;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getO() {
        return o;
    }

    public void setO(int o) {
        this.o = o;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }    
    
    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getTempP() {
        return tempP;
    }

    public void setTempP(int tempP) {
        this.tempP = tempP;
    }

    public int getTempO() {
        return tempO;
    }

    public void setTempO(int tempO) {
        this.tempO = tempO;
    }

    public int getTempS() {
        return tempS;
    }

    public void setTempS(int tempS) {
        this.tempS = tempS;
    }
    
    

    public boolean isBoostP() {
        return boostP;
    }

    public void setBoostP(boolean boostP) {
        this.boostP = boostP;
    }

    public boolean isBoostO() {
        return boostO;
    }

    public void setBoostO(boolean boostO) {
        this.boostO = boostO;
    }

    public boolean isBoostS() {
        return boostS;
    }

    public void setBoostS(boolean boostS) {
        this.boostS = boostS;
    }



    public usaha() {
        this.modal = 0;
        this.penghasilan = 0;
        this.operasional = 0;
    }

    public int getModal() {
        return modal;
    }

    public void setModal(int modal) {
        this.modal = modal;
    }

    public int getPenghasilan() {
        return penghasilan;
    }

    public void setPenghasilan(int penghasilan) {
        this.penghasilan = penghasilan;
    }

    public int getOperasional() {
        return operasional;
    }

    public void setOperasional(int operasional) {
        this.operasional = operasional;
    }

    public String getNamaUsaha() {
        return namaUsaha;
    }

    public void setNamaUsaha(String namaUsaha) {
        this.namaUsaha = namaUsaha;
    }

    public int getuPenghasilan() {
        return uPenghasilan;
    }

    public void setuPenghasilan(int uPenghasilan) {
        this.uPenghasilan = uPenghasilan;
    }

    public int getuOperasional() {
        return uOperasional;
    }

    public void setuOperasional(int uOperasional) {
        this.uOperasional = uOperasional;
    }

    public int getbPenghasilan() {
        return bPenghasilan;
    }

    public void setbPenghasilan(int bPenghasilan) {
        this.bPenghasilan = bPenghasilan;
    }

    public int getbOperasional() {
        return bOperasional;
    }

    public void setbOperasional(int bOperasional) {
        this.bOperasional = bOperasional;
    }

    public int getbSpesial() {
        return bSpesial;
    }

    public void setbSpesial(int bSpesial) {
        this.bSpesial = bSpesial;
    }

    
}
