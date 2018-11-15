/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ROG
 */
public class property extends asset {

    public property(String nama, int harga) {
        this.id = ThreadLocalRandom.current().nextInt(0, 1000000 + 1);
        this.nama = nama;
        this.harga = harga;
    }

    public property(int id, String nama, int harga, int turn) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.turn = turn;
    }

    public property() {

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

    public boolean save(int id_pemain) {
        String query = "";
        query = "INSERT INTO `property`(`nama`, `harga`, `turn`, `id_user`) VALUES (?,?,?,?)";
        try {
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, getNama());
            statement.setInt(2, getHarga());
            statement.setInt(3, getTurn());
            statement.setInt(4, id_pemain);

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

    public int getIdProperty(int id_pemain) {

        String query = "select max(id_property) FROM `property` WHERE nama=? and id_user=? and turn=?";

        try {
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, getNama());
            statement.setInt(2, id_pemain);
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

    public List<property> loadProperty(int id_pemain) {
        List<property> dataProperty = new ArrayList<>();
        property p;
        String query = "SELECT * FROM `property` where id_user=?";

        try {
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setInt(1, id_pemain);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                p = new property(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)
                );
                dataProperty.add(p);
            }
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataProperty;
    }

    public property getProperty(int id_property) {
        property p;
        String query = "SELECT * FROM property where id_property=?";

        try {
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setInt(1, id_property);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                p = new property(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)
                );
                return p;
            }
            statement.close();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
