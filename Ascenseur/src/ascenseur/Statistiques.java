package ascenseur;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Statistiques {
	
	 ArrayList <Integer> tabnbAppel;
	 ArrayList <Integer> tabConso;
	 Integer consoTotal;
	 Integer nbAppelTotal;
	 Date totalDuree;
	 
	 void calculTotalDuree(Batterie unBatterie){
	
	 }
	 
	 void calculTotalConso(ArrayList <Ascenseur> tabAscenseur){
		int i;
		
		for(i=0;i<tabAscenseur.size();i++)
		{
			this.consoTotal += tabAscenseur.get(i).getConsommation();
		}
	}

	 void calculNbTotalAppel(ArrayList <Ascenseur> tabAscenseur){
		int i;
			
		for(i=0;i<tabAscenseur.size();i++)
		{
			this.nbAppelTotal += tabAscenseur.get(i).getTabAppelsTraites().size();
		}
	 }

	 //Calcule le nombre d'appel par ascenseur, il met à jour le tableau tabnbAppel
	 void calculNbAppel(ArrayList <Ascenseur> tabAscenseur){
		 int i;
		 
		 for(i=0; i<tabAscenseur.size();i++)
		 {
			 this.tabnbAppel.set(i, tabAscenseur.get(i).getTabAppelsTraites().size());
		 }
	 }
	 
	//Calcule la conso totale par ascenseur, il met à jour le tableau tabConso
	 void calculConso(ArrayList <Ascenseur> tabAscenseur){
		 int i;
		 
		 for(i=0; i<tabAscenseur.size();i++)
		 {
			 this.tabConso.set(i, tabAscenseur.get(i).getConsommation());
		 }
	 }
}
