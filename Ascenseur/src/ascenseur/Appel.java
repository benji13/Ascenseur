package ascenseur;

public class Appel {

	private Integer idAppel;
	private Integer sourceAppel;
	private Integer etatAppel;
	private Integer destAppel;
	private Calendrier dateDebut;
	private Calendrier dateFin;
	
	public Integer getDestAppel() {
		return destAppel;
	}
	
	public Calendrier getDateDebut() {
		return dateDebut;
	}
	
	public Calendrier getDateFin() {
		return dateFin;
	}
	
	public Integer getEtatAppel() {
		return etatAppel;
	}
	
	public Integer getIdAppel() {
		return idAppel;
	}
	
	public Integer getSourceAppel() {
		return sourceAppel;
	}
	
	public void setDateDebut(Calendrier dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	public void setDateFin(Calendrier dateFin) {
		this.dateFin = dateFin;
	}
	
	public void setEtatAppel(Integer etatAppel) {
		this.etatAppel = etatAppel;
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
	
	public Appel(Integer idAppel,Integer sourceAppel,Integer etatAppel, Integer destAppel,Calendrier dateDebut, Calendrier dateFin) {
		this.sourceAppel = sourceAppel;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.destAppel = destAppel;
		this.idAppel = idAppel;
		this.etatAppel = etatAppel;
	}
	
}
