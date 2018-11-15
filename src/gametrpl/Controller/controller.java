/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package gametrpl.Controller;

import gametrpl.pemain;
import gametrpl.usaha;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author ROG
 */
public class controller {

    pemain pemain;

    public boolean uangCukup(int danaPemain, int harga) {
        return danaPemain >= harga;
    }

    public void save() {
        if (pemain.save()) {
            System.out.println("save berhasil");
            int id_pemain = pemain.getIdPemain();
            for (int i = 0; i < pemain.getUsaha().length; i++) {
                if (pemain.getUsaha()[i] != null) {
                    pemain.getUsaha()[i].save(id_pemain, i);
                }
            }
            if (!pemain.getKendaraan().isEmpty()) {
                int jmlhKendaraan = pemain.getJumlahKendaraan();
                for (int i = 0; i < jmlhKendaraan; i++) {
                    pemain.getKendaraan().get(i).save(id_pemain);
                }
            }
            if (!pemain.getProperty().isEmpty()) {
                int jmlhProperty = pemain.getJumlahProperty();
                System.out.println(jmlhProperty);
                for (int i = 0; i < jmlhProperty; i++) {
                    pemain.getProperty().get(i).save(id_pemain);
                }
            }
            if (pemain.getUtang() != null) {
                if (pemain.getUtang().getKendaraan() != null) {
                    int id_kendaraan = pemain.getUtang().getKendaraan().getIdKendaraan(id_pemain);
                    pemain.getUtang().save(id_pemain, id_kendaraan, true);
                } else {
                    int id_property = pemain.getUtang().getProperty().getIdProperty(id_pemain);
                    pemain.getUtang().save(id_pemain, id_property, false);

                }
            }

        } else {
            System.out.println("save gagal");
        }
    }

    public int randomPersen(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public int hitungPenghasilan() {
        int penghasilan, minPlusPenghasilan, penghasilanSeluruh = 0, penghasilanKotor, penghasilanBersih, operasional, persen = 0;
        int dmg=0;
        usaha[] usahaPemain = pemain.getUsaha();
          int event = randomPersen(0, 100);
            if (event == 14 || event == 28 || event == 42 || event == 56 || event == 70 || event == 84) {
                int jenisEvent = randomPersen(0, 100);
                System.out.println(jenisEvent);
                int demage = 0;
                String bencana = "", prsn = "";
                if (jenisEvent <= 15) {
                    dmg = 40;
                    bencana = "Gempa Bumi";
                    prsn = "40";
                } else if (jenisEvent < 15 && jenisEvent <= 65) {
                    dmg = 20;
                    bencana = "Banjir";
                    prsn = "20";
                } else if (jenisEvent < 65 && jenisEvent <= 75) {
                    dmg=60;
                    bencana = "Tsunami";
                    prsn = "60";
                } else if (jenisEvent < 75 && jenisEvent <= 100) {
                    dmg=15;
                    bencana = "Longsor";
                    prsn = "15";
                }
                pemain.startBencana(prsn, bencana);
                

            } else {
                
            }
        for (int i = 0; i < usahaPemain.length; i++) {
            //generate angka random berdasarkan trend usaha
            if (usahaPemain[i] != null) {
                if (usahaPemain[i].isBoostP()) {
                    persen = randomPersen(usahaPemain[i].getMin() - usahaPemain[i].getPersenUpS(), usahaPemain[i].getMax() + 1);
                    usahaPemain[i].setTempP(usahaPemain[i].getTempP() - 1);
                    if (usahaPemain[i].getTempP() == 0) {
                        usahaPemain[i].setBoostP(false);
                        usahaPemain[i].setTempP(usahaPemain[i].getP());

                    }
                } else {
                    persen = randomPersen(usahaPemain[i].getMin(), usahaPemain[i].getMax() + 1);
                }
                System.out.println("Persen :" + String.valueOf(persen));
                penghasilan = usahaPemain[i].getPenghasilan();
                minPlusPenghasilan = persen * penghasilan / 100;
                penghasilanKotor = penghasilan - minPlusPenghasilan;
                System.out.println("penghasilan :" + String.valueOf(penghasilanKotor));
                usahaPemain[i].setPenghasilan(penghasilanKotor);

                operasional = usahaPemain[i].getOperasional();
                if (usahaPemain[i].isBoostO()) {
                    operasional = operasional - (operasional * usahaPemain[i].getPersenOS() / 100);
                    usahaPemain[i].setTempO(usahaPemain[i].getTempO() - 1);
                    if (usahaPemain[i].getTempO() == 0) {
                        usahaPemain[i].setBoostO(false);
                        usahaPemain[i].setTempO(usahaPemain[i].getO());
                    }
                }

                if (usahaPemain[i].isBoostS()) {
                    int persenP = randomPersen(0 - usahaPemain[i].getPersenUpS(), 0 + 1);
                    int penghasilanB = penghasilan * persenP / 100;
                    penghasilanKotor = penghasilanKotor + penghasilanB;
                    operasional = operasional - (operasional * usahaPemain[i].getPersenOS() / 100);
                    usahaPemain[i].setTempS(usahaPemain[i].getTempS() - 1);
                    if (usahaPemain[i].getTempS() > 1) {
                        usahaPemain[i].setBoostS(false);
                        usahaPemain[i].setTempS(usahaPemain[i].getS());
                    }
                }
                if (pemain.isBencana()) {
                    penghasilanKotor = penghasilanKotor - (penghasilanKotor*dmg/100);
                }
                penghasilanBersih = penghasilanKotor - operasional;
                penghasilanSeluruh += penghasilanBersih;

            }
        }
        pemain.setUsaha(usahaPemain);
        return penghasilanSeluruh;
    }
}
