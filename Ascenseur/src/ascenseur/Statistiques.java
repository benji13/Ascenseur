package ascenseur;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Statistiques {
	
	private ArrayList <Integer> tabnbAppel;
	 private ArrayList <Integer> tabConso;
	 private ArrayList <Integer> tabConsoMoyenne;
	 private Integer consoTotal = 0;
	 private Integer nbAppelTotal = 0;
	 private Date totalDuree;
	 private Seconde sec;
	 private Integer ConsoMoyenneTotale = 0;
	 private long attenteMoyenne;
	
	public Statistiques() {
		int i;
		
		tabnbAppel = new ArrayList();
		tabConso = new ArrayList();
		tabConsoMoyenne = new ArrayList();
		
//		for(i=0; i<6;i++){
//			this.tabnbAppel.set(i,0);
//			this.tabConso.set(i, 0);
//			this.tabConsoMoyenne.set(i, 0);
//		}		
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
	 public ArrayList<Integer> getTabConsoMoyenne() {
		return tabConsoMoyenne;
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
	 public void setTabConsoMoyenne(ArrayList<Integer> tabConsoMoyenne) {
		this.tabConsoMoyenne = tabConsoMoyenne;
	}
	 public Integer getConsoTotal() {
		return consoTotal;
	}
	 public Integer getNbAppelTotal() {
		return nbAppelTotal;
	}
	public ArrayList<Integer> getTabConso() {
		return tabConso;
	}
	public ArrayList<Integer> getTabnbAppel() {
		return tabnbAppel;
	}
	public Date getTotalDuree() {
		return totalDuree;
	}
	public void setConsoTotal(Integer consoTotal) {
		this.consoTotal = consoTotal;
	}
	public void setNbAppelTotal(Integer nbAppelTotal) {
		this.nbAppelTotal = nbAppelTotal;
	}
	public void setTabConso(ArrayList<Integer> tabConso) {
		this.tabConso = tabConso;
	}
	public void setTabnbAppel(ArrayList<Integer> tabnbAppel) {
		this.tabnbAppel = tabnbAppel;
	}
	public void setTotalDuree(Date totalDuree) {
		this.totalDuree = totalDuree;
	}
	
	
	void calculStatistique(Batterie laBatterie){
		calculTotalDuree(laBatterie);
		
		calculNbTotalAppel(laBatterie.getTabAscenseur());
		calculNbAppel(laBatterie.getTabAscenseur());
		
		calculAttenteMoyenne(laBatterie.getTabAscenseur());
		
		calculConso(laBatterie.getTabAscenseur());
		calculConsoMoyenneTotale(laBatterie.getTabAscenseur());
		calculConsoMoyenne(laBatterie.getTabAscenseur());
		calculTotalConso(laBatterie.getTabAscenseur());
	}
	
	/**
	 *  
	 * @param unBatterie
	 */
	 void calculTotalDuree(Batterie laBatterie){
		 long duree = laBatterie.getCal().getChrono().getActuTime();
	 }
	 
	 void calculTotalConso(ArrayList <Ascenseur> tabAscenseur){
		int i;
		
		for(i=0;i<tabAscenseur.size();i++)
		{
			this.consoTotal += tabAscenseur.get(i).getConsommation();
		}
	}
/**
 * 
 * @param tabAscenseur
 */
	 void calculNbTotalAppel(ArrayList <Ascenseur> tabAscenseur){
		int i;
			
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
			 this.tabnbAppel.add(i, asc.getTabAppelsTraites().size());
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
			 this.tabConso.add(i, asc.getConsommation());
			 i++;
		 }
	 }
	 
	 void calculConsoMoyenne(ArrayList <Ascenseur> tabAscenseur){
		 int i = 0;
		 
		 for(Ascenseur asc : tabAscenseur)
		 {
			 if(tabnbAppel.get(i) != 0){
				 this.tabConsoMoyenne.set(i, asc.getConsommation()/tabnbAppel.get(i));
			 }
			 i++;
		 }
	 }
	 
	 void calculConsoMoyenneTotale(ArrayList <Ascenseur> tabAscenseur){
		 int i = 0;
		 
		 for(Integer asc : tabConsoMoyenne)
		 {
			 this.ConsoMoyenneTotale += this.tabConsoMoyenne.get(i);
			 i++;
		 }
	 }
	 
	 void calculAttenteMoyenne(ArrayList <Ascenseur> tabAscenseur){
		 int i = 0;
		 long dureeUnAppel=0;
		 
		 for(Ascenseur asc : tabAscenseur)
		 {
			 for (Appel unAppel : asc.getTabAppelsTraites()) {
				 dureeUnAppel = unAppel.getDateFin().getTime() - unAppel.getDateDebut().getTime();
				 this.attenteMoyenne += dureeUnAppel;
				 i++;
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
