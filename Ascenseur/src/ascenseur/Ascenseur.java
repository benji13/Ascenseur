package ascenseur;

import java.util.ArrayList;
import java.util.Collections;

public class Ascenseur {
	
	/**
	 * @return
	 */
	public Ascenseur(int idAscenseur, int positionActuelle,int xtemps) {
		// TODO Auto-generated constructor stub
		this.idAscenseur = idAscenseur;
		this.positionActuelle = positionActuelle;
		this.positionRepo = positionActuelle;
		
		this.nbPersonne = 0;
		this.etatAscenseur = false; //Arret
		this.consommation = 0;
		this.directionEnCours = 1; // Descend
		this.tempsParcoursAscenseur = 0;
		this.xtemps = xtemps;
		
		this.tabAppelAtraiter = new ArrayList<Appel>();
		this.tabAppelsTraites = new ArrayList<Appel>();
		this.tabDestination = new ArrayList<Integer>();
		
		System.out.println("Ascenseur "+ idAscenseur +" :Etage " + positionActuelle);
		
	}
	
	/**
	 * @param args
	 */
	// VARIABLES
	private int idAscenseur;
	private boolean etatAscenseur; //0 arret; 1 en deplacement
	private ArrayList<Appel> tabAppelAtraiter;
	private ArrayList<Appel> tabAppelsTraites;
	private ArrayList<Integer> tabDestination;
	private int positionActuelle;
	private int nbPersonne;
	private int consommation;
	private int directionEnCours; //0 monte ; 1 descend
	private int positionRepo;
	private int tempsParcoursAscenseur;
	private int xtemps;
	
