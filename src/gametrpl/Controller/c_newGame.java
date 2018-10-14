/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl.Controller;

import gametrpl.View.halamanNewGame;
import gametrpl.Controller.c_Main;
import gametrpl.Controller.c_usaha;
import gametrpl.pemain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author ROG
 */
public class c_newGame {

    halamanNewGame halamanNewGame;
    c_usaha c_usaha;
    pemain pemain;
    c_Main c_main;

    public c_newGame() {
        halamanNewGame = new halamanNewGame();

        halamanNewGame.setVisible(true);

        halamanNewGame.getB_mulai().addActionListener(new klikMulai());
        halamanNewGame.getbKembali().addActionListener(new klikKembali());
    }

    private class klikKembali implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            c_main = new c_Main();
            halamanNewGame.dispose();
        }
    }

    private class klikMulai implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String namaPemain = halamanNewGame.getTxt_nama().getText().trim();
            if (namaPemain.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(halamanNewGame, "Harap Isi Nama Terlebih Dahulu");
            } else {
                pemain = new pemain(namaPemain, 1);
                c_usaha = new c_usaha(pemain, true);
                halamanNewGame.dispose();
            }
        }
    }

}
