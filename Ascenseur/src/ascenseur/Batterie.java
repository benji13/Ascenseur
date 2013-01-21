/*
 * Definition de la classe Batterie.
 */
package ascenseur;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;

/**
 *
 * @author Mo & Thibaut
 */
public class Batterie extends Thread{
    
    //Attributs
    private ArrayList<Ascenseur> tabAscenseur;
    private ArrayList<Appel> tabTousLesAppels;
    private static ArrayList<Integer> tabPositionJournee;
    private static ArrayList<Integer> tabPositionWeekEnd;
    private static ArrayList<Boolean> tabResaPosition;
    private ArrayList<Thread> tabThread ;
    private static Calendrier cal;
    private static boolean semaine; //true si en semaine, false si en week end 
    private static boolean changement; // nous indique s'il y a un changement de mode ou pas
    private boolean mode; //true si on est en mode manuel et false si on est en mode autos
	private Seconde sec;
	
    public static boolean isSemaine() {
		return semaine;
	}
    public boolean isMode() {
		return mode;
	}
    public void setMode(boolean mode) {
		this.mode = mode;
	}
    
    public void setXTempx(int xtemps){
    	Batterie.cal.getChrono().setXtemps(xtemps);   	
    }
       
    public static void setChangement(boolean changement) {
		Batterie.changement = changement;
	}
    
    public static boolean isChangement() {
		return changement;
	}
    
