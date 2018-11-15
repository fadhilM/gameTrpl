/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl.Controller;

import gametrpl.View.halamanMain;
import gametrpl.Controller.c_mainMenu;
import gametrpl.Controller.c_newGame;
import gametrpl.Controller.c_loadGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ROG
 */
public class c_Main {
    halamanMain halamanMain;
    c_newGame c_newGame;
    c_loadGame c_loadGame;
    c_mainMenu c_mainMenu;

    public c_Main() {
        halamanMain = new halamanMain();
        
        halamanMain.setVisible(true);
        
        halamanMain.getB_NewGame().addActionListener(new klikNew());
        halamanMain.getB_loadGame().addActionListener(new klikLoad());
        halamanMain.getbKembali().addActionListener(new klikKembali());
    }
    
    private class klikKembali implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            c_mainMenu = new c_mainMenu();
            halamanMain.dispose();
        }
    }

    private class klikNew implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
           c_newGame = new c_newGame();
           halamanMain.dispose();
        }
    }

    private class klikLoad implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            halamanMain.dispose();
            c_loadGame = new c_loadGame();
        }
    }
    
}
