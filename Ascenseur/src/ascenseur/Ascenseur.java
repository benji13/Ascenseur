package ascenseur;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Observable;

public class Ascenseur extends Observable implements Runnable{
	
	/**
	 * @param args
	 */
	// VARIABLES
	private int idAscenseur;
	private boolean arret; //0 arret; 1 en deplacement
	public ArrayList<Appel> tabAppelAtraiter;
	private ArrayList<Appel> tabAppelsTraites;
	private ArrayList<Integer> tabDestination;
	private HashSet<Integer> tabDestinationTemp;
	private int positionActuelle;
	private int enAcceleration;
	private int nbPersonne;
	private int consommation;
	private boolean monte; //0 monte ; 1 descend
	private int positionRepo;
	private boolean enRepositionnement;
	private int tempsParcoursAscenseur;
	private Seconde sec;
	
	/**
	 * @return
	 */
	public Ascenseur(int idAscenseur, int positionActuelle,Seconde sec) {
		// TODO Auto-generated constructor stub
		this.idAscenseur = idAscenseur;
		this.positionActuelle = positionActuelle;
		this.positionRepo = positionActuelle;
		
		this.enAcceleration = 0;
		this.nbPersonne = 0;
		this.arret = true; //Arret
		this.consommation = 0;
		this.monte = false; // Descend
		this.tempsParcoursAscenseur = 0;
		this.enRepositionnement = false;
		
		this.tabAppelAtraiter = new ArrayList<Appel>();
		this.tabAppelsTraites = new ArrayList<Appel>();
		this.tabDestination = new ArrayList<Integer>();
		this.tabDestinationTemp = new HashSet<Integer>();
		this.sec = sec;
		
	}
	

	
	// GETTER ET SETTER
	public int getEnAcceleration() {
		return enAcceleration;
	}
	public void setEnAcceleration(int enAcceleration) {
		this.enAcceleration = enAcceleration;
	}
	public HashSet<Integer> getTabDestinationTemp() {
		return tabDestinationTemp;
	}
	public void setTabDestinationTemp(HashSet<Integer> tabDestinationTemp) {
		this.tabDestinationTemp = tabDestinationTemp;
	}
	public boolean isEnRepositionnement() {
		return enRepositionnement;
	}
	public void setEnRepositionnement(boolean enRepositionnement) {
		this.enRepositionnement = enRepositionnement;
	}
	public int getTempsParcoursAscenseur() {
		return tempsParcoursAscenseur;
	}
	public void setTempsParcoursAscenseur(int tempsParcoursAscenseur) {
		this.tempsParcoursAscenseur = tempsParcoursAscenseur;
	}
	public void setPositionRepo(int positionRepo) {
		this.positionRepo = positionRepo;
	}
	public int getPositionRepo() {
		return positionRepo;
	}

	public int getIdAscenseur() {
		return idAscenseur;
	}
	public int getPositionActuelle() {
		return positionActuelle;
	}
	public ArrayList<Appel> getTabAppelsTraites() {
		return tabAppelsTraites;
	}
	public int getConsommation() {
		return consommation;
	}

	public int getNbPersonne() {
		return nbPersonne;
	}
	public ArrayList<Appel> getTabAppelAtraiter() {
		return tabAppelAtraiter;
	}
	public ArrayList<Integer> getTabDestination() {
		return tabDestination;
	}
	public void setConsommation(int consommation) {
		this.consommation = consommation;
	}

	public void setNbPersonne(int nbPersonne) {
		this.nbPersonne = nbPersonne;
	}
	public void setTabDestination(ArrayList<Integer> tabDestination) {
		this.tabDestination = tabDestination;
	}
	public void setTabAppelAtraiter(ArrayList<Appel> tabAppelAtraiter) {
		this.tabAppelAtraiter = tabAppelAtraiter;
	}

