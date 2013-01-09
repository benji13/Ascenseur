package ascenseur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @author r395381
 *
 *
 *Perso :
 *
 *Gui fen = new Gui();
 *fen.fenetreManu();
 *
 */
public class Gui implements ActionListener {
	
	JFrame fenetreChoix;
	JPanel panelAutomatique, panelManuelle;
	JButton buttonAuto, buttonManu;
	JRadioButton radioJour, radioSoir;
	ButtonGroup groupChoix;
	
	
	JFrame fenetreManu;
	JPanel panelMenu, panelGauche, panelDroit, panelAppel, panelUtilisateur, panelAscenseur, panelTableau, panelStats;
	JMenuBar menuBar;
	JMenu menu, sousMenu;
	JMenuItem menuItem;
	JButton buttonValider, buttonStats, buttonMonter, bouttonDescendre;
	JTextField ascA, ascB, ascC, ascD, ascE, ascF, choix;
	JComboBox jeSuis, jeVais;
	String[] listEtages = { "1", "2", "3", "4", "5" };
	JTable tableauFile;
	JLabel a, b, c, d, e, f, labelAppel, labelUtilisateur, labelAscenseurs, labelStats; 
	String[] colonnesAppel = {"Origine",
            "Destination",
            "Ascenseur",
            "Etat"};
	Object[][] fileAppel = {
		    {"4", "6",
		     "E", "En cours"},
		    {"12", "21",
		     "A", "En cours"},
		    {"34", "33",
		     "C", "En attente"},
		    {"3", "23",
		     "F", "En attente"},
		    {"31", "17",
		     "F", "En attente"}
		};
	
	

	/**
	 * 
	 * Fenetre initial et constructeur de la classe GUI
	 * 
	 */
	public Gui(){
		
		fenetreChoix = new JFrame("M²B²T - Choix du Mode");
		fenetreChoix.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelAutomatique = new JPanel();
		panelManuelle = new JPanel();
		buttonAuto = new JButton("Automatique");
		buttonManu = new JButton("Manuelle");
		buttonManu.addActionListener(this);
		radioJour = new JRadioButton("Journée");
		radioSoir = new JRadioButton("Soir et Week-end");
		groupChoix = new ButtonGroup();
		
		groupChoix.add(radioJour);
		groupChoix.add(radioSoir);
		radioJour.setSelected(true);
		
		fenetreChoix.setLayout(new GridLayout(1,2));
		panelManuelle.setLayout(new GridLayout(3,1));
		
		panelAutomatique.add(buttonAuto);
		
		panelAutomatique.setBorder(BorderFactory.createLineBorder(Color.black));
		panelManuelle.setBorder(BorderFactory.createLineBorder(Color.black));

		
		panelManuelle.add(buttonManu);
		panelManuelle.add(radioJour);
		panelManuelle.add(radioSoir);
		
		fenetreChoix.add(panelAutomatique);
		fenetreChoix.add(panelManuelle);
		
		fenetreChoix.pack();
		fenetreChoix.setVisible(true);
		
	}
	
	
	
