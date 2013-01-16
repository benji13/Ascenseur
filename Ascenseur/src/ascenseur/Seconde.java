package ascenseur;

public class Seconde {
	 public synchronized void attenteSeconde   (int nbSec) throws InterruptedException {
		 
		 	for(int i=0;i<nbSec;i++){
//		 		System.out.println("Attente "+i+" seconde");
		 		wait () ; 
		 	}
		 }
	 public synchronized void declenchementSeconde () { /*System.out.println("Declenchement seconde");*/notifyAll () ; }

	 
}
