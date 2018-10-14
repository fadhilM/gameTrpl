/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl.Controller;

import gametrpl.pemain;
import gametrpl.property;
import gametrpl.Controller.c_usaha;
import gametrpl.View.poperty;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
/**
 *
 * @author ROG
 */
public class c_property {
    pemain pemain;
    poperty poperty;
    c_usaha c_usaha;

    public c_property(pemain pemain) {
        this.pemain = pemain;
        
        poperty = new poperty();
        poperty.setVisible(true);
        
        poperty.getBeliProperty1().addActionListener(new bp1());
        poperty.getBeliProperty2().addActionListener(new bp2());
        poperty.getBeliProperty3().addActionListener(new bp3());
        poperty.getBeliProperty4().addActionListener(new bp4());
        poperty.getBeliProperty5().addActionListener(new bp5());
        poperty.getBeliProperty6().addActionListener(new bp6());
        poperty.getBeliProperty7().addActionListener(new bp7());
        poperty.getBeliProperty8().addActionListener(new bp8());
        
    }
    
    
}
