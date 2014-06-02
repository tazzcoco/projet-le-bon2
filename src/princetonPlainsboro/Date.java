package princetonPlainsboro;

public class Date implements Comparable {

    //attributs
    private int jour;
    private int mois;
    private int annee;
    private int heure;
    private int minute;

    //constructeur 1
    public Date(int jour, int mois, int annee) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    //constructeur 2
    public Date(int jour, int mois, int annee, int heure, int minute) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.heure = heure;
        this.minute = minute;
    }

    public String toString() {
        return getJour() + "/" + getMois() + "/" + getAnnee();
    }

    public boolean equals(Object o) {
        if (o instanceof Date) {
            Date d = (Date) o;
            return (getAnnee() == d.getAnnee()) && (getMois() == d.getMois()) && (getJour() == d.getJour());
        } else {
            return false;
        }
    }

    // precondition : 'o' est une instance de 'Date' :
    public int compareTo(Object o) {
        Date d = (Date) o;
        if (getAnnee() != d.getAnnee()) {
            return getAnnee() - d.getAnnee();
        }
        // ici on a forcement annee == d.annee :
        if (getMois() != d.getMois()) {
            return getMois() - d.getMois();
        }
        // ici on a forcement annee == d.annee et mois == d.mois :
        return getJour() - d.getJour();
    }

    /**
     * @return the jour
     */
    public int getJour() {
        return jour;
    }

    /**
     * @return the mois
     */
    public int getMois() {
        return mois;
    }

    /**
     * @return the annee
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * @return the heure
     */
    public int getHeure() {
        return heure;
    }

    /**
     * @return the minute
     */
    public int getMinute() {
        return minute;
    }

    public String afficherDate() {
        String date= jour + "/" + mois + "/" + annee;
        return date;
    }
}
