package ascenseur;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;


/**
 * @author benji13
 *
 *
 *Perso :
 *
 *Gui fen = new Gui();
 *fen.fenetreManu();
 *
 */
public class Gui  implements ActionListener, Observer {
	
	JFrame fenetreChoix;
	JPanel panelAutomatique, panelManuelle;
	JButton buttonAuto, buttonManu;
	JRadioButton radioJour, radioSoir;
	ButtonGroup groupChoix;
	
	
	JFrame fenetreManu, fenetreStats;
	JPanel panelMenu, panelGauche, panelDroit, panelAppel, panelUtilisateur, panelAscenseur, panelTableau, panelStats, panelHaut, panelBas, panel1, panel2, panel3;
	JMenuBar menuBar;
	JMenu menu, sousMenu;
	JMenuItem menuItem, menuResetJ, menuResetS, menuApropos, menuQuitter;
	JButton buttonValider, buttonStats, buttonMonter, bouttonDescendre;
	JTextField ascA, ascB, ascC, ascD, ascE, ascF, choix, nbr1, nbr2, nbr3, nbr4, nbr5, nbr6, conso1, conso2, conso3, conso4, conso5, conso6, ConsoMoy, ConsoTot, AttMoy, NbrAppTot, DureeSimu;
	JComboBox jeSuis, jeVais;
	Integer[] listEtages;
	Integer[] listEtagesDyna;
	JTable tableauFile;
	JLabel a, b, c, d, e, f, labelAppel, labelUtilisateur, labelAscenseurs, labelStats, labelNbre, labelConso, labelVide, labelConsoMoy, labelConsoTot, labelAttMoy, labelNbrAppTot, labelDureeSimu; 
	String[] ascenseurString = {"A","B","C","D","E","F"};
	
	Object[] colonnesAppel = {"Origine",
            "Destination",
            "Ascenseur",
            "Etat"};
	Object[] fileAppel = {"null", "null","null", "null"};
	
	DefaultTableModel model = new DefaultTableModel();
	
//	= {
//		    {"4", "6",
//		     "E", "En cours"},
//		    {"12", "21",
//		     "A", "En cours"},
//		    {"34", "33",
//		     "C", "En attente"},
//		    {"3", "23",
//		     "F", "En attente"},
//		    {"31", "17",
//		     "F", "En attente"}
//		};
	
	
	
	Batterie laBatterie;
	int xtemps;
	Calendrier cal;
	Ascenseur unAscenseur;
	Seconde sec;
	

	/**
	 * 
	 * Fenetre initial et constructeur de la classe GUI
	 * 
	 */
	public Gui(Seconde sec){

		fenetreChoix = new JFrame("M²B²T - Choix du Mode");
		fenetreChoix.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreChoix.setResizable(false);
		
		panelAutomatique = new JPanel();
		panelManuelle = new JPanel();
		buttonAuto = new JButton("Automatique");
		buttonAuto.addActionListener(this);
		buttonManu = new JButton("Manuelle");
		buttonManu.addActionListener(this);
		radioJour = new JRadioButton("Semaine");
		radioSoir = new JRadioButton("Week-end");
		groupChoix = new ButtonGroup();
		
		groupChoix.add(radioJour);
		groupChoix.add(radioSoir);
		radioJour.setSelected(true);
		
		fenetreChoix.setLayout(new GridLayout(1,2));
		panelManuelle.setLayout(new GridLayout(3,1));
		
		panelAutomatique.add(buttonAuto);
		
		panelAutomatique.setBorder(BorderFactory.createLineBorder(Color.black));
		panelAutomatique.setBorder(new TitledBorder("Mode Auto"));
		panelManuelle.setBorder(BorderFactory.createLineBorder(Color.black));
		panelManuelle.setBorder(new TitledBorder("Mode Manuelle"));
		
		panelManuelle.add(buttonManu);
		panelManuelle.add(radioJour);
		panelManuelle.add(radioSoir);
		
		fenetreChoix.add(panelAutomatique);
		fenetreChoix.add(panelManuelle);
		
		fenetreChoix.pack();
		fenetreChoix.setVisible(true);
		
		this.sec =sec;
		
	}
//	debut com
	
