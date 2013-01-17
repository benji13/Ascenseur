package ascenseur;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Statistiques {
	
	public Statistiques(Seconde sec) {
		// TODO Auto-generated constructor stub
		this.sec = sec;
	}
	
	 ArrayList <Integer> tabnbAppel;
	 ArrayList <Integer> tabConso;
	 ArrayList <Integer> tabConsoMoyenne;
	 Integer consoTotal;
	 Integer nbAppelTotal;
	 Date totalDuree;
	 Seconde sec;
	 Integer ConsoMoyenneTotale;
	 long attenteMoyenne;
	 
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
			
		for(i=0;i<tabAscenseur.size();i++)
		{
			this.nbAppelTotal += tabAscenseur.get(i).getTabAppelsTraites().size();
		}
	 }
/**
 * 
 * @param tabAscenseur
 */
	 //Calcule le nombre d'appel par ascenseur, il met à jour le tableau tabnbAppel
	 void calculNbAppel(ArrayList <Ascenseur> tabAscenseur){
		 int i;
		 
		 for(i=0; i<tabAscenseur.size();i++)
		 {
			 this.tabnbAppel.set(i, tabAscenseur.get(i).getTabAppelsTraites().size());
		 }
	 }
/**
 * 	 
 * @param tabAscenseur
 */
	//Calcule la conso totale par ascenseur, il met à jour le tableau tabConso
	 void calculConso(ArrayList <Ascenseur> tabAscenseur){
		 int i;
		 
		 for(i=0; i<tabAscenseur.size();i++)
		 {
			 this.tabConso.set(i, tabAscenseur.get(i).getConsommation());
		 }
	 }
	 
	 void calculConsoMoyenne(ArrayList <Ascenseur> tabAscenseur){
		 int i;
		 
		 for(i=0; i<tabAscenseur.size();i++)
		 {
			 this.tabConsoMoyenne.set(i, tabAscenseur.get(i).getConsommation()/tabnbAppel.get(i));
		 }
	 }
	 
	 void calculConsoMoyenneTotale(ArrayList <Ascenseur> tabAscenseur){
		 int i;
		 
		 for(i=0; i<tabAscenseur.size();i++)
		 {
			 this.ConsoMoyenneTotale += this.tabConsoMoyenne.get(i);
		 }
	 }
	 
	 void calculAttenteMoyenne(ArrayList <Ascenseur> tabAscenseur){
		 int i;
		 long dureeUnAppel=0;
		 
		 for(i=0; i<tabAscenseur.size();i++)
		 {
			 for (Appel unAppel : tabAscenseur.get(i).getTabAppelsTraites()) {
				 dureeUnAppel = unAppel.getDateFin().getTime() - unAppel.getDateDebut().getTime();
				 this.attenteMoyenne += dureeUnAppel;
			}
			 
		 }
		 this.attenteMoyenne= this.attenteMoyenne/this.nbAppelTotal;
	 }
}
