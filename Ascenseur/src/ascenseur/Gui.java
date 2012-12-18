package ascenseur;

import javax.swing.*;
import java.awt.*;

/**
 * @author r395381
 *
 */
public class Gui {
	
	JFrame fenetreChoix;
	JPanel panelAutomatique, panelManuelle;
	JButton buttonAuto, buttonManu;
	JRadioButton radioJour, radioSoir;
	ButtonGroup groupChoix;
	
	
	JFrame fenetreManu;
	JPanel panelMenu, panelGauche, panelDroit, paneAppel, panelUtilisateur, panelAscenseur, panelTableau, panelStats;
	JMenuBar menuBar;
	JMenu menu, sousMenu;
	JMenuItem menuItem;
	JButton buttonValider, buttonStats, buttonMonter, bouttonDescendre;
	JTextField ascA, ascB, ascC, ascD, ascE, ascF, choix;
	JComboBox jeSuis, jeVais;
	JTable tableauFile;
	//JLabel a, b, c, d, e, f; 

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
	/**
	 * 
	 */
	/**
	 * 
	 */
	public void fenetreManu(){
		/*
		JFrame fenetreManu;
		JPanel panelMenu, panelGauche, panelDroit, paneAppel, panelUtilisateur, panelAscenseur, panelTableau, panelStats;
		JMenuBar menuBar;
		JMenu menu, sousMenu;
		JMenuItem menuItem;
		JButton buttonValider, buttonStats, buttonMonter, bouttonDescendre;
		JTextField ascA, ascB, ascC, ascD, ascE, ascF, choix;
		JComboBox jeSuis, jeVais;
		JTable tableauFile;
		*/
		
		fenetreManu = new JFrame("M²B²T - Gestion d'ascenseurs");
		fenetreManu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreChoix.setLayout(new BorderLayout());
		
		
		/**
		 * Declaration des panels
		 */
		panelMenu = new JPanel();
		panelGauche = new JPanel();
		panelDroit = new JPanel();
		paneAppel = new JPanel();
		panelUtilisateur = new JPanel();
		panelAscenseur = new JPanel();
		panelTableau = new JPanel();
		panelStats = new JPanel();
		panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.PAGE_AXIS));
		paneAppel.setLayout(new BoxLayout(paneAppel, BoxLayout.PAGE_AXIS));
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
		jeSuis = new JComboBox();
		buttonMonter = new JButton("^^ Monter");
		bouttonDescendre = new JButton("vv Descendre");
		choix = new JTextField("D");
		
		paneAppel.add(jeSuis);
		paneAppel.add(buttonMonter);
		paneAppel.add(choix);
		paneAppel.add(bouttonDescendre);
		panelGauche.add(paneAppel);
		
		
		
		jeVais = new JComboBox();
		buttonValider = new JButton("Valider");
		panelUtilisateur.add(jeVais);
		panelUtilisateur.add(buttonValider);
		panelGauche.add(panelUtilisateur);
		
		
		fenetreManu.add(panelGauche, BorderLayout.LINE_START);
		
		fenetreManu.pack();
		fenetreManu.setVisible(true);
		
				
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

}