	public void Init() throws InterruptedException{
		Seconde sec = new Seconde();
		Calendar cal1 = Calendar.getInstance(); cal1.set(2012, 01, 15, 15, 00, 00);
		
		xtemps = 1;	
	}
	
	
	
	/**
	 * 
	 * Fenetre manuelle
	 * 
	 */
	public void fenetreManu(){
		
		int i;
		listEtagesDyna = new Integer[45];
		
		fenetreManu = new JFrame("M²B²T - Gestion d'ascenseurs");
		fenetreManu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreManu.setResizable(false);
		fenetreChoix.setLayout(new BorderLayout());
		

		/**
		 * 
		 * Labels de l'interface
		 * 
		 */
		a = new JLabel("A", SwingConstants.CENTER);
		b = new JLabel("B", SwingConstants.CENTER);
		c = new JLabel("C", SwingConstants.CENTER);
		d = new JLabel("D", SwingConstants.CENTER);
		e = new JLabel("E", SwingConstants.CENTER);
		f = new JLabel("F", SwingConstants.CENTER);
		labelAppel = new JLabel("Appel");
		labelUtilisateur = new JLabel("Utilisateur");
		labelAscenseurs= new JLabel("Ascenseurs");
		labelStats = new JLabel("Statistiques");
		
		/**
		 * Declaration des panels
		 */
		panelGauche = new JPanel();
		panelDroit = new JPanel();
		panelAppel = new JPanel();
		panelUtilisateur = new JPanel();
		//panelAscenseur = new JPanel();
		panelTableau = new JPanel();
		//panelStats = new JPanel();
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
		menuResetJ = new JMenuItem("Semaine");
		menuResetJ.addActionListener(this);
		menu.add(menuResetJ);
		menuResetS = new JMenuItem("Week-end");
		menuResetS.addActionListener(this);
		menu.add(menuResetS);
		menu = new JMenu("Autre");
		menuBar.add(menu);
		menuApropos = new JMenuItem("A propos");
		menuApropos.addActionListener(this);
		menu.add(menuApropos);
		menuQuitter = new JMenuItem	("Quitter");
		menuQuitter.addActionListener(this);
		menu.add(menuQuitter);
		fenetreManu.setJMenuBar(menuBar);
		
		/**
		 * Panel de gauche pour choisir monter ou descendre
		 */
		
		listEtages = new Integer[45];
		for(i=0;i<=44;i++){
			listEtages[i]= (i-4) ;
		}
		
		jeSuis = new JComboBox(listEtages);
		buttonMonter = new JButton("^^ Monter");
		buttonMonter.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonMonter.addActionListener(this);
		bouttonDescendre = new JButton("vv Descendre");
		bouttonDescendre.setAlignmentX(Component.CENTER_ALIGNMENT);
		bouttonDescendre.addActionListener(this);
		bouttonDescendre.addActionListener(this);
		choix = new JTextField("");
		choix.setEditable(false);
		
		//panelAppel.add(labelAppel);
		panelAppel.add(jeSuis);
		panelAppel.add(buttonMonter);
		panelAppel.add(choix);
		panelAppel.add(bouttonDescendre);
		panelGauche.add(panelAppel);
		
		panelAppel.setBorder(new TitledBorder("Appel"));
		
		
		
		jeVais = new JComboBox(listEtages);
		jeVais.setEnabled(false);
		buttonValider = new JButton("Valider");
		buttonValider.addActionListener(this);
		buttonValider.setEnabled(false);
		//panelAppel.add(labelUtilisateur);
		panelUtilisateur.add(jeVais);
		panelUtilisateur.add(buttonValider);
		panelGauche.add(panelUtilisateur);
		
		panelUtilisateur.setBorder(new TitledBorder("Utilisateur"));
		
		
		/**
		 * Panel du haut avec l'etat des ascenseurs
		 */
		
		panelAscenseur = new JPanel(new GridLayout(2,6));	
		ascA = new JTextField();
		ascB = new JTextField();
		ascC = new JTextField();
		ascD = new JTextField();
		ascE = new JTextField();
		ascF = new JTextField();
		
		ascA.setEditable(false);
		ascB.setEditable(false);
		ascC.setEditable(false);
		ascD.setEditable(false);
		ascE.setEditable(false);
		ascF.setEditable(false);
		
		//panelAscenseur.add(labelAscenseurs);
		panelAscenseur.add(a);
		panelAscenseur.add(b);
		panelAscenseur.add(c);
		panelAscenseur.add(d);
		panelAscenseur.add(e);
		panelAscenseur.add(f);
		panelAscenseur.add(ascA);
		panelAscenseur.add(ascB);
		panelAscenseur.add(ascC);
		panelAscenseur.add(ascD);
		panelAscenseur.add(ascE);
		panelAscenseur.add(ascF);
		panelAscenseur.setBorder(new TitledBorder("Ascenseurs"));
		
		panelDroit.add(panelAscenseur);
		
		/**
		 * Panel milieu-droit contenant le tableau 
		 */
		panelTableau.setBorder(new TitledBorder("Traitement des appels"));
		//tableauFile = new JTable(fileAppel, colonnesAppel);
		//model.setNumRows(4);
		model.setColumnIdentifiers(colonnesAppel);
		model.insertRow(0, fileAppel);
		tableauFile = new JTable(model);
		tableauFile.setPreferredScrollableViewportSize(new Dimension(500, 70));
		JScrollPane scrollPane = new JScrollPane(tableauFile);
		panelTableau.add(scrollPane);
		panelDroit.add(panelTableau);
		
		
		
		
		/**
		 * Panel du bas contenant le bouton d'acces aux stats
		 */
		
		panelStats = new JPanel();
		buttonStats = new JButton("Acceder aux statistiques");
		buttonStats.addActionListener(this);
		//panelStats.add(labelStats);
		panelStats.add(buttonStats);
		panelDroit.add(panelStats);
		panelStats.setBorder(new TitledBorder("Ascenseurs"));
		
		/**
		 * Ajout des differents panels   la fenetre principale 
		 */
		fenetreManu.add(panelGauche, BorderLayout.LINE_START);
		fenetreManu.add(panelDroit, BorderLayout.LINE_END);
		
		fenetreManu.pack();				

	}
	
