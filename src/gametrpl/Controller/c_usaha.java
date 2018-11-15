/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package gametrpl.Controller;

import org.apache.commons.math3.distribution.NormalDistribution;

import gametrpl.View.halamanUsaha;
import gametrpl.pemain;
import gametrpl.usaha;
import gametrpl.Controller.c_dealer;
import gametrpl.Controller.c_property;
import gametrpl.Controller.c_bank;
import gametrpl.Controller.c_mainMenu;
import gametrpl.View.mainMenu;
import gametrpl.utang;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author ROG
 */
public class c_usaha extends controller {

    halamanUsaha halamanUsaha;
    c_mainMenu c_mainMenu;

    //usaha yang ada
    usaha laundry, kedaiKopi, percetakan, indomaret;

    //persentase min max penghasilan
    int min = -20, max = 80;

    c_usaha(pemain pemain, boolean isNewGame) {
        this.pemain = pemain;
        System.out.println(String.valueOf(this.pemain.getTurn()));
        this.pemain.setTurn(pemain.getTurn());

        laundry = new usaha("laundry", 9050, 2000, 1100, 5000, 5000, 5000, 5000, 5000, 10, 10, 5, 5, 5, 5, 5, -5, 5);
        kedaiKopi = new usaha("Kedai Kopi", 21500, 60000, 8000, 10000, 10000, 10000, 10000, 10000, 10, 10, 5, 5, 5, 5, 5, -5, 5);
        percetakan = new usaha("fotokopi", 200000, 60000, 40000, 13000, 13000, 13000, 13000, 13000, 10, 10, 5, 5, 5, 5, 5, -5, 5);
        indomaret = new usaha("Minimarket", 394000, 180000, 166000, 20000, 20000, 20000, 20000, 20000, 10, 10, 5, 5, 5, 5, 5, -5, 5);

        halamanUsaha = new halamanUsaha();
        halamanUsaha.setVisible(true);

        updateDana();
        updatePenghasilan();
        updateTurn();

        //label property dan kendaraan
        halamanUsaha.getKendaraanTxt().setText(String.valueOf(this.pemain.getJumlahKendaraan()));
        halamanUsaha.getPropertyTxt().setText(String.valueOf(this.pemain.getJumlahProperty()));

        //label modal
        halamanUsaha.getModal1().setText(String.valueOf(laundry.getModal()));
        halamanUsaha.getModal2().setText(String.valueOf(kedaiKopi.getModal()));
        halamanUsaha.getModal3().setText(String.valueOf(percetakan.getModal()));
        halamanUsaha.getModal4().setText(String.valueOf(indomaret.getModal()));

        //label penghasilan
        perbaruiStatus();

        //label Operasional
        //event listener buat usaha
        halamanUsaha.getB_nextTurn().addActionListener(new klikNextTurn());
        halamanUsaha.getB_buatUsaha1().addActionListener(new klikBuatLaundry());
        halamanUsaha.getB_buatUsaha2().addActionListener(new klikBuatKedaiKopi());
        halamanUsaha.getB_buatUsaha3().addActionListener(new klikBuatPercetakan());
        halamanUsaha.getB_buatUsaha4().addActionListener(new klikBuatIndomaret());

        //event listener upgrade 1 : penghasilan
        halamanUsaha.getAa().addActionListener(new klikUpgradeA1());
        halamanUsaha.getBa().addActionListener(new klikUpgradeB1());
        halamanUsaha.getCa().addActionListener(new klikUpgradeC1());
        halamanUsaha.getDa().addActionListener(new klikUpgradeD1());

//        event listener upgrade 2 : operasional
        halamanUsaha.getAb().addActionListener(new klikUpgradeA2());
        halamanUsaha.getBb().addActionListener(new klikUpgradeB2());
        halamanUsaha.getCb().addActionListener(new klikUpgradeC2());
        halamanUsaha.getDb().addActionListener(new klikUpgradeD2());
//
//        //event listener upgrade 3 : boost penghasilan
        halamanUsaha.getAc().addActionListener(new klikUpgradeA3());
        halamanUsaha.getBc().addActionListener(new klikUpgradeB3());
        halamanUsaha.getCc().addActionListener(new klikUpgradeC3());
        halamanUsaha.getDc().addActionListener(new klikUpgradeD3());
//
//        //eventListener upgrade 4 : boost operasional
        halamanUsaha.getAd().addActionListener(new klikUpgradeA4());
        halamanUsaha.getBd().addActionListener(new klikUpgradeB4());
        halamanUsaha.getCd().addActionListener(new klikUpgradeC4());
        halamanUsaha.getDd().addActionListener(new klikUpgradeD4());
//
//eventListener upgrade 5 : spesial boost
        halamanUsaha.getAe().addActionListener(new klikUpgradeA5());
        halamanUsaha.getBe().addActionListener(new klikUpgradeB5());
        halamanUsaha.getCe().addActionListener(new klikUpgradeC5());
        halamanUsaha.getDe().addActionListener(new klikUpgradeD5());

        halamanUsaha.getB_dealer().addActionListener(new klikDealer());
        halamanUsaha.getB_property().addActionListener(new klikProperty());
        halamanUsaha.getB_bank().addActionListener(new klikBank());
        halamanUsaha.getB_kembali().addActionListener(new kembali());
    }

