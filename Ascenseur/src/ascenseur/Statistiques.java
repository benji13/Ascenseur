package ascenseur;

import java.util.ArrayList;
import java.util.Calendar;

public class Statistiques {
	
	 ArrayList <Integer> tabnbAppel;
	 ArrayList <Integer> tabConsoMoyenne;
	 ArrayList <Integer> consoTotal;
	 ArrayList <Integer> nbAppelTotal;
	 
	 
	 int calculTotalDuree(ArrayList <Appel> tabAppel){
		int total = 0;
		int i,duree;
		
		for(i=0;i<tabAppel.size();i++)
		{
			duree = (int) (tabAppel.get(i).getDateFin().getDate().getTimeInMillis() - tabAppel.get(i).getDateDebut().getDate().getTimeInMillis());
			total+=duree;
		}
		 return total;
	 }
	 
	 Calendrier calculDureeSimu(){
		 
	 }
	 
	 void calculTotalConso(ArrayList <Ascenseur> tabAscenseur){
		 
	 }
}
