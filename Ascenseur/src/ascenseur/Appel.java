package ascenseur;

import java.util.Calendar;

public class Appel {

	private Integer idAppel;
	private Integer sourceAppel;
	private boolean traite;//parametre à true si l'appel est traité
	private Integer destAppel;
	private Calendar dateDebut;
	private Calendar dateFin;
	

/**
 * Constructeur MODE MANUEL
 * @param sourceAppel
 * @param destAppel
 * @param dateDebut
 */

//IL FAUT CHANGER LE TYPE CALENDAR PAR LE TYPE CHRONO DE MO'
	public Appel(Integer sourceAppel, Integer destAppel,Calendar dateDebut) {
		this.sourceAppel = sourceAppel;
		this.traite = false;
		this.destAppel = destAppel;
		this.dateDebut = dateDebut;
	}


	public Integer getDestAppel() {
		return destAppel;
	}
	
	public Calendar getDateDebut() {
		return dateDebut;
	}
	
	public Calendar getDateFin() {
		return dateFin;
	}
	
	public boolean getEtatAppel() {
		return traite;
	}
	
	public Integer getIdAppel() {
		return idAppel;
	}
	
	public Integer getSourceAppel() {
		return sourceAppel;
	}
	
	public void setDateDebut(Calendar dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	public void setDateFin(Calendar dateFin) {
		this.dateFin = dateFin;
	}
	
	public void setEtatAppel(boolean etatAppel) {
		this.traite = etatAppel;
	}
	
	public void setIdAppel(Integer idAppel) {
		this.idAppel = idAppel;
	}
	
	public void setSourceAppel(Integer sourceAppel) {
		this.sourceAppel = sourceAppel;
	}
	
	public void setDestAppel(Integer destAppel) {
		this.destAppel = destAppel;
	}
}

