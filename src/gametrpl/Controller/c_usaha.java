/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl.Controller;

import gametrpl.View.halamanUsaha;
import gametrpl.pemain;
import gametrpl.usaha;
import gametrpl.Controller.c_dealer;
import gametrpl.Controller.c_property;
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

    pemain pemain;
    halamanUsaha halamanUsaha;

    //usaha yang ada
    usaha laundry, kedaiKopi, percetakan, indomaret;

    //persentase min max penghasilan 
    int min = -20, max = 80;

    c_usaha(pemain pemain, boolean isNewGame) {
        this.pemain = pemain;
        System.out.println(String.valueOf(this.pemain.getTurn()));
        this.pemain.setTurn(pemain.getTurn());

        //trend laundry
        int[][] minMax1 = {
            {-20, 5},
            {-7, 15},
            {-10, 10},
            {-10, 10},
            {-10, 10},
            {-7, 15},
            {-20, 5},
            {-10, 10},
            {-10, 10},
            {-10, 10},
            {-10, 10},
            {-10, 10}};
        
        
        //trend kedai kopi
        int[][] minMax2 = {
            {-20, 5},
            {-10, 10},
            {-10, 10},
            {-7, 15},
            {-10, 10},
            {-20, 5},
            {-10, 10},
            {-10, 10},
            {-10, 10},
            {-7, 15},
            {-10, 10},
            {-10, 10}};
        
        //trend fotokopi
        int[][] minMax3 = {
            {-7, 15},
            {-7, 15},
            {-10, 10},
            {-10, 10},
            {-20, 5},
            {-10, 10},
            {-20, 5},
            {-10, 10},
            {-10, 10},
            {-10, 10},
            {-10, 10},
            {-20, 10}};
        
        //trend indomaret
        int[][] minMax4 = {
            {-20, 5},
            {-15, 7},
            {-10, 10},
            {-7, 15},
            {-10, 10},
            {-20, 5},
            {-10, 10},
            {-15, 7},
            {-10, 10},
            {-7, 15},
            {-10, 10},
            {-20, 5}};

        laundry = new usaha("laundry", 9050, 2000, 1100, 5000, 5000, 5000, 5000, 5000, 10, 10, 5, 5, 5, minMax1);
        kedaiKopi = new usaha("Kedai Kopi", 21500, 60000, 8000, 10000, 10000, 10000, 10000, 10000, 10, 10, 5, 5, 5, minMax2);
        percetakan = new usaha("fotokopi", 200000, 60000, 40000, 13000, 13000, 13000, 13000, 13000, 10, 10, 5, 5, 5, minMax3);
        indomaret = new usaha("Waralaba Minimarket", 394000, 180000, 166000, 20000, 20000, 20000, 20000, 20000, 10, 10, 5, 5, 5, minMax4);

        halamanUsaha = new halamanUsaha();
        halamanUsaha.setVisible(true);

        if (!isNewGame) {
            if (pemain.getLaundry().getNamaUsaha().equals(laundry.getNamaUsaha())) {
                halamanUsaha.getU11txt().setText(String.valueOf(this.pemain.getLaundry().getuPenghasilan()));
                halamanUsaha.getU12txt().setText(String.valueOf(this.pemain.getLaundry().getuOperasional()));
                halamanUsaha.getU13txt().setText(String.valueOf(this.pemain.getLaundry().getbPenghasilan()));
                halamanUsaha.getU14txt().setText(String.valueOf(this.pemain.getLaundry().getbOperasional()));
                halamanUsaha.getU15txt().setText(String.valueOf(this.pemain.getLaundry().getbSpesial()));
            }
            if (pemain.getKedaiKopi().getNamaUsaha().equals(kedaiKopi.getNamaUsaha())) {
                halamanUsaha.getU21txt().setText(String.valueOf(this.pemain.getKedaiKopi().getuPenghasilan()));
                halamanUsaha.getU22txt().setText(String.valueOf(this.pemain.getKedaiKopi().getuOperasional()));
                halamanUsaha.getU23txt().setText(String.valueOf(this.pemain.getKedaiKopi().getbPenghasilan()));
                halamanUsaha.getU24txt().setText(String.valueOf(this.pemain.getKedaiKopi().getbOperasional()));
                halamanUsaha.getU25txt().setText(String.valueOf(this.pemain.getKedaiKopi().getbSpesial()));
            }
            if (pemain.getPercetakan().getNamaUsaha().equals(percetakan.getNamaUsaha())) {
                halamanUsaha.getU31txt().setText(String.valueOf(this.pemain.getPercetakan().getuPenghasilan()));
                halamanUsaha.getU32txt().setText(String.valueOf(this.pemain.getPercetakan().getuOperasional()));
                halamanUsaha.getU33txt().setText(String.valueOf(this.pemain.getPercetakan().getbPenghasilan()));
                halamanUsaha.getU34txt().setText(String.valueOf(this.pemain.getPercetakan().getbOperasional()));
                halamanUsaha.getU35txt().setText(String.valueOf(this.pemain.getPercetakan().getbSpesial()));
            }
            if (pemain.getIndomaret().getNamaUsaha().equals(percetakan.getNamaUsaha())) {
                halamanUsaha.getU41txt().setText(String.valueOf(this.pemain.getIndomaret().getuPenghasilan()));
                halamanUsaha.getU42txt().setText(String.valueOf(this.pemain.getIndomaret().getuOperasional()));
                halamanUsaha.getU43txt().setText(String.valueOf(this.pemain.getIndomaret().getbPenghasilan()));
                halamanUsaha.getU44txt().setText(String.valueOf(this.pemain.getIndomaret().getbOperasional()));
                halamanUsaha.getU45txt().setText(String.valueOf(this.pemain.getIndomaret().getbSpesial()));
            }
        }

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
        halamanUsaha.getPenghasilan1().setText(String.valueOf(this.pemain.getLaundry().getPenghasilan()));
        halamanUsaha.getPenghasilan2().setText(String.valueOf(this.pemain.getKedaiKopi().getPenghasilan()));
        halamanUsaha.getPenghasilan3().setText(String.valueOf(this.pemain.getPercetakan().getPenghasilan()));
        halamanUsaha.getPenghasilan4().setText(String.valueOf(this.pemain.getIndomaret().getPenghasilan()));

        //label Operasional
        halamanUsaha.getOperasional1().setText(String.valueOf(this.pemain.getLaundry().getOperasional()));
        halamanUsaha.getOperasional2().setText(String.valueOf(this.pemain.getKedaiKopi().getOperasional()));
        halamanUsaha.getOperasional3().setText(String.valueOf(this.pemain.getPercetakan().getOperasional()));
        halamanUsaha.getOperasional4().setText(String.valueOf(this.pemain.getIndomaret().getOperasional()));

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

    public boolean udahPunya(usaha usaha, int index) {
        return pemain.getUsaha()[index].getNamaUsaha().equalsIgnoreCase(usaha.getNamaUsaha());
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
        popup("Selamat, Usaha Anda Berhasil Di Upgrade");

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
            if (udahPunya(laundry, 0)) {
                boostSpecial(0);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeB5 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(percetakan, 1)) {
                boostSpecial(1);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeC5 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(percetakan, 2)) {
                boostSpecial(2);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeD5 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(indomaret, 3)) {
                boostSpecial(3);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeA4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(laundry, 0)) {
                boostOperasional(0);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeB4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(kedaiKopi, 1)) {
                boostOperasional(1);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeC4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(percetakan, 2)) {
                boostOperasional(2);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeD4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(indomaret, 3)) {
                boostOperasional(3);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeA3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(laundry, 0)) {
                boostPenghasilan(0);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeB3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(kedaiKopi, 1)) {
                boostPenghasilan(1);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeC3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(percetakan, 2)) {
                boostPenghasilan(2);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeD3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(indomaret, 3)) {
                boostPenghasilan(3);
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeA2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(laundry, 0)) {
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
            if (udahPunya(kedaiKopi, 1)) {
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
            if (udahPunya(percetakan, 2)) {
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
            if (udahPunya(indomaret, 3)) {
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
            if (udahPunya(laundry, 0)) {
                upgradePenghasilanUsaha(laundry, 0);
                halamanUsaha.getPenghasilan1().setText(String.valueOf(pemain.getLaundry().getPenghasilan()));
            } else {
                popup("Usaha belum anda miliki");
            }
        }
    }

    private class klikUpgradeB1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (udahPunya(kedaiKopi, 1)) {
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
            if (udahPunya(percetakan, 2)) {
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
            if (udahPunya(indomaret, 3)) {
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

    private class klikBuatKedaiKopi implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
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

    private class klikBuatPercetakan implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
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

    private class klikBuatIndomaret implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
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

    private void perbaruiStatus() {
        halamanUsaha.getPenghasilan1().setText(String.valueOf(pemain.getLaundry().getPenghasilan()));
        halamanUsaha.getOperasional1().setText(String.valueOf(pemain.getLaundry().getOperasional()));
        halamanUsaha.getPenghasilan2().setText(String.valueOf(pemain.getKedaiKopi().getPenghasilan()));
        halamanUsaha.getOperasional2().setText(String.valueOf(pemain.getKedaiKopi().getOperasional()));
        halamanUsaha.getPenghasilan3().setText(String.valueOf(pemain.getPercetakan().getPenghasilan()));
        halamanUsaha.getOperasional3().setText(String.valueOf(pemain.getPercetakan().getOperasional()));
        halamanUsaha.getPenghasilan4().setText(String.valueOf(pemain.getIndomaret().getPenghasilan()));
        halamanUsaha.getOperasional4().setText(String.valueOf(pemain.getIndomaret().getOperasional()));
    }

    private class klikNextTurn implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            pemain.setPenghasilan(hitungPenghasilan());
            pemain.setDana(pemain.getDana() + pemain.getPenghasilan());
            if(pemain.getBulan()>12){
                pemain.setBulan(pemain.getBulan()+1);
            }else{
                pemain.setBulan(0);
            }
            updateDana();
            updatePenghasilan();
            tambahTurn();
            updateTurn();
            perbaruiStatus();
        }
    }

}