    public void tambahTurn() {
        pemain.setTurn(pemain.getTurn() + 1);
    }

    public void updateTurn() {
        halamanUsaha.getTurnTxt().setText(String.valueOf(this.pemain.getTurn()));
    }

    public void updateDana() {
        halamanUsaha.getDanaTxt().setText(String.valueOf(pemain.getDana()));
    }

    public void updatePenghasilan() {
        halamanUsaha.getPenghasilanTxt().setText(String.valueOf(pemain.getPenghasilan()));
    }

    public void popup(String popup) {
        JOptionPane.showMessageDialog(halamanUsaha, popup);
    }

    public boolean udahPunya(int index) {
        return pemain.getUsaha()[index] != null;
    }

    public void upgradePenghasilan(usaha usaha) {
        int upgradePenghasilan = 0;
        usaha[] uPemain = pemain.getUsaha();
        for (int i = 0; i < uPemain.length; i++) {
            if (uPemain[i].getNamaUsaha().equalsIgnoreCase(usaha.getNamaUsaha())) {
                //System.out.print(String.valueOf(uPemain[i].getPenghasilan()) + ",");
                upgradePenghasilan = uPemain[i].getPenghasilan() + (uPemain[i].getPenghasilan() * uPemain[i].getPersenUp() / 100);
                uPemain[i].setPenghasilan(upgradePenghasilan);
                pemain.setUsaha(uPemain);
                //System.out.println(String.valueOf(uPemain[i].getPenghasilan()));
                updateDana();
                popup("Selamat, Usaha Anda Berhasil Di Upgrade");
            }
        }
    }

    public void upgradeOperasional(int i) {
        int upgradeOperasional = 0;
        usaha[] uPemain = pemain.getUsaha();
        upgradeOperasional = uPemain[i].getOperasional() - (uPemain[i].getOperasional() * uPemain[i].getPersenO() / 100);
        uPemain[i].setOperasional(upgradeOperasional);
        pemain.setUsaha(uPemain);
        updateDana();

    }

    public void upgradeOperasionalUsaha(usaha usaha, int index) {
        if (uangCukup(pemain.getDana(), pemain.getUsaha()[index].getuOperasional())) {
            pemain.setDana(pemain.getDana() - pemain.getUsaha()[index].getuOperasional());
            upgradeOperasional(index);
            updateDana();
            popup("Selamat, Usaha Anda Berhasil Di Upgrade");
        } else {
            popup("Maaf Uang Yang Anda Miliki Tidak Cukup");
        }
    }

    public void upgradePenghasilanUsaha(usaha usaha, int index) {
        if (uangCukup(pemain.getDana(), pemain.getUsaha()[index].getuPenghasilan())) {
            pemain.setDana(pemain.getDana() - pemain.getUsaha()[index].getuPenghasilan());
            upgradePenghasilan(usaha);
            popup("Selamat, Usaha Anda Berhasil Di Upgrade");
        } else {
            updateDana();
            popup("Maaf Uang Yang Anda Miliki Tidak Cukup");
        }
    }

    public void boostPenghasilan(int index) {
        if (uangCukup(pemain.getDana(), pemain.getUsaha()[index].getbPenghasilan())) {
            pemain.setDana(pemain.getDana() - pemain.getUsaha()[index].getbPenghasilan());
            pemain.getUsaha()[index].setBoostP(true);
            updateDana();
            popup("Selamat, Usaha Anda Berhasil Di Upgrade");
        } else {
            popup("Maaf Uang Yang Anda Miliki Tidak Cukup");
        }
    }

