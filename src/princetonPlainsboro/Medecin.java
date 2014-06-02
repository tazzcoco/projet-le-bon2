package princetonPlainsboro;

public class Medecin {
    //attributs
    private String nom;
    private String prenom;
    private String specialite;
    private String numTel;
    private String mdp;
    
    //constructeur 1
    public Medecin(String nom, String prenom, String specialite) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
    }

    //contructeur 2
    public Medecin(String nom, String prenom, String specialite, String numTel, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.numTel = numTel;
        this.mdp = mdp;
    }

    //accesseurs
    public String getSpecialite() {
        return specialite;
    }

    public String getNom() {
        return nom;
    }
    
    public String getNumTel(){
        return numTel;
    }
    
    public String getMdp(){
        return mdp;
    }

    public String getPrenom() {
        return prenom;
    }

    public String toString() {
        return "Dr " + prenom + " " + nom + ", " + specialite;
    }

    public boolean equals(Object o) {
        if (o instanceof Medecin) {
            Medecin p = (Medecin) o;
            return nom.equals(p.nom) && prenom.equals(p.prenom);
        } else {
            return false;
        }
    }
    
    public String afficherDM() {
        String s = "Dossier Médecin";
        s += "\n\nNom : " + nom;
        s += "\nPrénom : " + prenom;
        s += "\nSpécialité : " + specialite;
        s += "\n\n\tContact :\nNuméro de téléphone : "+numTel;
        return s;
    }
}
