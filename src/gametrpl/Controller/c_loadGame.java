/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl.Controller;

import gametrpl.View.halamanLoadGame;
import gametrpl.Controller.c_Main;
import gametrpl.Controller.c_usaha;
import gametrpl.kendaraan;
import gametrpl.pemain;
import gametrpl.property;
import gametrpl.usaha;
import gametrpl.utang;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ROG
 */
public class c_loadGame {

    halamanLoadGame halamanLoadGame;
    c_usaha c_usaha;
    List<pemain> listPemain;
    List<usaha> usaha;
    List<kendaraan> kendaraan;
    List<property> property;
    pemain pemain;

    public c_loadGame() {
        halamanLoadGame = new halamanLoadGame();
        halamanLoadGame.setVisible(true);
        pemain pemain = new pemain();
        listPemain = pemain.getAll();
        setTabelModelPemain(listPemain);

        halamanLoadGame.getB_loadGame().addActionListener(new klikLoadGame());
        halamanLoadGame.getB_kembali().addActionListener(new klikKembali());
    }

    public void setTabelModelPemain(List<pemain> pemain) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(new String[]{
            "Id",
            "Nama",
            "Turn",}
        );

        for (pemain p : pemain) {
            Object[] o = new Object[3];
            o[0] = p.getIdPemain();
            o[1] = p.getPemain();
            o[2] = p.getTurn();
            dtm.addRow(o);
        }
        halamanLoadGame.getLoadGame().setModel(dtm);
    }

    private class klikKembali implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            halamanLoadGame.dispose();
            c_Main c_main = new c_Main();
        }
    }

    private class klikLoadGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int selectRow = halamanLoadGame.getLoadGame().getSelectedRow();
            if (selectRow < 0) {
                JOptionPane.showMessageDialog(halamanLoadGame, "Harap Pilih Data Save Terlebih Dahulu");
            } else {
                int id = Integer.parseInt(halamanLoadGame.getLoadGame().getValueAt(selectRow, 0).toString());
                pemain p = new pemain();
                pemain = p.getDataPemain(id);
                usaha u = new usaha();
                usaha = u.loadUsaha(id);
                if (!usaha.isEmpty()) {
                    int jmlhUsaha = pemain.getUsaha().length;
                    for (int i = 0; i < jmlhUsaha; i++) {
                        int jmlhData = usaha.size();
                        for (int j = 0; j < jmlhData; j++) {
                            if (i == usaha.get(j).getLetak()) {
                                pemain.getUsaha()[i] = usaha.get(j);
                                j = jmlhData;
                            }
                        }
                    }
                }
                kendaraan k = new kendaraan();
                kendaraan = k.loadKendaraan(id);
                if (!kendaraan.isEmpty()) {
                    pemain.getKendaraan().addAll(kendaraan);
                }
                property pr = new property();
                property = pr.loadProperty(id);
                if (!property.isEmpty()) {
                    pemain.getProperty().addAll(property);
                }
                utang ut = new utang();
                ut=ut.getDataUtang(id);
                if (ut!=null) {
                    pemain.setUtang(ut);
                }
                c_usaha = new c_usaha(pemain,true);
                halamanLoadGame.dispose();
            }
        }
    }

}
