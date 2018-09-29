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
    int persenUP,pesenOP;
    int [] persenBP,persenBO,persenBS;
    boolean boostP=false,boostO=false,boostS=false;
    int bP=5,bO=5,bS=5;
    int tempP=5,tempO=5,tempS=5;
    int min=-10,max=10;
    int minMod,maxMod;

    public usaha(String namaUsaha, int modal, int penghasilan, int operasional, int uPenghasilan, int uOperasional, int bPenghasilan, int bOperasional, int bSpesial, int persenUP, int pesenOP, int[] persenBP, int[] persenBO, int[] persenBS) {
        this.namaUsaha = namaUsaha;
        this.modal = modal;
        this.penghasilan = penghasilan;
        this.operasional = operasional;
        this.uPenghasilan = uPenghasilan;
        this.uOperasional = uOperasional;
        this.bPenghasilan = bPenghasilan;
        this.bOperasional = bOperasional;
        this.bSpesial = bSpesial;
        this.persenUP = persenUP;
        this.pesenOP = pesenOP;
        this.persenBP = persenBP;
        this.persenBO = persenBO;
        this.persenBS = persenBS;
    }

    public int getMinMod() {
        return minMod;
    }

    public void setMinMod(int minMod) {
        this.minMod = minMod;
    }

    public int getMaxMod() {
        return maxMod;
    }

    public void setMaxMod(int maxMod) {
        this.maxMod = maxMod;
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

    public int getbP() {
        return bP;
    }

    public void setbP(int bP) {
        this.bP = bP;
    }

    public int getbO() {
        return bO;
    }

    public void setbO(int bO) {
        this.bO = bO;
    }

    public int getbS() {
        return bS;
    }

    public void setbS(int bS) {
        this.bS = bS;
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

    public int getPersenUP() {
        return persenUP;
    }

    public void setPersenUP(byte persenUP) {
        this.persenUP = persenUP;
    }

    public int getPesenOP() {
        return pesenOP;
    }

    public void setPesenOP(byte pesenOP) {
        this.pesenOP = pesenOP;
    }

    public int[] getPersenBP() {
        return persenBP;
    }

    public void setPersenBP(int[] persenBP) {
        this.persenBP = persenBP;
    }

    public int[] getPersenBO() {
        return persenBO;
    }

    public void setPersenBO(int[] persenBO) {
        this.persenBO = persenBO;
    }

    public int[] getPersenBS() {
        return persenBS;
    }

    public void setPersenBS(int[] persenBS) {
        this.persenBS = persenBS;
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
