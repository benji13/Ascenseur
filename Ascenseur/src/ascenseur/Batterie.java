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
    private Calendrier cal;
   
	
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
    
    public void setCal(Calendrier cal) {
		this.cal = cal;
	}
    
    public Calendrier getCal() {
		return cal;
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
     * @throws InterruptedException 
     */
    
//Cette methode va,pour un ascenseur donné, lui affecter une position de repositionnement la plus appropiée
    void repositionnement(Ascenseur unAscenseur) throws InterruptedException{
    	if(unAscenseur.isArret()== true && unAscenseur.getTabDestination().isEmpty() ){	
	        int ecart = 40, i, id=-1;
	        ArrayList<Integer> tabRepositionement = new ArrayList<Integer>();
	        
	        this.cal.determinerPlageHoraire();
	        
	        if(this.cal.isWeek()){
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
	        unAscenseur.repositionnement();
    	}
        
    }//Fin repositionnement
    
    
    
    /**
     * Methode permettant de marquer un Appel comme "traite"
     * @return void
     * @param uneDate
     * @param unAppel 
     */
    void majDateFinAppel(Appel unAppel){
        unAppel.setDateFin(this.cal.getDateActuelle());
        
    }//Fin majDateFinAppel
    
    
    
    /**
     * Methode permettant d'assigner un Appel a un Ascenseur
     * @return void
     * @param unAppel 
     * @throws InterruptedException 
     */
    void assignerAppel() throws InterruptedException{
    	
        int id =0;
        int i = 0;
		int ecart = 40;
		int temp,nbPersonne;
		Appel unAppel = this.tabTousLesAppels.get((tabTousLesAppels.size()-1));
        boolean affected = false;//boolean permettant de savoir si l'appel a été affecté
        //Affecter un id à l'appel
        unAppel.setIdAppel(this.tabTousLesAppels.size()+1);
        
        while(!affected){
        	
        //ne pas oublier de changer le sens de direction quand affecte un appel        
        //Recherche l'ascenseur le plus proche à l'arret
    		for(i=0;i<this.tabAscenseur.size();i++){
    			if(this.tabAscenseur.get(i).isArret()){
    				temp = Math.abs(this.tabAscenseur.get(i).getPositionActuelle() - unAppel.getSourceAppel());
    				if(temp < ecart){
    					ecart = temp;
    					id = i;
    					affected = true;
    					this.tabAscenseur.get(i).setMonte(unAppel.isSensAppel());
    				}
    			}
    		}
    	
	    	//Recherche l'ascenseur le plus rapide en mouvement dans le meme sens
	    	if(!affected){
	    		int duree = -1;
	    		for(i=0;i<this.tabAscenseur.size();i++){
	    			if(!this.tabAscenseur.get(i).isFull() && this.tabAscenseur.get(i).isSurLaRoute(unAppel) && !this.tabAscenseur.get(i).isEnRepositionnement()){
    					temp = this.tabAscenseur.get(i).calculDureeTraitementAppel(unAppel);
    					if(temp<duree && duree!=-1){
    		        		duree = temp;
    		        		id = i;
    		        	}	
	    			}
	    		}
	    	}
        }
        if(affected)
        {
        	System.out.println("Affected");
    		nbPersonne = this.tabAscenseur.get(id).getNbPersonne();
    		this.tabAscenseur.get(id).addAppel(unAppel);
			this.tabAscenseur.get(id).setNbPersonne(nbPersonne++);
			tabAscenseur.get(id).triAppel();
			tabAscenseur.get(id).deplacement();
        }
			
    }//Fin assignerAppel
    
    
  //Revoir la partie commentaire, je ne sais pas faire.....  
    /**
     * Methode permettant la creation d'un appel.
     * @param sourceAppel
     * @param destAppel
     * @param uneDate 
     */
    void creationAppel(int sourceAppel, int destAppel, Date dateAppel){
        Appel unAppel = new Appel(sourceAppel, destAppel, dateAppel);
        tabTousLesAppels.add(unAppel);
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
    public Batterie(int xtemps, boolean isWeek) {
        this.tabAscenseur = new ArrayList<Ascenseur>();
        this.tabTousLesAppels = new ArrayList<Appel>();
        
        try
        {
        	this.cal = new Calendrier(xtemps, isWeek);
        }
        catch (InterruptedException e)
        {
        	System.out.println("Erreur construction");
        }
        
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
        
        Ascenseur ascenseur0;
        Ascenseur ascenseur1;
        Ascenseur ascenseur2;
        Ascenseur ascenseur3;
        Ascenseur ascenseur4;
        Ascenseur ascenseur5;
        
        
        if(isWeek){
        	ascenseur0 = new Ascenseur(0,tabPositionJournee.get(0),xtemps);
    		ascenseur1 = new Ascenseur(1,tabPositionJournee.get(1),xtemps);
    		ascenseur2 = new Ascenseur(2,tabPositionJournee.get(2),xtemps);
    		ascenseur3 = new Ascenseur(3,tabPositionJournee.get(3),xtemps);
    		ascenseur4 = new Ascenseur(4,tabPositionJournee.get(4),xtemps);
    		ascenseur5 = new Ascenseur(5,tabPositionJournee.get(5),xtemps);
        }
        else {
        	ascenseur0 = new Ascenseur(0,tabPositionWeekEnd.get(0),xtemps);
			ascenseur1 = new Ascenseur(1,tabPositionWeekEnd.get(1),xtemps);
			ascenseur2 = new Ascenseur(2,tabPositionWeekEnd.get(2),xtemps);
			ascenseur3 = new Ascenseur(3,tabPositionWeekEnd.get(3),xtemps);
			ascenseur4 = new Ascenseur(4,tabPositionWeekEnd.get(4),xtemps);
			ascenseur5 = new Ascenseur(5,tabPositionWeekEnd.get(5),xtemps);
        }
        
		this.tabAscenseur.add(ascenseur0);
    	this.tabAscenseur.add(ascenseur1);
    	this.tabAscenseur.add(ascenseur2);
    	this.tabAscenseur.add(ascenseur3);
    	this.tabAscenseur.add(ascenseur4);
    	this.tabAscenseur.add(ascenseur5);
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
