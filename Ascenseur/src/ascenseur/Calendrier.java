package ascenseur;

import java.util.Calendar;
import java.util.Date;

public class Calendrier {

	public Calendrier(int xtemps, boolean isWeek) throws InterruptedException {
		// TODO Auto-generated constructor stub
		this.chrono = new Chronometre(xtemps);
		this.isWeek = isWeek;
		this.dateActuelle = new Date(2012, 01, 15, 00, 00,00);
		this.dateDebutSimu = Calendar.getInstance();
		this.dateDebutSimu.set(2012, 01, 15, 00, 00,00);
		this.xtemps = xtemps;
		
		chrono.start();
	}
	
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
	public void setChrono(Chronometre chrono) {
		this.chrono = chrono;
	}
	
	public boolean isWeek() {
		return isWeek;
	}
	
	public void setWeek(boolean isWeek) {
		this.isWeek = isWeek;
	}
	
	public Date getDateActuelle() {
		 calculDateActuelle();
		 return this.dateActuelle;
	}
	
	public Calendar getDateDebutSimu() {
		return dateDebutSimu;
	}
	
	public int getXtemps() {
		return xtemps;
	}
	
	public void setDateActuelle(Date dateActuelle) {
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
		isWeek = true;
		if(getDateActuelle().getHours() > 18 && getDateActuelle().getHours() < 7 ){
			isWeek = false;
		}
	}
	/**
	 * Fonction permettant d'ajouter le temps écoulé à la date de départ de la simulation pour savoir la date actuelle
	 * @return
	 */
	public void calculDateActuelle(){
		Calendar temp = Calendar.getInstance();
		temp = this.dateDebutSimu;
		temp.add(Calendar.SECOND, (int) (this.chrono.getTempsEcouleSecs()));
		this.dateActuelle = temp.getTime();
	}

	public void afficherHeure() throws InterruptedException{
		for(;;){
			System.out.println(getDateActuelle());
			Thread.sleep(1000/xtemps);
		}
	}
}