	public void setIdAscenseur(int idAscenseur) {
		this.idAscenseur = idAscenseur;
	}
	public void setPositionActuelle(int positionActuelle) {
		this.positionActuelle = positionActuelle;
	}
	public void setTabAppelsTraites(ArrayList<Appel> tabAppelsTraites) {
		this.tabAppelsTraites = tabAppelsTraites;
	}
	public boolean isArret() {
		return arret;
	}
	public boolean isMonte() {
		return monte;
	}
	public void setArret(boolean arret) {
		this.arret = arret;
	}
	public void setMonte(boolean monte) {
		this.monte = monte;
	}
	// FONCTIONS
	/**
	 * Fonction permettant de savoir si un appel est sur la route de l'ascenseur
	 * @param unAppel
	 * @return
	 */
	public boolean isSurLaRoute(Appel unAppel) {
		boolean is = false;
		if(this.isMonte())
		{
			if (this.positionActuelle <= unAppel.getSourceAppel() && unAppel.isSensAppel()){
				is = true;
				//System.out.println("L'appel "+unAppel.getSourceAppel()+"--->"+unAppel.getDestAppel()+" est sur la route de l'ascenseur "+ this.idAscenseur);
			}
		}
		else{
			if (this.positionActuelle >= unAppel.getSourceAppel()&& !unAppel.isSensAppel()){
				is = true;
			}
		}
		return is;
	}
	
	
	public void run(){
			try {
				System.out.println("Ascenseur "+ idAscenseur +" :Etage " + positionActuelle);
				this.deplacement();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	/**
	 * Fonction permettant de déplacer un ascenseur d'un étage à un autre.
	 * Deux cas possible : Appel ou Repositionnement
	 * @return
	 * @throws InterruptedException
	 */
	public void deplacement() throws InterruptedException {
		// TODO Auto-generated method stub
		
		for(;;){
			while(!this.tabDestination.isEmpty()){
				System.out.println("ascenseur N°: " + idAscenseur + "  tabDestination: " + tabDestination);
				//////////////////////////
				setChanged();
				notifyObservers("horloge");
				//////////////////////////
			      setChanged();
			      notifyObservers("deplacement"+this.getIdAscenseur());
				
				//supprime la destination si elle correspond à l'étage actuel (Utiledans le cas où la source = l'etage à de l'ascenseur s'il est à l'arret)
				if(positionActuelle==this.tabDestination.get(0)){
					this.tabDestination.remove(0);
				}
				// passe en mouvement
				arret = false;
				//On incremente acceleration (utile qd variable = 1 ou 2)
				enAcceleration++;
				
				// Regarde si on monte ou descend
				if(this.positionActuelle < this.tabDestination.get(0)){
					monte = true;
				}
				else{
					monte = false;
				}

				//calcul temps du parcours
				sleepParcours();
				
				//les appels correspondant à cet etages passe en traité
				if(positionActuelle == this.tabDestination.get(0)){
					
					//////////////////////////
					setChanged();
					notifyObservers("deplacement"+this.getIdAscenseur());
					//////////////////////////
					traitementAppel();	
					System.out.println("Ascenseur "+ idAscenseur +" :Arrêt");
					//Thread.sleep((5*1000)/xtemps);
					this.sec.attenteSeconde(5);
				}
			}
			enAcceleration=0;
			repositionnement();		
			Thread.sleep(100);
		}
	}	
	
	void repositionnement() throws InterruptedException{
		// REPOSITIONNEMENT
	
		if(positionActuelle != positionRepo){
			this.enRepositionnement = true;
			//assigne la position de repositionnement
		    Batterie.repositionnement(this);
		    this.tabDestination.add(positionRepo);
			System.out.println("Ascenseur "+ idAscenseur +" :Je vais me repositionner au " + this.positionActuelle);
			//////////////////////////
			setChanged();
		    notifyObservers("repo"+this.getIdAscenseur());
		    ///////////////////////////
		}
		
		while(!this.tabDestination.isEmpty() ){
			
			//////////////////////////
			setChanged();
		    notifyObservers("repo"+this.getIdAscenseur());
			//////////////////////////

		    
			//System.out.println("Ascenseur n° "+this.getIdAscenseur()+" : ma position repo est : "+this.positionRepo);
			enAcceleration++;
			//Regarde s'il doit monter ou descendre pour joindre position de repositionnement
			if(this.positionActuelle < this.positionRepo){
				monte = true;
			}
			else{
				monte = false;
			}
			//ascenseur passe en mouvement
			arret=false;

			//Sleep suivant le nombre d'étage à parcourir
			sleepParcours();
			
			//les appels correspondant à cet etages passe en traité
			if(positionActuelle == this.tabDestination.get(0)){
				
				//////////////////////////
				setChanged();
				notifyObservers("repo"+this.getIdAscenseur());
				//////////////////////////
	    
	    
				traitementAppel();	
				System.out.println("Ascenseur "+ idAscenseur +" :Repositionnement OK!");
				//Thread.sleep((5*1000)/xtemps);
				this.sec.attenteSeconde(5);
			}
		}
		//On est repositionné
		//ascenseur passe à l'arret
		arret=true;
		//////////////////////////
		setChanged();
		notifyObservers("repo"+this.getIdAscenseur());
		//////////////////////////
		this.enRepositionnement = false;
		enAcceleration=0;
	}
	
	/**
	 * Fonction permettant de rechercher les appels correspondant à l'étage passé en paramètre
	 * @param etage
	 * @return
	 */
	public ArrayList<Appel> rechercheAppel(Integer etage){
		ArrayList<Appel> tabAppelTrouve = new ArrayList<Appel>();
		int i=0;
		while(i<this.tabAppelAtraiter.size()){
			 if(this.tabAppelAtraiter.get(i).getDestAppel() == etage)
				 tabAppelTrouve.add(this.tabAppelAtraiter.get(i));
			 i++;
		}
		return tabAppelTrouve;
	}
	
	/**
	 * Fonction permettant de rechercher les Sources correspondant à l'étage passé en paramètre dans tabDestination
	 * @param etage
	 * @return
	 */
	public ArrayList<Integer> rechercheSource(Integer etage){
		ArrayList<Integer> tabSourceTrouve = new ArrayList<Integer>();
		int i=0;
		while(i<this.tabDestination.size()){
			 if(this.tabDestination.get(i) == etage)
				 tabSourceTrouve.add(etage);
			 i++;
		}
		return tabSourceTrouve;
	}
	
	
	/**
	 * Fonction permettant de dire que nous avons traité un appel
	 * @return
	 */
	public void traitementAppel() {
		
		//////////////////////////
		setChanged();
		notifyObservers("tabAppel");
		//////////////////////////
		
		// TODO Auto-generated method stub
		ArrayList<Appel> tabAppelAsupprimer = new ArrayList<Appel>();
		ArrayList<Integer> tabSourceAsupprimer = new ArrayList<Integer>();
		// Recherche les appels correspondant à l'etage actuel.
		tabAppelAsupprimer = rechercheAppel(this.positionActuelle);
		tabSourceAsupprimer = rechercheSource(this.positionActuelle);
		
		for(int i=0;i<tabAppelAsupprimer.size();i++){
			tabAppelAsupprimer.get(i).setTraite(true);
			Batterie.majDateFinAppel(tabAppelAsupprimer.get(i));
		}
		
		//Déplace les appels de Atraiter vers Traiter
		tabAppelsTraites.addAll(tabAppelAsupprimer);
		tabAppelAtraiter.removeAll(tabAppelAsupprimer);
		this.tabDestination.removeAll(tabSourceAsupprimer);	
	}
	
	/**
	 * Fonction permettant de trier les appels se trouvant dans AppelsAtraiter afin de les organiser dans l'ordre où l'ascenseur va s'arrêter. (tabDestination)
	 * @return
	 */
	public void triAppel() {
		//Fonction triant les appels en attente
		//remplissage d'un tableau avec les destinations des appels
		
		//////////////////////////
		setChanged();
		notifyObservers("tabAppel");
		//////////////////////////

		for (Appel appel : this.tabAppelAtraiter) {
			this.tabDestination.add(appel.getDestAppel());
			if(appel.getSourceAppel() != this.positionActuelle)
				this.tabDestination.add(appel.getSourceAppel());
		}
		
		
		//Suppression des doublons
		this.tabDestinationTemp.addAll(this.tabDestination);		
		this.tabDestination.removeAll(this.tabDestination);		
		this.tabDestination.addAll(this.tabDestinationTemp);
		this.tabDestinationTemp.removeAll(this.tabDestinationTemp);
		
		//Algorithme de tri du precedent tableau
		if(this.monte == true){ //Si on monte
			Collections.sort(this.tabDestination);
		}
		else{ //Si on descend
			Collections.sort(this.tabDestination, Collections.reverseOrder());
		}

	}
	
	/**
	 * Fonction permettant de savoir combien de temps va mettre l'ascenseur avant de traiter l'appel passé en paramètre
	 * C'est l'equivalent d'une simulation, avant de lui affecter l'appel, on calcul le temps eventuel que mettrait l'ascenseur à arriver
	 * @return
	 */
	public int calculDureeTraitementAppel(Appel unAppel){ // /!\ Nettoyer code.
		int i=0;
		int nbEtageAparcourir;
		boolean monte = this.monte;
		tempsParcoursAscenseur=0;
		
		// pour toutes les destinations 
		for(i=0;i<this.tabDestination.size()-1;i++){
			//Test si on a passé l'étage
			if(monte){
				if(unAppel.getDestAppel()<this.tabDestination.get(i)){
					//Calcul le nombre d'etage a parcourir entre deux appels consécutifs
					nbEtageAparcourir = this.tabDestination.get(i+1)-this.tabDestination.get(i);
					nbEtageAparcourir = Math.abs(nbEtageAparcourir);
					
					// Calcul du temps à attendre entre le parcours de ces deux etages cumulé avec l'ancien
					if(nbEtageAparcourir >= 4){
						tempsParcoursAscenseur = tempsParcoursAscenseur + ((10+(nbEtageAparcourir-4)));
					}
					else if(nbEtageAparcourir == 3){
						tempsParcoursAscenseur = tempsParcoursAscenseur + 8;
					}
					else if(nbEtageAparcourir == 2){
						tempsParcoursAscenseur = tempsParcoursAscenseur + 6;
					}
					else if(nbEtageAparcourir == 1){
						tempsParcoursAscenseur = tempsParcoursAscenseur + 3;
					}
					// ajout 5 secondes d'arrêt
					tempsParcoursAscenseur = tempsParcoursAscenseur + 5;
				}
			}
			else if(!monte){
				if(unAppel.getDestAppel()>this.tabDestination.get(i)){
					//Calcul le nombre d'etage a parcourir entre deux appels consécutifs
					nbEtageAparcourir = this.tabDestination.get(i+1)-this.tabDestination.get(i);
					nbEtageAparcourir = Math.abs(nbEtageAparcourir);
					
					// Calcul du temps à attendre entre le parcours de ces deux etages cumulé avec l'ancien
					if(nbEtageAparcourir >= 4){
						tempsParcoursAscenseur = tempsParcoursAscenseur + ((10+(nbEtageAparcourir-4)));
					}
					else if(nbEtageAparcourir == 3){
						tempsParcoursAscenseur = tempsParcoursAscenseur + 8;
					}
					else if(nbEtageAparcourir == 2){
						tempsParcoursAscenseur = tempsParcoursAscenseur + 6;
					}
					else if(nbEtageAparcourir == 1){
						tempsParcoursAscenseur = tempsParcoursAscenseur + 3;
					}
					// ajout 5 secondes d'arrêt
					tempsParcoursAscenseur = tempsParcoursAscenseur + 5;
				}
			}
		
		}
		return tempsParcoursAscenseur;
	}
	
	/**
	 * Fonction permettant de savoir combien de temps va mettre l'ascenseur avant de traiter tout les appels
	 * @return
	 */
	public int calculDureeTraitementToutAppels(){
		int i=0;
		int nbEtageAparcourir;
		tempsParcoursAscenseur=0;
		
		// pour toutes les destinations sauf la dernière car pris dans la comparaison
		for(i=0;i<this.tabDestination.size();i++){
			
			//Calcul le nombre d'etage a parcourir entre deux appels consécutifs
			nbEtageAparcourir = this.tabDestination.get(i+1)-this.tabDestination.get(i);
			nbEtageAparcourir = Math.abs(nbEtageAparcourir);
			
			// Calcul du temps à attendre entre le parcours de ces deux etages cumulé avec l'ancien
			if(nbEtageAparcourir >= 4){
				tempsParcoursAscenseur = tempsParcoursAscenseur + ((10+(nbEtageAparcourir-4)));
			}
			else if(nbEtageAparcourir == 3){
				tempsParcoursAscenseur = tempsParcoursAscenseur + 8;
			}
			else if(nbEtageAparcourir == 2){
				tempsParcoursAscenseur = tempsParcoursAscenseur + 6;
			}
			else if(nbEtageAparcourir == 1){
				tempsParcoursAscenseur = tempsParcoursAscenseur + 3;
			}
		// ajout 5 secondes d'arrêt
		tempsParcoursAscenseur = tempsParcoursAscenseur + 5;
		}
		return tempsParcoursAscenseur;
	}
	
	
	/**
	 * Fonctione permettant de gerer le temps de parcours entre les etages via des sleep
	 * @param nbEtageAparcourir
	 * @throws InterruptedException
	 * @return
	 */
	
	public void sleepParcours() throws InterruptedException{
		int nbEtage =  Math.abs(tabDestination.get(0) - positionActuelle);
		if(enAcceleration == 1){ //premiere acceleration
			//Thread.sleep(3000/xtemps);
			this.sec.attenteSeconde(3);
		}
		else if(enAcceleration == 2){ //deuxieme acceleration
			//Thread.sleep(2000/xtemps);
			this.sec.attenteSeconde(2);
		}
		else{
			if(nbEtage > 2){
				//Thread.sleep(1000/xtemps);
				this.sec.attenteSeconde(1);
			}
			else if(nbEtage == 2){ //Deceleration 1
				//Thread.sleep(2000/xtemps);
				this.sec.attenteSeconde(2);
			}
			else if(nbEtage == 1){ //Deceleration 2
//				Thread.sleep(3000/xtemps);
				this.sec.attenteSeconde(3);
			}	
		}
		if(monte==true)
			positionActuelle++;
			//Consommation
			this.consommation += 1500;
		if(monte==false)
			positionActuelle--;
			//Consommation
			this.consommation += 1500;
		System.out.println("Ascenseur "+ idAscenseur +" :Etage " + positionActuelle);
	}
	
	
	/**
	 * fonction permettant d'ajouter un appel dans le tableau d'appels à traiter	
	 * @param unAppel
	 */
	public void addAppel(Appel unAppel)
	{
		this.tabAppelAtraiter.add(unAppel);
	}
	
	
	/**
	 * Fonction permettant de s'avoir si un ascenseur est plein ou pas.
	 * @return
	 */
	public boolean isFull(){
		boolean isFull;
		if(tabAppelAtraiter.size()==15){
			isFull=true;
		}
		else{ 
			isFull=false;
		}
		return isFull;
	}

}
