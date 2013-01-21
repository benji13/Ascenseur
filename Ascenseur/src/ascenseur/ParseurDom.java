/*
 * - M²B²T - ParseurDOM
 *
 * Classe qui represente le parseur DOM pour document XML
 *
 * Prend en entree un fichier xml de simulation et resort une liste d'Appel
 */

package ascenseur;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author CAMM13058701 - CAMUS Maxime
 */
public class ParseurDom {

	public ParseurDom() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @param args the command line arguments
	 */
	public static Document chargerDocument(String fichierXML) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//Le parseur va ignorer les commentaires
		factory.setIgnoringComments(true);
		//Le parseur va prendre en compte les espaces de noms, s'il y en a dans le document XML.
		factory.setNamespaceAware(true);
		//Le parseur avec lequel on va obtenir un arbre DOM (Document).
		DocumentBuilder parseur = null;
		//L'arbre DOM final qu'on va manipuler.
		Document arbreDOM = null;

		try {
			parseur = factory.newDocumentBuilder();
		} catch (ParserConfigurationException pce) {
			System.err.println(pce.getMessage());
		}

		try {
			arbreDOM = (Document) parseur.parse(fichierXML);
		} catch (SAXException se) {
			System.err.println(se.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return arbreDOM;

	}

	/**
	 * Cette methode va me permettre de manipuler l'arbre DOM et d'en extraire les appels.
	 * @param arbre l'arborescence XML.
	 * @return une liste d'Appels.
	 */
	public static ArrayList<Appel> extraireAppels(Document arbre) {
		ArrayList<Appel> listeAppels = null;
		listeAppels = new ArrayList<Appel>();

		//Obtenir les noeuds <appel>
		NodeList lesAppel = arbre.getElementsByTagName("appel");
		//Obtenir les noeuds <heure_appel>
		NodeList lesHeureAppel = arbre.getElementsByTagName("heure_appel");
		//Obtenir les noeuds <origine>
		NodeList lesOrigine = arbre.getElementsByTagName("origine");
		//Obtenir les noeuds <destination>
		NodeList lesDestination = arbre.getElementsByTagName("destination");


		//On boucle sur la collection d'appel qu'on vient d'obtenir
		for (int i = 0; i < lesAppel.getLength(); i++) {
			Appel d = new Appel();

			//A chaque etape on recupere le noeud courant (c'est un appel).
			Node docCourant = lesAppel.item(i);
			//On le cast en objet Element pour pouvoir extraire le contenu.
			Element docTmp = (Element) docCourant;

			//On extraie le type d'un appel.
			d.setIdAppel(Integer.parseInt(docTmp.getAttribute("id")));

			//On extraie la source d'un Appel.
			d.setSourceAppel(Integer.parseInt(lesOrigine.item(i).getTextContent()));

			//On extraie l'heure d'un appel :
			Calendar date;
			date = Calendar.getInstance();
			//On precise le format de la date
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
			//On récupère dans le fichier XML le champ heure_appel
			String dateXML = lesHeureAppel.item(i).getTextContent();

			try {
				//On parse le champ heure_appel selon le format prevu au dessu : hh:mm:ss
				date.setTime(simpleDateFormat.parse(dateXML));
				//System.out.println(date);
			} catch (ParseException e) {
				// Affichage des erreurs de parsing sur la String dateXML
				e.printStackTrace();
			}


			d.setDateDebut(date);

			//On extraie le destination d'un appel.
			d.setDestAppel(Integer.parseInt(lesDestination.item(i).getTextContent()));

			//On place l'appel comme non traite (ou Ã  traiter)
			d.setTraite(false);

			//On ajoute le tout a notre Arraylist
			listeAppels.add(d);
		}
		return listeAppels;
	}
}
