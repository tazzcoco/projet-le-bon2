/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package princetonPlainsboroInterface;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import princetonPlainsboro.*;

public class DossierPatient extends javax.swing.JFrame {

    private final DossierPatientListener dpl;
    private ListeMedecinMedical lmm;
    private MenuMedical mm;
    private princetonPlainsboroInterface.FicheDeSoins fds;
    private NouvelleAdmission na;
    private String l;
    private String c;
    private String s ="";
    private Acte acte;
    private Medecin m;
    private Patient currentPatient;    
    
    private int index = 0;
    private DefaultListModel<String> actes;

    private DossierMedical dm;

    public DossierPatient() {
        initComponents();
        setLocationRelativeTo(getParent());
        dpl = new DossierPatientListener();
        jButton1.addActionListener(dpl);
        jButton2.addActionListener(dpl);
        jButton3.addActionListener(dpl);
        jButton4.addActionListener(dpl);
        jButton5.addActionListener(dpl);
        jButton7.addActionListener(dpl);
        jButton8.addActionListener(dpl);
        jButton9.addActionListener(dpl);
        jButton10.addActionListener(dpl);
    }

    public DossierMedical getDM() {
        return dm;
    }

    public void setDM(DossierMedical dm) {
        this.dm = dm;
    }

    public void setJTable2(JTable jTable2) {
        this.jTable2 = jTable2;
    }

    public JTable getJTable2() {
        return jTable2;
    }

