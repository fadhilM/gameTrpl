/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl.View;

import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author ROG
 */
public class halamanNewGame extends javax.swing.JFrame {

    /**
     * Creates new form halamanNewGame
     */
    public halamanNewGame() {
        initComponents();
    }

    public JButton getB_mulai() {
        return b_mulai;
    }

    public JTextField getTxt_nama() {
        return txt_nama;
    }

    public JButton getbKembali() {
        return bKembali;
    }
    
    

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_nama = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        bKembali = new javax.swing.JButton();
        b_mulai = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1050, 750));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, 304, 45));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gametrpl/gambar/Klik New Game + back.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        bKembali.setText("kembali");
        bKembali.setBorderPainted(false);
        bKembali.setContentAreaFilled(false);
        getContentPane().add(bKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 635, 100, 90));

        b_mulai.setText("Mulai");
        b_mulai.setBorderPainted(false);
        b_mulai.setContentAreaFilled(false);
        getContentPane().add(b_mulai, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 360, 300, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(halamanNewGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(halamanNewGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(halamanNewGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(halamanNewGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new halamanNewGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bKembali;
    private javax.swing.JButton b_mulai;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txt_nama;
    // End of variables declaration//GEN-END:variables
}
