package princetonPlainsboro;

class Test {

    public static void main(String[] args) {
        LectureXML test = new LectureXML("dossiers.xml");
        DossierMedical dm = test.getDossier();
        //dm.afficher();

        System.out.println("\n********\n");

        Patient p1 = new Patient("Bole", "Pat");
        System.out.println("> cout de " + p1.toString() + " : " + dm.coutPatient(p1));

        System.out.println("\n********\n");

        String spe = "Cardiologue";
        System.out.println("> cout de la specialite '" + spe + "' : " + dm.coutSpecialite(spe));

        System.out.println("\n********\n");

        //Medecin m1 = new Medecin("Deblouze", "Agathe", "Cardiologue", );
        //dm.afficherListePatients(m1);

        System.out.println("\n********\n");

        Date d1 = new Date(1, 11, 2005);
        Date d2 = new Date(5, 1, 2006);
        System.out.println("> nombre de fiches entre " + d1 + " et " + d2 + " : " + dm.nombreFichesIntervalle(d1, d2));

        System.out.println("\n********\n");

        System.out.println();
        System.out.println("Dossier trie selon les dates :");
        //dm.trierDates();
        System.out.println(" ");
        dm.afficherFichesEntre(new Date(1, 10, 2005), new Date(15, 11, 2005));

        System.out.println("\n********\n");

        System.out.println();
        System.out.println("Dossier trie selon les couts :");
        //dm.afficherListeCoutCroissant();

        //dm.ajouterPatient();
        dm.afficherListePatients();
        System.out.println("\n");
        dm.afficherListeMedecins();
        System.out.println("\n");

        Patient p2 = new Patient("Toto", "TOTO", new Date(6, 6, 1991), 2, "blabla");
        Patient p3 = new Patient("Tata", "TATA", new Date(6, 2, 1991), 2, "blabla");
        p2.afficherDP();
        System.out.println("\n");
        System.out.println("\n");

        //dm.trierDates();
        
        System.out.println("\n");
        dm.afficherListeCoutCroissant();
        //dm.ajouterMedecin();
        
        System.out.println("Test XML !!");
        Medecin m = new Medecin("Jean", "Lourde", "Urologie","0145236789", "Lourde_J");
        LectureXML.addMedecin(m);
    }
}
