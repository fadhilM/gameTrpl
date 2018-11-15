/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package gametrpl.Controller;

import gametrpl.pemain;
import gametrpl.kendaraan;
import gametrpl.Controller.c_usaha;
import gametrpl.Controller.c_property;
import gametrpl.Controller.c_bank;
import gametrpl.Controller.c_mainMenu;
import gametrpl.View.dealer;
import gametrpl.usaha;
import gametrpl.utang;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

/**
 *
 * @author ROG
 */
public class c_dealer extends controller {
    dealer dealer;
    kendaraan beli;
    c_usaha c_usaha;
    c_property c_property;
    c_bank c_bank;
    c_mainMenu c_mainMenu;

    String[] daftarKendaraan;

    c_dealer(pemain pemain) {

        this.pemain = pemain;

        dealer = new dealer();
        dealer.setVisible(true);

        daftarKendaraan = new String[8];

        daftarKendaraan[0] = "Honda Beat";
        daftarKendaraan[1] = "Honda Pcx";
        daftarKendaraan[2] = "Honda CBR";
        daftarKendaraan[3] = "Kawasaki Ninja";
        daftarKendaraan[4] = "Toyota Yaris";
        daftarKendaraan[5] = "Honda Mobilio";
        daftarKendaraan[6] = "Audi A4";
        daftarKendaraan[7] = "BMW M5";

        dealer.getPenghasilanTxt().setText(String.valueOf(pemain.getPenghasilan()));
        updateDana();
        updateKendaraanSpesifik();
        updateTurn();

        //label property dan kendaraan
        dealer.getKendaraanTxt().setText(String.valueOf(this.pemain.getJumlahKendaraan()));
        dealer.getPropertyTxt().setText(String.valueOf(this.pemain.getJumlahProperty()));

        dealer.getB_usaha().addActionListener(new klikUsaha());
        dealer.getB_property().addActionListener(new klikProperty());
        dealer.getB_bank().addActionListener(new klikBank());

        dealer.getBeliKendaraan1().addActionListener(new bK1());
        dealer.getBeliKendaraan2().addActionListener(new bK2());
        dealer.getBeliKendaraan3().addActionListener(new bK3());
        dealer.getBeliKendaraan4().addActionListener(new bK4());
        dealer.getBeliKendaraan5().addActionListener(new bK5());
        dealer.getBeliKendaraan6().addActionListener(new bK6());
        dealer.getBeliKendaraan7().addActionListener(new bK7());
        dealer.getBeliKendaraan8().addActionListener(new bK8());
        dealer.getB_beli().addActionListener(new klikBeli());
        dealer.getB_nextTurn().addActionListener(new klikNextTurn());
        dealer.getB_kembali().addActionListener(new klikKembali());
    }

    private void pesen(kendaraan kendaraan) {
        kendaraan.randomId();
        beli = kendaraan;
    }

    public void popup(String popup) {
        JOptionPane.showMessageDialog(dealer, popup);
    }

    public void updateDana() {
        dealer.getDanaTxt().setText(String.valueOf(pemain.getDana()));
    }

    public void updateKendaraan() {
        dealer.getKendaraanTxt().setText(String.valueOf(pemain.getJumlahKendaraan()));
    }
     public void updateProperty() {
        dealer.getPropertyTxt().setText(String.valueOf(pemain.getJumlahProperty()));
    }

    public void updatePenghasilan() {
        dealer.getPenghasilanTxt().setText(String.valueOf(pemain.getPenghasilan()));
    }

    public void updateKendaraanSpesifik() {
        int[] jumlah = new int[daftarKendaraan.length];
        for (int i = 0; i < daftarKendaraan.length; i++) {
            jumlah[i] = pemain.cariKendaraan(daftarKendaraan[i]);
        }
        dealer.getJmlh1().setText(String.valueOf(jumlah[0]));
        dealer.getJmlh2().setText(String.valueOf(jumlah[1]));
        dealer.getJmlh3().setText(String.valueOf(jumlah[2]));
        dealer.getJmlh4().setText(String.valueOf(jumlah[3]));
        dealer.getJmlh5().setText(String.valueOf(jumlah[4]));
        dealer.getJmlh6().setText(String.valueOf(jumlah[5]));
        dealer.getJmlh7().setText(String.valueOf(jumlah[6]));
        dealer.getJmlh8().setText(String.valueOf(jumlah[7]));
    }

