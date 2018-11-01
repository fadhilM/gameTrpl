/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package gametrpl.Controller;

import gametrpl.utang;
import gametrpl.kendaraan;
import gametrpl.property;
import gametrpl.pemain;
import gametrpl.Controller.c_usaha;
import gametrpl.Controller.c_property;
import gametrpl.Controller.c_dealer;
import gametrpl.View.halamanBankAwal;
import gametrpl.View.halamanBankJaminan;
import gametrpl.View.halamanBankKalku;
import gametrpl.usaha;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ROG
 */
public class c_bank {

    utang utang;
    pemain pemain;
    property property;
    kendaraan kendaraan;
    byte awal = 1;

    c_usaha c_usaha;
    c_property c_property;
    c_dealer c_dealer;
    halamanBankAwal halamanBankAwal;
    halamanBankJaminan halamanBankJaminan;
    halamanBankKalku halamanBankKalku;

    public c_bank(pemain pemain) {
        this.pemain = pemain;
        halamanBankAwal = new halamanBankAwal();
        halamanBankJaminan = new halamanBankJaminan();
        halamanBankKalku = new halamanBankKalku();

        //Tombol Ganti Halaman
        halamanBankAwal.getB_dealer().addActionListener(new klikDealer());
        halamanBankAwal.getB_property().addActionListener(new klikProperty());
        halamanBankAwal.getB_usaha().addActionListener(new klikUsaha());
        halamanBankJaminan.getB_bank().addActionListener(new klikBank());
        halamanBankJaminan.getB_dealer().addActionListener(new klikDealer());
        halamanBankJaminan.getB_property().addActionListener(new klikProperty());
        halamanBankJaminan.getB_usaha().addActionListener(new klikUsaha());
        halamanBankKalku.getB_usaha().addActionListener(new klikUsaha());
        halamanBankKalku.getB_bank().addActionListener(new klikBank());
        halamanBankKalku.getB_dealer().addActionListener(new klikDealer());

        halamanBankAwal.getB_pinjam().addActionListener(new klikPinjam());
        halamanBankKalku.getB_pinjam().addActionListener(new klikPinjam());
        halamanBankAwal.getB_hitung().addActionListener(new klikHitung());
        halamanBankJaminan.getB_hitung().addActionListener(new klikHitung());

        halamanBankAwal.getB_nextTurn().addActionListener(new klikNextTurn());
        halamanBankJaminan.getB_nextTurn().addActionListener(new klikNextTurn());
        halamanBankKalku.getB_nextTurn().addActionListener(new klikNextTurn());

        //button di halaman kalkulasi
        halamanBankKalku.getB_kalkulasi().addActionListener(new kalkulasi());
        halamanBankKalku.getPinjamanTxt().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                if (c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '0')) {
                        ke.consume();
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        halamanBankKalku.getSukuBunga().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                if (c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '0' || c == '.')) {
                        ke.consume();
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        halamanBankKalku.getTenor1().setActionCommand("12");
        halamanBankKalku.getTenor2().setActionCommand("24");
        halamanBankKalku.getTenor3().setActionCommand("36");
        halamanBankKalku.getTenor4().setActionCommand("48");

        //tampilan di halaman peminjaman/jaminan
        setTabelModelKendaraan(this.pemain.getKendaraan());
        setTabelModelProperty(this.pemain.getProperty());
        halamanBankJaminan.getTenor1().setActionCommand("12");
        halamanBankJaminan.getTenor2().setActionCommand("24");
        halamanBankJaminan.getTenor3().setActionCommand("36");
        halamanBankJaminan.getTenor4().setActionCommand("48");

        //button yang ada di halaman peminajaman/jaminan
        halamanBankJaminan.getPilihJaminanK().addActionListener(new pilihJK());
        halamanBankJaminan.getPilihJaminanP().addActionListener(new pilihPK());
        halamanBankJaminan.getB_pinjam().addActionListener(new pinjam());

        //informasi 
        halamanBankAwal.getDanaTxt().setText(String.valueOf(pemain.getDana()));
        halamanBankAwal.getKendaraanTxt().setText(String.valueOf(pemain.getJumlahKendaraan()));
        halamanBankAwal.getPropertyTxt().setText(String.valueOf(pemain.getJumlahProperty()));
        halamanBankAwal.getTurnTxt().setText(String.valueOf(pemain.getTurn()));
        halamanBankJaminan.getDanaTxt().setText(String.valueOf(pemain.getDana()));
        halamanBankJaminan.getKendaraanTxt().setText(String.valueOf(pemain.getJumlahKendaraan()));
        halamanBankJaminan.getPropertyTxt().setText(String.valueOf(pemain.getJumlahProperty()));
        halamanBankJaminan.getTurnTxt().setText(String.valueOf(pemain.getTurn()));
        halamanBankKalku.getDanaTxt().setText(String.valueOf(pemain.getDana()));
        halamanBankKalku.getKendaraanTxt().setText(String.valueOf(pemain.getJumlahKendaraan()));
        halamanBankKalku.getPropertyTxt().setText(String.valueOf(pemain.getJumlahProperty()));
        halamanBankKalku.getTurnTxt().setText(String.valueOf(pemain.getTurn()));

        //Tampil Halaman Awal Bank
        awal = 1;
        halamanBankAwal.setVisible(true);

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

            penghasilan = usahaPemain[i].getPenghasilan();
            int minPlusPenghasilan = persen * penghasilan / 100;
            penghasilanKotor = penghasilan - minPlusPenghasilan;

            usahaPemain[i].setPenghasilan(penghasilanKotor);

            operasional = usahaPemain[i].getOperasional();
            if (usahaPemain[i].isBoostO()) {
                int persenB = ThreadLocalRandom.current().nextInt(-15, -5 - 1);
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

    public void popup(String popup) {
        JOptionPane.showMessageDialog(halamanBankAwal, popup);
    }

    public void updatePenghasilan() {
        halamanBankAwal.getPenghasilanTxt().setText(String.valueOf(pemain.getPenghasilan()));
        halamanBankJaminan.getPenghasilanTxt().setText(String.valueOf(pemain.getPenghasilan()));
        halamanBankKalku.getPenghasilanTxt().setText(String.valueOf(pemain.getPenghasilan()));
    }

    public void updateDana() {
        halamanBankAwal.getDanaTxt().setText(String.valueOf(pemain.getDana()));
        halamanBankJaminan.getDanaTxt().setText(String.valueOf(pemain.getDana()));
        halamanBankKalku.getDanaTxt().setText(String.valueOf(pemain.getDana()));
    }

    public void tambahTurn() {
        pemain.setTurn(pemain.getTurn() + 1);
    }

    public void updateTurn() {
        halamanBankAwal.getTurnTxt().setText(String.valueOf(pemain.getTurn()));
        halamanBankJaminan.getTurnTxt().setText(String.valueOf(pemain.getTurn()));
        halamanBankKalku.getTurnTxt().setText(String.valueOf(pemain.getTurn()));
    }

    public void updateKendaraan() {
        halamanBankAwal.getKendaraanTxt().setText(String.valueOf(pemain.getJumlahKendaraan()));
        halamanBankJaminan.getKendaraanTxt().setText(String.valueOf(pemain.getJumlahKendaraan()));
        halamanBankKalku.getKendaraanTxt().setText(String.valueOf(pemain.getJumlahKendaraan()));      
    }

    public void updateProperty() {
        halamanBankAwal.getPropertyTxt().setText(String.valueOf(pemain.getJumlahProperty()));
        halamanBankJaminan.getPropertyTxt().setText(String.valueOf(pemain.getJumlahProperty()));
        halamanBankKalku.getPropertyTxt().setText(String.valueOf(pemain.getJumlahProperty()));
    }

    private class klikNextTurn implements ActionListener {

        public klikNextTurn() {
        }

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

    private class pinjam implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (utang == null) {
                popup("Harap Pilih Jaminan yang digunakan");
            } else if (!halamanBankJaminan.getTenor1().isSelected() && !halamanBankJaminan.getTenor2().isSelected()
                    && !halamanBankJaminan.getTenor3().isSelected() && !halamanBankJaminan.getTenor4().isSelected()) {
                popup("Harap Pilih Lama Peminjaman");
            } else {
                double tenor = Double.parseDouble(halamanBankJaminan.getTenor().getSelection().getActionCommand());
                utang.setTenor(tenor);
                double danaUtang;
                if (utang.isJaminan()) {
                    danaUtang = utang.getKendaraan().hargaJual(pemain) * 80 / 100;
                } else {
                    danaUtang = utang.getProperty().hargaJual(pemain) * 80 / 100;
                }
                utang.setUtang(danaUtang);
                utang.setTurn(pemain.getTurn());

                double angsuran = utang.hitungAngsuran(danaUtang, tenor, utang.getSukubunga());
                utang.setAngsuran(angsuran);
                utang.hitungBiayaPeminjaman();
                pemain.setUtang(utang);
                pemain.setDana((int) (pemain.getDana() + utang.getUtang()));

                popup("Pinjaman yang Anda Dapatkan sebesar " + danaUtang + " dengan angsuran sebesar " + angsuran + " yang harus dibayar selama " + utang.getTenor() + " bulan");
            }

        }
    }

    private class pilihPK implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int selectRow = halamanBankJaminan.getTabelPropertyJ().getSelectedRow();
            if (selectRow < 0) {
                popup("Harap Jaminan terlebih dahulu");
            } else {
                int id = Integer.parseInt(halamanBankJaminan.getTabelPropertyJ().getValueAt(selectRow, 0).toString());
                for (int i = 0; i < pemain.getJumlahProperty(); i++) {
                    if (id == pemain.getProperty().get(i).getId()) {
                        property = pemain.getProperty().get(i);
                    }
                }
                utang = new utang(property, false);
                halamanBankJaminan.getMerkText().setText(utang.getProperty().getNama());
                halamanBankJaminan.getPinjamanText().setText(String.valueOf(utang.getProperty().hargaJual(pemain) * 80 / 100));

            }
        }
    }

    private class pilihJK implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int selectRow = halamanBankJaminan.getTabelKendaraanJ().getSelectedRow();
            if (selectRow < 0) {
                popup("Harap pilih Jaminan terlebih dahulu");
            } else {
                int id = Integer.parseInt(halamanBankJaminan.getTabelKendaraanJ().getValueAt(selectRow, 0).toString());
                for (int i = 0; i < pemain.getJumlahKendaraan(); i++) {
                    if (id == pemain.getKendaraan().get(i).getId()) {
                        kendaraan = pemain.getKendaraan().get(i);
                    }
                }
                utang = new utang(kendaraan, true);
                halamanBankJaminan.getMerkText().setText(utang.getKendaraan().getNama());
                halamanBankJaminan.getPinjamanText().setText(String.valueOf(utang.getKendaraan().hargaJual(pemain) * 80 / 100));
            }
        }
    }

    private class kalkulasi implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            double pinjaman, sukuBunga, tenor, angsuran, bungaPerbulan;
            if (halamanBankKalku.getPinjamanTxt().getText().trim().equals("")) {
                popup("Harap Isi Jumlah Pinjaman Yang Akan Dihitung");
            } else if (halamanBankKalku.getSukuBunga().getText().trim().equals("")) {
                popup("Harap Isi Jumlah Suku Bunga Per Tahun Yang Akan Dihitung");
            } else if (!halamanBankKalku.getTenor1().isSelected() && !halamanBankKalku.getTenor2().isSelected()
                    && !halamanBankKalku.getTenor3().isSelected() && !halamanBankKalku.getTenor4().isSelected()) {
                popup("Harap Pilih Lama Peminjaman");
            } else {
                pinjaman = Double.parseDouble(halamanBankKalku.getPinjamanTxt().getText());
                sukuBunga = Double.parseDouble(halamanBankKalku.getSukuBunga().getText());
                tenor = Double.parseDouble(halamanBankKalku.getTenor().getSelection().getActionCommand());
                utang utang = new utang();
                bungaPerbulan = utang.getBungaPerBulan(sukuBunga, pinjaman);
                angsuran = utang.hitungAngsuran(pinjaman, tenor, sukuBunga);
                halamanBankKalku.getBungaBulananText().setText(String.valueOf(bungaPerbulan));
                halamanBankKalku.getPokokPlusBungaText().setText(String.valueOf(angsuran));
            }

        }
    }

    private class klikBank implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            halamanBankAwal.setVisible(true);
            if (awal == 2) {
                halamanBankJaminan.setVisible(false);
            } else {
                halamanBankKalku.setVisible(false);
            }
            awal = 1;
        }
    }

    private class klikHitung implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            halamanBankKalku.setVisible(true);
            if (awal == 1) {
                halamanBankAwal.setVisible(false);
            } else {
                halamanBankJaminan.setVisible(false);
            }
            awal = 3;

        }
    }

    private class klikPinjam implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (pemain.getUtang() == null) {
                halamanBankJaminan.setVisible(true);
                if (awal == 1) {
                    halamanBankAwal.setVisible(false);
                } else {
                    halamanBankKalku.setVisible(false);
                }
                awal = 2;
            } else {
                popup("Anda Masih Memiliki Hutang");
            }

        }
    }

    private class klikDealer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            c_dealer = new c_dealer(pemain);
            if (awal == 1) {
                halamanBankAwal.dispose();
            } else if (awal == 2) {
                halamanBankJaminan.dispose();
            } else {
                halamanBankKalku.dispose();
            }

        }
    }

    private class klikProperty implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            c_property = new c_property(pemain);
            if (awal == 1) {
                halamanBankAwal.dispose();
            } else if (awal == 2) {
                halamanBankJaminan.dispose();
            } else {
                halamanBankKalku.dispose();
            }
        }
    }

    private class klikUsaha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            c_usaha = new c_usaha(pemain, true);
            if (awal == 1) {
                halamanBankAwal.dispose();
            } else if (awal == 2) {
                halamanBankJaminan.dispose();
            } else {
                halamanBankKalku.dispose();
            }
        }
    }

    public void setTabelModelKendaraan(List<kendaraan> kendaraan) {
        if (kendaraan == null) {
            popup("Anda Tidak Memiliki Kendaraan");
        } else {
            DefaultTableModel dtm = new DefaultTableModel();
            dtm.setColumnIdentifiers(new String[]{
                "Id",
                "Merk",
                "Tipe",
                "Turn Pembelian",
                "Harga Jual"
            }
            );

            for (kendaraan k : kendaraan) {
                Object[] o = new Object[5];
                o[0] = k.getId();
                o[1] = k.getNama();
                if (k.isTipe()) {
                    o[2] = "Motor";
                } else {
                    o[2] = "Mobil";
                }
                o[3] = k.getTurn();
                o[4] = k.hargaJual(pemain);
                dtm.addRow(o);
            }
            halamanBankJaminan.getTabelKendaraanJ().setModel(dtm);
        }
    }

    public void setTabelModelProperty(List<property> property) {
        if (property == null) {
            popup("Anda Tidak Memiliki Property");
        } else {
            DefaultTableModel dtm = new DefaultTableModel();
            dtm.setColumnIdentifiers(new String[]{
                "Id",
                "Tipe",
                "Turn Pembelian",
                "Harga Jual"
            }
            );

            for (property p : property) {
                Object[] o = new Object[4];
                o[0] = p.getId();
                o[1] = p.getNama();
                o[2] = p.getTurn();
                o[3] = p.hargaJual(pemain);
                dtm.addRow(o);
            }
            halamanBankJaminan.getTabelPropertyJ().setModel(dtm);
        }
    }

}
