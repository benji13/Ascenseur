package ascenseur;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
import java.util.Hashtable;
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
public class Gui  implements ActionListener, Observer, ChangeListener {
	
	JFrame fenetreChoix;
	JPanel panelAutomatique, panelManuelle;
	JButton buttonAuto, buttonManu;
	JRadioButton radioJour, radioSoir;
	ButtonGroup groupChoix;
	
	int[] tableDateSim = {0,0,0,0};
	
	JFrame fenetreManu, fenetreStats, fenetreVisu;
	JPanel panelMenu, panelGauche, panelMilieu, panelDroit, panelAppel, panelUtilisateur, panelAscenseur, panelTableau, panelStats, panelHaut, panelBas, panelRefresh, panel1, panel2, panel3;
	JMenuBar menuBar;
	JMenu menu, sousMenu;
	JMenuItem menuItem, menuResetJ, menuApropos, menuQuitter;
	JButton buttonValider, buttonStats, buttonMonter, bouttonDescendre, bouttonVisu, bouttonRefreshStat;
	JTextField ascA, ascB, ascC, ascD, ascE, ascF, choix, nbr1, nbr2, nbr3, nbr4, nbr5, nbr6, consoTotAsc1, consoTotAsc2, consoTotAsc3, consoTotAsc4, consoTotAsc5, consoTotAsc6, conso1, conso2, conso3, conso4, conso5, conso6, ConsoMoy, ConsoTot, AttMoy, NbrAppTot, DureeSimu;
	JComboBox jeSuis, jeVais, nbrPersonne;
	Integer[] listEtages;
	Integer[] listEtagesDyna;
	Integer[] listPersonne;
	JTable tableauFile;
	JLabel a, b, c, d, e, f, labelAppel, labelUtilisateur, labelAscenseurs, labelStats, labelNbre, labelConso, labelVide, labelConsoMoy, labelConsoTot, labelAttMoy, labelConsoTotAsc, labelNbrAppTot, labelDureeSimu, labelHorloge; 
	String[] ascenseurString = {"A","B","C","D","E","F"};
	Border  blackline = BorderFactory.createLineBorder(Color.black);
	
	Object[] colonnesAppel = {"Origine",
            "Destination",
            "Ascenseur",
            "Etat"};
	Object[] fileAppel = {"null", "null","null", "null"};
	
	DefaultTableModel model = new DefaultTableModel();

	JSlider slider0, slider1, slider2, slider3, slider4, slider5, sliderAcc;
	
	static final int RDC= 0;
	static final int SOMMET = 44;
	
	Batterie laBatterie;
	int xtemps;
	Calendrier cal;
	Ascenseur unAscenseur;
	Seconde sec;
	
	
	JLabel label;
    final JTextField text;
    JButton bu ;
    JPanel p ;
    final JFrame pickDate ;
    
    Statistiques objStats;
	

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
		//panelManuelle.add(radioJour);
		//panelManuelle.add(radioSoir);
		
		label = new JLabel("Selected Date:");
        text = new JTextField(20);
        bu = new JButton("popup");
        p = new JPanel();
        pickDate = new JFrame();
        
        p.add(label);
        p.add(text);
        p.add(bu);
        bu.addActionListener(this);
        
        panelManuelle.add(p);
		
		fenetreChoix.add(panelAutomatique);
		fenetreChoix.add(panelManuelle);
		
		
		
		fenetreChoix.pack();
		fenetreChoix.setVisible(true);
		
