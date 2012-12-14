/*
 * Definition de la classe Batterie.
 */
package ascenseur;

import java.util.ArrayList;

/**
 *
 * @author XiXiMe
 */
public class Batterie {
    
    //Attributs
    private ArrayList<Ascenseur> tabAscenseur;
    private ArrayList<Appel> tabTousLesAppels;
    private ArrayList tabPositionJournee;
    private ArrayList tabPositionWeekEnd;

        
    //Definition des Getters/Setters --> permet l'acces aux attributs
    public ArrayList<Ascenseur> getTabAscenseur() {
        return tabAscenseur;
    }

    public void setTabAscenseur(ArrayList<Ascenseur> tabAscenseur) {
        this.tabAscenseur = tabAscenseur;
    }

    public ArrayList<Appel> getTabTousLesAppels() {
        return tabTousLesAppels;
    }

    public void setTabTousLesAppels(ArrayList<Appel> tabTousLesAppels) {
        this.tabTousLesAppels = tabTousLesAppels;
    }

    public ArrayList getTabPositionJournee() {
        return tabPositionJournee;
    }

    public void setTabPositionJournee(ArrayList tabPositionJournee) {
        this.tabPositionJournee = tabPositionJournee;
    }

    public ArrayList getTabPositionWeekEnd() {
        return tabPositionWeekEnd;
    }

    public void setTabPositionWeekEnd(ArrayList tabPositionWeekEnd) {
        this.tabPositionWeekEnd = tabPositionWeekEnd;
    }
    
    
    
    //Definitions des methodes    
    /**
     * Methode d'initialisation de la Batterie
     * @return : void
     * @param : Calendier uneDate 
     */
    void init(Calendrier uneDate){
        
    }//Fin init
    
    
    
    /**
     * Methode de repositionnement des acsenseurs.
     * Permets le repositionnment de chaque ascenseur independemment.
     * Est appelee lorsqu'un ascenseur est à l'arret.
     * @param uneDate
     * @param unAppel
     * @return void
     */
    void repositionnement(Calendrier uneDate, Appel unAppel){
        
        
    }//Fin repositionnement
    
    
    
    /**
     * Methode permettant de marquer un Appel comme "traite"
     * @return void
     * @param uneDate
     * @param unAppel 
     */
    void majDateFinAppel(Calendrier uneDate, Appel unAppel){
        
        
    }//Fin majDateFinAppel
    
    
    
    /**
     * Methode permettant d'assigner un Appel a un Ascenseur
     * @return void
     * @param unAppel 
     */
    void assignerAppel(Appel unAppel){
        
        
    }//Fin assignerAppel
    
    
    
    /**
     * Methode permettant la creation d'un appel.
     * @return void
     * @param sourceAppel
     * @param destAppel
     * @param uneDate 
     */
    void creationAppel(int sourceAppel, int destAppel, Calendrier uneDate){
        
    }//Fin creationAppel
    
    
    
    /**
     * Methode permettant de trouver l'ascenseur le plus rapide (ayant le moins de passagers)
     * @param unAppel
     * @return 
     */
    int calculPlusRapide(Appel unAppel){
        
        return 0;
    }//Fin calculPlusRapide
    
    
    
    /**
     * Methode permettant de trouver l'ascenseur le plus proche de la source de l'appel.
     * @param unAppel
     * @return 
     */
    int calculPlusProche(Appel unAppel){
        
        return 0;
    }//Fin calculPlusProche
    
    
    //Definition du constructeur de la classe
    public Batterie(ArrayList<Ascenseur> tabAscenseur, ArrayList<Appel> tabTousLesAppels) {
        this.tabAscenseur = tabAscenseur;
        this.tabTousLesAppels = tabTousLesAppels;
        
        ArrayList<Integer> tabPositionJournee = new ArrayList<Integer>();
        tabPositionJournee.add(0);
        tabPositionJournee.add(0);
        tabPositionJournee.add(0);
        tabPositionJournee.add(10);
        tabPositionJournee.add(25);
        tabPositionJournee.add(35);
        ArrayList<Integer> tabPositionWeekEnd = new ArrayList<Integer>();
        tabPositionWeekEnd.add(38);
        tabPositionWeekEnd.add(38);
        tabPositionWeekEnd.add(38);
        tabPositionJournee.add(0);
        tabPositionJournee.add(0);
        tabPositionJournee.add(-2);
        this.tabPositionJournee = tabPositionJournee;
        this.tabPositionWeekEnd = tabPositionWeekEnd;
    }

    /**
     * Methode toString pour l'affichage en console du status de la Batterie (peut être modifie en fct des besoins)
     * @return 
     */
    @Override
    public String toString() {
        return "Batterie{" + "tabAscenseur=" + tabAscenseur + ", tabTousLesAppels=" + tabTousLesAppels + ", tabPositionJournee=" + tabPositionJournee + ", tabPositionWeekEnd=" + tabPositionWeekEnd + '}';
    }
    
    
    
}//Fin Class
