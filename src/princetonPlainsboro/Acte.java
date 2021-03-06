package princetonPlainsboro;

import java.text.DecimalFormat;

public class Acte {

    //attributs

    private Code code;
    private int coef;
    private Date date;
    private String type;
    private Medecin medecin;
    private double coutTotal;
    private String observations;

    //constructeur
    public Acte(Code code, int coef) {
        this.code = code;
        this.coef = coef;
        
    }
   
    public Acte(Code code, int coef,String observations, String type) {
        this.code = code;
        this.coef = coef;
        this.observations = observations;    
        this.type = type;
    }
    
    public String getObservations(){
        return observations;
    }
    
    public Code getCodeCS(){
        return Code.CS;
    }
    
    public Acte(Date date,String type,String observation, Medecin medecin,Code code,int coef, double coutTotal){
        this.date=date;
        this.type=type;
        this.observations=observation;
        this.medecin=medecin;
        this.code=code;
        this.coef=coef;
        this.coutTotal=coutTotal;
        
    }

//    public Acte(Date date, String libelle, String observation, Medecin medecin, Code code, double coutTotal) {
//        this.date=date;
//        this.libelle=libelle;
//        this.observation=observation;
//        this.medecin=medecin;
//        this.code=code;
//        this.coutTotal=coutTotal;
//    }

    @Override
    public String toString() {
       return code.toString() + ", coefficient : " + coef;
    }

    public double cout() {
        return code.calculerCout(coef);
    }
    public int getCoef(){
        return coef;
    }
    public Code getCode(){
        return code;
    }
    
    public String getLibelle() {
        return code.getLibelle();
    }
    
    public String getType(){
        return type;
    }

}
