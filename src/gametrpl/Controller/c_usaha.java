/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl.Controller;

import gametrpl.View.halamanUsaha;
import gametrpl.pemain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author ROG
 */
public class c_usaha {
    pemain pemain;
    halamanUsaha halamanUsaha;

    c_usaha(pemain pemain) {
        this.pemain=pemain;
        
        halamanUsaha = new halamanUsaha();
        halamanUsaha.setVisible(true);
    }
    
}
