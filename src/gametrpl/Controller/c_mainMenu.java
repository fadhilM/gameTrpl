/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl.Controller;

import gametrpl.View.mainMenu;
import gametrpl.Controller.c_Main;
import gametrpl.Controller.c_caraMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author ROG
 */
public class c_mainMenu {
    mainMenu mainMenu;
    c_Main c_Main;
    c_caraMain c_caraMain;

    public c_mainMenu() {
        mainMenu = new mainMenu();
        
        mainMenu.setVisible(true);
                
        mainMenu.getB_bermain().addActionListener(new klikMain());
//        mainMenu.getB_caraMain().addActionListener(new klikCaraMain());
    }

    private class klikMain implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            c_Main = new c_Main();
            mainMenu.dispose();
        }
    }

//    private class klikCaraMain implements ActionListener {
//        @Override
////        public void actionPerformed(ActionEvent ae) {
////            c_caraMain = new c_caraMain();
////        }
//    }    
    
}