    public void boostOperasional(int index) {
        if (uangCukup(pemain.getDana(), pemain.getUsaha()[index].getbOperasional())) {
            pemain.setDana(pemain.getDana() - pemain.getUsaha()[index].getbOperasional());
            pemain.getUsaha()[index].setBoostO(true);
            updateDana();
            popup("Selamat, Usaha Anda Berhasil Di Upgrade");
        } else {
            popup("Maaf Uang Yang Anda Miliki Tidak Cukup");
        }
    }

    public void boostSpecial(int index) {
        if (uangCukup(pemain.getDana(), pemain.getUsaha()[index].getbSpesial())) {
            pemain.setDana(pemain.getDana() - pemain.getUsaha()[index].getbSpesial());
            pemain.getUsaha()[index].setBoostS(true);
            updateDana();
            popup("Selamat, Usaha Anda Berhasil Di Upgrade");
        } else {
            popup("Maaf Uang Yang Anda Miliki Tidak Cukup");
        }
    }

    private class kembali implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int save = JOptionPane.showConfirmDialog(null, "Apakah Anda Ingin Menyimpan Progress Game Sebelum Kembali ", "Perhatian", JOptionPane.YES_NO_OPTION);
            if (save == JOptionPane.YES_OPTION) {
                save();
            }
            halamanUsaha.dispose();
            c_mainMenu = new c_mainMenu();

        }
    }

    private class klikBank implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            c_bank c_bank = new c_bank(pemain);
            halamanUsaha.dispose();
        }
    }

    private class klikProperty implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            c_property c = new c_property(pemain);
            halamanUsaha.dispose();
        }
    }

    private class klikDealer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            c_dealer c = new c_dealer(pemain);
            halamanUsaha.dispose();
        }
    }

    private class klikUpgradeA5 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(0)) {
                boostSpecial(0);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeB5 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(1)) {
                boostSpecial(1);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeC5 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(2)) {
                boostSpecial(2);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeD5 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(3)) {
                boostSpecial(3);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeA4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(0)) {
                boostOperasional(0);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeB4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(1)) {
                boostOperasional(1);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeC4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(2)) {
                boostOperasional(2);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeD4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(3)) {
                boostOperasional(3);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeA3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(0)) {
                boostPenghasilan(0);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeB3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(1)) {
                boostPenghasilan(1);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeC3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(2)) {
                boostPenghasilan(2);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeD3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(3)) {
                boostPenghasilan(3);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeA2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(0)) {
                upgradeOperasionalUsaha(laundry, 0);
                halamanUsaha.getOperasional1().setText(String.valueOf(pemain.getLaundry().getOperasional()));
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeB2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(1)) {
                upgradeOperasionalUsaha(kedaiKopi, 1);
                halamanUsaha.getOperasional2().setText(String.valueOf(pemain.getKedaiKopi().getOperasional()));
            } else {
                popup("Usaha belum anda miliki");
            }

        }
    }

    private class klikUpgradeC2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(2)) {
                upgradeOperasionalUsaha(percetakan, 0);
                halamanUsaha.getOperasional3().setText(String.valueOf(pemain.getPercetakan().getOperasional()));
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeD2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(3)) {
                upgradeOperasionalUsaha(indomaret, 0);
                halamanUsaha.getOperasional4().setText(String.valueOf(pemain.getIndomaret().getOperasional()));
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeA1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(0)) {
                upgradePenghasilanUsaha(laundry, 0);
                halamanUsaha.getPenghasilan1().setText(String.valueOf(pemain.getLaundry().getPenghasilan()));
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    public void updateKendaraan() {
        halamanUsaha.getKendaraanTxt().setText(String.valueOf(pemain.getJumlahKendaraan()));
    }

    public void updateProperty() {
        halamanUsaha.getPropertyTxt().setText(String.valueOf(pemain.getJumlahProperty()));
    }

    private class klikUpgradeB1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(1)) {
                upgradePenghasilanUsaha(kedaiKopi, 1);
                halamanUsaha.getPenghasilan2().setText(String.valueOf(pemain.getKedaiKopi().getPenghasilan()));
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeC1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(2)) {
                upgradePenghasilanUsaha(percetakan, 2);
                halamanUsaha.getPenghasilan3().setText(String.valueOf(pemain.getPercetakan().getPenghasilan()));
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeD1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(3)) {
                upgradePenghasilanUsaha(indomaret, 3);
                halamanUsaha.getPenghasilan3().setText(String.valueOf(pemain.getIndomaret().getPenghasilan()));
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikBuatLaundry implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!udahPunya(0)) {
                if (uangCukup(pemain.getDana(), laundry.getModal())) {
                    pemain.setDana(pemain.getDana() - laundry.getModal());
                    pemain.setLaundry(laundry);
                    popup("Selamat Usaha Berhasil Dibuat");
                    updateDana();
                    //label upgrade usaha 1
                    halamanUsaha.getPenghasilan1().setText(String.valueOf(pemain.getLaundry().getPenghasilan()));
                    halamanUsaha.getOperasional1().setText(String.valueOf(pemain.getLaundry().getOperasional()));
                    halamanUsaha.getU11txt().setText(String.valueOf(pemain.getLaundry().getuPenghasilan()));
                    halamanUsaha.getU12txt().setText(String.valueOf(pemain.getLaundry().getuOperasional()));
                    halamanUsaha.getU13txt().setText(String.valueOf(pemain.getLaundry().getbPenghasilan()));
                    halamanUsaha.getU14txt().setText(String.valueOf(pemain.getLaundry().getbOperasional()));
                    halamanUsaha.getU15txt().setText(String.valueOf(pemain.getLaundry().getbSpesial()));
                } else {
                    popup("Maaf, Dana yang anda miliki tidak mencukupi untuk membuat usaha ini");
                }
            }
        }
    }

    private class klikBuatKedaiKopi implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!udahPunya(1)) {
                if (uangCukup(pemain.getDana(), kedaiKopi.getModal())) {
                    pemain.setDana(pemain.getDana() - kedaiKopi.getModal());
                    pemain.setKedaiKopi(kedaiKopi);
                    popup("Selamat Usaha Berhasil Dibuat");
                    updateDana();
                    //label upgrade usaha 2
                    halamanUsaha.getPenghasilan2().setText(String.valueOf(pemain.getKedaiKopi().getPenghasilan()));
                    halamanUsaha.getOperasional2().setText(String.valueOf(pemain.getKedaiKopi().getOperasional()));
                    halamanUsaha.getPenghasilan2().setText(String.valueOf(pemain.getKedaiKopi().getPenghasilan()));
                    halamanUsaha.getU21txt().setText(String.valueOf(pemain.getKedaiKopi().getuPenghasilan()));
                    halamanUsaha.getU22txt().setText(String.valueOf(pemain.getKedaiKopi().getuOperasional()));
                    halamanUsaha.getU23txt().setText(String.valueOf(pemain.getKedaiKopi().getbPenghasilan()));
                    halamanUsaha.getU24txt().setText(String.valueOf(pemain.getKedaiKopi().getbOperasional()));
                    halamanUsaha.getU25txt().setText(String.valueOf(pemain.getKedaiKopi().getbSpesial()));
                } else {
                    popup("Maaf, Dana yang anda miliki tidak mencukupi untuk membuat usaha ini");
                }
            }
        }
    }

    private class klikBuatPercetakan implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!udahPunya(2)) {
                if (uangCukup(pemain.getDana(), percetakan.getModal())) {
                    pemain.setDana(pemain.getDana() - indomaret.getModal());
                    pemain.setPercetakan(percetakan);
                    popup("Selamat Usaha Berhasil Dibuat");
                    updateDana();
                    //label upgrade usaha 3
                    halamanUsaha.getPenghasilan3().setText(String.valueOf(pemain.getPercetakan().getPenghasilan()));
                    halamanUsaha.getOperasional3().setText(String.valueOf(pemain.getPercetakan().getOperasional()));
                    halamanUsaha.getU31txt().setText(String.valueOf(pemain.getPercetakan().getuPenghasilan()));
                    halamanUsaha.getU32txt().setText(String.valueOf(pemain.getPercetakan().getuOperasional()));
                    halamanUsaha.getU33txt().setText(String.valueOf(pemain.getPercetakan().getbPenghasilan()));
                    halamanUsaha.getU34txt().setText(String.valueOf(pemain.getPercetakan().getbOperasional()));
                    halamanUsaha.getU35txt().setText(String.valueOf(pemain.getPercetakan().getbSpesial()));
                } else {
                    popup("Maaf, Dana yang anda miliki tidak mencukupi untuk membuat usaha ini");
                }
            }
        }
    }

    private class klikBuatIndomaret implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!udahPunya(3)) {
                if (uangCukup(pemain.getDana(), indomaret.getModal())) {
                    pemain.setDana(pemain.getDana() - indomaret.getModal());
                    pemain.setIndomaret(indomaret);
                    popup("Selamat Usaha Berhasil Dibuat");
                    updateDana();
                    //label upgrade usaha 4
                    halamanUsaha.getPenghasilan4().setText(String.valueOf(pemain.getIndomaret().getPenghasilan()));
                    halamanUsaha.getOperasional4().setText(String.valueOf(pemain.getIndomaret().getOperasional()));
                    halamanUsaha.getU41txt().setText(String.valueOf(pemain.getIndomaret().getuPenghasilan()));
                    halamanUsaha.getU42txt().setText(String.valueOf(pemain.getIndomaret().getuOperasional()));
                    halamanUsaha.getU43txt().setText(String.valueOf(pemain.getIndomaret().getbPenghasilan()));
                    halamanUsaha.getU44txt().setText(String.valueOf(pemain.getIndomaret().getbOperasional()));
                    halamanUsaha.getU45txt().setText(String.valueOf(pemain.getIndomaret().getbSpesial()));
                } else {
                    popup("Maaf, Dana yang anda miliki tidak mencukupi untuk membuat usaha ini");
                }
            }
        }
    }

    private void perbaruiStatus() {
        if (pemain.getUsaha()[0] != null) {
            halamanUsaha.getPenghasilan1().setText(String.valueOf(this.pemain.getLaundry().getPenghasilan()));
            halamanUsaha.getOperasional1().setText(String.valueOf(this.pemain.getLaundry().getOperasional()));
            halamanUsaha.getU11txt().setText(String.valueOf(this.pemain.getLaundry().getuPenghasilan()));
            halamanUsaha.getU12txt().setText(String.valueOf(this.pemain.getLaundry().getuOperasional()));
            halamanUsaha.getU13txt().setText(String.valueOf(this.pemain.getLaundry().getbPenghasilan()));
            halamanUsaha.getU14txt().setText(String.valueOf(this.pemain.getLaundry().getbOperasional()));
            halamanUsaha.getU15txt().setText(String.valueOf(this.pemain.getLaundry().getbSpesial()));
        }

        if (pemain.getUsaha()[1] != null) {
            halamanUsaha.getPenghasilan2().setText(String.valueOf(this.pemain.getKedaiKopi().getPenghasilan()));
            halamanUsaha.getOperasional2().setText(String.valueOf(this.pemain.getKedaiKopi().getOperasional()));
            halamanUsaha.getU21txt().setText(String.valueOf(this.pemain.getKedaiKopi().getuPenghasilan()));
            halamanUsaha.getU22txt().setText(String.valueOf(this.pemain.getKedaiKopi().getuOperasional()));
            halamanUsaha.getU23txt().setText(String.valueOf(this.pemain.getKedaiKopi().getbPenghasilan()));
            halamanUsaha.getU24txt().setText(String.valueOf(this.pemain.getKedaiKopi().getbOperasional()));
            halamanUsaha.getU25txt().setText(String.valueOf(this.pemain.getKedaiKopi().getbSpesial()));
        }

        if (pemain.getUsaha()[2] != null) {
            halamanUsaha.getPenghasilan3().setText(String.valueOf(this.pemain.getPercetakan().getPenghasilan()));
            halamanUsaha.getOperasional3().setText(String.valueOf(this.pemain.getPercetakan().getOperasional()));
            halamanUsaha.getU31txt().setText(String.valueOf(this.pemain.getPercetakan().getuPenghasilan()));
            halamanUsaha.getU32txt().setText(String.valueOf(this.pemain.getPercetakan().getuOperasional()));
            halamanUsaha.getU33txt().setText(String.valueOf(this.pemain.getPercetakan().getbPenghasilan()));
            halamanUsaha.getU34txt().setText(String.valueOf(this.pemain.getPercetakan().getbOperasional()));
            halamanUsaha.getU35txt().setText(String.valueOf(this.pemain.getPercetakan().getbSpesial()));
        }

        if (pemain.getUsaha()[3] != null) {
            halamanUsaha.getPenghasilan4().setText(String.valueOf(this.pemain.getIndomaret().getPenghasilan()));
            halamanUsaha.getOperasional4().setText(String.valueOf(this.pemain.getIndomaret().getOperasional()));
            halamanUsaha.getU41txt().setText(String.valueOf(this.pemain.getIndomaret().getuPenghasilan()));
            halamanUsaha.getU42txt().setText(String.valueOf(this.pemain.getIndomaret().getuOperasional()));
            halamanUsaha.getU43txt().setText(String.valueOf(this.pemain.getIndomaret().getbPenghasilan()));
            halamanUsaha.getU44txt().setText(String.valueOf(this.pemain.getIndomaret().getbOperasional()));
            halamanUsaha.getU45txt().setText(String.valueOf(this.pemain.getIndomaret().getbSpesial()));
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
            perbaruiStatus();
        }
    }

}
