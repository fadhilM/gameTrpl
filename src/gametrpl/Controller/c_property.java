/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl.Controller;

import gametrpl.pemain;
import gametrpl.property;
import gametrpl.Controller.c_usaha;
import gametrpl.Controller.c_dealer;
import gametrpl.Controller.c_bank;
import gametrpl.View.poperty;
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
public class c_property extends controller {

    pemain pemain;
    poperty poperty;
    c_usaha c_usaha;
    c_dealer c_dealer;
    property beli;
    c_bank c_bank;

    String[] daftarProperty;

    public c_property(pemain pemain) {
        this.pemain = pemain;

        daftarProperty = new String[8];

        daftarProperty[0] = "21m^2";
        daftarProperty[1] = "36m^2";
        daftarProperty[2] = "45m^2";
        daftarProperty[3] = "54m^2";
        daftarProperty[4] = "60m^2";
        daftarProperty[5] = "70m^2";
        daftarProperty[6] = "90m^2";
        daftarProperty[7] = "120m^2";

        poperty = new poperty();
        poperty.setVisible(true);

        poperty.getPenghasilanTxt().setText(String.valueOf(pemain.getPenghasilan()));
        updateDana();
        updatePropertySpesifik();
        updateTurn();

        //label property dan kendaraan
        poperty.getKendaraanTxt().setText(String.valueOf(this.pemain.getJumlahKendaraan()));
        poperty.getPropertyTxt().setText(String.valueOf(this.pemain.getJumlahProperty()));

        poperty.getB_usaha().addActionListener(new klikUsaha());
        poperty.getB_dealer().addActionListener(new klikDealer());
        poperty.getB_bank().addActionListener(new klikBank());

        poperty.getBeliProperty1().addActionListener(new bK1());
        poperty.getBeliProperty2().addActionListener(new bK2());
        poperty.getBeliProperty3().addActionListener(new bK3());
        poperty.getBeliProperty4().addActionListener(new bK4());
        poperty.getBeliProperty5().addActionListener(new bK5());
        poperty.getBeliProperty6().addActionListener(new bK6());
        poperty.getBeliProperty7().addActionListener(new bK7());
        poperty.getBeliProperty8().addActionListener(new bK8());
        poperty.getB_beli().addActionListener(new klikBeli());
        poperty.getB_nextTurn().addActionListener(new klikNextTurn());
    }

    private void pesen(property property) {
        beli = property;
    }

    public void popup(String popup) {
        JOptionPane.showMessageDialog(poperty, popup);
    }

    public void updateDana() {
        poperty.getDanaTxt().setText(String.valueOf(pemain.getDana()));
    }

    public void updateProperty() {
        poperty.getPropertyTxt().setText(String.valueOf(pemain.getJumlahProperty()));
    }
    
    public void updateKendaraan() {
        poperty.getKendaraanTxt().setText(String.valueOf(pemain.getJumlahKendaraan()));
    }

    private int randomPersen(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public int hitungPenghasilan() {
        int penghasilan, penghasilanSeluruh = 0, penghasilanKotor, penghasilanBersih, operasional, persen = 0;
        usaha[] usahaPemain = pemain.getUsaha();
        for (int i = 0; i < usahaPemain.length; i++) {
            //generate angka random berdasarkan trend usaha
            if (usahaPemain[i].isBoostP()) {
                persen = randomPersen(usahaPemain[i].getMinMax()[pemain.getBulan()][0] - usahaPemain[i].getPersenUp(), usahaPemain[i].getMinMax()[pemain.getBulan()][1] + 1);

                usahaPemain[i].setTempP(usahaPemain[i].getTempP() - 1);
                if (usahaPemain[i].getTempP() == 0) {
                    usahaPemain[i].setBoostP(false);
                    usahaPemain[i].setTempP(usahaPemain[i].getP());

                }
            } else {
                persen = randomPersen(usahaPemain[i].getMinMax()[pemain.getBulan()][0], usahaPemain[i].getMinMax()[pemain.getBulan()][1] + 1);
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
                int persenP = randomPersen(usahaPemain[i].getMinMax()[pemain.getBulan()][0], usahaPemain[i].getMinMax()[pemain.getBulan()][1] + 1);
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
        poperty.getPenghasilanTxt().setText(String.valueOf(pemain.getPenghasilan()));
    }

    public void updatePropertySpesifik() {
        int[] jumlah = new int[daftarProperty.length];
        for (int i = 0; i < daftarProperty.length; i++) {
            jumlah[i] = pemain.cariProperty(daftarProperty[i]);
        }
        poperty.getJmlh1().setText(String.valueOf(jumlah[0]));
        poperty.getJmlh2().setText(String.valueOf(jumlah[1]));
        poperty.getJmlh3().setText(String.valueOf(jumlah[2]));
        poperty.getJmlh4().setText(String.valueOf(jumlah[3]));
        poperty.getJmlh5().setText(String.valueOf(jumlah[4]));
        poperty.getJmlh6().setText(String.valueOf(jumlah[5]));
        poperty.getJmlh7().setText(String.valueOf(jumlah[6]));
        poperty.getJmlh8().setText(String.valueOf(jumlah[7]));
    }

    public void tambahTurn() {
        pemain.setTurn(pemain.getTurn() + 1);
    }

    public void updateTurn() {
        poperty.getTurnTxt().setText(String.valueOf(pemain.getTurn()));
    }

    private class klikBank implements ActionListener {

        public klikBank() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            c_bank = new c_bank(pemain);
            poperty.dispose();
        }
    }

    private class klikDealer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            c_dealer = new c_dealer(pemain);
            poperty.dispose();
        }
    }

    private class klikNextTurn implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pemain.setPenghasilan(hitungPenghasilan());
            pemain.setDana(pemain.getDana() + pemain.getPenghasilan());
            if (pemain.getBulan() > 12) {
                pemain.setBulan(pemain.getBulan() + 1);
            } else {
                pemain.setBulan(0);
            }
            if (pemain.getUtang() != null) {
                if (pemain.getDana() > pemain.getUtang().getAngsuran()) {
                    pemain.setDana((int) (pemain.getDana() - pemain.getUtang().getAngsuran()));
                    pemain.getUtang().bayarUtang();
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
                    pemain.getProperty().add(beli);
                    updateDana();
                    updateProperty();
                    updatePropertySpesifik();
                    popup("Property Berhasil Dibeli");
                } else {
                    popup("Maaf Uang Anda Tidak Cukup");
                }
            } else {
                JOptionPane.showMessageDialog(poperty, "Silahkan Pilih Property Yang Akan Dibeli");
            }

        }
    }

    private class bK1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(new property("21m^2", 390000));

        }
    }

    private class bK2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(new property("36m^2", 420000));
        }
    }

    private class bK3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(new property("45m^2", 500000));
        }
    }

    private class bK4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(new property("54m^2", 670000));
        }
    }

    private class bK5 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(new property("60m^2", 700000));
        }
    }

    private class bK6 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(new property("70m^2", 900000));
        }
    }

    private class bK7 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(new property("90m^2", 1200000));
        }
    }

    private class bK8 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pesen(new property("120m^2", 4000000));
        }
    }

    private class klikUsaha implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            c_usaha = new c_usaha(pemain, false);
            poperty.dispose();
        }
    }

}
