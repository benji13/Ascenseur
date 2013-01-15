package ascenseur;

import java.util.Date;

public class Appel {

	

	private Integer idAppel;
	private Integer sourceAppel;
	private boolean traite;//parametre à true si l'appel est traité
	private Integer destAppel;
	private Date dateDebut;
	private Date dateFin;
	private boolean sensAppel; //parametre à true s'il monte
	

/**
 * Constructeur MODE MANUEL
 * @param sourceAppel
 * @param destAppel
 * @param dateDebut
 */

//IL FAUT CHANGER LE TYPE CALENDAR PAR LE TYPE CHRONO DE MO'
	public Appel(Integer sourceAppel, Integer destAppel,Date dateDebut) {
		this.sourceAppel = sourceAppel;
		this.traite = false;
		this.destAppel = destAppel;
		this.dateDebut = dateDebut;
	}

	//constructeur par defaut utile pour le parseurDOm par exemple. 
	public Appel() {
		
	// TODO Auto-generated constructor stub
}

	public void setSensAppel(boolean sensAppel) {
		this.sensAppel = sensAppel;
	}
	public boolean isTraite() {
		return traite;
	}
	public boolean isSensAppel() {
		return sensAppel;
	}
	public void setTraite(boolean traite) {
		this.traite = traite;
	}
	public Integer getDestAppel() {
		return destAppel;
	}
	
	public Date getDateDebut() {
		return dateDebut;
	}
	
	public Date getDateFin() {
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
	
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	public void setDateFin(Date dateFin) {
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
	
	public void determineSens(){
		if (this.sourceAppel < this.destAppel)
			this.sensAppel = true;
		else
			this.sensAppel = false;
			
	}
	
	@Override
	public String toString() {
		return "Appel [idAppel=" + idAppel + ", sourceAppel=" + sourceAppel
				+ ", traite=" + traite + ", destAppel=" + destAppel
				+ ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", sensAppel=" + sensAppel + "]";
	}
}

