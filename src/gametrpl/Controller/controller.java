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

    public boolean uangCukup(int danaPemain, int harga) {
        return danaPemain >= harga;
    }
}