	// GETTER ET SETTER
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
	public int getDirectionEnCours() {
		return directionEnCours;
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
	public boolean getEtatAscenseur() {
		return etatAscenseur;
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
	public void setEtatAscenseur(boolean etatAscenseur) {
		this.etatAscenseur = etatAscenseur;
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
	public void setDirectionEnCours(int directionEnCours) {
		this.directionEnCours = directionEnCours;
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
	
	// FONCTIONS

	/**
	 * Fonction permettant de déplacer un ascenseur d'un étage à un autre.
	 * Deux cas possible : Appel ou Repositionnement
	 * @return
	 * @throws InterruptedException
	 */
	public void deplacement() throws InterruptedException {
		// TODO Auto-generated method stub
		
		int nbEtageAparcourir;
		
		if(tabAppelAtraiter.size() != 0){ // DEPLACEMENT CAR APPEL
			//ascenseur passe en mouvement
			etatAscenseur=true;
			
			//calcul nombre etage à parcourir
			nbEtageAparcourir = tabAppelAtraiter.get(0).getDestAppel() - positionActuelle;
			nbEtageAparcourir = Math.abs(nbEtageAparcourir);
			System.out.println("Ascenseur "+ idAscenseur +" :Etage " + positionActuelle);
			//calcul temps du parcours
			if(nbEtageAparcourir >= 4){
				Thread.sleep(((10+nbEtageAparcourir)*1000)/xtemps);
			}
			else if(nbEtageAparcourir == 3){
				Thread.sleep((8*1000)/xtemps);
			}
			else if(nbEtageAparcourir == 2){
				Thread.sleep((6*1000)/xtemps);
			}
			else if(nbEtageAparcourir == 1){
				Thread.sleep((3*1000)/xtemps);
			}
			
			//changement de l'etage
			positionActuelle=tabAppelAtraiter.get(0).getDestAppel();
			System.out.println("Ascenseur "+ idAscenseur +" :Etage " + positionActuelle);
			
			//ascenseur passe à l'arret
			etatAscenseur=false;
			
			//les appels correspondant à cet etages passe en traité
			traitementAppel();	
			System.out.println("Ascenseur "+ idAscenseur +" :Appel traité!");
		}
		else{ // REPOSITIONNEMENT
			//ascenseur passe en mouvement
			etatAscenseur=true;
			System.out.println("Ascenseur "+ idAscenseur +" :Je vais me repositionner");
			//calcul nombre etage à parcourir
			nbEtageAparcourir = positionRepo - positionActuelle;
			nbEtageAparcourir = Math.abs(nbEtageAparcourir);
			
			
			//Sleep suivant le nombre d'étage à parcourir
			sleepParcours(nbEtageAparcourir);
						
			//changement de l'etage
			positionActuelle=positionRepo;
			System.out.println("Ascenseur" + idAscenseur + " :Etage Repo " + positionActuelle);
			
			//ascenseur passe à l'arret
			etatAscenseur=false;

		}
	}
	
	/**
	 * Fonction permettant de rechercher les appels correspondant à l'étage passé en paramètre
	 * @param etage
	 * @return
	 */
	public ArrayList<Appel> rechercheAppel(Integer etage){
		ArrayList<Appel> tabAppelTrouve = new ArrayList<Appel>();
		int i=0;
		while(i<tabAppelAtraiter.size()){
			 if(tabAppelAtraiter.get(i).getDestAppel()==etage)
				 tabAppelTrouve.add(tabAppelAtraiter.get(i));
			 i++;
		}
		return tabAppelTrouve;
	}
	
	/**
	 * Fonction permettant de dire que nous avons traité un appel
	 * @return
	 */
	public void traitementAppel() {
		// TODO Auto-generated method stub
		ArrayList<Appel> tabAppelAsupprimer = new ArrayList<Appel>();
		
		// Recherche les appels correspondant à l'etage actuel.
		tabAppelAsupprimer = rechercheAppel(positionActuelle);
		
		//Déplace les appels de Atraiter vers Traiter
		tabAppelsTraites.addAll(tabAppelAsupprimer);
		tabAppelAtraiter.removeAll(tabAppelAsupprimer);
	}
	
	/**
	 * Fonction permettant de trier les appels se trouvant dans AppelsAtraiter afin de les organiser dans l'ordre où l'ascenseur va s'arrêter.
	 * @return
	 */
	public void triAppel() {
		//Fonction triant les appels en attente
		
		//remplissage d'un tableau avec les destinations des appels
		for (Appel appel : this.tabAppelAtraiter) {
			tabDestination.add(appel.getDestAppel());
		}
		
		//Algorithme de tri du precedent tableau
		if(this.directionEnCours==0){ //Si on monte
			Collections.sort(tabDestination);
		}
		else{ //Si on descend
			Collections.sort(tabDestination, Collections.reverseOrder());
		}
	}
	
	/**
	 * Fonction permettant de savoir combien de temps va mettre l'ascenseur pour traiter tout les appels en cours
	 * @return
	 */
	public void calculDureeTraitementAppel(){
		int i=0;
		int nbEtageAparcourir;
		tempsParcoursAscenseur=0;
		
		// pour tout les appels sauf le dernier car pris dans la comparaison
		for(i=0;i<tabAppelAtraiter.size();i++){
			
			//Calcul le nombre d'etage a parcourir entre deux appels consécutifs
			nbEtageAparcourir = tabAppelAtraiter.get(i+1).getDestAppel()-tabAppelAtraiter.get(i).getDestAppel();
			nbEtageAparcourir = Math.abs(nbEtageAparcourir);
			
			// Calcul du temps à attendre entre le parcours de ces deux etages cumulé avec l'ancien
			if(nbEtageAparcourir >= 4){
				tempsParcoursAscenseur = tempsParcoursAscenseur + ((10+nbEtageAparcourir)*1000)/xtemps;
			}
			else if(nbEtageAparcourir == 3){
				tempsParcoursAscenseur = tempsParcoursAscenseur + ((8*1000)/xtemps);
			}
			else if(nbEtageAparcourir == 2){
				tempsParcoursAscenseur = tempsParcoursAscenseur + ((6*1000)/xtemps);
			}
			else if(nbEtageAparcourir == 1){
				tempsParcoursAscenseur = tempsParcoursAscenseur + ((3*1000)/xtemps);
			}
		}
	}
	
	/**
	 * Fonctione permettant de gerer le temps de parcours entre les etages via des sleep
	 * @param nbEtageAparcourir
	 * @throws InterruptedException
	 * @return
	 */
	public void sleepParcours(int nbEtageAparcourir) throws InterruptedException{
		int nbEtage = nbEtageAparcourir;
		if(nbEtage >= 4){
			Thread.sleep(((10+nbEtage)*1000)/xtemps);
		}
		else if(nbEtage == 3){
			Thread.sleep((8*1000)/xtemps);
		}
		else if(nbEtage == 2){
			Thread.sleep((6*1000)/xtemps);
		}
		else if(nbEtage == 1){
			Thread.sleep((3*1000)/xtemps);
		}
	}
	
	/**
	 * fonction permettant d'ajouter un appel dans le tableau d'appels à traiter	
	 * @param unAppel
	 */
	public void addAppel(Appel unAppel)
	{
		this.tabAppelAtraiter.add(unAppel);
	}
}