	 public void ouvertureFichier() {
         JFileChooser ouverture = new JFileChooser(".simu");
         FileNameExtensionFilter filtre = new FileNameExtensionFilter("Fichiers de simu", "simu");	
         ouverture.setFileSelectionMode(JFileChooser.FILES_ONLY );
         ouverture.setFileFilter(filtre);
         ouverture.setAcceptAllFileFilterUsed(false);
         ouverture.showOpenDialog(null);
         File f = ouverture.getSelectedFile();
         String fichier = f.getName();
         System.out.println("Choix : " + fichier);
 }
	
	/**
	 * 
	 * Fenetre des statistiques
	 * 
	 */
	public void fenetreStats(){
		
		fenetreStats = new JFrame("MÂ²BÂ²T - Statistiques");
		fenetreStats.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		/**
		 * Declaration des panels
		 */
		panelHaut = new JPanel();
		panelHaut.setBorder(new TitledBorder("Ascenseurs" ));
		panelBas = new JPanel();
		panelBas.setBorder(new TitledBorder("Statistiques Globales"));
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		
		/**
		 * Declaration des labels
		 */
		labelAscenseurs = new JLabel("Ascenseurs");
		labelStats = new JLabel("Statistiques Globales");
		labelNbre = new JLabel("Nombre d'appels total",SwingConstants.CENTER);
		labelConso = new JLabel("Consommation moyenne",SwingConstants.CENTER);
		labelVide = new JLabel("");
		a = new JLabel("A",SwingConstants.CENTER);
		b = new JLabel("B",SwingConstants.CENTER);
		c = new JLabel("C",SwingConstants.CENTER);
		d = new JLabel("D",SwingConstants.CENTER);
		e = new JLabel("E",SwingConstants.CENTER);
		f = new JLabel("F",SwingConstants.CENTER);
		labelConsoMoy = new JLabel("Consommation Moyenne",SwingConstants.CENTER);
		labelConsoTot = new JLabel("Consommation Totale",SwingConstants.CENTER);
		labelAttMoy = new JLabel("Attente Moyenne",SwingConstants.CENTER);
		labelNbrAppTot = new JLabel("Nombre d'appels Total",SwingConstants.CENTER);
		labelDureeSimu = new JLabel("Dur e simulation",SwingConstants.CENTER);
		
		
		/**
		 * Declaration des TextFiels
		 */
		nbr1 = new JTextField();
		nbr1.setEnabled(false);
		nbr2 = new JTextField();
		nbr2.setEnabled(false);
		nbr3 = new JTextField();
		nbr3.setEnabled(false);
		nbr4 = new JTextField();
		nbr4.setEnabled(false);
		nbr5 = new JTextField();
		nbr5.setEnabled(false);
		nbr6 = new JTextField();
		nbr6.setEnabled(false);
		conso1 = new JTextField();
		conso1.setEnabled(false);
		conso2 = new JTextField();
		conso2.setEnabled(false);
		conso3 = new JTextField();
		conso3.setEnabled(false);
		conso4 = new JTextField();
		conso4.setEnabled(false);
		conso5 = new JTextField();
		conso5.setEnabled(false);
		conso6 = new JTextField();
		conso6.setEnabled(false);
		ConsoMoy = new JTextField();
		ConsoMoy.setEnabled(false);
		ConsoTot = new JTextField();
		ConsoTot.setEnabled(false);
		AttMoy = new JTextField();
		AttMoy.setEnabled(false);
		NbrAppTot = new JTextField();
		NbrAppTot.setEnabled(false);
		DureeSimu = new JTextField();
		DureeSimu.setEnabled(false);

		
		/**
		 * Panel Ascenseurs contenant un tableau
		 */
		panelHaut.setLayout(new GridLayout(3,7,20,10));
		panelHaut.add(labelVide);
		panelHaut.add(a);
		panelHaut.add(b);
		panelHaut.add(c);
		panelHaut.add(d);
		panelHaut.add(e);
		panelHaut.add(f);
		panelHaut.add(labelNbre);
		panelHaut.add(nbr1);
		panelHaut.add(nbr2);
		panelHaut.add(nbr3);
		panelHaut.add(nbr4);
		panelHaut.add(nbr5);
		panelHaut.add(nbr6);
		panelHaut.add(labelConso);
		panelHaut.add(conso1);
		panelHaut.add(conso2);
		panelHaut.add(conso3);
		panelHaut.add(conso4);
		panelHaut.add(conso5);
		panelHaut.add(conso6);
		
		/**
		 * Panel Staistiques globales contenant un tableau
		 */
		panelBas.setLayout(new GridLayout(1,3,200,0));
		panel1.setLayout(new GridLayout(4,1));
		panel2.setLayout(new GridLayout(4,1));
		panel3.setLayout(new GridLayout(4,1));
		
		panel1.add(labelConsoMoy);
		panel1.add(ConsoMoy);
		panel1.add(labelNbrAppTot);
		panel1.add(NbrAppTot);
		panelBas.add(panel1);
		panel2.add(labelConsoTot);
		panel2.add(ConsoTot);
		panel2.add(labelDureeSimu);
		panel2.add(DureeSimu);
		panelBas.add(panel2);
		panel3.add(labelAttMoy);
		panel3.add(AttMoy);
		panelBas.add(panel3);
		
		/**
		 * Ajout des differents panels   la fenetre principale 
		 */
		fenetreStats.add(panelHaut, BorderLayout.NORTH);
		fenetreStats.add(panelBas, BorderLayout.SOUTH);
		


		fenetreStats.pack();
		/**
		 * Fait disparaitre la fenetre de statistique et apparaitre la fenetre manuelle lors de la fermeture de la fenetre 
		 */
		 fenetreStats.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		    	  fenetreStats.setVisible(false);
		    	  fenetreManu.setVisible(true);
		      }
		    });
		
	}
	
	public void fenetreAuto(){
		
	}
	
	
	/**
	 * 
	 * Methode permettant de r cuperer la position actuelle des 6 ascenceurs
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
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
			
		if(arg0.getSource() == buttonManu){
			int i;
			  fenetreManu.setVisible(true);
			  fenetreChoix.setVisible(false);
			  
			  if(radioJour.isSelected()){
					laBatterie = new Batterie(xtemps, true,sec);
				}
				else if(radioSoir.isSelected()){
					laBatterie = new Batterie(xtemps, false,sec);
				}
				for (i=0;i<6;i++){
					laBatterie.getTabAscenseur().get(i).addObserver(this);
				}
		  }
		
		if(arg0.getSource() == buttonAuto){
			  ouvertureFichier();
			  fenetreManu.setVisible(true);
			  fenetreChoix.setVisible(false);
		  }
		
		  if(arg0.getSource() == buttonStats){
			  fenetreManu.setVisible(false);
			  fenetreStats.setVisible(true);
		  }
		
		if(arg0.getSource() == menuResetJ){
			laBatterie.stopSimuBrute();
			laBatterie = new Batterie(xtemps, true,sec);
		  }
		if(arg0.getSource() == menuResetS){
			laBatterie.stopSimuBrute();
			laBatterie = new Batterie(xtemps, false,sec);
		  }
		if(arg0.getSource() == menuApropos){
			JDialog aPropos = new JDialog(fenetreManu,"A propos",true);
			JPanel aProposg = new JPanel();
			JPanel aProposd = new JPanel();
			aProposd.setLayout(new GridLayout(4,1));
			ImageIcon antIcon = new ImageIcon("ascenseur.gif");
			JLabel LImage = new JLabel(antIcon);
			aProposg.add(LImage);
			aProposd.add(new JLabel("Groupe I - MÂ²BÂ²T"));
			aProposd.add(new JLabel("Camus - Courvoisier - Ndiaye - Olivier - Pollet-Villard"));
			aProposd.add(new JLabel("Projet Ascenseur V1.0"));
			aProposd.add(new JLabel("Janvier 2013"));
			aPropos.add(aProposg,BorderLayout.WEST);
			aPropos.add(aProposd,BorderLayout.EAST);
			aPropos.pack();
			aPropos.setVisible(true);
			
			aPropos.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		  }
		if(arg0.getSource() == menuQuitter){
			System.exit(0);
		  }

		
		if(arg0.getSource() == bouttonDescendre){
			int i;
			jeSuis.setEnabled(false);
			jeVais.setEnabled(true);
			buttonValider.setEnabled(true);
			buttonMonter.setEnabled(false);
			bouttonDescendre.setEnabled(false);
			
			for(i=0;i<jeSuis.getSelectedIndex();i++){
				listEtagesDyna[i]= (i-4) ;
			}
			jeVais.removeAllItems();
			
			for(i=0;i<jeSuis.getSelectedIndex();i++){
				jeVais.addItem(listEtagesDyna[i]);
			}
			
		  }
		
		if(arg0.getSource() == buttonMonter){
			int i;
			jeSuis.setEnabled(false);
			jeVais.setEnabled(true);
			buttonValider.setEnabled(true);
			buttonMonter.setEnabled(false);
			bouttonDescendre.setEnabled(false);
			
			for(i=jeSuis.getSelectedIndex()+1;i<=44;i++){
				listEtagesDyna[i]= (i-4) ;
			}
			
			jeVais.removeAllItems();
			
			for(i=jeSuis.getSelectedIndex()+1;i<=44;i++){
				jeVais.addItem(listEtagesDyna[i]);
			}
			
		  }
		
		
		  if(arg0.getSource() == buttonValider){
			  int js, jv;
			  Date appelDate = new Date();
			  
			  js = (Integer) jeSuis.getSelectedItem();
			  jv = (Integer) jeVais.getSelectedItem();
			 			  
			  System.out.println("appel depuis "+js+" je vais   "+jv);
			  jeSuis.setEnabled(true);  
			  jeVais.setEnabled(false);
			  buttonValider.setEnabled(false);
			  buttonMonter.setEnabled(true);
			  bouttonDescendre.setEnabled(true);
			  laBatterie.creationAppelManu(js, jv);
			try {
				unAscenseur = laBatterie.assignerAppel();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			choix.setText(ascenseurString[unAscenseur.getIdAscenseur()]);
			

					
		  }
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg1.equals("repo")){
			
			ascA.setText(""+laBatterie.getTabAscenseur().get(0).getPositionActuelle());
			ascB.setText(""+laBatterie.getTabAscenseur().get(1).getPositionActuelle());
			ascC.setText(""+laBatterie.getTabAscenseur().get(2).getPositionActuelle());
			ascD.setText(""+laBatterie.getTabAscenseur().get(3).getPositionActuelle());
			ascE.setText(""+laBatterie.getTabAscenseur().get(4).getPositionActuelle());
			ascF.setText(""+laBatterie.getTabAscenseur().get(5).getPositionActuelle());
		}
		
		if(arg1.equals("deplacement")){
			ascA.setText(""+laBatterie.getTabAscenseur().get(0).getPositionActuelle());
			ascB.setText(""+laBatterie.getTabAscenseur().get(1).getPositionActuelle());
			ascC.setText(""+laBatterie.getTabAscenseur().get(2).getPositionActuelle());
			ascD.setText(""+laBatterie.getTabAscenseur().get(3).getPositionActuelle());
			ascE.setText(""+laBatterie.getTabAscenseur().get(4).getPositionActuelle());
			ascF.setText(""+laBatterie.getTabAscenseur().get(5).getPositionActuelle());
		}
		
		if(arg1.equals("listAppel")){
			
//			
//			fileAppel[0] = laBatterie.getTabAscenseur().get(0).getTabAppelAtraiter().get(0).getSourceAppel();
//			fileAppel[1] = laBatterie.getTabAscenseur().get(0).getTabAppelAtraiter().get(0).getDestAppel();
//			fileAppel[2] = laBatterie.getTabAscenseur().get(0).getIdAscenseur();
//			fileAppel[3] = laBatterie.getTabAscenseur().get(0).getTabAppelAtraiter().get(0).getEtatAppel();
//			
			//tableauFile.removeAll();
//			model.removeRow(0);
//			model.insertRow(0, fileAppel);
//			
//			
//			System.out.println(laBatterie.getTabAscenseur().get(0).getTabAppelAtraiter().get(0).getSourceAppel());
//			System.out.println(laBatterie.getTabAscenseur().get(0).getTabAppelAtraiter().get(0).getDestAppel());
//			System.out.println(laBatterie.getTabAscenseur().get(0).getIdAscenseur());
//			System.out.println(laBatterie.getTabAscenseur().get(0).getTabAppelAtraiter().get(0).getEtatAppel());
//			
//			int i, j;
//			
//			for(i=0;i<model.getRowCount();i++){
//				model.removeRow(i);
//			}
//			
//			
//			for(i=0;i<6;i++){
//				if(laBatterie.getTabAscenseur().get(i).getTabAppelsTraites().size() != 0){
//					for(j=0;j<laBatterie.getTabAscenseur().get(i).getTabAppelsTraites().size();j++){
//						fileAppel[0] = laBatterie.getTabAscenseur().get(i).getTabAppelsTraites().get(j).getSourceAppel();
//						fileAppel[1] = laBatterie.getTabAscenseur().get(i).getTabAppelsTraites().get(j).getDestAppel();
//						fileAppel[2] = laBatterie.getTabAscenseur().get(i).getIdAscenseur();
//						fileAppel[3] = laBatterie.getTabAscenseur().get(i).getTabAppelsTraites().get(j).getEtatAppel();
//						model.insertRow((i*6)+j, fileAppel);
//					}
//				}
//			}
		
		}
		
	}

}