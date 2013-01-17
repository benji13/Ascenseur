package ascenseur;

/**
 * @author XiXiMe
 * 
 * Class CreatorXML : Cette class represente la methode de creation d'un arbre DOM auquel on ajoute des noeud, ordonnes de la maniere souhaitee.
 * 
 * L'algorithme ici present permet de remplir les conditions du cahier des charges sur le point des % a repescter au niveu des destinations des appels.
 * 
 * 
 */

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class CreatorXML {
	
	

	public static void creatorXML(Integer x, String context) throws Exception {

		//param
		//Integer x = 899;
		//String context;

		//On cree un arbre qui represente le document final
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser = factory.newDocumentBuilder();
		// On cree le document
		Document doc = parser.newDocument();


		/*
		 * Debut creation contenu XML
		 */

		//On cree un element (le 1er qui est unique)
		Element appels = doc.createElement("appels");
		//Ajout de cet Element au doc
		doc.appendChild(appels);


		//Variables globales
		
		int t=0, numCas, nbAppel = 0, dest, source, pas=0;
		double nb20Premier = 0, nbAutre = 0, nbRez = 0, nbPark = 0, nb5Dernier = 0, A=0, B=0, C=0, D=0, E=0;
		
		//On cree une date
		Date uneDate = new Date();
		String heureDebut = "00:00:00";
		
		//variables de context
		if (context == "Journee") {
			//On fixe le debut de la journee
			heureDebut = "08:00:00";
			//On calcul l'increment pour les secondes (24h divise par nb d'appels)
			pas = (36000000/x);//(10h/x)
			//On fixe les pourcentages, ici 5 correspond a 20%
			A=5; B=6.6; C=5; D=2.5; E=20;
		} else if (context == "JourDeWeek") {
			//On fixe le debut de la journee
			heureDebut = "08:00:00";
			//On calcul l'increment pour les secondes (24h divise par nb d'appels)
			pas = (36000000/x);	 //(10h/x)
			//On fixe les pourcentages, ici 5 correspond a 20%
			A=2.2; B=x; C=20; D=4; E=4;
		} else { //nuit
			//On fixe le debut de la journee
			heureDebut = "18:00:00";
			//On calcul l'increment pour les secondes (24h divise par nb d'appels)
			pas = (50400000/x);//(14h/x)
			//On fixe les pourcentages, ici 5 correspond a 20%
			A=2.2; 
			B=0; 
			C=20; 
			D=4; 
			E=4;
		}
		
		//On precise le format de la date
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		
		//On cree notre date a partir de l'heure fixee
		uneDate = simpleDateFormat.parse(heureDebut);
		
		
		while (nbAppel < x) {

			// 1 - On cree le noeud parent appel
			Element appel = doc.createElement("appel");
			appels.appendChild(appel);

			//On ajoute un attribut au noeud parent (appel)
			appel.setAttribute("id",String.valueOf(nbAppel));

			//System.out.println(pas);	
			if (nbAppel != 0) { //autre que le premier tour
				//On incremente la date
				uneDate = new Date(uneDate.getTime()+(pas));
			}
			//System.out.println(uneDate);
						
			
						
			//On parse le champ heure_appel selon le format prevu au dessu : HH:mm:ss (HH = format 24h et hh = 12h)
			String laDate = simpleDateFormat.format(uneDate);
			
						
			//Noeud enfant heure_appel ajoute au noeud parent (appel)
			Element heure_appel = doc.createElement("heure_appel");
			heure_appel.appendChild(doc.createTextNode(laDate));
			appel.appendChild(heure_appel);


			//Flag qui permet d'etre sur d'avoir un appel
			String ok = null;
			while (ok != "ok") {

				//On pioche entre 0 et 5 pour alterner entre les cas (type de destination)
				numCas = (0 + (int)(Math.random() * ((4 - 0) + 1)));

				switch (numCas) {
				case 0:
					//Si on a pas encore atteint les 20%
					if (nb5Dernier < (x/A)) {
						//t++;
						//System.out.println(t);
						//On calcule aleatoirement entre [35 - 40] une valeur pour la destination
						dest = (35 + (int)(Math.random() * ((40 - 35) + 1)));
						//Idem pour origine entre [-4 - 0]
						source = (-4 + (int)(Math.random() * ((0 + 4) + 1)));

						//Noeud enfant (origine) qu'on ajoute au noeud parent (appel)
						Element origine = doc.createElement("origine");
						origine.appendChild(doc.createTextNode(String.valueOf(source)));
						appel.appendChild(origine);

						//Noeud enfant destination ajoute au noeud parent (appel)
						Element destination = doc.createElement("destination");
						destination.appendChild(doc.createTextNode(String.valueOf(dest)));
						appel.appendChild(destination);

						nb5Dernier ++;
						nbAppel++;
						ok = "ok";

					}

					break;

				case 1:
					//Si on a pas encore atteint les 15%
					if (nb20Premier < (x/B)) {
						//On calcule aleatoirement entre [35 - 40] une valeur pour la destination
						dest = (0 + (int)(Math.random() * ((20 - 0) + 1)));
						//Idem pour origine entre [-4 - 0]
						source = (-4 + (int)(Math.random() * ((0 + 4) + 1)));

						//Noeud enfant (origine) qu'on ajoute au noeud parent (appel)
						Element origine = doc.createElement("origine");
						origine.appendChild(doc.createTextNode(String.valueOf(source)));
						appel.appendChild(origine);

						//Noeud enfant destination ajoute au noeud parent (appel)
						Element destination = doc.createElement("destination");
						destination.appendChild(doc.createTextNode(String.valueOf(dest)));
						appel.appendChild(destination);

						nb20Premier ++;
						nbAppel++;
						ok = "ok";
					}

					break;

				case 2:
					//Si on a pas encore atteint les 20%
					if (nbAutre < (x/C)) {
						//On calcule aleatoirement entre [35 - 40] une valeur pour la destination
						dest = (21 + (int)(Math.random() * ((34 - 21) + 1)));
						//Idem pour origine entre [-4 - 0]
						source = (-4 + (int)(Math.random() * ((0 + 4) + 1)));

						//Noeud enfant (origine) qu'on ajoute au noeud parent (appel)
						Element origine = doc.createElement("origine");
						origine.appendChild(doc.createTextNode(String.valueOf(source)));
						appel.appendChild(origine);

						//Noeud enfant destination ajoute au noeud parent (appel)
						Element destination = doc.createElement("destination");
						destination.appendChild(doc.createTextNode(String.valueOf(dest)));
						appel.appendChild(destination);

						nbAutre ++;
						nbAppel++;
						ok = "ok";
					}

					break;

				case 3:
					//Si on a pas encore atteint les 40%
					if (nbRez < (x/D)) {
						//On calcule aleatoirement entre [35 - 40] une valeur pour la destination
						dest = 0;
						//Idem pour origine entre [-4 - 0]
						source = (1 + (int)(Math.random() * ((40 - 1) + 1)));

						//Noeud enfant (origine) qu'on ajoute au noeud parent (appel)
						Element origine = doc.createElement("origine");
						origine.appendChild(doc.createTextNode(String.valueOf(source)));
						appel.appendChild(origine);

						//Noeud enfant destination ajoute au noeud parent (appel)
						Element destination = doc.createElement("destination");
						destination.appendChild(doc.createTextNode(String.valueOf(dest)));
						appel.appendChild(destination);

						nbRez ++;
						nbAppel++;
						ok = "ok";

					}

					break;

				case 4:
					//Si on a pas encore atteint les 5%
					if (nbPark < (x/E)) {
						//On calcule aleatoirement entre [35 - 40] une valeur pour la destination
						dest = (-4 + (int)(Math.random() * ((0 + 4) + 1)));
						//Idem pour origine entre [-4 - 0]
						source = (1 + (int)(Math.random() * ((40 - 1) + 1)));

						//Noeud enfant (origine) qu'on ajoute au noeud parent (appel)
						Element origine = doc.createElement("origine");
						origine.appendChild(doc.createTextNode(String.valueOf(source)));
						appel.appendChild(origine);

						//Noeud enfant destination ajoute au noeud parent (appel)
						Element destination = doc.createElement("destination");
						destination.appendChild(doc.createTextNode(String.valueOf(dest)));
						appel.appendChild(destination);

						nbPark ++;
						nbAppel++;
						ok = "ok";

					}

					break;


				}//Fin switch

			}//Fin While ok

		}//Fin While


		
		
		/*
		 * Fin creation contenu XML
		 */

		//On cree une instance de transformation
		TransformerFactory tfact = TransformerFactory.newInstance();
		//On precise que l'on veut indenter
		tfact.setAttribute("indent-number", new Integer(4));
		//On cree un objet de l'instance precedente
		Transformer transformer = tfact.newTransformer();
		//On precise l'encodage et l'indentation (retour a la ligne)
		transformer.setOutputProperty("encoding", "utf-8");
		//transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource sourceDom = new DOMSource(doc);

		//On cree le fichier xml depuis l'arbre cree
		FileWriter fw = new FileWriter("../Ascenseur//xml//XMLFile1.simu");
		StreamResult result = new StreamResult(fw);
		transformer.transform(sourceDom, result);
	}
}