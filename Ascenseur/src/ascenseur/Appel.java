package ascenseur;

public class Appel {

	private Integer idAppel;
	private Integer sourceAppel;
	private boolean traite;//parametre � true si l'appel est trait�
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
	
	public boolean getEtatAppel() {
		return traite;
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
	
	public Appel(Integer idAppel,Integer sourceAppel,boolean traite, Integer destAppel,Calendrier dateDebut, Calendrier dateFin) {
		this.sourceAppel = sourceAppel;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.destAppel = destAppel;
		this.idAppel = idAppel;
		this.traite = traite;
	}
	//Cr�ation d'un second constructeur, car lorsque l'on cr�e un appel on ne connait pas n�c�ssairement 
	//la date de fin, ni l'id
	public Appel(Integer sourceAppel,boolean traite, Integer destAppel,Calendrier dateDebut) {
		this.sourceAppel = sourceAppel;
		this.traite = traite;
		this.destAppel = destAppel;
		this.dateDebut = dateDebut;
	}
}