		this.sec =sec;
		
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
		panelMilieu = new JPanel();
		panelDroit = new JPanel();
		panelAppel = new JPanel();
		panelUtilisateur = new JPanel();
		//panelAscenseur = new JPanel();
		panelTableau = new JPanel();
		//panelStats = new JPanel();
		panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.PAGE_AXIS));
		panelMilieu.setLayout(new BoxLayout(panelMilieu, BoxLayout.PAGE_AXIS));
		panelAppel.setLayout(new BoxLayout(panelAppel, BoxLayout.PAGE_AXIS));
		panelUtilisateur.setLayout(new BoxLayout(panelUtilisateur, BoxLayout.PAGE_AXIS));

		
		/**
		 * Barre de menu superieur
		 */
		menuBar = new JMenuBar();
		menu = new JMenu("Reset");
		menuBar.add(menu);
		menuResetJ = new JMenuItem("Démarrer une nouvelle simu");
		menuResetJ.addActionListener(this);
		menu.add(menuResetJ);
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
		
		listPersonne = new Integer[15];
		for(i=0;i<15;i++){
			listPersonne[i]= i+1;
		}
		
		jeSuis = new JComboBox(listEtages);
		jeSuis.setBorder(new TitledBorder("Etage d'appel"));
		buttonMonter = new JButton("^^ Monter");
		buttonMonter.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonMonter.addActionListener(this);
		bouttonDescendre = new JButton("vv Descendre");
		bouttonDescendre.setAlignmentX(Component.CENTER_ALIGNMENT);
		bouttonDescendre.addActionListener(this);
		choix = new JTextField("");
		choix.setEditable(false);
		
		//panelAppel.add(labelAppel);
		panelAppel.add(jeSuis);
		panelAppel.add(buttonMonter);
		panelAppel.add(choix);
		panelAppel.add(bouttonDescendre);
		panelGauche.add(panelAppel);
		
		panelAppel.setBorder(new TitledBorder(blackline, "Appel", TitledBorder.CENTER, 0, null));

		
		
		
		jeVais = new JComboBox(listEtages);
		jeVais.setBorder(new TitledBorder("Etage de destination"));
		jeVais.setEnabled(false);
		nbrPersonne = new JComboBox(listPersonne);
		nbrPersonne.setBorder(new TitledBorder("Nbr de personne"));
		buttonValider = new JButton("Valider");
		buttonValider.addActionListener(this);
		buttonValider.setEnabled(false);
		//panelAppel.add(labelUtilisateur);
		panelUtilisateur.add(jeVais);
		//panelUtilisateur.add(nbrPersonne);
		panelUtilisateur.add(buttonValider);
		panelGauche.add(panelUtilisateur);
		
		panelUtilisateur.setBorder(new TitledBorder(blackline, "Utilisateur", TitledBorder.CENTER, 0, null));
		
		
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
		
		panelAscenseur.setBorder(new TitledBorder(blackline, "Ascenseurs", TitledBorder.CENTER, 0, null));

		
		panelMilieu.add(panelAscenseur);
		
		/**
		 * Panel milieu contenant le tableau 
		 */
		panelTableau.setBorder(new TitledBorder(blackline, "Traitement des appels", TitledBorder.CENTER, 0, null));
		//tableauFile = new JTable(fileAppel, colonnesAppel);
		//model.setNumRows(4);
		model.setColumnIdentifiers(colonnesAppel);
		model.insertRow(0, fileAppel);
		tableauFile = new JTable(model);
		tableauFile.setEnabled(false);
		tableauFile.setPreferredScrollableViewportSize(new Dimension(500, 70));
		JScrollPane scrollPane = new JScrollPane(tableauFile);
		panelTableau.add(scrollPane);
		panelMilieu.add(panelTableau);
		
		
		/**
		 * Panel de droite contenant le l'horloge et l'acceleration
		 */
		
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put( new Integer( 1 ), new JLabel("Min") );
		labelTable.put( new Integer( 10 ), new JLabel("Max") );
		labelTable.put( new Integer( 5 ), new JLabel("5") );
		
		sliderAcc = new JSlider(JSlider.VERTICAL, 1, 10, 1);
		sliderAcc.setMajorTickSpacing(1);
		sliderAcc.setPaintTicks(true);
		sliderAcc.setLabelTable( labelTable );
		sliderAcc.setPaintLabels(true);
		sliderAcc.addChangeListener(this);
		panelDroit.add(sliderAcc);
		panelDroit.setBorder(new TitledBorder(blackline, "Accélération", TitledBorder.CENTER, 0, null));
		
		
		/**
		 * Panel du bas contenant le bouton d'acces aux stats
		 */
		
		panelStats = new JPanel();
		buttonStats = new JButton("Statistiques");
		buttonStats.addActionListener(this);
		
		bouttonVisu = new JButton("Visualisation");
		bouttonVisu.addActionListener(this);
		
		labelHorloge = new JLabel();
		//panelStats.add(labelStats);
		panelStats.add(buttonStats);
		panelStats.add(bouttonVisu);
		panelStats.add(labelHorloge);
		panelMilieu.add(panelStats);
		
		panelStats.setBorder(new TitledBorder(blackline, "Informations", TitledBorder.CENTER, 0, null));
		
		/**
		 * Ajout des differents panels   la fenetre principale 
		 */
		fenetreManu.add(panelGauche, BorderLayout.LINE_START);
		fenetreManu.add(panelMilieu, BorderLayout.CENTER);
		fenetreManu.add(panelDroit, BorderLayout.LINE_END);
		
		fenetreManu.pack();				

	}
	
	

	 
	
	/**
	 * 
	 * Fenetre des statistiques
	 * 
	 */
	public void fenetreStats(){
		
		fenetreStats = new JFrame("M²B²T - Statistiques");
		fenetreStats.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		objStats = new Statistiques();
		
		/**
		 * Declaration des panels
		 */
		panelHaut = new JPanel();
		panelHaut.setBorder(new TitledBorder("Ascenseurs" ));
		panelBas = new JPanel();
		panelBas.setBorder(new TitledBorder("Statistiques Globales"));
		panelRefresh = new JPanel();
		panelRefresh.setBorder(new TitledBorder("Refresh" ));
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		
		/**
		 * Declaration des labels
		 */
		labelAscenseurs = new JLabel("Ascenseurs");
		labelStats = new JLabel("Statistiques Globales");
		labelNbre = new JLabel("Nombre d'appels total",SwingConstants.CENTER);
		labelConsoTotAsc = new JLabel("Consommation totale",SwingConstants.CENTER);
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
		bouttonRefreshStat = new JButton("Resfresh");
		bouttonRefreshStat.addActionListener(this);
		
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
		
		consoTotAsc1 = new JTextField();
		consoTotAsc1.setEnabled(false);
		consoTotAsc2= new JTextField();
		consoTotAsc2.setEnabled(false);
		consoTotAsc3 = new JTextField();
		consoTotAsc3.setEnabled(false);
		consoTotAsc4 = new JTextField();
		consoTotAsc4.setEnabled(false);
		consoTotAsc5 = new JTextField();
		consoTotAsc5.setEnabled(false);
		consoTotAsc6 = new JTextField();
		consoTotAsc6.setEnabled(false);
		
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
		panelHaut.setLayout(new GridLayout(4,7,20,10));
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
		panelHaut.add(labelConsoTotAsc);
		panelHaut.add(consoTotAsc1);
		panelHaut.add(consoTotAsc2);
		panelHaut.add(consoTotAsc3);
		panelHaut.add(consoTotAsc4);
		panelHaut.add(consoTotAsc5);
		panelHaut.add(consoTotAsc6);
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
		
		panelRefresh.add(bouttonRefreshStat);
		
		/**
		 * Ajout des differents panels   la fenetre principale 
		 */
		fenetreStats.add(panelHaut, BorderLayout.NORTH);
		fenetreStats.add(panelBas, BorderLayout.CENTER);
		fenetreStats.add(panelRefresh, BorderLayout.SOUTH);
		


		fenetreStats.pack();
		/**
		 * Fait disparaitre la fenetre de statistique et apparaitre la fenetre manuelle lors de la fermeture de la fenetre 
		 */
		 fenetreStats.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		    	  fenetreStats.setVisible(false);
		    	  //fenetreManu.setVisible(true);
		      }
		    });
		
	}
	
	
	
	
	
	
	/**
	 * 
	 * Fenetre de visualisation
	 * 
	 */
