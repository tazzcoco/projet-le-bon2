package princetonPlainsboroInterface;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import princetonPlainsboro.*;

/**
 *
 * @author Thomas
 */
public class MenuMedical extends JFrame {

    private DossierMedical dm;
    private NouvelleAdmission na;
    private ListeMedecinMedical lmm;
    private FicheDeSoins fds;
    private PrincetonInterface pi;

    private final MenuMedicalListener mml;

    public MenuMedical() {
        initComponents();
        LectureXML test = new LectureXML("dossiers.xml");
        dm = test.getDossier();
        //dm = new DossierMedical();
        //Patient p = new Patient("Riviere","Tommy",new Date(23,9,1994),1234567891,"20 rue Barnave\n38000 Grenoble\nFrance");
        //dm.ajouterPatient(p);
        setLocationRelativeTo(getParent());
        mml = new MenuMedicalListener();
        jButton1.addActionListener(mml);
        jButton3.addActionListener(mml);
        jButton4.addActionListener(mml);
        jButton5.addActionListener(mml);
        jButton6.addActionListener(mml);
    }

    public DossierMedical getDM() {
        return dm;
    }

    public void setDM(DossierMedical dm) {
        this.dm = dm;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jButton3.setBackground(new java.awt.Color(0, 153, 51));
        jButton3.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(51, 51, 51));
        jButton3.setText("Nouvelle Admission");

        jButton4.setBackground(new java.awt.Color(0, 153, 51));
        jButton4.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(51, 51, 51));
        jButton4.setText("Fiches de Soins");

        jButton5.setBackground(new java.awt.Color(0, 153, 51));
        jButton5.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(51, 51, 51));
        jButton5.setText("Liste des Patients");

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(153, 153, 255));
        jTextField1.setFont(new java.awt.Font("Calibri", 1, 70)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 153, 51));
        jTextField1.setText("MT²");
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 153, 51));
        jButton6.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(51, 51, 51));
        jButton6.setText("Liste des Médecins");

        jTextField2.setBackground(new java.awt.Color(153, 153, 255));
        jTextField2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(0, 153, 51));
        jTextField2.setText("menu Médecin & Secrétaire médicale");
        jTextField2.setBorder(null);

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(153, 153, 255));
        jButton1.setText("Déconnexion");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(142, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(146, 146, 146))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(MenuMedical.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuMedical.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuMedical.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuMedical.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuMedical().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    public void ajouterPatient() {
        //creation des JTextFields pour récupérer les renseignements du patient et du JPanel
        JTextField fieldPrenom = new JTextField(5);
        JTextField fieldNom = new JTextField(7);
        JTextField fieldBirth1 = new JTextField(3);
        JTextField fieldBirth2 = new JTextField(3);
        JTextField fieldBirth3 = new JTextField(7);
        JTextField fieldNumSecu = new JTextField(5);
        JTextField fieldAdresse = new JTextField(10);
        //création des JLabels
        JLabel labelNom = new JLabel("Nom :");
        JLabel labelPrenom = new JLabel("Prénom :");
        JLabel labelBirth = new JLabel("Date de naissance :");
        JLabel labelAdresse = new JLabel("Adresse :");
        JLabel labelNumSecu = new JLabel("Numéro de sécurité sociale (13 chiffres) :");
        //création du JPanel
        JPanel myPanel = new JPanel();

        //création d'un panel pour les 3 JTextField de la date de naissance
        JPanel panelBirth = new JPanel();
        panelBirth.setLayout(new FlowLayout());
        panelBirth.add(fieldBirth1);
        panelBirth.add(fieldBirth2);
        panelBirth.add(fieldBirth3);

        //organisation de la fenêtre d'entrée utilisateur
        myPanel.setLayout(new GridLayout(5, 2));
        myPanel.add(labelNom);
        myPanel.add(fieldNom);
        myPanel.add(labelPrenom);
        myPanel.add(fieldPrenom);
        myPanel.add(labelBirth);
        myPanel.add(panelBirth);
        myPanel.add(labelAdresse);
        myPanel.add(fieldAdresse);
        myPanel.add(labelNumSecu);
        myPanel.add(fieldNumSecu);

        //instanciation de la fenêtre d'entrée utilisateur
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Veuillez entrer les détails du patient :", JOptionPane.OK_CANCEL_OPTION);
        Patient p = new Patient(fieldNom.getText(), fieldPrenom.getText(), new Date(Integer.parseInt(fieldBirth1.getText()), Integer.parseInt(fieldBirth2.getText()), Integer.parseInt(fieldBirth3.getText())), Long.parseLong(fieldNumSecu.getText()), fieldAdresse.getText());

        if (result == JOptionPane.OK_OPTION) {
            dm.ajouterPatient(p);
        }
    }

    public class MenuMedicalListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            Rectangle positionFenetre = getBounds();

            if (source == jButton1) {
                pi = new PrincetonInterface();
                pi.setBounds(positionFenetre);
                pi.setDM(dm);
                pi.setVisible(true);
                setVisible(false);
            } else if (source == jButton3) {
                //méthode ajouterPatient(); de DossierMedical
                ajouterPatient();
                //on ouvre la fenêtre de la liste de patients                
                na = new NouvelleAdmission();
                na.setBounds(positionFenetre);
                na.setDM(dm);
                na.getJList3().setModel(dm.getPatients());
                DefaultComboBoxModel cbModel = new DefaultComboBoxModel(dm.getMedecins().toArray());
                na.getJComboBox2().setModel(cbModel);
                na.setVisible(true);
                setVisible(false);
            } else if (source == jButton4) {
                fds = new FicheDeSoins();
                fds.setBounds(positionFenetre);
                fds.setDM(dm);
                fds.getJTextArea1().setText(dm.afficher());
                fds.getJTextArea1().setCaretPosition(0);
                DefaultComboBoxModel cbModel = new DefaultComboBoxModel(dm.getPatients().toArray());
                fds.getJComboBox1().setModel(cbModel);
                fds.setVisible(true);
                setVisible(false);
            } else if (source == jButton5) {
                na = new NouvelleAdmission();
                na.setBounds(positionFenetre);
                na.setDM(dm);
                na.getJList3().setModel(dm.getPatients());
                DefaultComboBoxModel cbModel = new DefaultComboBoxModel(dm.getMedecins().toArray());
                na.getJComboBox2().setModel(cbModel);
                na.setVisible(true);
                setVisible(false);
            } else if (source == jButton6) {
                lmm = new ListeMedecinMedical();
                lmm.setBounds(positionFenetre);
                lmm.setDM(dm);
                lmm.getJList1().setModel(dm.getMedecins());
                lmm.setVisible(true);
                setVisible(false);
            }
        }
    }
}
