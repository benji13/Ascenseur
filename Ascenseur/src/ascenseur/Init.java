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
		Calendrier cal = new Calendrier(1,sec);
		
//		cal.start();
//		Thread.sleep(1000);
//		cal.getChrono().start();
//		
		
		new SplashInitial();
		Gui fen = new Gui(sec);
		fen.fenetreManu();
		fen.fenetreStats();
		fen.fenetreVisu();
		
		


	}

}
