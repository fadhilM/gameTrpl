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
import gametrpl.koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ROG
 */
public class pemain {

    String pemain;
    int dana, penghasilan, turn,id;
    usaha[] usaha;
    ArrayList<kendaraan> kendaraan;
    ArrayList<property> property;
    utang utang;
    boolean bencana;
    String persen,tipeBencana;

    public pemain(String pemain, int turn) {
        this.turn = turn;
        this.pemain = pemain;
        dana = 1000000;
        penghasilan = 0;
        usaha = new usaha[4];
        utang = null;
        for (int i = 0; i < usaha.length; i++) {
            usaha[i] = null;
        }
        kendaraan = new ArrayList<kendaraan>();
        property = new ArrayList<property>();
    }

    public pemain(int id, String nama, int dana, int turn, int idUtang) {
        
    }
    
    public pemain(int id, String nama, int dana, int turn) {
        this.id    = id;
        this.pemain=nama;
        this.dana = dana;
        this.turn = turn;
        usaha = new usaha[4];
        utang = null;
        for (int i = 0; i < usaha.length; i++) {
            usaha[i] = null;
        }
        kendaraan = new ArrayList<kendaraan>();
        property = new ArrayList<property>();
    }

    public pemain() {
    }
    
    public void startBencana(String persen,String tipeBencana){
        bencana =true;
        this.persen = persen;
        this.tipeBencana = tipeBencana;
    }
    
    public void endBencana(){
        bencana = false;
    }
    
    public boolean isBencana(){
        return bencana;
    }

    public String getPersen() {
        return persen;
    }

    public String getTipeBencana() {
        return tipeBencana;
    }

    public utang getUtang() {
        return utang;
    }

    public void setUtang(utang utang) {
        this.utang = utang;
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
            if (kendaraan.getId() == id) {
                this.kendaraan.remove(kendaraan);
            } else {
                System.out.println("Data Tidak Ditemukan");
            }
        }
    }

    public void sitaProperty(int id) {
        for (property property : property) {
            if (property.getId() == id) {
                this.property.remove(property);
            } else {
                System.out.println("Data Tidak Ditemukan");
            }
        }
    }

    public List<pemain> getAll() {
        List<pemain> dataPemain = new ArrayList<>();
        pemain p;
        String query = "SELECT * FROM `pemain`";

        try {
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                p = new pemain(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)
                );
                dataPemain.add(p);
            }
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataPemain;
    }

    public pemain getDataPemain(int id) {
        pemain p;
        String query = "SELECT * FROM pemain where id_pemain=?";

        try {
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            rs.next();
            p = new pemain(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4)
            );
            statement.close();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean save() {
        String query = "INSERT INTO `pemain`(`nama`, `dana`, `turn`) VALUES (?,?,?)";
        try {
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, getPemain());
            statement.setInt(2, getDana());
            statement.setInt(3, getTurn());

            int row = statement.executeUpdate();
            if (row > 0) {
                return true;
            }
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getIdPemain() {

        String query = "select max(id_pemain) FROM `pemain` WHERE nama=? and dana=? and turn=?";

        try {
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, getPemain());
            statement.setInt(2, getDana());
            statement.setInt(3, getTurn());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
