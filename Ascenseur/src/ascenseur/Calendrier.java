package ascenseur;

import java.util.Calendar;
import java.util.Date;

public class Calendrier {

	// VARIABLES
	private Chronometre chrono;
	private boolean isWeek;
	private Date dateActuelle;
	private Calendar dateDebutSimu;
	private int xtemps;
	
	// GETTER SETTER
	public Chronometre getChrono() {
		return chrono;
	}
	public Date getDateActuelle() {
		return dateActuelle;
	}
	public Calendar getDateDebutSimu() {
		return dateDebutSimu;
	}
	public int getXtemps() {
		return xtemps;
	}
	public void setChrono(Chronometre chrono) {
		this.chrono = chrono;
	}
	public void setDateActuelle(Date dateActuelle) {
		this.dateActuelle = dateActuelle;
	}
	public void setDateDebutSimu(Calendar dateDebutSimu) {
		this.dateDebutSimu = dateDebutSimu;
	}
	public void setWeek(boolean isWeek) {
		this.isWeek = isWeek;
	}
	public void setXtemps(int xtemps) {
		this.xtemps = xtemps;
	}
	
	//FONCTIONS
	public void determinerPlageHoraire(){
		
	}
	/**
	 * Fonction permettant d'ajouter le temps écoulé à la date de départ de la simulation.
	 * @return
	 */
	public void calculDateActuelle(){
		// A FINIR. Ajouter des sec à la date de début de simu
		this.dateDebutSimu.add(Calendar.SECOND, (int) (chrono.getTempsEcouleSecs()*xtemps));
		this.dateActuelle = this.dateDebutSimu.getTime();
	}

}
