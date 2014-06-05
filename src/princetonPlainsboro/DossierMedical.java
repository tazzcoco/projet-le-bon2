package princetonPlainsboro;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DossierMedical {

    //attributs
    private DefaultListModel<FicheDeSoins> fiches; //liste des fiches de soins 
    private DefaultListModel<Patient> patients; //liste des patients
    private DefaultListModel<Medecin> medecins; //liste des médecins
    private DefaultListModel<Secretaire> secretaires;

    //accesseurs des listes
    public DefaultListModel<FicheDeSoins> getFiches() {
        return fiches;
    }

    public DefaultListModel<Secretaire> getSecretaire() {
        return secretaires;
    }

    public DefaultListModel<Patient> getPatients() {
        return patients;
    }

    public DefaultListModel<Medecin> getMedecins() {
        return medecins;
    }

    public void setMedecins(DefaultListModel dlm) {
        this.medecins = dlm;
    }

    public void setPatient(DefaultListModel dlm) {
        this.patients = dlm;
    }

    public void setSecretaire(DefaultListModel dlm) {
        this.secretaires = dlm;
    }

    //constructeur
    public DossierMedical() {
        fiches = new DefaultListModel();
        patients = new DefaultListModel();
        medecins = new DefaultListModel();
        secretaires = new DefaultListModel();
    }

    public void ajouterFiche(FicheDeSoins fiche) {
        fiches.addElement(fiche);
        for (int i = 0; i < fiches.size(); i++) {
            if (!medecins.contains(fiche.getMedecin())) {
                medecins.addElement(fiche.getMedecin());
            }
            if (!patients.contains(fiche.getPatient())) {
                patients.addElement(fiche.getPatient());
            }
        }

    }

    public void supprimerFiche(FicheDeSoins fiche) {
        if (fiches.contains(fiche)) {
            fiches.removeElement(fiche);
        }
    }

    public String afficher() {
        String s = new String();
        s += "Dossier medical informatisé :\n";
        s += "\n-----------------------------";
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            s += f.afficher();
            // pour separer les fiches de soins :
            s += "\n--------------------------------------";
        }
        return s;
    }

    public double coutPatient(Patient p) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (p.equals(f.getPatient())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public double coutMedecin(Medecin m) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (m.equals(f.getMedecin())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public double coutSpecialite(String specialite) {
        double cout = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (specialite.equals(f.getMedecin().getSpecialite())) {
                cout += f.coutTotal();
            }
        }
        return cout;
    }

    public String afficherListePatients(Medecin m) {
        String s = "> liste des patients du " + m.toString() + " :";
        Vector<Patient> liste = new Vector<Patient>();
        // 'liste' contient tous les patients deja affiches
        // --> ceci permet de ne pas reafficher un patient deja affiche
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            if (m.equals(f.getMedecin())) {
                Patient p = f.getPatient();
                if (!liste.contains(p)) {
                    s += "\n - " + p.toString();
                    liste.add(p);
                }
            }
        }
        return s;
    }

    public int nombreFichesIntervalle(Date d1, Date d2) {
        int n = 0;
        for (int i = 0; i < fiches.size(); i++) {
            FicheDeSoins f = fiches.get(i);
            Date d = f.getDate();
            if (d.compareTo(d1) >= 0 && d.compareTo(d2) <= 0) {
                n++;
            }
        }
        return n;
    }

    public String trierDates() {
        String s = "";
        List<FicheDeSoins> copieFiches = new ArrayList<FicheDeSoins>();

        //on remplit copieFiches avec les éléments de la DefaultListModel fiches
        for (int i = 0; i < fiches.size(); i++) {
            copieFiches.add(fiches.get(i));
        }
        s += "Listes des fiches rangées par dates croissantes :\n";
        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int j = 1; j < copieFiches.size(); j++) {
                FicheDeSoins f2 = copieFiches.get(j);
                if (f2.getDate().compareTo(f1.getDate()) < 0) {
                    imin = j;
                    f1 = f2;
                }
            }
            // on affiche la fiche de soins trouvee :
            s += f1.afficher();
            s += "------------------------";
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
        return s;
    }

    // tri generique :
    public String trier(ComparaisonFiches c) {
        String s = "";
        List<FicheDeSoins> copieFiches = new ArrayList<FicheDeSoins>();
        s += "Liste des fiches de soins par coûts croissants :\n";
        //on remplit copieFiches avec les éléments de la DefaultListModel fiches
        for (int i = 0; i < fiches.size(); i++) {
            copieFiches.add(fiches.get(i));
        }

        while (!copieFiches.isEmpty()) {
            // on cherche la fiche de soins minimale :
            int imin = 0;
            FicheDeSoins f1 = copieFiches.get(imin);
            for (int i = 1; i < copieFiches.size(); i++) {
                FicheDeSoins f2 = copieFiches.get(i);
                if (c.comparer(f2, f1) < 0) {
                    imin = i;
                    f1 = f2;
                }
            }
            // on affiche la fiche de soins trouvee :
            s += f1.afficher();
            //on la supprime de la liste :
            copieFiches.remove(imin);
        }
        return s;
    }

    public String afficherListePatients() {
        String s = "";
        //on créé une liste de patient
        ArrayList<Patient> lp = new ArrayList<>();
        s += "Liste des patients :\n";
        //on la remplit et on affiche chaque patient sous la forme : NOM Prenom
        for (int i = 0; i < fiches.size(); i++) {
            if (!lp.contains(fiches.get(i).getPatient())) {
                lp.add(fiches.get(i).getPatient());
                s += fiches.get(i).getPatient().getNom().toUpperCase() + " " + fiches.get(i).getPatient().getPrenom();
            }//end if
        }//end for
        return s;
    }

    public String afficherListeMedecins() {
        String s = "";
        //même principe que pour afficherListePatients, on créé une liste de médecins en premier
        ArrayList<Medecin> lm = new ArrayList<Medecin>();
        s += "Liste des médecins :\n";
        //on la remplit et on affiche chaque médecin sous la forme : NOM Prenom
        for (int i = 0; i < fiches.size(); i++) {
            if (!lm.contains(fiches.get(i).getMedecin())) {
                lm.add(fiches.get(i).getMedecin());
                s += fiches.get(i).getMedecin().getNom().toUpperCase() + " " + fiches.get(i).getMedecin().getPrenom();
            }//end if
        }//end for
        return s;
    }

    public DefaultListModel getLibelles() {
        DefaultListModel<String> libelle = new DefaultListModel();
        for (int i = 0; i < getFiches().size(); i++) {
            for (int a = 0; a < getFiches().get(i).getActes().size(); a++) {
                if (!libelle.contains(((Acte) getFiches().get(i).getActes().get(a)).getLibelle())) {
                    libelle.addElement(((Acte) getFiches().get(i).getActes().get(a)).getLibelle());
                }
            }
        }
        return libelle;
    }

    public String afficherFichesEntre(Date d1, Date d2) {
        String s = "";
        //creation d'une liste de fiche de soins dont les dates sont comprises entre d1 et d2
        List<FicheDeSoins> lf = new ArrayList<FicheDeSoins>();
        s += "Liste des fiches de soins entre le " + d1 + " et le " + d2 + " : \n";
        //on remplit la liste lf
        for (int i = 0; i < fiches.size(); i++) {
            if (d1.compareTo(d2) < 0) {
                if ((fiches.get(i).getDate().compareTo(d1) > 0) && (fiches.get(i).getDate().compareTo(d2) < 0)) {
                    lf.add(fiches.get(i));
                }//end if
            } else {
                if (d1.compareTo(d2) > 0) {
                    if ((fiches.get(i).getDate().compareTo(d1) < 0) && (fiches.get(i).getDate().compareTo(d2) > 0)) {
                        lf.add(fiches.get(i));
                    }//end if
                }//end if
            }//end if/else
        }//end for
        //on utilise le même code que dans trierDates, appliqué ici à la liste lf et non au dossier médical entier.
        while (!lf.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            FicheDeSoins f1 = lf.get(imin);
            for (int j = 1; j < lf.size(); j++) {
                FicheDeSoins f2 = lf.get(j);
                if (f2.getDate().compareTo(f1.getDate()) < 0) {
                    imin = j;
                    f1 = f2;
                }//end if
            }//end for
            // on affiche la fiche de soins trouvee :
            s += f1.afficher();
            s += "------------------------";
            //on la supprime de la liste :
            lf.remove(imin);
        }
        return s;
    }

    public String afficherListeCoutCroissant() {
        String s = trier(new ComparaisonFichesCouts());
        return s;
    }

    public void ajouterPatient(Patient p) {
        patients.addElement(p);
        System.out.println("Patient ajouté !");
    }

    public void retirerPatient(Patient p) {
        patients.removeElement(p);
        p = null;
        System.out.println("Patient retiré !");
    }

    public void ajouterMedecin(Medecin m) {
        medecins.addElement(m);
        System.out.println("Médecin ajouté !");
    }

    public void retirerMedecin(Medecin m) {
        medecins.removeElement(m);
        m = null;
        System.out.println("Médecin retiré !");
    }

    public void ajouterSecretaire(Secretaire s) {
        secretaires.addElement(s);
        System.out.println("Secrétaire ajouté !");
    }

    public void retirerSecretaire(Secretaire s) {
        secretaires.removeElement(s);
        s = null;
        System.out.println("Secrétaire retiré !");
    }

}
