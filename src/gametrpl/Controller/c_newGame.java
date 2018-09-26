/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl.Controller;

import gametrpl.View.halamanNewGame;
import gametrpl.Controller.c_usaha;
import gametrpl.pemain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author ROG
 */
public class c_newGame {
    halamanNewGame halamanNewGame;
    c_usaha c_usaha;
    pemain pemain;

    public c_newGame() {
        halamanNewGame = new halamanNewGame();
        
        halamanNewGame.setVisible(true);
        
        halamanNewGame.getB_mulai().addActionListener(new klikMulai());
    }

    private class klikMulai implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            String namaPemain=halamanNewGame.getTxt_nama().getText().trim();
            pemain = new pemain(namaPemain);
            c_usaha = new c_usaha(pemain);
            halamanNewGame.dispose();
        }
    }
    
}