    public void tambahTurn() {
        pemain.setTurn(pemain.getTurn() + 1);
    }

    public void updateTurn() {
        dealer.getTurnTxt().setText(String.valueOf(pemain.getTurn()));
    }

    private class klikKembali implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
           int save = JOptionPane.showConfirmDialog(null, "Apakah Anda Ingin Menyimpan Progress Game Sebelum Kembali", "Perhatian", JOptionPane.YES_NO_OPTION);
            if (save == JOptionPane.YES_OPTION) {
                save();
            }
            dealer.dispose();
            c_mainMenu = new c_mainMenu();
        }
    }

    private class klikBank implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            c_bank = new c_bank(pemain);
            dealer.dispose();
        }
    }

    private class klikProperty implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            c_property = new c_property(pemain);
            dealer.dispose();
        }
    }

    private class klikNextTurn implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pemain.setPenghasilan(hitungPenghasilan());
            if (pemain.isBencana()) {
                popup("Penghasilan Bulan Ini Berkurang Sebanyak " + pemain.getPersen() + " untuk Melakukan Perbaikan Disebabkan Oleh " + pemain.getTipeBencana());
                pemain.endBencana();
            }
            pemain.setDana(pemain.getDana() + pemain.getPenghasilan());
            if (pemain.getUtang() != null) {
                if (pemain.getDana() > pemain.getUtang().getAngsuran()) {
                    pemain.setDana((int) (pemain.getDana() - pemain.getUtang().getAngsuran()));
                    pemain.getUtang().bayarUtang();
                    System.out.println(String.valueOf(pemain.getUtang().getUtang()));
                    if (pemain.getUtang().getTotalPembayaran() < 1) {
                        utang utang = null;
                        pemain.setUtang(utang);
                        popup("Utang Telah Lunas");
                    } else {
                        popup("Anda Membayar Angsuran Pinjaman Sebesar: " + String.valueOf(pemain.getUtang().getAngsuran()) + " sisa utang : " + String.valueOf(pemain.getUtang().getTotalPembayaran()));
                    }
                } else {
                    if (pemain.getUtang().isJaminan()) {
                        pemain.sitaKendaraan(pemain.getUtang().getKendaraan().getId());
                        updateKendaraan();

                    } else {
                        pemain.sitaProperty(pemain.getUtang().getProperty().getId());
                        updateProperty();
                    }
                    popup("Uang Anda tidak Cukup untuk melunasi utang, jaminan anda akan disita oleh bank");
                }

            }
            updateDana();
            updatePenghasilan();
            tambahTurn();
            updateTurn();
        }
    }

    private class klikBeli implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (beli != null) {
                if (uangCukup(pemain.getDana(), beli.getHarga())) {
                    pemain.setDana(pemain.getDana() - beli.getHarga());
                    beli.setTurn(pemain.getTurn());
                    pemain.tambahKendaraan(beli);
                    System.out.println("+++++++");
                    for (int i = 0; i < pemain.getKendaraan().size(); i++) {
                        System.out.print(pemain.getKendaraan().get(i).getId());
                        System.out.println("," + pemain.getKendaraan().get(i).getTurn());
                    }
                    updateDana();
                    updateKendaraan();
                    updateKendaraanSpesifik();
                    popup("Kendaraan Berhasil Dibeli");
                } else {
                    popup("Maaf Uang Anda Tidak Cukup");
                }
            }else{
                popup("Silahkan Pilih Kendaraan Yang Akan Dibeli");
            }
        }
    }

    private class bK1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(new kendaraan("Honda Beat", 15500, true));

        }
    }

    private class bK2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(new kendaraan("Honda Pcx", 28000, true));
        }
    }

    private class bK3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(new kendaraan("Honda CBR", 34000, true));
        }
    }

    private class bK4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(new kendaraan("Kawasaki Ninja", 71000, true));
        }
    }

    private class bK5 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(new kendaraan("Toyota Yaris", 232000, false));
        }
    }

    private class bK6 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(new kendaraan("Honda Mobilio", 247000, false));
        }
    }

    private class bK7 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(new kendaraan("Audi A4", 1200000, false));
        }
    }

    private class bK8 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(new kendaraan("BMW M5", 4000000, false));
        }
    }

    private class klikUsaha implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            c_usaha = new c_usaha(pemain, false);
            dealer.dispose();
        }
    }

}