    public static void setSemaine(boolean semaine) {
		Batterie.semaine = semaine;
	}
    public ArrayList<Boolean> getTabResaPosition() {
		return tabResaPosition;
	}
    public void setTabResaPosition(ArrayList<Boolean> tabResaPosition) {
		Batterie.tabResaPosition = tabResaPosition;
	}
	
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
        Batterie.tabPositionJournee = tabPositionJournee;
    }

    public ArrayList<Integer> getTabPositionWeekEnd() {
        return tabPositionWeekEnd;
    }

    public void setTabPositionWeekEnd(ArrayList <Integer> tabPositionWeekEnd) {
        Batterie.tabPositionWeekEnd = tabPositionWeekEnd;
    }
    
    public void setCal(Calendrier cal) {
		Batterie.cal = cal;
	}
    
    public Calendrier getCal() {
		return cal;
	}
    
    
    //Definitions des methodes    
    public void run(){
    	this.assignerAppelAuto();
    }
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
    public static void repositionnement(Ascenseur unAscenseur) throws InterruptedException{
    	if(unAscenseur.getTabDestination().isEmpty() ){	
	        int ecart = 400, i, id=-1;
	        ArrayList<Integer> tabRepositionement = new ArrayList<Integer>();
	        if(Batterie.changement){
	        	System.out.println("Changement : "+Batterie.cal.getDateActuelle().getTime());
	        	 for(int k=0;k<6;k++){
	              	//Initialise le tableau de reservation de position repos à faux. (Aucune position n'a été réservée)
	              	Boolean bool = new Boolean(false);
	              	Batterie.tabResaPosition.set(k, bool);
	              }
	        	}
		        if(Batterie.semaine){
		        	tabRepositionement = Batterie.tabPositionJournee;
		        }
		        else
		        	tabRepositionement = Batterie.tabPositionWeekEnd;
		        Batterie.changement = false;
	       
	        
	        System.out.println(unAscenseur.getIdAscenseur()+" : "+Batterie.tabResaPosition);
	        for(i=0;i<6;i++){
	        	//Si la position n'a pas été réservée
	        	if(!Batterie.tabResaPosition.get(i)){
		        	int temp = Math.abs(unAscenseur.getPositionActuelle() - tabRepositionement.get(i));
		        	
		        	if(temp<ecart)
		        	{
		        		ecart = temp;
		        		id = i;
		        	}
	        	}
	        }
    		//Reservation de l'emplacement
    		Batterie.tabResaPosition.set(id, true);
	        unAscenseur.setPositionRepo(tabRepositionement.get(id));
    	}
    }//Fin repositionnement
    
    public void libererReservation(ArrayList<Integer> tabRepositionement,int etage){
    	int id=0;
    	System.out.println(tabRepositionement);
    	for(int i=0;i<6;i++){
    		if(Batterie.tabResaPosition.get(i) && etage==tabRepositionement.get(i)){
    			id=i;
    		}
    	}
    	
    	Batterie.tabResaPosition.set(id, false);
    	System.out.println("liberation de "+tabRepositionement.get(id));   	
    }
    
    
    
    /**
     * Methode permettant de marquer un Appel comme "traite"
     * @return void
     * @param uneDate
     * @param unAppel 
     */
    public static void majDateFinAppel(Appel unAppel){
    	Calendar uneDate = Calendar.getInstance() ;
    	uneDate.setTime(Batterie.cal.getDateActuelle().getTime());
        unAppel.setDateFin(uneDate);        
    }//Fin majDateFinAppel
    
    
    
    /**
     * Methode permettant d'assigner un Appel a un Ascenseur
     * @return void
     * @param unAppel 
     * @throws InterruptedException 
     */
    Ascenseur assignerAppel(Appel unAppel) throws InterruptedException{
    	
        int id =-1;
        int i = 0;
		int ecart = 400;
		int temp,nbPersonne,zoneCritique;
        boolean affected = false;//boolean permettant de savoir si l'appel a été affecté
        if(this.isMode()){
        	//Affecter un id à l'appel
        	unAppel.setIdAppel(this.tabTousLesAppels.size()+1);
        }
        unAppel.determineSens();
        while(!affected){	
        //ne pas oublier de changer le sens de direction quand affecte un appel        
        //Recherche l'ascenseur le plus proche à l'arret
    		for(i=0;i<6;i++){
    			if(this.tabAscenseur.get(i).isArret() && !this.tabAscenseur.get(i).isEnRepositionnement()){
    				temp = Math.abs(this.tabAscenseur.get(i).getPositionActuelle() - unAppel.getSourceAppel());
    				if(temp < ecart){
    					ecart = temp;
    					id = this.tabAscenseur.get(i).getIdAscenseur();
    					affected = true;
    					this.tabAscenseur.get(id).setMonte(unAppel.isSensAppel());
    				}
    			}
    		}
    	
	    	//Recherche l'ascenseur le plus rapide en mouvement dans le meme sens
	    	if(!affected){
	    		int duree = 100000000;
	    		for(i=0;i<6;i++){
	    			//Si la source de l'appel se trouve à moins de 2 etages d'ecart l'ascenseur ne doit pas etre séléctioné, car l'ascenseur ne pourra pas s'arrêter
	    			zoneCritique = Math.abs(this.tabAscenseur.get(i).getPositionActuelle() - unAppel.getSourceAppel());
	    			if(!this.tabAscenseur.get(i).isFull() && !this.tabAscenseur.get(i).isEnRepositionnement() && zoneCritique>2 &&this.tabAscenseur.get(i).isSurLaRoute(unAppel) ){
    					temp = this.tabAscenseur.get(i).calculDureeTraitementAppel(unAppel);
    					if(temp<duree){
    		        		duree = temp;
    		        		id = this.tabAscenseur.get(i).getIdAscenseur();
    		        		affected=true;
    		        	}	
	    			}
	    		}
	    	}
        }
        ArrayList<Integer> tabRepositionement = new ArrayList<Integer>();

        if(Batterie.changement){
        	 for(int k=0;k<6;k++){
             	//Initialise le tableau de reservation de position repos à faux. (Aucune position n'a été réservée)
             	Boolean bool = new Boolean(false);
             	Batterie.tabResaPosition.set(k, bool);
             }
        }
        if(Batterie.semaine){
        	tabRepositionement = Batterie.tabPositionJournee;
        }
        else
        	tabRepositionement = Batterie.tabPositionWeekEnd;
       
        if(affected){
        	libererReservation(tabRepositionement,this.tabAscenseur.get(id).getPositionRepo());
        	System.out.println("Liberation de la reservation de l'ascenseur "+ this.tabAscenseur.get(id).getIdAscenseur() +" " +Batterie.tabResaPosition);
    		nbPersonne = this.tabAscenseur.get(id).getNbPersonne();
    		this.tabAscenseur.get(id).addAppel(unAppel);
			this.tabAscenseur.get(id).setNbPersonne(nbPersonne++);
			tabAscenseur.get(id).triAppel();
        }
        
        return tabAscenseur.get(id);
    }//Fin assignerAppel

    public void assignerAppelAuto(){
    	int i = 0;
    	long ecart;
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
//    	for(int j=0;j<this.getTabTousLesAppels().size();j++){
//			System.out.println(this.getTabTousLesAppels().get(j).getIdAppel()+"///"+this.getTabTousLesAppels().get(j).getDateDebut().getTime()+"---"+ this.getTabTousLesAppels().get(j).getDateDebut().getTime());
//		}
    	
    	while(i<this.tabTousLesAppels.size()){
    		System.out.println(Batterie.cal.getDateActuelle().getTimeInMillis()/1000+"//"+this.tabTousLesAppels.get(i).getDateDebut().getTimeInMillis()/1000);
    		System.out.println("Recuperation de l'iD  :  "+this.getTabTousLesAppels().get(i).getIdAppel());
    		ecart = Batterie.cal.getDateActuelle().getTimeInMillis()/1000 - this.tabTousLesAppels.get(i).getDateDebut().getTimeInMillis()/1000;
    		try {	
				if(Batterie.cal.getDateActuelle().getTimeInMillis()/1000 == this.tabTousLesAppels.get(i).getDateDebut().getTimeInMillis()/1000 ||(ecart<2 && Batterie.cal.getDateActuelle().getTimeInMillis()/1000 > this.tabTousLesAppels.get(i).getDateDebut().getTimeInMillis()/1000)){
					assignerAppel(this.tabTousLesAppels.get(i));
					//System.out.println("Lancement appel");
					i++;
				}	
				sec.attenteSeconde(1);
	    	} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
    	this.stopSimu();
    	System.out.println("Debut creation log");
		this.creationLog();
		System.out.println("Fin creation log");
		System.out.println("Debut creation statistiques");
		this.creationStat();
		System.out.println("Fin creation statistiques");
    }
  //Revoir la partie commentaire, je ne sais pas faire.....  
    /**
     * Methode permettant la creation d'un appel.
     * @param sourceAppel
     * @param destAppel
     * @param uneDate 
     */
    void creationAppelAuto(int sourceAppel, int destAppel, Calendar dateAppel){
        Appel unAppel = new Appel(sourceAppel, destAppel, dateAppel);
        tabTousLesAppels.add(unAppel);
    }//Fin creationAppel
    
    public Appel creationAppelManu(int sourceAppel, int destAppel){
    	Calendar uneDate = Calendar.getInstance() ;
    	uneDate.setTime(Batterie.cal.getDateActuelle().getTime());
        Appel unAppel = new Appel(sourceAppel, destAppel, uneDate);
        tabTousLesAppels.add(unAppel);
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
    
    public void creationLog(){
    	for(int j=0;j<6;j++){
    		for(int i=0;i<this.tabAscenseur.get(j).getTabAppelsTraites().size();i++){
    			System.out.println(j+"//"+this.tabAscenseur.get(j).getTabAppelsTraites().get(i).getIdAppel()+"//"+this.tabAscenseur.get(j).getTabAppelsTraites().get(i).getSourceAppel());
    		}
    	}

		try {
			PrintWriter out  = new PrintWriter(new FileWriter("log.txt", false));
			for(Ascenseur asc : tabAscenseur){
				for(Appel unAppel : asc.getTabAppelsTraites()){
					System.out.println(asc.getIdAscenseur());
					out.println("Ascenseur N°" + asc.getIdAscenseur());
					out.println("\n\tAppel N°" + unAppel.getIdAppel());
					out.println("\n\t\tSource: " + unAppel.getSourceAppel());
					out.println("\n\t\tDestination: " + unAppel.getDestAppel());
					out.println("\n\t\tDebut: " + unAppel.getDateDebut().getTime());
					out.println("\n\t\tFin: " + unAppel.getDateFin().getTime() + "\n\n");
				}
			}
			out.println("\n\n\n");
			out.close();
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
    }
    
    public void creationStat(){
        Statistiques stat = new Statistiques();
        stat.calculStatistique(this);
        try {
            PrintWriter out  = new PrintWriter(new FileWriter("statistiques.txt", false));
            out.println("######## STATISTIQUES ########");
            out.println("Date simulation : " + Calendar.getInstance().getTime());
            out.println("### Statistiques globales ###");
            out.println("\t\tDurée simulation: " + stat.getTotalDuree());
            out.println("\t\tConsommation Totale(W): " + stat.getConsoTotal());
            out.println("\t\tConsommation Moyenne(W/Appel): " + stat.getConsoMoyenneTotale());
            out.println("\t\tNombre d'appel total: " + stat.getNbAppelTotal());
            out.println("\t\tAttente moyenne: " + stat.getAttenteMoyenne());
            out.println("\n\n### Statistiques détaillées ###");
            out.println("# Ascenseur A #");
            out.println("\t\tNombre d'appel Total: " + stat.getTabnbAppel()[0]);
            out.println("\t\tConsommation totale (W): " + stat.getTabConso()[0]);
            out.println("\t\tConsommation moyenne (W/Appel): " + stat.getTabConsoMoyenne()[0]);
            out.println("# Ascenseur B #");
            out.println("\t\tNombre d'appel Total: " + stat.getTabnbAppel()[1]);
            out.println("\t\tConsommation totale (W): " + stat.getTabConso()[1]);
            out.println("\t\tConsommation moyenne (W/Appel): " + stat.getTabConsoMoyenne()[1]);
            out.println("# Ascenseur C #");
            out.println("\t\tNombre d'appel Total:" + stat.getTabnbAppel()[2]);
            out.println("\t\tConsommation totale (W): " + stat.getTabConso()[2]);
            out.println("\t\tConsommation moyenne (W/Appel): " + stat.getTabConsoMoyenne()[2]);
            out.println("# Ascenseur D #");
            out.println("\t\tNombre d'appel Total: " + stat.getTabnbAppel()[3]);
            out.println("\t\tConsommation totale (W): " + stat.getTabConso()[3]);
            out.println("\t\tConsommation moyenne (W/Appel): " + stat.getTabConsoMoyenne()[3]);
            out.println("# Ascenseur E #");
            out.println("\t\tNombre d'appel Total: " + stat.getTabnbAppel()[4]);
            out.println("\t\tConsommation totale (W): " + stat.getTabConso()[4]);
            out.println("\t\tConsommation moyenne (W/Appel): " + stat.getTabConsoMoyenne()[4]);
            out.println("# Ascenseur F #");
            out.println("\t\tNombre d'appel Total: " + stat.getTabnbAppel()[5]);
            out.println("\t\tConsommation totale (W): " + stat.getTabConso()[5]);
            out.println("\t\tConsommation moyenne (W/Appel): " + stat.getTabConsoMoyenne()[5]);
            out.close();
        } 
        catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } 
    }
    
    //Definition du constructeur de la classe
    public Batterie(int xtemps,Seconde sec) {
        this.tabAscenseur = new ArrayList<Ascenseur>();
        this.tabTousLesAppels = new ArrayList<Appel>();
        Batterie.tabResaPosition = new ArrayList<Boolean>();
        tabThread = new ArrayList<Thread>();
        this.setMode(true);
        
        for(int i=0;i<6;i++){
        	//Initialise le tableau de reservation de position repos à faux. (Aucune position n'a été réservée)
        	Boolean bool = new Boolean(true);
        	Batterie.tabResaPosition.add(bool);
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
        tabPositionWeekEnd.add(0);
        tabPositionWeekEnd.add(0);
        tabPositionWeekEnd.add(-2);
        
        
        Batterie.tabPositionJournee = tabPositionJournee;
        Batterie.tabPositionWeekEnd = tabPositionWeekEnd;
        
        
        Ascenseur ascenseur0;
        Ascenseur ascenseur1;
        Ascenseur ascenseur2;
        Ascenseur ascenseur3;
        Ascenseur ascenseur4;
        Ascenseur ascenseur5;
        
        Thread t0;
        Thread t1;
        Thread t2;
        Thread t3;
        Thread t4;
        Thread t5;
        
        if(Batterie.semaine){
        	ascenseur0 = new Ascenseur(0,tabPositionJournee.get(0),sec);
    		ascenseur1 = new Ascenseur(1,tabPositionJournee.get(1),sec);
    		ascenseur2 = new Ascenseur(2,tabPositionJournee.get(2),sec);
    		ascenseur3 = new Ascenseur(3,tabPositionJournee.get(3),sec);
    		ascenseur4 = new Ascenseur(4,tabPositionJournee.get(4),sec);
    		ascenseur5 = new Ascenseur(5,tabPositionJournee.get(5),sec);
        }
        else {
        	ascenseur0 = new Ascenseur(0,tabPositionWeekEnd.get(0),sec);
			ascenseur1 = new Ascenseur(1,tabPositionWeekEnd.get(1),sec);
			ascenseur2 = new Ascenseur(2,tabPositionWeekEnd.get(2),sec);
			ascenseur3 = new Ascenseur(3,tabPositionWeekEnd.get(3),sec);
			ascenseur4 = new Ascenseur(4,tabPositionWeekEnd.get(4),sec);
			ascenseur5 = new Ascenseur(5,tabPositionWeekEnd.get(5),sec);
        }
        
   
        
        t0 = new Thread(ascenseur0);
        t1 = new Thread(ascenseur1);
        t2 = new Thread(ascenseur2);
        t3 = new Thread(ascenseur3);
        t4 = new Thread(ascenseur4);
        t5 = new Thread(ascenseur5);
        
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        
        this.tabThread.add(t0);
        this.tabThread.add(t1);
        this.tabThread.add(t2);
        this.tabThread.add(t3);
        this.tabThread.add(t4);
        this.tabThread.add(t5);
        
		this.tabAscenseur.add(ascenseur0);
    	this.tabAscenseur.add(ascenseur1);
    	this.tabAscenseur.add(ascenseur2);
    	this.tabAscenseur.add(ascenseur3);
    	this.tabAscenseur.add(ascenseur4);
    	this.tabAscenseur.add(ascenseur5);   	
   
    	Batterie.changement = false;    	
    	try
        {
        	Batterie.cal = new Calendrier(xtemps,sec);
           	Batterie.cal.start();
        	Batterie.cal.getChrono().start();
        }
        catch (InterruptedException e)
        {
        	System.out.println("Erreur construction");
        }
    	this.getCal().determinerPlageHoraire();
    }

    public Batterie(int xtemps,Seconde sec,String file) {
    	this.sec = sec;
        this.tabAscenseur = new ArrayList<Ascenseur>();
        this.tabTousLesAppels = new ArrayList<Appel>();
        Batterie.tabResaPosition = new ArrayList<Boolean>();
        tabThread = new ArrayList<Thread>();
        this.setMode(false);
        for(int i=0;i<6;i++){
        	//Initialise le tableau de reservation de position repos à faux. (Aucune position n'a été réservée)
        	Boolean bool = new Boolean(true);
        	Batterie.tabResaPosition.add(bool);
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
        tabPositionWeekEnd.add(0);
        tabPositionWeekEnd.add(0);
        tabPositionWeekEnd.add(-2);
        
        
        Batterie.tabPositionJournee = tabPositionJournee;
        Batterie.tabPositionWeekEnd = tabPositionWeekEnd;
        
        
        Ascenseur ascenseur0;
        Ascenseur ascenseur1;
        Ascenseur ascenseur2;
        Ascenseur ascenseur3;
        Ascenseur ascenseur4;
        Ascenseur ascenseur5;
        
        Thread t0;
        Thread t1;
        Thread t2;
        Thread t3;
        Thread t4;
        Thread t5;

        if(Batterie.semaine){
        	ascenseur0 = new Ascenseur(0,tabPositionJournee.get(0),sec);
    		ascenseur1 = new Ascenseur(1,tabPositionJournee.get(1),sec);
    		ascenseur2 = new Ascenseur(2,tabPositionJournee.get(2),sec);
    		ascenseur3 = new Ascenseur(3,tabPositionJournee.get(3),sec);
    		ascenseur4 = new Ascenseur(4,tabPositionJournee.get(4),sec);
    		ascenseur5 = new Ascenseur(5,tabPositionJournee.get(5),sec);
        }
        else {
        	ascenseur0 = new Ascenseur(0,tabPositionWeekEnd.get(0),sec);
			ascenseur1 = new Ascenseur(1,tabPositionWeekEnd.get(1),sec);
			ascenseur2 = new Ascenseur(2,tabPositionWeekEnd.get(2),sec);
			ascenseur3 = new Ascenseur(3,tabPositionWeekEnd.get(3),sec);
			ascenseur4 = new Ascenseur(4,tabPositionWeekEnd.get(4),sec);
			ascenseur5 = new Ascenseur(5,tabPositionWeekEnd.get(5),sec);
        }

        t0 = new Thread(ascenseur0);
        t1 = new Thread(ascenseur1);
        t2 = new Thread(ascenseur2);
        t3 = new Thread(ascenseur3);
        t4 = new Thread(ascenseur4);
        t5 = new Thread(ascenseur5);
        
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        
        this.tabThread.add(t0);
        this.tabThread.add(t1);
        this.tabThread.add(t2);
        this.tabThread.add(t3);
        this.tabThread.add(t4);
        this.tabThread.add(t5);
        
		this.tabAscenseur.add(ascenseur0);
    	this.tabAscenseur.add(ascenseur1);
    	this.tabAscenseur.add(ascenseur2);
    	this.tabAscenseur.add(ascenseur3);
    	this.tabAscenseur.add(ascenseur4);
    	this.tabAscenseur.add(ascenseur5);   	
   
    	Batterie.changement = false;
    	this.tabTousLesAppels = ParseurDom.extraireAppels(ParseurDom.chargerDocument(file));
    	
    	
    	try
        {
        	Batterie.cal = new Calendrier(xtemps,sec,1,0,1970,this.tabTousLesAppels.get(0).getDateDebut().getTime().getHours()-1);
				
        	Batterie.cal.getDateDebutSimu().set(Calendar.MINUTE, 50);
        	Batterie.cal.getDateDebutSimu().set(Calendar.SECOND, 30);
        	Batterie.cal.getDateActuelle().set(Calendar.MINUTE, 50);
        	Batterie.cal.getDateActuelle().set(Calendar.SECOND, 30);
    		System.out.println(Batterie.cal.getDateActuelle().getTime());

        	
        	Batterie.cal.start();
        	Batterie.cal.getChrono().start();
        }
        catch (InterruptedException e)
        {
        	System.out.println("Erreur construction");
        }
    	this.getCal().determinerPlageHoraire();
    	this.start();
    }
    
    
    
    public Batterie(int xtemps,Seconde sec,int jour, int mois, int annee, int heure) {
        this.tabAscenseur = new ArrayList<Ascenseur>();
        this.tabTousLesAppels = new ArrayList<Appel>();
        Batterie.tabResaPosition = new ArrayList<Boolean>();
        tabThread = new ArrayList<Thread>();
        this.setMode(true);
        
        for(int i=0;i<6;i++){
        	//Initialise le tableau de reservation de position repos à faux. (Aucune position n'a été réservée)
        	Boolean bool = new Boolean(true);
        	Batterie.tabResaPosition.add(bool);
        }
        
        try
        {
        	Batterie.cal = new Calendrier(xtemps, sec, jour, mois, annee, heure);
        	Batterie.cal.start();
        	Batterie.cal.getChrono().start();
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
        tabPositionWeekEnd.add(0);
        tabPositionWeekEnd.add(0);
        tabPositionWeekEnd.add(-2);
        
        
        Batterie.tabPositionJournee = tabPositionJournee;
        Batterie.tabPositionWeekEnd = tabPositionWeekEnd;
        this.getCal().determinerPlageHoraire();
        System.out.println("debug "+Batterie.semaine);
        Ascenseur ascenseur0;
        Ascenseur ascenseur1;
        Ascenseur ascenseur2;
        Ascenseur ascenseur3;
        Ascenseur ascenseur4;
        Ascenseur ascenseur5;
        
        Thread t0;
        Thread t1;
        Thread t2;
        Thread t3;
        Thread t4;
        Thread t5;
        
        
        
        if(Batterie.semaine){
        	ascenseur0 = new Ascenseur(0,tabPositionJournee.get(0),sec);
    		ascenseur1 = new Ascenseur(1,tabPositionJournee.get(1),sec);
    		ascenseur2 = new Ascenseur(2,tabPositionJournee.get(2),sec);
    		ascenseur3 = new Ascenseur(3,tabPositionJournee.get(3),sec);
    		ascenseur4 = new Ascenseur(4,tabPositionJournee.get(4),sec);
    		ascenseur5 = new Ascenseur(5,tabPositionJournee.get(5),sec);
        }
        else {
        	ascenseur0 = new Ascenseur(0,tabPositionWeekEnd.get(0),sec);
			ascenseur1 = new Ascenseur(1,tabPositionWeekEnd.get(1),sec);
			ascenseur2 = new Ascenseur(2,tabPositionWeekEnd.get(2),sec);
			ascenseur3 = new Ascenseur(3,tabPositionWeekEnd.get(3),sec);
			ascenseur4 = new Ascenseur(4,tabPositionWeekEnd.get(4),sec);
			ascenseur5 = new Ascenseur(5,tabPositionWeekEnd.get(5),sec);
        }
        
   
        
        t0 = new Thread(ascenseur0);
        t1 = new Thread(ascenseur1);
        t2 = new Thread(ascenseur2);
        t3 = new Thread(ascenseur3);
        t4 = new Thread(ascenseur4);
        t5 = new Thread(ascenseur5);
        
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        
        this.tabThread.add(t0);
        this.tabThread.add(t1);
        this.tabThread.add(t2);
        this.tabThread.add(t3);
        this.tabThread.add(t4);
        this.tabThread.add(t5);
        
		this.tabAscenseur.add(ascenseur0);
    	this.tabAscenseur.add(ascenseur1);
    	this.tabAscenseur.add(ascenseur2);
    	this.tabAscenseur.add(ascenseur3);
    	this.tabAscenseur.add(ascenseur4);
    	this.tabAscenseur.add(ascenseur5);   	
   
    	Batterie.changement = false;
    }
    /**
     * Methode toString pour l'affichage en console du status de la Batterie (peut Ãªtre modifie en fct des besoins)
     * @return 
     */
    @Override
    public String toString() {
        return "Batterie{" + "tabAscenseur=" + tabAscenseur + ", tabTousLesAppels=" + tabTousLesAppels + ", tabPositionJournee=" + tabPositionJournee + ", tabPositionWeekEnd=" + tabPositionWeekEnd + '}';
    }
    
    public void stopSimu(){
    	while(tabThread.get(0).isAlive() || tabThread.get(1).isAlive() || tabThread.get(2).isAlive() || tabThread.get(3).isAlive() || tabThread.get(4).isAlive() || tabThread.get(5).isAlive()){
    		for(int i=0;i<6;i++){
	    		if(tabThread.get(i).isAlive() && tabAscenseur.get(i).getTabDestination().isEmpty() && tabAscenseur.get(i).isArret()){
	    			this.tabThread.get(i).stop();
	    		}
    		}
    	}
    	this.getCal().getChrono().stop();
    	System.out.println("Temps Totale Simu en accelerée: "+this.getCal().getDateActuelle().getTime());
    }
    
    public void stopSimuBrute(){
    	for(int i=0;i<5;i++){
    			this.tabThread.get(i).stop();
    	}
    	this.getCal().getChrono().stop();
    	try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}//Fin Class
