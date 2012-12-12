package ascenseur;

import java.util.ArrayList;
import java.util.Collections;

public class Ascenseur {

	/**
	 * @param args
	 */
	// VARIABLES
	private int idAscenseur;
	private int etatAscenseur;
	private ArrayList<Appel> tabAppelAtraiter;
	private ArrayList<Appel> tabAppelsTraites;
	private ArrayList<Integer> tabDestination;
	private int positionActuelle;
	private int nbPersonne;
	private int consommation;
	private int directionEnCours; //0 monte ; 1 descend
	
	
	// GETTER ET SETTER
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
	public int getEtatAscenseur() {
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
	public void setEtatAscenseur(int etatAscenseur) {
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
	private void triAppel() {
		//Fonction triant les appels en attente
		
		//remplissage d'un tableau avec les destinations des appels
		ArrayList<Integer> TabDest = new ArrayList<Integer>();
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
	
	private void deplacement() {
		// TODO Auto-generated method stub
		
	}
	
	private void traitementAppel() {
		// TODO Auto-generated method stub
		ArrayList<Appel> tabAppelAsupprimer = new ArrayList<Appel>();
		for (Integer i : this.tabDestination) {
			if(positionActuelle == i){
				// A FAIRE : RECHERCHER LES APPELS CORRESPONDANT A CET ETAGE 
				// LES PLACER DANS tabAppelAsupprimer
				
			}
		}
		//Déplace les appels de Atraiter vers Traiter
		tabAppelsTraites.addAll(tabAppelAsupprimer);
		tabAppelAtraiter.removeAll(tabAppelAsupprimer);
	}

}
