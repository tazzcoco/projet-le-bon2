package princetonPlainsboro;

import java.util.Calendar;

public class Patient {
    //attributs
    private String nom;
    private String prenom;
    private long numSecu;
    private String adresse;
    private Date dateNaissance;
    
    //constructeur 1
    public Patient(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
    //constructeur 2
    public Patient(String nom, String prenom, Date dateNaissance, long numSecu, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.numSecu = numSecu;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
    }

    public String toString() {
        return prenom + " " + nom;
    }

    public boolean equals(Object o) {
        if (o instanceof Patient) {
            Patient p = (Patient) o;
            return nom.equals(p.nom) && prenom.equals(p.prenom);
        } else {
            return false;
        }
    }

    //accesseurs
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public long getNumSecu() {
        return numSecu;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }
    
    //méthode pour calculer l'age d'un patient
    public int calculAge(){
        Calendar curr = Calendar.getInstance();
        int yeardiff = curr.get(Calendar.YEAR) - dateNaissance.getAnnee();
        if (dateNaissance.getMois() > curr.get(Calendar.MONTH)) {
            yeardiff = yeardiff - 1;
        } else {
            if (dateNaissance.getMois() == curr.get(Calendar.MONTH)) {
                if (dateNaissance.getJour() > curr.get(Calendar.DAY_OF_MONTH)) {
                    yeardiff = yeardiff - 1;
                }//end if
            }//end if
        }//end if/else
        return yeardiff;
    }
    
    //méthode affichant le dossier patient, le tableau des actes concernant un patient est instancié dans l'interface DossierPatient
    public String afficherDP() {
        String s = "Dossier Patient";
        s += "\n\nNom : " + nom;
        s += "\nPrénom : " + prenom;
        s += "\nDate de Naissance :" + dateNaissance /*" (" + this.calculAge() + " ans)"*/;
        s += "\nAdresse : "+adresse;
        s += "\nN° sécurité sociale : " + numSecu;
        return s;
    }
}
