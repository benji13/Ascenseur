package ascenseur;

import java.util.Calendar;

public class Calendrier {

	// VARIABLES
	private Chronometre chrono;
	private boolean isWeek;
	private Calendar dateActuelle;
	private Calendar dateDebutSimu;
	private int xtemps;
	
	// GETTER SETTER
	public Chronometre getChrono() {
		return chrono;
	}
	public void setChrono(Chronometre chrono) {
		this.chrono = chrono;
	}
	
	public boolean isWeek() {
		return isWeek;
	}
	
	public void setWeek(boolean isWeek) {
		this.isWeek = isWeek;
	}
	
	public Calendar getDateActuelle() {
		return dateActuelle;
	}
	
	public Calendar getDateDebutSimu() {
		return dateDebutSimu;
	}
	
	public int getXtemps() {
		return xtemps;
	}
	
	public void setDateActuelle(Calendar dateActuelle) {
		this.dateActuelle = dateActuelle;
	}
	
	public void setDateDebutSimu(Calendar dateDebutSimu) {
		this.dateDebutSimu = dateDebutSimu;
	}
	
	public void setXtemps(int xtemps) {
		this.xtemps = xtemps;
	}
	//FONCTIONS
	public void determinerPlageHoraire(){
		
	}
	
	public void calculDateActuelle(){
		// A FINIR. Ajouter des sec à la date de début de simu
		this.dateActuelle = this.dateDebutSimu.add(Calendar.SECOND, (chrono.getTempsEcouleSecs()*xtemps));
	}

}
