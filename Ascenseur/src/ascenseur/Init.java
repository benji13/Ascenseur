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
		Seconde sec = new Seconde();
		Calendrier cal = new Calendrier(1, true, sec);
		
//		cal.start();
//		Thread.sleep(1000);
//		cal.getChrono().start();
//		
//		
		
		
		Gui fen = new Gui(sec);
		fen.Init();
		fen.fenetreManu();
		fen.fenetreStats();
		
		
		
		
		//fen.refreshGui();
		
		

		
		/*
		System.out.println("Simulation en mode manuel -- en mode Semaine ");
	
		//Creéation du calendrier permettant de générer la date de base.
		Calendar cal1 = Calendar.getInstance(); cal1.set(2012, 01, 15, 15, 00, 00);
		
		int xtemps = 60;
		Calendrier cal= new Calendrier(xtemps, true);
		

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
		
		
		//Création de la batterie
		System.out.println("## Création de la batterie ##");
		Batterie laBatterie = new Batterie(xtemps, true);
		
		Thread.sleep(1000);
		
		//Simu appels
		System.out.println("## Appel N°1 ##");
		laBatterie.creationAppelManu(35, 40);
		
	
		Ascenseur unAscenseur = laBatterie.assignerAppel();
		System.out.println("###################### ASCENSEUR N°" + unAscenseur.getIdAscenseur() + "######################");
		*/
		
		
		/*
		Thread.sleep(4000);
		System.out.println("## Appel N°2 ##");
		laBatterie.creationAppelManu(39, 50);
		
			
		unAscenseur = laBatterie.assignerAppel();
		System.out.println("###################### ASCENSEUR N°" + unAscenseur.getIdAscenseur() + "######################");
		Thread.sleep(4000);
		System.out.println("## Appel N°3 ##");
		laBatterie.creationAppelManu(18, -4);
		
			
		unAscenseur = laBatterie.assignerAppel();
		System.out.println("###################### ASCENSEUR N°" + unAscenseur.getIdAscenseur() + "######################");
		Thread.sleep(4000);
		System.out.println("## Appel N°4 ##");
		laBatterie.creationAppelManu(12, 6);
		
			
		unAscenseur = laBatterie.assignerAppel();
		System.out.println("###################### ASCENSEUR N°" + unAscenseur.getIdAscenseur() + "######################");
		Thread.sleep(4000);
		
			
		System.out.println("## Appel N°5 ##");
		laBatterie.creationAppelManu(40, 6);
		
			
		unAscenseur = laBatterie.assignerAppel();
		System.out.println("###################### ASCENSEUR N°" + unAscenseur.getIdAscenseur() + "######################");
		Thread.sleep(4000);
		System.out.println("## Appel N°6 ##");
		laBatterie.creationAppelManu(32, -1);
		
			
		unAscenseur = laBatterie.assignerAppel();
		System.out.println("###################### ASCENSEUR N°" + unAscenseur.getIdAscenseur() + "######################");
		Thread.sleep(4000);
		System.out.println("## Appel N°7 ##");
		laBatterie.creationAppelManu(-1, 15);
		
			
		unAscenseur = laBatterie.assignerAppel();
		System.out.println("###################### ASCENSEUR N°" + unAscenseur.getIdAscenseur() + "######################");
		Thread.sleep(4000);
		System.out.println("## Appel N°8 ##");
		laBatterie.creationAppelManu(5, 20);
		
			
		unAscenseur = laBatterie.assignerAppel();
		System.out.println("###################### ASCENSEUR N°" + unAscenseur.getIdAscenseur() + "######################");
		*/
		
		
		//laBatterie.stopSimu();

	}

}
