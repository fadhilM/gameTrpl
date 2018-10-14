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
import gametrpl.View.dealer;
import gametrpl.usaha;

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

    pemain pemain;
    dealer dealer;
    kendaraan beli;
    c_usaha c_usaha;
    c_property c_property;

    kendaraan[] daftarKendaraan;

    c_dealer(pemain pemain) {

        this.pemain = pemain;

        daftarKendaraan = new kendaraan[8];
        daftarKendaraan[0] = new kendaraan("kendaraan1", 1000);
        daftarKendaraan[1] = new kendaraan("kendaraan2", 1000);
        daftarKendaraan[2] = new kendaraan("kendaraan3", 1000);
        daftarKendaraan[3] = new kendaraan("kendaraan4", 1000);
        daftarKendaraan[4] = new kendaraan("kendaraan5", 1000);
        daftarKendaraan[5] = new kendaraan("kendaraan6", 1000);
        daftarKendaraan[6] = new kendaraan("kendaraan7", 1000);
        daftarKendaraan[7] = new kendaraan("kendaraan8", 1000);

        dealer = new dealer();
        dealer.setVisible(true);

        dealer.getPenghasilanTxt().setText(String.valueOf(pemain.getPenghasilan()));
        updateDana();
        updateKendaraanSpesifik();
        updateTurn();
        
        //label property dan kendaraan
        dealer.getKendaraanTxt().setText(String.valueOf(this.pemain.getJumlahKendaraan()));
        dealer.getPropertyTxt().setText(String.valueOf(this.pemain.getJumlahProperty()));

        dealer.getB_usaha().addActionListener(new klikUsaha());
        dealer.getB_property().addActionListener(new klikProperty());

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
    }

    private void pesen(kendaraan kendaraan) {
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

    private int randomPersen(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public int hitungPenghasilan() {
        int penghasilan, penghasilanSeluruh = 0, penghasilanKotor, penghasilanBersih, operasional, persen = 0;
        usaha[] usahaPemain = pemain.getUsaha();
        for (int i = 0; i < usahaPemain.length; i++) {
            //generate angka random berdasarkan min max usaha
            if (usahaPemain[i].isBoostP()) {
                persen = randomPersen(usahaPemain[i].getMin() - usahaPemain[i].getPersenUp(), usahaPemain[i].getMax() + 1);

                usahaPemain[i].setTempP(usahaPemain[i].getTempP() - 1);
                if (usahaPemain[i].getTempP() == 0) {
                    usahaPemain[i].setBoostP(false);
                    usahaPemain[i].setTempP(usahaPemain[i].getP());

                }
            } else {
                persen = ThreadLocalRandom.current().nextInt(usahaPemain[i].getMin(), usahaPemain[i].getMax() + 1);
            }
            System.out.println("Persen :" + String.valueOf(persen));

            penghasilan = usahaPemain[i].getPenghasilan();
            int minPlusPenghasilan = persen * penghasilan / 100;
            penghasilanKotor = penghasilan - minPlusPenghasilan;

            System.out.println("penghasilan :" + String.valueOf(penghasilanKotor));
            usahaPemain[i].setPenghasilan(penghasilanKotor);

            operasional = usahaPemain[i].getOperasional();
            if (usahaPemain[i].isBoostO()) {
                int persenB = ThreadLocalRandom.current().nextInt(-15, -5 - 1);
                System.out.println(String.valueOf(persenB));
                operasional = operasional - (operasional * persenB / 100);
                usahaPemain[i].setTempO(usahaPemain[i].getTempO() - 1);
                if (usahaPemain[i].getTempO() == 0) {
                    usahaPemain[i].setBoostO(false);
                    usahaPemain[i].setTempO(usahaPemain[i].getO());
                }
            }

            if (usahaPemain[i].isBoostS()) {
                int persenP = randomPersen(usahaPemain[i].getMin() - 5, usahaPemain[i].getMax() + 1);
                int persenO = ThreadLocalRandom.current().nextInt(-5, -15 - 1);
                int penghasilanB = penghasilan * persenP / 100;
                penghasilanKotor = penghasilanKotor + penghasilanB;
                operasional = operasional - (operasional * persenO / 100);
                usahaPemain[i].setTempS(usahaPemain[i].getTempS() - 1);
                if (usahaPemain[i].getTempS() > 1) {
                    usahaPemain[i].setBoostS(false);
                    usahaPemain[i].setTempS(usahaPemain[i].getS());
                }
            }

            penghasilanBersih = penghasilanKotor - operasional;
            penghasilanSeluruh += penghasilanBersih;

        }
        pemain.setUsaha(usahaPemain);
        return penghasilanSeluruh;
    }
    
    public void updatePenghasilan() {
        dealer.getPenghasilanTxt().setText(String.valueOf(pemain.getPenghasilan()));
    }

    public void updateKendaraanSpesifik() {
        int[] jumlah = new int[daftarKendaraan.length];
        for (int i = 0; i < daftarKendaraan.length; i++) {
            jumlah[i] = pemain.cariKendaraan(daftarKendaraan[i].getNama());
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
            pemain.setDana(pemain.getDana() + pemain.getPenghasilan());
            updateDana();
            updatePenghasilan();
            tambahTurn();
            updateTurn();
        }
    }

    private class klikBeli implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (uangCukup(pemain.getDana(), beli.getHarga())) {
                pemain.setDana(pemain.getDana() - beli.getHarga());
                beli.setTurn(pemain.getTurn());
                pemain.getKendaraan().add(beli);
                updateDana();
                updateKendaraan();
                updateKendaraanSpesifik();
                popup("Kendaraan Berhasil Dibeli");
            } else {
                popup("Maaf Uang Anda Tidak Cukup");
            }
        }
    }

    private class bK1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(daftarKendaraan[0]);
            System.out.println("asdasd");

        }
    }

    private class bK2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(daftarKendaraan[1]);
        }
    }

    private class bK3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(daftarKendaraan[2]);
        }
    }

    private class bK4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(daftarKendaraan[3]);
        }
    }

    private class bK5 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(daftarKendaraan[4]);
        }
    }

    private class bK6 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(daftarKendaraan[5]);
        }
    }

    private class bK7 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(daftarKendaraan[6]);
        }
    }

    private class bK8 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(daftarKendaraan[7]);
        }
    }

    private class klikUsaha implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            c_usaha = new c_usaha(pemain, false);
            dealer.dispose();
        }
    }

}
