/*
 * Definition de la classe Batterie.
 */
package ascenseur;

import java.util.ArrayList;
import java.util.Date;
import java.lang.Math;
/**
 *
 * @author XiXiMe
 */
public class Batterie {
    
    //Attributs
    private ArrayList<Ascenseur> tabAscenseur;
    private ArrayList<Appel> tabTousLesAppels;
    private ArrayList<Integer> tabPositionJournee;
    private ArrayList<Integer> tabPositionWeekEnd;

        
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

    public ArrayList<Integer> getTabPositionJournee() {
        return tabPositionJournee;
    }

    public void setTabPositionJournee(ArrayList <Integer> tabPositionJournee) {
        this.tabPositionJournee = tabPositionJournee;
    }

    public ArrayList<Integer> getTabPositionWeekEnd() {
        return tabPositionWeekEnd;
    }

    public void setTabPositionWeekEnd(ArrayList <Integer> tabPositionWeekEnd) {
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
     * Permet le repositionnment de chaque ascenseur independemment.
     * Est appelee lorsqu'un ascenseur est Ã  l'arret.
     * @param uneDate
     * @param unAppel
     * @return void
     */
//Faire gaff quand la postion de l'ascenseur est egale à la reposition e l'ascenseur+à l'arret
//Cette methode va,pour un ascenseur donné, lui affecter une position de repositionnement la plus appropiée
    void repositionnement(Ascenseur unAscenseur, Calendrier uneDate){
        int ecart = 40, i, id=-1;
        ArrayList<Integer> tabRepositionement = new ArrayList<Integer>();
        if(uneDate.isWeek()){
        	tabRepositionement = this.tabPositionJournee;
        }
        else
        	tabRepositionement = this.tabPositionWeekEnd;
        for(i=0;i<tabRepositionement.size();i++){
        	int temp = Math.abs(unAscenseur.getPositionActuelle() - tabRepositionement.get(i));
        	
        	if(temp<ecart)
        	{
        		ecart = temp;
        		id = i;
        	}
        }
        unAscenseur.setPositionRepo(tabRepositionement.get(id));
        
    }//Fin repositionnement
    
    
    
    /**
     * Methode permettant de marquer un Appel comme "traite"
     * @return void
     * @param uneDate
     * @param unAppel 
     */
    void majDateFinAppel(Calendrier uneDate, Appel unAppel){
        unAppel.setDateFin(uneDate.calculDateActuelle());
        
    }//Fin majDateFinAppel
    
    
    
    /**
     * Methode permettant d'assigner un Appel a un Ascenseur
     * @return void
     * @param unAppel 
     */
    void assignerAppel(Appel unAppel){
        int i;
        //Affecter un id à l'appel
        unAppel.setIdAppel(this.tabTousLesAppels.size()+1);
    	for(i=0;i<this.tabAscenseur.size();i++)
    	{
    		if(this.tabAscenseur.get(i).getPositionActuelle() == unAppel.getSourceAppel()){
    			this.tabAscenseur.get(i).addAppel(unAppel);
    		}
    		else
    		{
    			
    		}
    	}
        
    }//Fin assignerAppel
    
    
  //Revoir la partie commentaire, je ne sais pas faire.....  
    /**
     * Methode permettant la creation d'un appel.
     * @return Appe
     * @param sourceAppel
     * @param destAppel
     * @param uneDate 
     */
    Appel creationAppel(int sourceAppel, int destAppel, Date dateAppel){
        Appel unAppel = new Appel(sourceAppel, destAppel, dateAppel);
        return unAppel;
    }//Fin creationAppel
    
    
    
    /**
     * Methode permettant de trouver l'ascenseur le plus rapide (ayant le moins de passagers)
     * @param unAppel
     * @return 
     */
    int calculPlusRapide(Appel unAppel){
    	 int i, temps, id = -1;
         temps = -1;
         for(i=0;i<this.tabAscenseur.size();i++){
         	int temp = this.tabAscenseur.get(i).getTempsParcoursAscenseur();
         	
         	if(temp<temps && temps!=-1){
        		temps = temp;
        		id = i;
        	}
        }
        return id;
    }//Fin calculPlusRapide
    
    
    
    /**
     * Methode permettant de trouver l'ascenseur le plus proche de la source de l'appel.
     * Elle retourne l'id de l'ascenseur le plus proche
     * @param unAppel
     * @return 
     */
    int calculPlusProche(Appel unAppel){
        int i, distance, id = -1;
        distance = 40;
        for(i=0;i<this.tabAscenseur.size();i++){
        	int temp ;
        	temp = Math.abs (this.tabAscenseur.get(i).getPositionActuelle() - unAppel.getSourceAppel().intValue());
        	
        	if(temp<distance){
        		distance = temp;
        		id = i;
        	}
        }
        return id;
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
     * Methode toString pour l'affichage en console du status de la Batterie (peut Ãªtre modifie en fct des besoins)
     * @return 
     */
    @Override
    public String toString() {
        return "Batterie{" + "tabAscenseur=" + tabAscenseur + ", tabTousLesAppels=" + tabTousLesAppels + ", tabPositionJournee=" + tabPositionJournee + ", tabPositionWeekEnd=" + tabPositionWeekEnd + '}';
    }
    
    
    
}//Fin Class
