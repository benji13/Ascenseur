package ascenseur;

import java.util.Calendar;

public class Calendrier {

	private Chronometre chrono;
	private boolean isWeek;
	private Calendar date;
	
	
	public Chronometre getChrono() {
		return chrono;
	}
	public void setChrono(Chronometre chrono) {
		this.chrono = chrono;
	}
	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
	
	public boolean isWeek() {
		return isWeek;
	}
	
	public void setWeek(boolean isWeek) {
		this.isWeek = isWeek;
	}
	
	public void determinerPlageHoraire(){

		
	}

}
