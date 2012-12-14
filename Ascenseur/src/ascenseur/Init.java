package ascenseur;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
/**
 * 
 * @author benji13
 * 
 * Classe permettant d'initialiser et instancier nos classes 
 *
 */

public class Init {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Simulation en mode manuel -- en mode Semaine ");
	
		Calendar cal1 = Calendar.getInstance(); cal1.set(2012, 01, 15, 15, 00, 22);
		Calendar cal2 = Calendar.getInstance(); cal2.set(2012, 01, 15, 15, 30, 22);
		Calendar cal3 = Calendar.getInstance(); cal3.set(2012, 01, 15, 16, 00, 22);
		Calendar cal4 = Calendar.getInstance(); cal4.set(2012, 01, 15, 16, 30, 22);
		Calendar cal5 = Calendar.getInstance(); cal5.set(2012, 01, 15, 17, 00, 22);
		Calendar cal6 = Calendar.getInstance(); cal6.set(2012, 01, 15, 17, 30, 22);
		
		Calendrier dateAppel1 = new Calendrier();dateAppel1.setDate(cal1);
		Calendrier dateAppel2 = new Calendrier();dateAppel2.setDate(cal2);
		Calendrier dateAppel3 = new Calendrier();dateAppel3.setDate(cal3);
		Calendrier dateAppel4 = new Calendrier();dateAppel4.setDate(cal4);
		Calendrier dateAppel5 = new Calendrier();dateAppel5.setDate(cal5);
		Calendrier dateAppel6 = new Calendrier();dateAppel6.setDate(cal6);
		
		
		Appel Appel1 = new Appel(1, 5, false, 11,dateAppel1, dateAppel2);
		Appel Appel2 = new Appel(2, 13, false, 2,dateAppel3, dateAppel4);
		Appel Appel3 = new Appel(3, 20, false, 21,dateAppel5, dateAppel6);
		Appel Appel4 = new Appel(4, 5, false, 11,dateAppel1, dateAppel2);
		Appel Appel5 = new Appel(5, 13, false, 2,dateAppel3, dateAppel4);
		Appel Appel6 = new Appel(6, 20, false, 21,dateAppel5, dateAppel6);
		Appel Appel7 = new Appel(7, 5, false, 11,dateAppel1, dateAppel2);
		Appel Appel8 = new Appel(8, 13, false, 2,dateAppel3, dateAppel4);
		Appel Appel9 = new Appel(9, 20, false, 21,dateAppel5, dateAppel6);
		Appel Appel10 = new Appel(10, 5, false, 11,dateAppel1, dateAppel2);
		Appel Appel11 = new Appel(11, 13, false, 2,dateAppel3, dateAppel4);
		Appel Appel12 = new Appel(12, 20, false, 21,dateAppel5, dateAppel6);
		Appel Appel13 = new Appel(13, 5, false, 11,dateAppel1, dateAppel2);
		Appel Appel14 = new Appel(14, 13, false, 2,dateAppel3, dateAppel4);
		Appel Appel15 = new Appel(15, 20, false, 21,dateAppel5, dateAppel6);
		Appel Appel16 = new Appel(16, 5, false, 11,dateAppel1, dateAppel2);
		Appel Appel17 = new Appel(17, 13, false, 2,dateAppel3, dateAppel4);
		Appel Appel18 = new Appel(18, 20, false, 21,dateAppel5, dateAppel6);
		
		ArrayList<Appel> ListeAppelsAtraiter = new ArrayList<Appel> ();
		ListeAppelsAtraiter.add(Appel1);
		ListeAppelsAtraiter.add(Appel2);
		ListeAppelsAtraiter.add(Appel3);
		ListeAppelsAtraiter.add(Appel4);
		ListeAppelsAtraiter.add(Appel5);
		ListeAppelsAtraiter.add(Appel6);
		ListeAppelsAtraiter.add(Appel7);
		ListeAppelsAtraiter.add(Appel8);
		ListeAppelsAtraiter.add(Appel9);
		ListeAppelsAtraiter.add(Appel10);
		ListeAppelsAtraiter.add(Appel11);
		ListeAppelsAtraiter.add(Appel12);
		ListeAppelsAtraiter.add(Appel13);
		ListeAppelsAtraiter.add(Appel14);
		ListeAppelsAtraiter.add(Appel15);
		ListeAppelsAtraiter.add(Appel16);
		ListeAppelsAtraiter.add(Appel17);
		ListeAppelsAtraiter.add(Appel18);
		
		Ascenseur ascenseur1 = new Ascenseur();
		Ascenseur ascenseur2 = new Ascenseur();
		Ascenseur ascenseur3 = new Ascenseur();
		Ascenseur ascenseur4 = new Ascenseur();
		Ascenseur ascenseur5 = new Ascenseur();
		Ascenseur ascenseur6 = new Ascenseur();
		
		ArrayList<Ascenseur> tabAscenseur = new ArrayList<Ascenseur>();
		tabAscenseur.add(ascenseur1);
		tabAscenseur.add(ascenseur2);
		tabAscenseur.add(ascenseur3);
		tabAscenseur.add(ascenseur4);
		tabAscenseur.add(ascenseur5);
		tabAscenseur.add(ascenseur6);
		
		//Batterie laBatterie = new Batterie(tabAscenseur, ListeAppelsAtraiter, tabPositionJournee, tabPositionWeekEnd)
		Statistiques uneStat = new Statistiques();
		

	}

}