public void fenetreVisu(){
		
		fenetreVisu = new JFrame("M²B²T - Visualisation");
		fenetreVisu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		fenetreVisu.setResizable(false);
		
		JPanel panelSlider = new JPanel();
		panelSlider.setLayout(new GridLayout(1,6));
		
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put( new Integer( 0 ), new JLabel("Sous-Sol") );
		labelTable.put( new Integer( 4 ), new JLabel("RDC") );
		labelTable.put( new Integer( SOMMET ), new JLabel("Sommet") );
		
		slider0 = new JSlider(JSlider.VERTICAL, RDC, SOMMET, 0);
		slider0.setMajorTickSpacing(1);
		slider0.setPaintTicks(true);
		slider0.setLabelTable( labelTable );
		slider0.setPaintLabels(true);
		slider0.setBorder(new TitledBorder("Ascenseur A"));
		
		
		slider1 = new JSlider(JSlider.VERTICAL, RDC, SOMMET, 0);
		slider1.setMajorTickSpacing(1);
		slider1.setPaintTicks(true);
		slider1.setLabelTable( labelTable );
		slider1.setPaintLabels(true);
		slider1.setBorder(new TitledBorder("Ascenseur B"));
		

		slider2 = new JSlider(JSlider.VERTICAL, RDC, SOMMET, 0);
		slider2.setMajorTickSpacing(1);
		slider2.setPaintTicks(true);
		slider2.setLabelTable( labelTable );
		slider2.setPaintLabels(true);
		slider2.setBorder(new TitledBorder("Ascenseur C"));
		
		slider3 = new JSlider(JSlider.VERTICAL, RDC, SOMMET, 0);
		slider3.setMajorTickSpacing(1);
		slider3.setPaintTicks(true);
		slider3.setLabelTable( labelTable );
		slider3.setPaintLabels(true);
		slider3.setBorder(new TitledBorder("Ascenseur D"));
		
		slider4 = new JSlider(JSlider.VERTICAL, RDC, SOMMET, 0);
		slider4.setMajorTickSpacing(1);
		slider4.setPaintTicks(true);
		slider4.setLabelTable( labelTable );
		slider4.setPaintLabels(true);
		slider4.setBorder(new TitledBorder("Ascenseur E"));

		
		slider5 = new JSlider(JSlider.VERTICAL, RDC, SOMMET, 0);
		slider5.setMajorTickSpacing(1);
		slider5.setPaintTicks(true);
		slider5.setLabelTable( labelTable );
		slider5.setPaintLabels(true);
		slider5.setBorder(new TitledBorder("Ascenseur F"));
		
		panelSlider.add(slider0);
		panelSlider.add(slider1);
		panelSlider.add(slider2);
		panelSlider.add(slider3);
		panelSlider.add(slider4);
		panelSlider.add(slider5);
			
		fenetreVisu.add(panelSlider);
		
		/**
		 * Fait disparaitre la fenetre de statistique et apparaitre la fenetre manuelle lors de la fermeture de la fenetre 
		 */
		fenetreVisu.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		    	  fenetreVisu.setVisible(false);
		      }
		    });
		
		fenetreVisu.pack();
		fenetreVisu.setVisible(false);
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
	
	public void fenetreAuto(){
		
	}
	
	

	
	public void Init() throws InterruptedException{
		Calendar cal1 = Calendar.getInstance(); cal1.set(2012, 01, 15, 15, 00, 00);
		
		xtemps = 1;	
	}
	
	
	public void refreshPositions(String nomAsc){
		
		
	}
	

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == bu){
			int i;
			DatePicker D = new DatePicker(pickDate);
			for(i=0;i<3;i++){
				tableDateSim[i] = D.setPickedDateTable()[i];
				System.out.println(""+tableDateSim[i]);
			}
		  }
			
		if(arg0.getSource() == buttonManu){
			int i;
			  fenetreManu.setVisible(true);
			  fenetreChoix.setVisible(false);
			  
			  if(radioJour.isSelected()){
					laBatterie = new Batterie(xtemps,sec);
				}
				else if(radioSoir.isSelected()){
					laBatterie = new Batterie(xtemps,sec);
				}
				for (i=0;i<6;i++){
					laBatterie.getTabAscenseur().get(i).addObserver(this);
				}
				
				ascA.setText(""+laBatterie.getTabAscenseur().get(0).getPositionActuelle());
				ascB.setText(""+laBatterie.getTabAscenseur().get(1).getPositionActuelle());
				ascC.setText(""+laBatterie.getTabAscenseur().get(2).getPositionActuelle());
				ascD.setText(""+laBatterie.getTabAscenseur().get(3).getPositionActuelle());
				ascE.setText(""+laBatterie.getTabAscenseur().get(4).getPositionActuelle());
				ascF.setText(""+laBatterie.getTabAscenseur().get(5).getPositionActuelle());
				slider0.setValue(laBatterie.getTabAscenseur().get(0).getPositionActuelle()+4);
				slider1.setValue(laBatterie.getTabAscenseur().get(1).getPositionActuelle()+4);
				slider2.setValue(laBatterie.getTabAscenseur().get(2).getPositionActuelle()+4);
				slider3.setValue(laBatterie.getTabAscenseur().get(3).getPositionActuelle()+4);
				slider4.setValue(laBatterie.getTabAscenseur().get(4).getPositionActuelle()+4);
				slider5.setValue(laBatterie.getTabAscenseur().get(5).getPositionActuelle()+4);
		  
				for(i=0;i<model.getRowCount();i++){
					model.removeRow(i);
				}
		}
		
		if(arg0.getSource() == buttonAuto){
			  ouvertureFichier();
			  fenetreManu.setVisible(true);
			  fenetreChoix.setVisible(false);
		  }
		
		  if(arg0.getSource() == buttonStats){
			  //fenetreManu.setVisible(false);
			  fenetreStats.setVisible(true);
		  }
		  
		  if(arg0.getSource() == bouttonVisu){
			  fenetreVisu.setVisible(true);
		  }
		
		if(arg0.getSource() == menuResetJ){
			fenetreChoix.setVisible(true);
			fenetreStats.setVisible(false);
			fenetreVisu.setVisible(false);
			laBatterie.stopSimuBrute();
			fenetreManu.dispose();
		  }
		if(arg0.getSource() == menuApropos){
			JDialog aPropos = new JDialog(fenetreManu,"A propos",true);
			JPanel aProposg = new JPanel();
			JPanel aProposd = new JPanel();
			aProposd.setLayout(new GridLayout(4,1));
			ImageIcon antIcon = new ImageIcon("ascenseur.gif");
			JLabel LImage = new JLabel(antIcon);
			aProposg.add(LImage);
			aProposd.add(new JLabel("Groupe I - M²B²T"));
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
			
			if((Integer)jeSuis.getSelectedItem()==-4){
				JOptionPane.showMessageDialog(fenetreManu,
					    "Impossible de descendre en dessous de -4",
					    "Inane warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			else{
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
			
		  }
		
		if(arg0.getSource() == buttonMonter){
			int i;
			
			if((Integer)jeSuis.getSelectedItem()==40){
				JOptionPane.showMessageDialog(fenetreManu,
					    "Impossible de monter au dessus de 40",
					    "Inane warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			else{
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
			
		  }
		
		
		  if(arg0.getSource() == buttonValider){
			  int js, jv;
			  
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
		  
		  if(arg0.getSource() == bouttonRefreshStat){
			  objStats.calculStatistique(laBatterie);
			  
			  for(Integer nbrApp : objStats.getTabnbAppel()){
				  System.out.println(""+nbrApp);
			  }
		  }
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg1.equals("horloge")){
//			Calendrier calTemp = new Calendrier();
//			Date dateTemp = new Date();
//			calTemp = laBatterie.getCal();
//			dateTemp = laBatterie.getCal().getDateActuelle().getTime();
//			labelHorloge.setText(""+calTemp.getDateActuelle().getTime());
			

		}
		
		
		if(arg1.equals("deplacement0") || arg1.equals("repo0")){
			ascA.setText(""+laBatterie.getTabAscenseur().get(0).getPositionActuelle());
			slider0.setValue(laBatterie.getTabAscenseur().get(0).getPositionActuelle()+4);
		}
		if(arg1.equals("deplacement1") || arg1.equals("repo1")){
			ascB.setText(""+laBatterie.getTabAscenseur().get(1).getPositionActuelle());
			slider1.setValue(laBatterie.getTabAscenseur().get(1).getPositionActuelle()+4);
		}
		if(arg1.equals("deplacement2") || arg1.equals("repo2")){
			ascC.setText(""+laBatterie.getTabAscenseur().get(2).getPositionActuelle());
			slider2.setValue(laBatterie.getTabAscenseur().get(2).getPositionActuelle()+4);
		}
		if(arg1.equals("deplacement3") || arg1.equals("repo3")){
			ascD.setText(""+laBatterie.getTabAscenseur().get(3).getPositionActuelle());
			slider3.setValue(laBatterie.getTabAscenseur().get(3).getPositionActuelle()+4);
		}
		if(arg1.equals("deplacement4") || arg1.equals("repo4")){
			ascE.setText(""+laBatterie.getTabAscenseur().get(4).getPositionActuelle());
			slider4.setValue(laBatterie.getTabAscenseur().get(4).getPositionActuelle()+4);
		}
		if(arg1.equals("deplacement5") || arg1.equals("repo5")){
			ascF.setText(""+laBatterie.getTabAscenseur().get(5).getPositionActuelle());
			slider5.setValue(laBatterie.getTabAscenseur().get(5).getPositionActuelle()+4);
		}
		
		if(arg1.equals("tabAppel")){
			int i;
			
			for(i=0;i<model.getRowCount();i++){
				model.removeRow(i);
			}
			
			for(i=0; i<6;i++){
				for (Appel appel : laBatterie.getTabAscenseur().get(i).tabAppelAtraiter) {
					fileAppel[0] = appel.getSourceAppel();
					fileAppel[1] = appel.getDestAppel();
					fileAppel[2] = laBatterie.getTabAscenseur().get(i).getIdAscenseur();
					fileAppel[3] = appel.getEtatAppel();
					model.addRow(fileAppel);
				}
			}
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getSource() == sliderAcc){
			System.out.println(""+sliderAcc.getValue());
			laBatterie.setXTempx(sliderAcc.getValue());
		}
		
	}

}