	/**
	 * 
	 * Fenetre manuelle
	 * 
	 */
	public void fenetreManu(){
		
		fenetreManu = new JFrame("M²B²T - Gestion d'ascenseurs");
		fenetreManu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreChoix.setLayout(new BorderLayout());
		

		/**
		 * 
		 * Labels de l'interface
		 * 
		 */
		a = new JLabel("A");
		b = new JLabel("B");
		c = new JLabel("C");
		d = new JLabel("D");
		e = new JLabel("E");
		f = new JLabel("F");
		labelAppel = new JLabel("Appel");
		labelUtilisateur = new JLabel("Utilisateur");
		labelAscenseurs= new JLabel("Ascenseurs");
		labelStats = new JLabel("Statistiques");
		
		/**
		 * Declaration des panels
		 */
		panelMenu = new JPanel();
		panelGauche = new JPanel();
		panelDroit = new JPanel();
		panelAppel = new JPanel();
		panelUtilisateur = new JPanel();
		panelAscenseur = new JPanel();
		panelTableau = new JPanel();
		panelStats = new JPanel();
		panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.PAGE_AXIS));
		panelDroit.setLayout(new BoxLayout(panelDroit, BoxLayout.PAGE_AXIS));
		panelAppel.setLayout(new BoxLayout(panelAppel, BoxLayout.PAGE_AXIS));
		panelUtilisateur.setLayout(new BoxLayout(panelUtilisateur, BoxLayout.PAGE_AXIS));
		
		
		/**
		 * Barre de menu superieur
		 */
		menuBar = new JMenuBar();
		menu = new JMenu("Reset");
		menuBar.add(menu);
		menuItem = new JMenuItem("Journée");
		menu.add(menuItem);
		menuItem = new JMenuItem("Soir et Week-end");
		menu.add(menuItem);
		fenetreManu.setJMenuBar(menuBar);
		menu = new JMenu("A propos");
		menuBar.add(menu);
		menu = new JMenu("Quitter");
		menuBar.add(menu);
		
		/**
		 * Panel de gauche pour choisir monter ou descendre
		 */
		
		jeSuis = new JComboBox(listEtages);
		buttonMonter = new JButton("^^ Monter");
		bouttonDescendre = new JButton("vv Descendre");
		choix = new JTextField("D");
		
		panelAppel.add(labelAppel);
		panelAppel.add(jeSuis);
		panelAppel.add(buttonMonter);
		panelAppel.add(choix);
		panelAppel.add(bouttonDescendre);
		panelGauche.add(panelAppel);
		
		
		
		jeVais = new JComboBox(listEtages);
		buttonValider = new JButton("Valider");
		buttonValider.addActionListener(this);
		panelAppel.add(labelUtilisateur);
		panelUtilisateur.add(jeVais);
		panelUtilisateur.add(buttonValider);
		panelGauche.add(panelUtilisateur);
		
		
		/**
		 * Panel du haut avec l'etat des ascenseurs
		 */
		
		panelAscenseur = new JPanel();	
		ascA = new JTextField();
		ascB = new JTextField();
		ascC = new JTextField();
		ascD = new JTextField();
		ascE = new JTextField();
		ascF = new JTextField();
		
		panelAscenseur.add(labelAscenseurs);
		panelAscenseur.add(a);
		panelAscenseur.add(ascA);
		panelAscenseur.add(b);
		panelAscenseur.add(ascB);
		panelAscenseur.add(c);
		panelAscenseur.add(ascC);
		panelAscenseur.add(d);
		panelAscenseur.add(ascD);
		panelAscenseur.add(e);
		panelAscenseur.add(ascE);
		panelAscenseur.add(f);
		panelAscenseur.add(ascF);
		
		panelDroit.add(panelAscenseur);
		
		/**
		 * Panel milieu-droit contenant le tableau 
		 */
		panelTableau = new JPanel();
		
		panelDroit.add(panelTableau);
		tableauFile = new JTable(fileAppel, colonnesAppel);
		panelDroit.add(tableauFile);
		
		/**
		 * Panel du bas contenant le bouton d'acces aux stats
		 */
		
		panelStats = new JPanel();
		buttonStats = new JButton("Acceder aux statistiques");
		panelStats.add(labelStats);
		panelStats.add(buttonStats);
		panelDroit.add(panelStats);
		
		/**
		 * Ajout des differents panels à la fenetre principale 
		 */
		fenetreManu.add(panelGauche, BorderLayout.LINE_START);
		fenetreManu.add(panelDroit, BorderLayout.LINE_END);
		
		fenetreManu.pack();				

	}
	
	/**
	 * 
	 * Fenetre des statistiques
	 * 
	 */
	public void fenetreStats(){
		
	}
	
	/**
	 * 
	 * Methode permettant de rafraichir tous les élements d'interface
	 * 
	 */
	public void refreshGui(){

	}
	
	/**
	 * 
	 * Methode permettant de récuperer la position actuelle des 6 ascenceurs
	 *  
	 */
	public void getPositionAsc(){
		
	}
	
	/**
	 * 
	 * Methode permettant de recuperer la liste des appels
	 * 
	 */
	public void getListeAppels(){
		
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == buttonManu){
			  fenetreManu.setVisible(true);
			  fenetreChoix.setVisible(false);
		  }
		
		
		  if(arg0.getSource() == buttonValider){
			  int js, jv;
			  Date appelDate = new Date();
			  
			  js = jeSuis.getSelectedIndex();
			  jv = jeVais.getSelectedIndex();
			 			  
			  System.out.println("appelle depuis "+js+" je vais à"+jv);
			  
			  //creationAppel(js, jv, appelDate);

		  }
	}  
}



