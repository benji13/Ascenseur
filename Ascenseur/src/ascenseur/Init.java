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
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Simulation en mode manuel -- en mode Semaine ");
	
		//Creéation du calendrier permettant de générer la date de base.
		Calendar cal1 = Calendar.getInstance(); cal1.set(2012, 01, 15, 15, 00, 00);
		
		//Création de plusieurs dates
		Date date1 = cal1.getTime();
		System.out.println("Date 1: " + date1);
		cal1.add(Calendar.SECOND, 1);
		Date date2 = cal1.getTime();
		System.out.println("Date 2: " + date2);
		cal1.add(Calendar.SECOND, 5);
		Date date3 = cal1.getTime();
		System.out.println("Date 3: " + date3);
		cal1.add(Calendar.SECOND, 2);
		Date date4 = cal1.getTime();
		System.out.println("Date 4: " +date4);
		
		
		//Création d'appel à partir des dates précedentes
		Appel Appel1 = new Appel(1, 5, date1);
		Appel Appel2 = new Appel(4, 13, date2);
		Appel Appel3 = new Appel(17, 2, date3 );
		Appel Appel4 = new Appel(8, 5, date4);
		
		//Ajoute les appel à la liste
		ArrayList<Appel> ListeAppelsAtraiter = new ArrayList<Appel> ();
		ListeAppelsAtraiter.add(Appel1);
		ListeAppelsAtraiter.add(Appel2);
		ListeAppelsAtraiter.add(Appel3);
		ListeAppelsAtraiter.add(Appel4);
		
		//Création des ascenseur
		Ascenseur ascenseur1 = new Ascenseur(1,0,1);
		Ascenseur ascenseur2 = new Ascenseur(2,17,1);
		Ascenseur ascenseur3 = new Ascenseur(3,10,1);
		Ascenseur ascenseur4 = new Ascenseur(4,0,1);
		Ascenseur ascenseur5 = new Ascenseur(5,0,1);
		Ascenseur ascenseur6 = new Ascenseur(6,0,1);
		
		//Création du tableau d'ascenseur
		ArrayList<Ascenseur> tabAscenseur = new ArrayList<Ascenseur>();
		tabAscenseur.add(ascenseur1);
		tabAscenseur.add(ascenseur2);
		tabAscenseur.add(ascenseur3);
		tabAscenseur.add(ascenseur4);
		tabAscenseur.add(ascenseur5);
		tabAscenseur.add(ascenseur6);
		
		//Création de la batterie
		Batterie laBatterie = new Batterie(1);
		
		System.out.println("Assignement appel en cours...");
		//Assigne 2 appels à ascenseur 1
		ascenseur1.addAppel(Appel1);
		ascenseur1.setMonte(true);
		ascenseur1.addAppel(Appel2);

		//Assigne 1 appel à ascenseur 2
		ascenseur2.addAppel(Appel3);
		
		//Assigne 1 appel à ascenseur 3
		ascenseur3.addAppel(Appel4);
		System.out.println("Assignement appel OK");
		
		System.out.println("Tri des listes d'appels en cours...");
		//Tri des appels
		ascenseur1.triAppel();
		System.out.println("Ascenseur 1: Ma liste d'appel " + ascenseur1.getTabDestination());
		ascenseur2.triAppel();
		System.out.println("Ascenseur 2: Ma liste d'appel " + ascenseur2.getTabDestination());
		ascenseur3.triAppel();
		System.out.println("Ascenseur 3: Ma liste d'appel " + ascenseur3.getTabDestination());
		System.out.println("Tri des listes d'appels OK");
		
		//Création du calendrier
		Calendrier monCalendrier = new Calendrier(1);
		
		
		System.out.println("Traitement des appels en cours...");
		//Traitement des appels
		System.out.println("Heure: " + monCalendrier.calculDateActuelle());
		ascenseur1.deplacement();
		System.out.println("Heure: " + monCalendrier.calculDateActuelle());
		System.out.println("Heure: " + monCalendrier.calculDateActuelle());
		ascenseur1.deplacement();
		System.out.println("Heure: " + monCalendrier.calculDateActuelle());
		System.out.println("Heure: " + monCalendrier.calculDateActuelle());
		ascenseur2.deplacement();
		System.out.println("Heure: " + monCalendrier.calculDateActuelle());
		System.out.println("Heure: " + monCalendrier.calculDateActuelle());
		ascenseur3.deplacement();
		System.out.println("Heure: " + monCalendrier.calculDateActuelle());
		System.out.println("Traitement des appels FINI");
		System.out.println("Repositionnement en cours...");
		System.out.println("Heure: " + monCalendrier.calculDateActuelle());
		ascenseur1.deplacement();
		System.out.println("Heure: " + monCalendrier.calculDateActuelle());
		System.out.println("Heure: " + monCalendrier.calculDateActuelle());
		ascenseur2.deplacement();
		System.out.println("Heure: " + monCalendrier.calculDateActuelle());
		System.out.println("Heure: " + monCalendrier.calculDateActuelle());
		ascenseur3.deplacement();
		System.out.println("Heure: " + monCalendrier.calculDateActuelle());
		System.out.println("Repositionnement FINI");
		
	}

}
