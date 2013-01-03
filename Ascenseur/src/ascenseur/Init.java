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
		
		int xtemps = 1;
		
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
		
		/*
		//Création d'appel à partir des dates précedentes
		Appel Appel1 = new Appel(0, 3, date1);
		Appel Appel2 = new Appel(4, 13, date2);
		Appel Appel3 = new Appel(10, 13, date3 );
		Appel Appel4 = new Appel(13, 18, date4);
		
		//Ajoute les appel à la liste
		ArrayList<Appel> ListeAppelsAtraiter = new ArrayList<Appel> ();
		ListeAppelsAtraiter.add(Appel1);
		ListeAppelsAtraiter.add(Appel2);
		ListeAppelsAtraiter.add(Appel3);
		ListeAppelsAtraiter.add(Appel4);
		*/
		/*
		//Création des ascenseur
		Ascenseur ascenseur1 = new Ascenseur(1,0,xtemps);
		Ascenseur ascenseur2 = new Ascenseur(2,17,xtemps);
		Ascenseur ascenseur3 = new Ascenseur(3,10,xtemps);
		Ascenseur ascenseur4 = new Ascenseur(4,40,xtemps);
		Ascenseur ascenseur5 = new Ascenseur(5,40,xtemps);
		Ascenseur ascenseur6 = new Ascenseur(6,40,xtemps);
		
		//Création du tableau d'ascenseur
		ArrayList<Ascenseur> tabAscenseur = new ArrayList<Ascenseur>();
		tabAscenseur.add(ascenseur1);
		tabAscenseur.add(ascenseur2);
		tabAscenseur.add(ascenseur3);
		tabAscenseur.add(ascenseur4);
		tabAscenseur.add(ascenseur5);
		tabAscenseur.add(ascenseur6);
		*/
		
		//Création de la batterie
		System.out.println("## Création de la batterie ##");
		Batterie laBatterie = new Batterie(xtemps, true);
		
		//Simu 1er appel
		System.out.println("## Création appel N°1 ##");
		laBatterie.creationAppel(0, 5, date1);
		System.out.println(" * Source: " + laBatterie.getTabTousLesAppels().get(0).getSourceAppel());
		System.out.println(" * Destination: " + laBatterie.getTabTousLesAppels().get(0).getDestAppel());
		System.out.println("## Assigne appel N°1 ##");
		Ascenseur unAscenseur = laBatterie.assignerAppel();
		System.out.println(" * Ascenseur N°" + unAscenseur.getIdAscenseur() + " vient vous chercher !");
		unAscenseur.deplacement();
		laBatterie.repositionnement(unAscenseur);
		
		//Simu 2ème appel
		System.out.println("## Création appel N°2 ##");
		laBatterie.creationAppel(0, 3, date1);
		System.out.println(" * Source: " + laBatterie.getTabTousLesAppels().get(1).getSourceAppel());
		System.out.println(" * Destination: " + laBatterie.getTabTousLesAppels().get(1).getDestAppel());
		System.out.println("## Assigne appel N°2 ##");
		unAscenseur = laBatterie.assignerAppel();
		System.out.println(" * Ascenseur N°" + unAscenseur.getIdAscenseur() + " vient vous chercher !");
		unAscenseur.deplacement();
		laBatterie.repositionnement(unAscenseur);
		
		//Simu 3ème appel
		System.out.println("## Création appel N°3 ##");
		laBatterie.creationAppel(2, 5, date1);
		System.out.println(" * Source: " + laBatterie.getTabTousLesAppels().get(2).getSourceAppel());
		System.out.println(" * Destination: " + laBatterie.getTabTousLesAppels().get(2).getDestAppel());
		System.out.println("## Assigne appel N°3 ##");
		unAscenseur = laBatterie.assignerAppel();
		System.out.println(" * Ascenseur N°" + unAscenseur.getIdAscenseur() + " vient vous chercher !");
		unAscenseur.deplacement();
		laBatterie.repositionnement(unAscenseur);
		
		//Simu 4ème appel
		System.out.println("## Création appel N°4 ##");
		laBatterie.creationAppel(3, 5, date1);
		System.out.println(" * Source: " + laBatterie.getTabTousLesAppels().get(3).getSourceAppel());
		System.out.println(" * Destination: " + laBatterie.getTabTousLesAppels().get(3).getDestAppel());
		System.out.println("## Assigne appel N°4 ##");
		unAscenseur = laBatterie.assignerAppel();
		System.out.println(" * Ascenseur N°" + unAscenseur.getIdAscenseur() + " vient vous chercher !");
		unAscenseur.deplacement();
		laBatterie.repositionnement(unAscenseur);
		
		/*
		System.out.println("Assignement des 4 appels...");
		//Assigne 2 appels à ascenseur 1
		//ascenseur1.addAppel(Appel1);
		//ascenseur1.setMonte(true);
		//ascenseur1.addAppel(Appel2);
		//ascenseur1.addAppel(Appel3);
		//ascenseur1.addAppel(Appel4);
		
		
		//Tri des appels
		System.out.println("Tri des appels...");
		ascenseur1.triAppel();
		System.out.println("Ascenseur 1: Ma liste d'appel " + ascenseur1.getTabDestination());
		
		
		//Création du calendrier
		Calendrier monCalendrier = new Calendrier(xtemps, true);
				
		System.out.println("Traitement des appels en cours...");
		//Traitement des appels
		monCalendrier.getChrono().start();
		System.out.println("Heure debut : " + monCalendrier.calculDateActuelle());
		ascenseur1.deplacement();
		ascenseur1.setMonte(false);
		ascenseur1.deplacement();
	//	ascenseur1.deplacement();
	//	ascenseur1.deplacement();
	//	ascenseur1.deplacement();
	//	ascenseur1.deplacement();
	//	ascenseur1.setMonte(false);
	//	ascenseur1.deplacement();
		monCalendrier.getChrono().stop();
		System.out.println("Heure fin : " + monCalendrier.calculDateActuelle());
		System.out.println("FIN");
		*/
	}

}
