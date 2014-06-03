package princetonPlainsboro;

import java.text.DecimalFormat;

public class Acte {

    //attributs

    private Code code;
    private int coef;
    private Date date;
    private String libelle;
    private Medecin medecin;
    private String observation;
    private double coutTotal;

    //constructeur
    public Acte(Code code, int coef) {
        this.code = code;
        this.coef = coef;
    }
    
//    public Acte(Date date,String libelle,String observation, Medecin medecin,Code code, double coutTotal){
//        this.date=date;
//        this.libelle=libelle;
//        this.observation=observation;
//        this.medecin=medecin;
//        this.code=code;
//        this.coutTotal=coutTotal;
//    }

    public Acte(Date date, String libelle, String observation, Medecin medecin, Code code, double coutTotal) {
        this.date=date;
        this.libelle=libelle;
        this.observation=observation;
        this.medecin=medecin;
        this.code=code;
        this.coutTotal=coutTotal;
    }

    @Override
    public String toString() {
        return code.toString() + ", coefficient : " + coef;
    }

    public double cout() {
        return code.calculerCout(coef);
    }
    
    public Code getCode(){
        return code;
    }
    
    public String getLibelle() {
        return code.getLibelle();
    }

}
