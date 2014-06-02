package princetonPlainsboro;

import java.text.DecimalFormat;

public class Acte {

    //attributs

    private Code code;
    private int coef;

    //constructeur
    public Acte(Code code, int coef) {
        this.code = code;
        this.coef = coef;
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
