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
public class kendaraan extends asset {

    boolean tipe;

    public kendaraan(int id, String nama, int harga, boolean tipe) {
        this.id = id;
        this.harga = harga;
        this.nama = nama;
        this.tipe = tipe;
    }

    public kendaraan(int id, String nama, int harga, boolean tipe, int turn) {
        this.id = id;
        this.harga = harga;
        this.nama = nama;
        this.tipe = tipe;
        this.turn = turn;
    }

    public kendaraan(String nama, int harga, boolean tipe) {
        this.id = ThreadLocalRandom.current().nextInt(0, 1000000 + 1);
        this.nama = nama;
        this.harga = harga;
    }

    public kendaraan() {

    }

    public boolean isTipe() {
        return tipe;
    }

    public void setTipe(boolean tipe) {
        this.tipe = tipe;
    }

    public double hargaJual(pemain pemain) {
        double hargaJual = 0, umurEkonomis, penyusutan;
        int sisa, tahun, nilaiResidu, jangkaWaktu;
        double persenPenyusutan = 0;
        if (tipe) {
            persenPenyusutan = 25;
            umurEkonomis = 4;
        } else {
            persenPenyusutan = 12.5;
            umurEkonomis = 8;
        }
        nilaiResidu = harga * 40 / 100;
        jangkaWaktu = pemain.getTurn() - turn;
        sisa = jangkaWaktu % 12;
        tahun = (jangkaWaktu - sisa) / 12;
        penyusutan = (((persenPenyusutan / 100) * harga) - nilaiResidu) / umurEkonomis;
        hargaJual = harga + (penyusutan * tahun);
        return hargaJual;
    }

    public boolean save(int id_pemain) {
        String query = "INSERT INTO `kendaraan`(`nama`, `harga`, `tipe`, `turn`, `id_user`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setString(1, getNama());
            statement.setInt(2, getHarga());
            statement.setBoolean(3, isTipe());
            statement.setInt(4, getTurn());
            statement.setInt(5, id_pemain);

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

    public int getIdKendaraan(int id_pemain) {

        String query = "select max(id_kendaraan) FROM `kendaraan` WHERE nama=? and id_user=? and turn=?";

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

    public List<kendaraan> loadKendaraan(int id_pemain) {
        List<kendaraan> dataKendaraan = new ArrayList<>();
        kendaraan k;
        String query = "SELECT * FROM `kendaraan` where id_user=?";

        try {
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setInt(1, id_pemain);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                k = new kendaraan(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(4),
                        rs.getBoolean(4),
                        rs.getInt(5)
                );
                dataKendaraan.add(k);
            }
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataKendaraan;
    }

    public kendaraan getKendaraan(int id_kendaraan) {
        kendaraan k;
        String query = "SELECT * FROM kendaraan where id_kendaraan=?";

        try {
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setInt(1, id_kendaraan);
            ResultSet rs = statement.executeQuery();
            rs.next();
            k = new kendaraan(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(4),
                    rs.getBoolean(4),
                    rs.getInt(5)
            );
            statement.close();
            return k;
        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
