package ascenseur;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Statistiques {
	
	private int tabnbAppel[] = {0,0,0,0,0,0};
	 private int tabConso[] = {0,0,0,0,0,0};
	 private int tabConsoMoyenne[] = {0,0,0,0,0,0};
	 private Integer consoTotal = 0;
	 private Integer nbAppelTotal = 0;
	 private long totalDuree;
	 private Seconde sec;
	 private Integer ConsoMoyenneTotale = 0;
	 private long attenteMoyenne;
	 private String totalDureeString;
	
	public Statistiques() {
		int i;
		
	}
	
	public int[] getTabConsoMoyenne() {
		return tabConsoMoyenne;
	}
	
	public int[] getTabConso() {
		return tabConso;
	}
	
	public int[] getTabnbAppel() {
		return tabnbAppel;
	}
	 
	 
	 public long getAttenteMoyenne() {
		return attenteMoyenne;
	}
	 public Integer getConsoMoyenneTotale() {
		return ConsoMoyenneTotale;
	}
	 public Seconde getSec() {
		return sec;
	}
	
	 public void setAttenteMoyenne(long attenteMoyenne) {
		this.attenteMoyenne = attenteMoyenne;
	}
	 public void setConsoMoyenneTotale(Integer consoMoyenneTotale) {
		ConsoMoyenneTotale = consoMoyenneTotale;
	}
	 public void setSec(Seconde sec) {
		this.sec = sec;
	}
	 
	 public Integer getConsoTotal() {
		return consoTotal;
	}
	 public Integer getNbAppelTotal() {
		return nbAppelTotal;
	}
	
	
	public String getTotalDuree() {
		return totalDureeString;
	}
	public void setConsoTotal(Integer consoTotal) {
		this.consoTotal = consoTotal;
	}
	public void setNbAppelTotal(Integer nbAppelTotal) {
		this.nbAppelTotal = nbAppelTotal;
	}
	
	public void setTotalDuree(long totalDuree) {
		this.totalDuree = totalDuree;
	}
	
	
	void calculStatistique(Batterie laBatterie){
		calculTotalDuree(laBatterie);
		
		calculNbTotalAppel(laBatterie.getTabAscenseur());
		calculNbAppel(laBatterie.getTabAscenseur());
		
		calculAttenteMoyenne(laBatterie.getTabAscenseur());
		
		calculConso(laBatterie.getTabAscenseur());
		calculConsoMoyenne(laBatterie.getTabAscenseur());
		calculConsoMoyenneTotale(laBatterie.getTabAscenseur());
		calculTotalConso(laBatterie.getTabAscenseur());
	}
	
	/**
	 *  
	 * @param unBatterie
	 */
	 void calculTotalDuree(Batterie laBatterie){
		 
		 int jour = 0;
		 int heure = 0;
		 int min = 0;
		 int sec = 0;
		 
		 totalDuree = laBatterie.getCal().getChrono().getActuTime();
		 
		 jour = (int) Math.rint(totalDuree/86400);
	        heure = (int) (totalDuree - (jour*86400));
	        heure = (int) Math.rint(heure/3600);
	        min = (int) (totalDuree - (jour*86400) - (heure*3600));
	        min = (int) Math.rint(min/60);
	        sec = (int) (totalDuree - (jour*86400) - (heure*3600) - (min*60));

	        
	        totalDureeString = jour+":"+heure+":"+min+":"+sec;
	 }
	 
	 void calculTotalConso(ArrayList <Ascenseur> tabAscenseur){
		 consoTotal=0;
		for(Ascenseur asc : tabAscenseur)
		{
			this.consoTotal += asc.getConsommation();
		}
	}
/**
 * 
 * @param tabAscenseur
 */
	 void calculNbTotalAppel(ArrayList <Ascenseur> tabAscenseur){
		 nbAppelTotal = 0;
		for(Ascenseur asc : tabAscenseur)
		{
			this.nbAppelTotal += asc.getTabAppelsTraites().size();
		}
	 }
/**
 * 
 * @param tabAscenseur
 */
	 //Calcule le nombre d'appel par ascenseur, il met à jour le tableau tabnbAppel
	 void calculNbAppel(ArrayList <Ascenseur> tabAscenseur){
		 int i = 0;
		 
		 for(Ascenseur asc : tabAscenseur)
		 {
			 this.tabnbAppel[i] = asc.getTabAppelsTraites().size();
			 i++;
		 }
	 }
/**
 * 	 
 * @param tabAscenseur
 */
	//Calcule la conso totale par ascenseur, il met à jour le tableau tabConso
	 void calculConso(ArrayList <Ascenseur> tabAscenseur){
		 int i = 0;
		 
		 for(Ascenseur asc : tabAscenseur)
		 {
			 this.tabConso[i] = asc.getConsommation();
			 i++;
		 }
	 }
	 
	 void calculConsoMoyenne(ArrayList <Ascenseur> tabAscenseur){
		 int i = 0;
		 
		 for(Ascenseur asc : tabAscenseur)
		 {
			 if(tabnbAppel[i] != 0){
				 this.tabConsoMoyenne[i] = asc.getConsommation()/tabnbAppel[i];
			 }
			 i++;
		 }
	 }
	 
	 void calculConsoMoyenneTotale(ArrayList <Ascenseur> tabAscenseur){
		 int i;
		 ConsoMoyenneTotale = 0;
		 for(i=0;i<6;i++)
		 {
			 this.ConsoMoyenneTotale += this.tabConsoMoyenne[i];
		 }
	 }
	 
	 void calculAttenteMoyenne(ArrayList <Ascenseur> tabAscenseur){
		 long dureeUnAppel=0;
		 attenteMoyenne = 0;		 
		 
		 for(Ascenseur asc : tabAscenseur)
		 {
			 for (Appel unAppel : asc.getTabAppelsTraites()) {
				 dureeUnAppel = unAppel.getDateFin().getTimeInMillis() - unAppel.getDateDebut().getTimeInMillis();
				 dureeUnAppel = dureeUnAppel/1000;
				 this.attenteMoyenne += dureeUnAppel;
			}
			 
		 }
		 if(nbAppelTotal != 0){
			 this.attenteMoyenne = this.attenteMoyenne/this.nbAppelTotal;
		 }
		 else{
			 this.attenteMoyenne = 0;
		 }
	 }
}
