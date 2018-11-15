/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl;

import gametrpl.kendaraan;
import gametrpl.property;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Optional;

/**
 *
 * @author ROG
 */
public class utang {

    double utang, sukubunga = 9, tenor, angsuran, totalPembayaran;
    int turn, id;
    property property = null;
    kendaraan kendaraan = null;
    private static DecimalFormat df3 = new DecimalFormat(".###");
    boolean jaminan;
    int id_kendaraan, id_property;

    public utang(kendaraan kendaraan, boolean jaminan) {
        this.id = ThreadLocalRandom.current().nextInt(0, 1000000 + 1);
        this.kendaraan = kendaraan;
        this.jaminan = jaminan;
    }

    public utang(property property, boolean jaminan) {
        this.id = ThreadLocalRandom.current().nextInt(0, 1000000 + 1);
        this.property = property;
        this.jaminan = jaminan;
    }

    public utang(double utang, double sukubunga, double tenor, int turn, property property) {
        this.utang = utang;
        this.sukubunga = sukubunga;
        this.tenor = tenor;
        this.turn = turn;
        this.property = property;
    }

    public utang(double utang, double sukubunga, double tenor, int turn, kendaraan kendaraan) {
        this.utang = utang;
        this.sukubunga = sukubunga;
        this.tenor = tenor;
        this.turn = turn;
        this.kendaraan = kendaraan;
    }

    public utang(int id, double utang, double sisa_utang, double sukubunga, double tenor, int turn, int id_property, int id_kendaraan) {
        this.id = id;
        this.totalPembayaran = sisa_utang;
        this.utang = utang;
        this.sukubunga = sukubunga;
        this.tenor = tenor;
        this.turn = turn;
        this.id_kendaraan = id_kendaraan;
        this.id_property = id_property;
        this.angsuran = hitungAngsuran(utang, tenor, sukubunga);
    }

    public utang() {
    }

    ;

    public double getTotalPembayaran() {
        return totalPembayaran;
    }

    public void setTotalPembayaran(double totalPembayaran) {
        this.totalPembayaran = totalPembayaran;
    }

    public double getAngsuran() {
        return angsuran;
    }

    public void setAngsuran(double angsuran) {
        this.angsuran = angsuran;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isJaminan() {
        return jaminan;
    }

    public void setJaminan(boolean jaminan) {
        this.jaminan = jaminan;
    }

    public double getUtang() {
        return utang;
    }

    public void setUtang(double utang) {
        this.utang = utang;
        angsuran = hitungAngsuran(utang, tenor, sukubunga);
    }

    public void bayarUtang() {
        this.totalPembayaran -= angsuran;

    }

    public double bayarUtang(pemain pemain) {
        utang -= angsuran;
        return pemain.getDana() - angsuran;
    }

    public double getSukubunga() {
        return sukubunga;
    }

    public void setSukubunga(double sukubunga) {
        this.sukubunga = sukubunga;
    }

    public double getBungaPerBulan(double sukuBunga, double pinjaman) {
        return pinjaman * (sukuBunga / 12) / 100;
    }

    public double getTenor() {
        return tenor;
    }

    public void setTenor(double tenor) {
        this.tenor = tenor;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public double hitungAngsuran(double hutang, double tenor, double sukuBunga) {
        double angsuran = ((hutang / tenor) + (getBungaPerBulan(sukuBunga, hutang)));
        angsuran = Double.parseDouble(df3.format(angsuran));
        return angsuran;
    }

    public void hitungBiayaPeminjaman() {
        totalPembayaran = angsuran * 12;
    }

    public property getProperty() {
        return property;
    }

    public void setProperty(property property) {
        this.property = property;
    }

    public kendaraan getKendaraan() {
        return kendaraan;
    }

    public void setKendaraan(kendaraan kendaraan) {
        this.kendaraan = kendaraan;
    }

    public boolean save(int id_pemain, int id_jaminan, boolean jaminan) {
        String query = "";
        if (jaminan) {//jika jaminan kendaraan
            query = "INSERT INTO `utang`(`utang`, `sisa_utang`, `suku_bunga`, `tenor`, `turn`, `id_kendaraan`, `id_user`) VALUES (?,?,?,?,?,?,?)";
        } else {
            query = "INSERT INTO `utang`(`utang`, `sisa_utang`, `suku_bunga`, `tenor`, `turn`, `id_property`,`id_user`) VALUES (?,?,?,?,?,?,?)";
        }

        try {
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setDouble(1, getUtang());
            statement.setDouble(2, getTotalPembayaran());
            statement.setDouble(3, getSukubunga());
            statement.setDouble(4, getTenor());
            statement.setInt(5, getTurn());
            statement.setInt(6, id_jaminan);
            statement.setInt(7, id_pemain);

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

    public utang getDataUtang(int id) {
        utang u;
        String query = "SELECT * FROM utang where id_user=?";
        try {
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                u = new utang(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8)
                );
                if (u.id_kendaraan != 0) {
                    kendaraan k = new kendaraan();
                    k = k.getKendaraan(u.id_kendaraan);
                    u.setKendaraan(kendaraan);
                }
                if (u.id_property != 0) {
                    property p = new property();
                    p = p.getProperty(u.id_property);
                    u.setProperty(p);
                }
                return u;
            }
            statement.close();
            return null;

        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
