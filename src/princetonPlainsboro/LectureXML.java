/*
 * LectureXML.java
 *
 * Created on 5 janvier 2006, 18:26
 *
 * Lecture d'un document XML et transformation en instances Java
 */
package princetonPlainsboro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Lecture d'un document XML et transformation en instances Java.
 *
 * @author promayon
 */
public class LectureXML {

    /// nom du document XML a analyser
    private String nomFichier;
    private final static String repBase = "src/donnees/";

    // 'nomFichier' est le nom d'un fichier XML se trouvant dans le repertoire 'repBase' a lire :
    public LectureXML(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public DefaultListModel getSecretaireLourd() {
        Secretaire secretaireCourant = null;
        String nomCourant = "";
        String prenomCourant = "";
        String motDePasseSecretaire = "";
        String donneesCourantes = "";
        DefaultListModel<Secretaire> secretaires;

        secretaires = new DefaultListModel();
        try {
            // instanciation du parser
            InputStream in = new FileInputStream(repBase + "secretaire.xml");
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                // traitement selon l'evenement
                switch (event) {

                    case XMLStreamConstants.START_ELEMENT:
                        if (parser.getLocalName().equals("root")) {
                            // medecins = new DefaultListModel();
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:

                        if (parser.getLocalName().equals("secretaire")) {
                            secretaireCourant = new Secretaire(nomCourant, prenomCourant, motDePasseSecretaire);
                            secretaires.addElement(secretaireCourant);
                            break;
                        }
                        if (parser.getLocalName().equals("nom")) {
                            nomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("prenom")) {
                            prenomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("motDePasse")) {
                            motDePasseSecretaire = donneesCourantes;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        donneesCourantes = parser.getText();
                        break;
//                        if (parser.getLocalName().equals("root")) {
//                            this.getDossier().ajouterMedecin(medecinCourant);
//                        }
//                        break;

                }
            }
            parser.close();
        } catch (XMLStreamException ex) {
            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Details :");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Verifier le chemin.");
            System.out.println(ex.getMessage());
        }
        return secretaires;
    }

    public DefaultListModel getPatientLourd() {
        Patient patientCourant = null;
        String nomCourant = "";
        Date dateDeNaissance = null;
        String adresse = "";
        long numSecu = 0;
        String prenomCourant = "";

        String donneesCourantes = "";
        DefaultListModel<Patient> patients;

        patients = new DefaultListModel();
        try {
            // instanciation du parser
            InputStream in = new FileInputStream(repBase + "patient.xml");
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                // traitement selon l'evenement
                switch (event) {

                    case XMLStreamConstants.START_ELEMENT:
                        if (parser.getLocalName().equals("root")) {
                            // medecins = new DefaultListModel();
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:

                        if (parser.getLocalName().equals("patient")) {
                            patientCourant = new Patient(nomCourant, prenomCourant, dateDeNaissance, numSecu, adresse);
                            patients.addElement(patientCourant);
                        }
                        if (parser.getLocalName().equals("nom")) {
                            nomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("prenom")) {
                            prenomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("dateDeNaissance")) {
                            int annee = Integer.parseInt(donneesCourantes.substring(0, donneesCourantes.indexOf('-')));
                            int mois = Integer.parseInt(donneesCourantes.substring(donneesCourantes.indexOf('-') + 1, donneesCourantes.lastIndexOf('-')));
                            int jour = Integer.parseInt(donneesCourantes.substring(donneesCourantes.lastIndexOf('-') + 1, donneesCourantes.length()));

                            dateDeNaissance = new Date(jour, mois, annee);
                        }
                        if (parser.getLocalName().equals("adresse")) {
                            adresse = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("numeroSecu")) {
                            numSecu = Long.parseLong(donneesCourantes);
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        donneesCourantes = parser.getText();
                        break;
//                        if (parser.getLocalName().equals("root")) {
//                            this.getDossier().ajouterMedecin(medecinCourant);
//                        }
//                        break;

                }
            }
            parser.close();
        } catch (XMLStreamException ex) {
            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Details :");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Verifier le chemin.");
            System.out.println(ex.getMessage());
        }
        return patients;
    }

    public DefaultListModel getMedecinLourd() {
        Medecin medecinCourant = null;
        String nomCourant = "";
        String prenomCourant = "";
        String specialiteCourante = "";
        String numeroTelCourant = "";
        String motDePasseMedecin = "";
        String donneesCourantes = "";
        DefaultListModel<Medecin> medecins;

        medecins = new DefaultListModel();
        try {
            // instanciation du parser
            InputStream in = new FileInputStream(repBase + "medecin.xml");
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                // traitement selon l'evenement
                switch (event) {

                    case XMLStreamConstants.START_ELEMENT:
                        if (parser.getLocalName().equals("root")) {
                            // medecins = new DefaultListModel();
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:

                        if (parser.getLocalName().equals("medecin")) {
                            medecinCourant = new Medecin(nomCourant, prenomCourant, specialiteCourante, numeroTelCourant, motDePasseMedecin);
                            medecins.addElement(medecinCourant);
                            break;
                        }
                        if (parser.getLocalName().equals("nom")) {
                            nomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("prenom")) {
                            prenomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("specialite")) {
                            specialiteCourante = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("numeroTel")) {
                            numeroTelCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("motDePasse")) {
                            motDePasseMedecin = donneesCourantes;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        donneesCourantes = parser.getText();
                        break;
//                        if (parser.getLocalName().equals("root")) {
//                            this.getDossier().ajouterMedecin(medecinCourant);
//                        }
//                        break;

                }
            }
            parser.close();
        } catch (XMLStreamException ex) {
            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Details :");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Verifier le chemin.");
            System.out.println(ex.getMessage());
        }
        return medecins;
    }

    public DossierMedical getDossier() {
        DossierMedical dossierCourant = null;
        Date date = null;
        Medecin medecinCourant = null;
        Patient patientCourant = null;
        List<Acte> actes = new Vector<Acte>();
        String donneesCourantes = "";
        String nomCourant = "";
        String prenomCourant = "";
        String specialiteCourante = "";
        String numeroTelCourant = "";
        String motDePasseMedecin = "";
        Date dateDeNaissance = null;
        String adresse = "";
        long numSecu = 0;
        Code codeCourant = null;
        int coefCourant = 0;
        double coutTotal = 0.00;
        String observation = "";
        String nomActe = "";

        // analyser le fichier par StAX
        try {
            // instanciation du parser
            InputStream in = new FileInputStream(repBase + nomFichier);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(in);

            // lecture des evenements
            for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
                // traitement selon l'evenement
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (parser.getLocalName().equals("dossiers")) {
                            dossierCourant = new DossierMedical();
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:

                        if (parser.getLocalName().equals("acte")) {
                            coutTotal = codeCourant.calculerCout(coefCourant);
                            actes.add(new Acte(date, nomActe, observation, medecinCourant, codeCourant, coefCourant, coutTotal));
                        }

                        if (parser.getLocalName().equals("observation")) {
                            observation = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("nomActe")) {
                            nomActe = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("code")) {
                            codeCourant = getCode(donneesCourantes);
                            if (codeCourant == null) {
                                throw new XMLStreamException("Impossible de trouver le code d'acte = " + donneesCourantes);
                            }
                        }
                        if (parser.getLocalName().equals("coef")) {
                            coefCourant = Integer.parseInt(donneesCourantes);
                        }
//                        if (parser.getLocalName().equals("type")) {
//                            type =donneesCourantes;
//                        }
                        if (parser.getLocalName().equals("date")) {
                            int annee = Integer.parseInt(donneesCourantes.substring(0, donneesCourantes.indexOf('-')));
                            int mois = Integer.parseInt(donneesCourantes.substring(donneesCourantes.indexOf('-') + 1, donneesCourantes.lastIndexOf('-')));
                            int jour = Integer.parseInt(donneesCourantes.substring(donneesCourantes.lastIndexOf('-') + 1, donneesCourantes.length()));

                            date = new Date(jour, mois, annee);
                        }
                        if (parser.getLocalName().equals("ficheDeSoins")) {
                            FicheDeSoins f = new FicheDeSoins(patientCourant, medecinCourant, date);
                            // ajout des actes
                            for (int i = 0; i < actes.size(); i++) {
                                Acte a = (Acte) actes.get(i);
                                f.ajouterActe(a);
                            }
                            // effacer tous les actes de la liste
                            actes.clear();
                            // ajouter la fiche de soin au dossiers
                            dossierCourant.ajouterFiche(f);
                        }
                        if (parser.getLocalName().equals("medecin")) {
                            medecinCourant = new Medecin(nomCourant, prenomCourant, specialiteCourante, numeroTelCourant, motDePasseMedecin);
                            //dossierCourant.setMedecins(this.getMedecinLourd());
                        }
                        if (parser.getLocalName().equals("nom")) {
                            nomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("patient")) {
                            patientCourant = new Patient(nomCourant, prenomCourant, dateDeNaissance, numSecu, adresse);
                        }
                        if (parser.getLocalName().equals("prenom")) {
                            prenomCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("specialite")) {
                            specialiteCourante = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("numeroTel")) {
                            numeroTelCourant = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("motDePasse")) {
                            motDePasseMedecin = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("dateDeNaissance")) {
                            int annee = Integer.parseInt(donneesCourantes.substring(0, donneesCourantes.indexOf('-')));
                            int mois = Integer.parseInt(donneesCourantes.substring(donneesCourantes.indexOf('-') + 1, donneesCourantes.lastIndexOf('-')));
                            int jour = Integer.parseInt(donneesCourantes.substring(donneesCourantes.lastIndexOf('-') + 1, donneesCourantes.length()));

                            dateDeNaissance = new Date(jour, mois, annee);
                        }
                        if (parser.getLocalName().equals("adresse")) {
                            adresse = donneesCourantes;
                        }
                        if (parser.getLocalName().equals("numeroSecu")) {
                            numSecu = Long.parseLong(donneesCourantes);
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        donneesCourantes = parser.getText();
                        break;
                } // end switch
            } // end while
            parser.close();
        } catch (XMLStreamException ex) {
            System.out.println("Exception de type 'XMLStreamException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Details :");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Exception de type 'IOException' lors de la lecture du fichier : " + nomFichier);
            System.out.println("Verifier le chemin.");
            System.out.println(ex.getMessage());
        }
        dossierCourant.setSecretaire(this.getSecretaireLourd());
        dossierCourant.setPatient(this.getPatientLourd());
        dossierCourant.setMedecins(this.getMedecinLourd());
        return dossierCourant;

    }

    private static Code getCode(String code) {
        if (code.equals("CS")) {
            return Code.CS;
        }
        if (code.equals("CSC")) {
            return Code.CSC;
        }
        if (code.equals("FP")) {
            return Code.FP;
        }
        if (code.equals("KC")) {
            return Code.KC;
        }
        if (code.equals("KE")) {
            return Code.KE;
        }
        if (code.equals("K")) {
            return Code.K;
        }
        if (code.equals("KFA")) {
            return Code.KFA;
        }
        if (code.equals("KFB")) {
            return Code.KFB;
        }
        if (code.equals("ORT")) {
            return Code.ORT;
        }
        if (code.equals("PRO")) {
            return Code.PRO;
        }
        // probleme : code inconnu
        return null;
    }

    private static String getCodeString(Code code) {
        if (code.equals(Code.CS)) {
            return "CS";
        }
        if (code.equals(Code.CSC)) {
            return "CSC";
        }
        if (code.equals(Code.FP)) {
            return "FP";
        }
        if (code.equals(Code.KC)) {
            return "KC";
        }
        if (code.equals(Code.KE)) {
            return "KE";
        }
        if (code.equals(Code.K)) {
            return "K";
        }
        if (code.equals(Code.KFA)) {
            return "KFA";
        }
        if (code.equals(Code.KFB)) {
            return "KFB";
        }
        if (code.equals(Code.ORT)) {
            return "ORT";
        }
        if (code.equals(Code.PRO)) {
            return "PRO";
        }
        //probleme : code inconnu
        return null;
    }

    public static boolean addMedecin(Medecin m) {
        try {
            File xmlMedecins = new File(repBase + "medecin.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlMedecins);

            NodeList nl = doc.getElementsByTagName("medecin");
            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);
                System.out.println(n.getNodeName());
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(((Element) n).getElementsByTagName("nom").item(0).getTextContent());
                }

            }
            // Get root element.
            Element root = doc.getDocumentElement();
            // Create a new medecin.
            Element medecin = doc.createElement("medecin");
            // Create name.
            Element nom = doc.createElement("nom");
            nom.setTextContent(m.getNom());
            // Append name to medecin.
            medecin.appendChild(nom);
            // Create prenom.
            Element prenom = doc.createElement("prenom");
            prenom.setTextContent(m.getPrenom());
            // Append prenom to medecin.
            medecin.appendChild(prenom);
            // Create specialite.
            Element specialite = doc.createElement("specialite");
            specialite.setTextContent(m.getSpecialite());
            // Append specialite to medecin.
            medecin.appendChild(specialite);
            // Create numeroTel.
            Element numeroTel = doc.createElement("numeroTel");
            numeroTel.setTextContent(m.getNumTel());
            // Append numeroTel to medecin.
            medecin.appendChild(numeroTel);
            // Create motDePasse.
            Element motDePasse = doc.createElement("motDePasse");
            motDePasse.setTextContent(m.getMdp());
            // Append motDePasse to medecin.
            medecin.appendChild(motDePasse);

            root.appendChild(medecin);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(doc),
                        new StreamResult(new FileOutputStream(repBase + "medecin.xml")));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            }

        } catch (Exception e) {

        }
        return true;
    }

    public static boolean addPatient(Patient p) {
        try {
            File xmlPatients = new File(repBase + "patient.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlPatients);

            NodeList nl = doc.getElementsByTagName("patient");
            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(((Element) n).getElementsByTagName("nom").item(0).getTextContent());
                }
            }
            // Get root element.
            Element root = doc.getDocumentElement();
            // Create a new patient.
            Element patient = doc.createElement("patient");
            // Create name.
            Element nom = doc.createElement("nom");
            nom.setTextContent(p.getNom());
            // Append name to patient.
            patient.appendChild(nom);
            // Create prenom.
            Element prenom = doc.createElement("prenom");
            prenom.setTextContent(p.getPrenom());
            // Append prenom to patient.
            patient.appendChild(prenom);
            // Create adresse.
            Element adresse = doc.createElement("adresse");
            adresse.setTextContent(p.getAdresse());
            // Append adresse to patient.
            patient.appendChild(adresse);
            // Create dateDeNaissance.
            Element dateDeNaissance = doc.createElement("dateDeNaissance");
            dateDeNaissance.setTextContent(p.getDateNaissance().afficherDate());
            // Append dateDeNaissance to patient.
            patient.appendChild(dateDeNaissance);
            // Create numeroSecu.
            Element numeroSecu = doc.createElement("numeroSecu");
            numeroSecu.setTextContent(String.valueOf(p.getNumSecu()));
            // Append numeroSecu to patient.
            patient.appendChild(numeroSecu);

            root.appendChild(patient);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                //tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(doc),
                        new StreamResult(new FileOutputStream(repBase + "patient.xml")));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            }

        } catch (Exception e) {

        }
        return true;
    }

    public static boolean addSecretaire(Secretaire s) {
        try {
            File xmlSecretaires = new File(repBase + "secretaire.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlSecretaires);

            NodeList nl = doc.getElementsByTagName("secretaire");
            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(((Element) n).getElementsByTagName("nom").item(0).getTextContent());
                }
            }
            // Get root element.
            Element root = doc.getDocumentElement();
            // Create a new secretaire.
            Element secretaire = doc.createElement("secretaire");
            // Create name.
            Element nom = doc.createElement("nom");
            nom.setTextContent(s.getNomS());
            // Append name to secretaire.
            secretaire.appendChild(nom);
            // Create prenom.
            Element prenom = doc.createElement("prenom");
            prenom.setTextContent(s.getPrenomS());
            // Append prenom to secretaire.
            secretaire.appendChild(prenom);
            // Create motDePasse.
            Element motDePasse = doc.createElement("motDePasse");
            motDePasse.setTextContent(s.getMDPS());
            // Append adresse to secretaire.
            secretaire.appendChild(motDePasse);

            root.appendChild(secretaire);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(doc),
                        new StreamResult(new FileOutputStream(repBase + "secretaire.xml")));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            }

        } catch (Exception e) {

        }
        return true;
    }

    public static boolean addFicheDeSoins(FicheDeSoins fds) {
        try {
            File xmlFicheDeSoins = new File(repBase + "dossiers_copy.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFicheDeSoins);

            NodeList nl = doc.getElementsByTagName("dossiers");
            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(((Element) n).getElementsByTagName("nom").item(0).getTextContent());
                }
            }
            // Get root element.
            Element dossiers = doc.getDocumentElement();
            
            // Create a new ficheDeSoins
            Element ficheDeSoins = doc.createElement("ficheDeSoins");
            
            // Create a new date
            Element date = doc.createElement("date");
            date.setTextContent(fds.getDate().afficherDate());
            //Append date to ficheDeSoins
            ficheDeSoins.appendChild(date);

            // Create a new medecin.
            Element medecin = doc.createElement("medecin");
            // Create name.
            Element nomM = doc.createElement("nom");
            nomM.setTextContent(fds.getMedecin().getNom());
            // Append name to medecin.
            medecin.appendChild(nomM);
            // Create prenom.
            Element prenomM = doc.createElement("prenom");
            prenomM.setTextContent(fds.getMedecin().getPrenom());
            // Append prenom to medecin.
            medecin.appendChild(prenomM);
            // Create specialite.
            Element specialite = doc.createElement("specialite");
            specialite.setTextContent(fds.getMedecin().getSpecialite());
            // Append specialite to medecin.
            medecin.appendChild(specialite);
            // Create numeroTel.
            Element numeroTel = doc.createElement("numeroTel");
            numeroTel.setTextContent(fds.getMedecin().getNumTel());
            // Append numeroTel to medecin.
            medecin.appendChild(numeroTel);
            // Create motDePasse.
            Element motDePasse = doc.createElement("motDePasse");
            // Append motDePasse to medecin.
            medecin.appendChild(motDePasse);
            // Append medecin to dossiers
            ficheDeSoins.appendChild(medecin);

            // Create a new patient.
            Element patient = doc.createElement("patient");
            // Create name.
            Element nomP = doc.createElement("nom");
            nomP.setTextContent(fds.getPatient().getNom());
            // Append name to patient.
            patient.appendChild(nomP);
            // Create prenom.
            Element prenomP = doc.createElement("prenom");
            prenomP.setTextContent(fds.getPatient().getPrenom());
            // Append prenom to patient.
            patient.appendChild(prenomP);
            // Create adresse.
            Element adresse = doc.createElement("adresse");
            adresse.setTextContent(fds.getPatient().getAdresse());
            // Append adresse to patient.
            patient.appendChild(adresse);
            // Create dateDeNaissance.
            Element dateDeNaissance = doc.createElement("dateDeNaissance");
            dateDeNaissance.setTextContent(fds.getPatient().getDateNaissance().afficherDate());
            // Append dateDeNaissance to patient.
            patient.appendChild(dateDeNaissance);
            // Create numeroSecu.
            Element numeroSecu = doc.createElement("numeroSecu");
            numeroSecu.setTextContent(String.valueOf(fds.getPatient().getNumSecu()));
            // Append numeroSecu to patient.
            patient.appendChild(numeroSecu);
            // Apppend patient to ficheDeSoins
            ficheDeSoins.appendChild(patient);

            for (int a = 0; a < fds.getActes().size(); a++) {
                // Create acte
                Element acte = doc.createElement("acte");
                // Create code
                Element code = doc.createElement("code");
                code.setTextContent(getCodeString(((Acte) fds.getActes().get(a)).getCode()));
                // Append code to acte
                acte.appendChild(code);
                // Create coef
                Element coef = doc.createElement("coef");
                coef.setTextContent(String.valueOf(((Acte) fds.getActes().get(a)).getCoef()));
                // Append coef to acte
                acte.appendChild(coef);
                // Create nomActe
                Element nomActe = doc.createElement("nomActe");
                nomActe.setTextContent(((Acte) fds.getActes().get(a)).getLibelle());
                // Append nomActe to acte
                acte.appendChild(nomActe);
                nomActe.setTextContent(((Acte) fds.getActes().get(a)).getType());
                // Create observation
                Element observation = doc.createElement("observation");
                observation.setTextContent(((Acte) fds.getActes().get(a)).getObservations());
                // Append observation to acte
                acte.appendChild(observation);

                // Append acte to ficheDeSoins
                ficheDeSoins.appendChild(acte);
            }
            
            dossiers.appendChild(ficheDeSoins);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(doc),
                        new StreamResult(new FileOutputStream(repBase + "dossiers.xml")));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
           }

        } catch (Exception e) {

        }
        return true;
    }
}
