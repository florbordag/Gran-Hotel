/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gran.hotel.vista;
import com.placeholder.PlaceHolder;
import java.awt.Toolkit;
import javax.swing.JFrame;



/**
 *
 * @author Flor
 */
public class AdminLogin extends javax.swing.JFrame {
PlaceHolder holder;
    /**
     * Creates new form HotelSafari
     */
    public AdminLogin() {
              this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setVisible(true);  
        initComponents();
Toolkit tk= Toolkit.getDefaultToolkit();
int xsize= (int) tk.getScreenSize().getWidth();
int ysize= (int) tk.getScreenSize().getHeight();
this.setSize(xsize, ysize);


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDos = new javax.swing.JPanel();
        accesoadmin = new javax.swing.JPanel();
        salir = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();
        Admin1 = new javax.swing.JButton();
        reserve = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelDos.setBackground(new java.awt.Color(0, 0, 0));
        panelDos.setToolTipText("");
        panelDos.setAlignmentX(0.0F);
        panelDos.setAlignmentY(0.0F);
        panelDos.setMinimumSize(new java.awt.Dimension(0, 0));
        panelDos.setPreferredSize(new java.awt.Dimension(0, 0));
        panelDos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                panelDosPropertyChange(evt);
            }
        });
        panelDos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        accesoadmin.setBackground(new java.awt.Color(0, 0, 0));
        accesoadmin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Panel de Administrador", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(204, 204, 0))); // NOI18N
        accesoadmin.setForeground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout accesoadminLayout = new javax.swing.GroupLayout(accesoadmin);
        accesoadmin.setLayout(accesoadminLayout);
        accesoadminLayout.setHorizontalGroup(
            accesoadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );
        accesoadminLayout.setVerticalGroup(
            accesoadminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );

        panelDos.add(accesoadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 300, 300, 420));

        salir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        salir.setForeground(new java.awt.Color(204, 204, 0));
        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gran/hotel/vista/salir.jpg"))); // NOI18N
        salir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)));
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        panelDos.add(salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1137, 760, 300, -1));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gran/hotel/vista/2.jpg"))); // NOI18N
        fondo.setAlignmentY(0.0F);
        fondo.setMaximumSize(new java.awt.Dimension(2048, 1536));
        panelDos.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 1280, 730));

        Admin1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Admin1.setForeground(new java.awt.Color(204, 204, 0));
        Admin1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gran/hotel/vista/adminbt.jpg"))); // NOI18N
        Admin1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)));
        Admin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Admin1ActionPerformed(evt);
            }
        });
        panelDos.add(Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 760, 300, -1));

        reserve.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        reserve.setForeground(new java.awt.Color(204, 204, 0));
        reserve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gran/hotel/vista/reserve.jpg"))); // NOI18N
        reserve.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)));
        reserve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reserveActionPerformed(evt);
            }
        });
        panelDos.add(reserve, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 760, 300, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDos, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDos, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelDosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_panelDosPropertyChange
        // TODO add your handling code here:
                Toolkit tk2 =Toolkit.getDefaultToolkit();
        panelDos.setSize((int) tk2.getScreenSize().getWidth(), (int) tk2.getScreenSize().getHeight());
    }//GEN-LAST:event_panelDosPropertyChange

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void Admin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Admin1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_Admin1ActionPerformed

    private void reserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reserveActionPerformed
        // TODO add your handling code here:
                
         panelDos.removeAll();
        panelDos.repaint();
          Reservar reservar=new Reservar();
          reservar.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_reserveActionPerformed

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
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Admin1;
    private javax.swing.JPanel accesoadmin;
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel panelDos;
    private javax.swing.JButton reserve;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
