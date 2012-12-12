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
		// TODO Auto-generated method stub
		
		if(this.directionEnCours==0)
		{
			
		}
		else
		{
			
		}
	}
	
	private void deplacement() {
		// TODO Auto-generated method stub
		
	}
	
	private void traitementAppel() {
		// TODO Auto-generated method stub
		
	}

}