    public void ajouterActeLourd() {
        //panel global
        JPanel panelGlobal = new JPanel();

        //TextField permettant de recuperer la date pour l'acte a créer
        JTextField fieldDateDay = new JTextField(3);
        JTextField fieldDateMonth = new JTextField(3);
        JTextField fieldDateYear = new JTextField(7);

        //ComboBox  permettant de choisir dans la liste des libelles/des medecins
        final JComboBox comboLibelle;
        final JComboBox cb1;
        final JComboBox cbType;
        DecimalFormat dec = new DecimalFormat("0.00");
        
        cbType = new JComboBox();        
        cbType.addItem("Thérapeutique");
        cbType.addItem("Diagnostique");

        DefaultComboBoxModel cbModel = new DefaultComboBoxModel(dm.getLibelles().toArray());
        comboLibelle = new JComboBox(cbModel);
        comboLibelle.setSelectedItem(dm.getLibelles().get(0));
        DefaultComboBoxModel cbModel1 = new DefaultComboBoxModel(dm.getMedecins().toArray());
        cb1 = new JComboBox(cbModel1);
        cb1.setSelectedItem(dm.getMedecins().get(0));
        
        ActionListener cbListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                if (cb == comboLibelle) {
                    for (int i = 0; i < dm.getLibelles().size(); i++) {
                        if (cb.getSelectedItem().equals(dm.getLibelles().get(i))) {
                            l = dm.getLibelles().get(i).toString();
                        }
                    }
                    if (l.equals("consultation au cabinet")) {
                        c = "CS";
                    } else if (l.equals("consultation cardiologie")) {
                        c = "CSC";
                    } else if (l.equals("forfait pediatrique")) {
                        c = "FP";
                    } else if (l.equals("actes de chirurgie et de specialite")) {
                        c = "KC";
                    } else if (l.equals("actes d'echographie, de doppler")) {
                        c = "KE";
                    } else if (l.equals("autres actes de specialite")) {
                        c = "K";
                    } else if (l.equals("forfait A")) {
                        c = "KFA";
                    } else if (l.equals("forfait B")) {
                        c = "KFB";
                    } else if (l.equals("orthodontie")) {
                        c = "ORT";
                    } else if (l.equals("prothese dentaire")) {
                        c = "PRO";
                    }
                } else if (cb == cb1) {
                    for (int i = 0; i < dm.getMedecins().size(); i++) {
                        if (cb.getSelectedItem().equals(dm.getMedecins().get(i))) {
                            m = dm.getMedecins().get(i);
                        }
                    }
                } else if (cb == cbType){
                    if(cb.getSelectedItem().equals("Thérapeutique")){
                        s = "thérapeutique";
                    }
                    if(cb.getSelectedItem().equals("Diagnostique")){
                        s = "diagnostique";
                    }
                }
            }
        };

        comboLibelle.addActionListener(cbListener);
        cb1.addActionListener(cbListener);
        cbType.addActionListener(cbListener);

        //TextArea pour récupérer les observation éventuelle du medecin
        JTextArea areaObservation = new JTextArea();

        //création des JLabels
        JLabel labelDate = new JLabel("Date de l'Acte :");
        JLabel labelType = new JLabel("Type de l'acte :");
        JLabel labelLibelle = new JLabel("Nom de l'acte :");
        JLabel labelObservation = new JLabel("Observation éventuelle :");

        //création d'un panel pour les 3 JTextField de la date
        JPanel panelDate1 = new JPanel();
        panelDate1.setLayout(new FlowLayout());
        panelDate1.add(fieldDateDay);
        panelDate1.add(fieldDateMonth);
        panelDate1.add(fieldDateYear);

        //JLabel et JTextField
        JLabel labelCoef = new JLabel("Coefficient : ");
        JTextField fieldCoef = new JTextField(2);

        //organisation panelGlobal
        panelGlobal.setLayout(new GridLayout(6, 2));
        panelGlobal.add(labelDate);
        panelGlobal.add(panelDate1);
        panelGlobal.add(labelType);
        panelGlobal.add(cbType);
        panelGlobal.add(labelLibelle);
        panelGlobal.add(comboLibelle);
        panelGlobal.add(labelObservation);
        panelGlobal.add(areaObservation);
        panelGlobal.add(labelCoef);
        panelGlobal.add(fieldCoef);
        panelGlobal.add(cb1);

        Acte a = null;

        int result = JOptionPane.showConfirmDialog(this, panelGlobal,
                "Veuillez saisir les informations concernant l'acte médical :", JOptionPane.OK_CANCEL_OPTION);
        Date d;
        d = new Date(Integer.parseInt(fieldDateDay.getText()), Integer.parseInt(fieldDateMonth.getText()), Integer.parseInt(fieldDateYear.getText()));

        if (result == JOptionPane.OK_OPTION) {
            if (c.equals("CS")) {
                a = new Acte(Code.CS, Integer.parseInt(fieldCoef.getText()), areaObservation.getText(), s);
            } else if (c.equals("CSC")) {
                a = new Acte(Code.CSC, Integer.parseInt(fieldCoef.getText()), areaObservation.getText(), s);
            } else if (c.equals("FP")) {
                a = new Acte(Code.FP, Integer.parseInt(fieldCoef.getText()), areaObservation.getText(), s);
            } else if (c.equals("KC")) {
                a = new Acte(Code.KC, Integer.parseInt(fieldCoef.getText()), areaObservation.getText(), s);
            } else if (c.equals("KE")) {
                a = new Acte(Code.KE, Integer.parseInt(fieldCoef.getText()), areaObservation.getText(), s);
            } else if (c.equals("K")) {
                a = new Acte(Code.K, Integer.parseInt(fieldCoef.getText()), areaObservation.getText(), s);
            } else if (c.equals("KFA")) {
                a = new Acte(Code.KFA, Integer.parseInt(fieldCoef.getText()), areaObservation.getText(), s);
            } else if (c.equals("KFB")) {
                a = new Acte(Code.KFB, Integer.parseInt(fieldCoef.getText()), areaObservation.getText(), s);
            } else if (c.equals("ORT")) {
                a = new Acte(Code.ORT, Integer.parseInt(fieldCoef.getText()), areaObservation.getText(), s);
            } else if (c.equals("PRO")) {
                a = new Acte(Code.PRO, Integer.parseInt(fieldCoef.getText()), areaObservation.getText(), s);
            }

            for (int z = 0; z < dm.getFiches().getSize(); z++) {
                if (currentPatient.equals(dm.getFiches().get(z).getPatient())) {
                    if (m.equals(dm.getFiches().get(z).getMedecin())) {
                        if (d.equals(dm.getFiches().get(z).getDate())) {
                            dm.getFiches().get(z).ajouterActe(a);
                        } else {
                            princetonPlainsboro.FicheDeSoins f = new princetonPlainsboro.FicheDeSoins(currentPatient, m, d);
                            f.ajouterActe(a);
                            dm.ajouterFiche(f);
                            break;
                        }
                    } else {
                        princetonPlainsboro.FicheDeSoins f = new princetonPlainsboro.FicheDeSoins(currentPatient, m, d);
                        f.ajouterActe(a);
                        dm.ajouterFiche(f);
                        break;
                    }
                }
            }
            Object[] line = new Object[6];
            line[0] = d.afficherDate();
            line[1] = l;
            line[2] = areaObservation.getText();
            line[3] = m.toString();
            line[4] = ((Acte) a).getCode();
            line[5] = dec.format((a).cout());
            ((DefaultTableModel) jTable2.getModel()).addRow(line);
            jTable2.repaint();
        }
    }

    public void supprimerActe() {
        actes = new DefaultListModel();
        JPanel panel = new JPanel();

        final JComboBox comboLibelle;
        JLabel labelActe = new JLabel("Choisir l'acte à supprimer : ");

        for (int j = 0; j < dm.getFiches().size(); j++) {
            if (currentPatient.equals(dm.getFiches().get(j).getPatient())) {
                for (int a = 0; a < dm.getFiches().get(j).getActes().size(); a++) {
                    actes.addElement(dm.getFiches().get(j).getDate().afficherDate() + " :" + dm.getFiches().get(j).getActes().get(a).toString());
                    System.out.println(actes.size());
                }
            }
        }

        DefaultComboBoxModel cbModel = new DefaultComboBoxModel(actes.toArray());
        comboLibelle = new JComboBox(cbModel);

        ActionListener cbListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                if (cb == comboLibelle) {
                    for (int a = 0; a < actes.size(); a++) {
                        if (actes.get(a).equals((String) cb.getSelectedItem())) {
                            for (int i = 0; i < dm.getFiches().size(); i++) {
                                if (currentPatient.equals(dm.getFiches().get(i).getPatient())) {
                                    for (int j = 0; j < dm.getFiches().get(i).getActes().size(); j++) {
                                        if (cb.getSelectedItem().equals(dm.getFiches().get(i).getDate().afficherDate() + " :" + dm.getFiches().get(i).getActes().get(j).toString())) {
                                            acte = ((Acte) dm.getFiches().get(i).getActes().get(j));
                                            index = a;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        };

        comboLibelle.addActionListener(cbListener);

        panel.add(labelActe);

        panel.add(comboLibelle);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "Veuillez choisir l'acte à supprimer", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            for (int i = 0; i < dm.getFiches().size(); i++) {
                if (currentPatient.equals(dm.getFiches().get(i).getPatient())) {
                    if (dm.getFiches().get(i).getActes().contains(acte)) {
                        dm.getFiches().get(i).supprimerActe(acte);
                    }
                }
            }
        }

        ((DefaultTableModel) jTable2.getModel()).removeRow(index);
        jTable2.repaint();
    }

    public void setCurrentPatient(Patient currentPatient) {
        this.currentPatient = currentPatient;
    }

    public JTextArea getJTextArea2() {
        return jTextArea2;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton6 = new javax.swing.JButton();
        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        jButton6.setBackground(new java.awt.Color(153, 0, 153));
        jButton6.setForeground(new java.awt.Color(153, 153, 255));
        jButton6.setText("Ajouter acte");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dossier patient");
        setAlwaysOnTop(true);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(153, 153, 255));

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(153, 153, 255));
        jButton2.setText("Retour Menu");

        jButton1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton1.setText("Liste des Patients");

        jButton8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton8.setText("Fiches de Soins");

        jButton9.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jButton9.setText("Liste des Médecins");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addGap(18, 18, 18)
                .addComponent(jButton9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jButton3.setBackground(new java.awt.Color(0, 153, 51));
        jButton3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(51, 51, 51));
        jButton3.setText("Visualiser les Fiches de Soins");

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(153, 153, 255));
        jButton4.setText("Imprimer");

        jButton5.setBackground(new java.awt.Color(0, 153, 51));
        jButton5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(51, 51, 51));
        jButton5.setText("Supprimer Patient");

        jButton7.setBackground(new java.awt.Color(0, 153, 51));
        jButton7.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(51, 51, 51));
        jButton7.setText("Supprimer Acte");

        jTable2.setBackground(new java.awt.Color(153, 153, 255));
        jTable2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Nom de l'acte", "Observations", "Médecin", "Code", "Coût total"
            }
        ));
        jScrollPane5.setViewportView(jTable2);

        jButton10.setBackground(new java.awt.Color(0, 153, 51));
        jButton10.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(51, 51, 51));
        jButton10.setText("Ajouter Acte");

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane6.setViewportView(jTextArea2);

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(153, 153, 255));
        jTextField1.setFont(new java.awt.Font("Calibri", 1, 40)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 153, 51));
        jTextField1.setText("MT²");
        jTextField1.setBorder(null);

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(153, 153, 255));
        jTextField2.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(0, 153, 51));
        jTextField2.setText("Dossier Patient");
        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

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
            java.util.logging.Logger.getLogger(DossierPatient.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DossierPatient.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DossierPatient.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DossierPatient.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DossierPatient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    public class DossierPatientListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            Rectangle positionFenetre = getBounds();

            if (source == jButton1) {
                na = new NouvelleAdmission();
                na.setBounds(positionFenetre);
                na.setDM(dm);
                na.getJList3().setModel(dm.getPatients());
                DefaultComboBoxModel cbModel = new DefaultComboBoxModel(dm.getMedecins().toArray());
                na.getJComboBox2().setModel(cbModel);
                na.setVisible(true);
                setVisible(false);
            } else if (source == jButton2) {
                mm = new MenuMedical();
                mm.setBounds(positionFenetre);
                mm.setDM(dm);
                mm.setVisible(true);
                setVisible(false);
            } else if (source == jButton3) {
                String s = "";
                fds = new FicheDeSoins();
                fds.setBounds(positionFenetre);
                fds.setDM(dm);
                for (int i = 0; i < dm.getFiches().size(); i++) {
                    if (currentPatient.equals(dm.getFiches().get(i).getPatient())) {
                        s += dm.getFiches().get(i).afficher();
                    }
                }
                fds.getJTextArea1().setText(s);
                fds.getJTextArea1().setCaretPosition(0);
                DefaultComboBoxModel cbModel = new DefaultComboBoxModel(dm.getPatients().toArray());
                fds.getJComboBox1().setModel(cbModel);
                fds.setVisible(true);
                setVisible(false);
            } else if (source == jButton4){
                PrinterJob contenu = PrinterJob.getPrinterJob();
                contenu.setPrintable(new Impression());
                if (contenu.printDialog()) {
                    try {
                        contenu.print();
                    } catch (PrinterException ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (source == jButton5) {
                //méthode retirerPatient(Patient p); de DossierMedical
                dm.retirerPatient(currentPatient);
                na = new NouvelleAdmission();
                na.setBounds(positionFenetre);
                na.setDM(dm);
                na.getJList3().setModel(dm.getPatients());
                DefaultComboBoxModel cbModel = new DefaultComboBoxModel(dm.getMedecins().toArray());
                na.getJComboBox2().setModel(cbModel);
                na.setVisible(true);
                setVisible(false);
                //fenêtre de confirmation à ajoute "Patient retiré"
                repaint();
            } else if (source == jButton7) {
                //méthode retirerActe(Acte a); de FicheDeSoins
                supprimerActe();
                //fenêtre de confirmation à ajouter "Acte retiré"
                repaint();
            } else if (source == jButton8) {
                fds = new FicheDeSoins();
                fds.setBounds(positionFenetre);
                fds.setDM(dm);
                fds.getJTextArea1().setText(dm.afficher());
                fds.getJTextArea1().setCaretPosition(0);
                DefaultComboBoxModel cbModel = new DefaultComboBoxModel(dm.getPatients().toArray());
                fds.getJComboBox1().setModel(cbModel);
                fds.setVisible(true);
                setVisible(false);
            } else if (source == jButton9) {
                lmm = new ListeMedecinMedical();
                lmm.setBounds(positionFenetre);
                lmm.setDM(dm);
                lmm.getJList1().setModel(dm.getMedecins());
                lmm.setVisible(true);
                setVisible(false);
            } else if (source == jButton10) {
                ajouterActeLourd();
                repaint();
            }
        }
    }
}
