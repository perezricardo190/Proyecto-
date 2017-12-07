
package radiomapserver;

import java.awt.Toolkit;


public class About extends javax.swing.JFrame {

    /** Creates new form About */
    public About() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Logo_SocialFace = new javax.swing.JLabel();
        UCY = new javax.swing.JLabel();
        Course = new javax.swing.JLabel();
        javax.swing.JLabel Product_Version = new javax.swing.JLabel();
        javax.swing.JLabel Prof = new javax.swing.JLabel();
        Created = new javax.swing.JLabel();
        DZeina = new javax.swing.JLabel();
        GConst = new javax.swing.JLabel();
        MSavva = new javax.swing.JLabel();
        HHadji = new javax.swing.JLabel();
        Copyright = new javax.swing.JLabel();
        CloseButton = new javax.swing.JButton();
        javax.swing.JLabel Version = new javax.swing.JLabel();
        DZeina1 = new javax.swing.JLabel();
        javax.swing.JLabel Prof1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Radio Map Server ");
        setIconImage(Toolkit.getDefaultToolkit().createImage("./src/SF_icon.png"));

        Logo_SocialFace.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        UCY.setFont(new java.awt.Font("Tahoma", 1, 11));
        UCY.setText(" ";

        Course.setText("RadioMap Disribution Server");

        Product_Version.setFont(Product_Version.getFont().deriveFont(Product_Version.getFont().getStyle() | java.awt.Font.BOLD));
        Product_Version.setText(" ");

         Prof.setFont(Prof.getFont().deriveFont(Prof.getFont().getStyle() | java.awt.Font.BOLD));
         Prof.setText(" ");

        Created.setFont(new java.awt.Font("Tahoma", 1, 11));
        Created.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Created.setText(" ");

        DZeina.setText("  ");

        GConst.setText(" ");

        MSavva.setText(" ");

        HHadji.setText(" ");

        Copyright.setFont(new java.awt.Font("Tahoma", 1, 11));
        Copyright.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Copyright.setText(" ");
        Copyright.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        CloseButton.setText("Close");
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });

        Version.setText("1.0");

        DZeina1.setText(" ");

        Prof1.setFont(Prof1.getFont().deriveFont(Prof1.getFont().getStyle() | java.awt.Font.BOLD));
        Prof1.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Logo_SocialFace, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                    .addComponent(CloseButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Copyright, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Prof1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(DZeina1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Created)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(GConst)
                                    .addComponent(MSavva)
                                    .addComponent(HHadji)))
                            .addComponent(Course)
                            .addComponent(UCY)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Product_Version)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Version))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Prof)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DZeina)))
                        .addGap(244, 244, 244)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Logo_SocialFace)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(UCY, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Course)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Product_Version)
                    .addComponent(Version))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Prof)
                    .addComponent(DZeina))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Prof1)
                    .addComponent(DZeina1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Created)
                    .addComponent(GConst))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MSavva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HHadji)
                .addGap(18, 18, 18)
                .addComponent(Copyright)
                .addGap(18, 18, 18)
                .addComponent(CloseButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_CloseButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButton;
    private javax.swing.JLabel Copyright;
    private javax.swing.JLabel Course;
    private javax.swing.JLabel Created;
    private javax.swing.JLabel DZeina;
    private javax.swing.JLabel DZeina1;
    private javax.swing.JLabel GConst;
    private javax.swing.JLabel HHadji;
    private javax.swing.JLabel Logo_SocialFace;
    private javax.swing.JLabel MSavva;
    private javax.swing.JLabel UCY;
    // End of variables declaration//GEN-END:variables

}
