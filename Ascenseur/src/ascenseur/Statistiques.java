package ascenseur;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Statistiques {
	
	 ArrayList <Integer> tabnbAppel;
	 ArrayList <Integer> tabConsoMoyenne;
	 Integer consoTotal;
	 Integer nbAppelTotal;
	 
	 
	 Date calculTotalDuree(ArrayList <Appel> tabAppel){
		
		int total = 0;
		int i,duree;
		
		for(i=0;i<tabAppel.size();i++)
		{
			duree = (int) (tabAppel.get(i).getDateFin().getDate().getTimeInMillis() - tabAppel.get(i).getDateDebut().getDate().getTimeInMillis());
			total+=duree;
		}
		
		Date resultDate = new Date(total);
		return resultDate;
	 }
	 
	 void calculTotalConso(ArrayList <Ascenseur> tabAscenseur){
		int i;
		
		for(i=0;i<tabAscenseur.size();i++)
		{
			this.consoTotal += tabAscenseur.get(i).getConsommation();
		}
	}

	 
	 
}
