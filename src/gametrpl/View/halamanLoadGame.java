/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl.View;

import javax.swing.JButton;
import javax.swing.JTable;

/**
 *
 * @author ROG
 */
public class halamanLoadGame extends javax.swing.JFrame {

    /**
     * Creates new form loadGame
     */
    public halamanLoadGame() {
        initComponents();
    }

    public JTable getLoadGame() {
        return loadGame;
    }

    public JButton getB_loadGame() {
        return b_loadGame;
    }

    public JButton getB_kembali() {
        return b_kembali;
    }
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        loadGame = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        b_loadGame = new javax.swing.JButton();
        b_kembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1053, 800));
        setMinimumSize(new java.awt.Dimension(1053, 800));
        setPreferredSize(new java.awt.Dimension(1053, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loadGame.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(loadGame);
        loadGame.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 95, 654, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gametrpl/gambar/Klik Load Game Baru.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        b_loadGame.setText("jButton1");
        b_loadGame.setBorderPainted(false);
        b_loadGame.setContentAreaFilled(false);
        getContentPane().add(b_loadGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 550, 280, 70));

        b_kembali.setText("jButton1");
        b_kembali.setBorderPainted(false);
        b_kembali.setContentAreaFilled(false);
        getContentPane().add(b_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 640, 100, 90));

        pack();
        setLocationRelativeTo(null);
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
            java.util.logging.Logger.getLogger(halamanLoadGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(halamanLoadGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(halamanLoadGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(halamanLoadGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new halamanLoadGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_kembali;
    private javax.swing.JButton b_loadGame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable loadGame;
    // End of variables declaration//GEN-END:variables
}