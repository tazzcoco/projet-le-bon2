/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package princetonPlainsboro;

/**
 *
 * @author Thomas
 */

public class Secretaire {
    private String nom;
    private String prenom;
    private String mdp;
    
    public Secretaire(String nom, String prenom, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
    }
    
    public String getNomS(){
        return nom;
    }
    
    public String getPrenomS(){
        return prenom;
    }
    
    public String getMDPS(){
        return mdp;
    }
}
