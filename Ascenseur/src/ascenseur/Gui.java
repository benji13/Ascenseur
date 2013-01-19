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
	JPanel panelMenu, panelGauche, panelMilieu, panelDroit, panelAppel, panelUtilisateur, panelAscenseur, panelTableau, panelStats, panelHaut, panelBas, panelTitre, panelCont, panelRefresh, panel1, panel2, panel3;
	JMenuBar menuBar;
	JMenu menu, sousMenu;
	JMenuItem menuItem, menuResetJ, menuApropos, menuQuitter;
	JButton buttonValider, buttonStats, buttonMonter, bouttonDescendre, bouttonVisu, bouttonRefreshStat;
	JTextField ascA, ascB, ascC, ascD, ascE, ascF, choix, nbr1, nbr2, nbr3, nbr4, nbr5, nbr6, consoTotAsc1, consoTotAsc2, consoTotAsc3, consoTotAsc4, consoTotAsc5, consoTotAsc6, conso1, conso2, conso3, conso4, conso5, conso6, ConsoMoy, ConsoTot, AttMoy, NbrAppTot, DureeSimu;
	JComboBox jeSuis, jeVais, nbrPersonne, listeHeures;
	Integer[] listEtages;
	Integer[] listEtagesDyna;
	Integer[] listPersonne;
	JTable tableauFile;
	JLabel a, b, c, d, e, f, labelAppel, labelUtilisateur, labelAscenseurs, labelStats, labelNbre, labelConso, labelVide, labelConsoMoy, labelConsoTot, labelAttMoy, labelConsoTotAsc, labelNbrAppTot, labelDureeSimu, labelHorloge, img0, img1, img2, img3, img4, img5; 
	String[] ascenseurString = {"A","B","C","D","E","F"};
	Border  blackline = BorderFactory.createLineBorder(Color.black);
	
	Object[] colonnesAppel = {"Origine",
            "Destination",
            "Ascenseur",
            "Etat"};
	Object[] fileAppel = {"null", "null","null", "null"};
	
	DefaultTableModel model = new DefaultTableModel();
	
	ImageIcon imgStop, imgMonte, imgDescend;

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

		int i;
		
		fenetreChoix = new JFrame("M²B²T - Choix du Mode");
		fenetreChoix.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreChoix.setIconImage(Toolkit.getDefaultToolkit().getImage("images/myIcon.png"));
		fenetreChoix.setResizable(false);
		
		panelAutomatique = new JPanel();
		panelManuelle = new JPanel();
		buttonAuto = new JButton("Automatique");
		buttonAuto.addActionListener(this);
		buttonManu = new JButton("Manuel");
		buttonManu.setEnabled(false);
		buttonManu.addActionListener(this);

		fenetreChoix.setLayout(new GridLayout(1,2));
		panelManuelle.setLayout(new BoxLayout(panelManuelle,BoxLayout.Y_AXIS));
		
		panelAutomatique.add(buttonAuto);
		
		panelAutomatique.setBorder(BorderFactory.createLineBorder(Color.black));
		panelAutomatique.setBorder(new TitledBorder("Mode Auto"));
		panelManuelle.setBorder(BorderFactory.createLineBorder(Color.black));
		panelManuelle.setBorder(new TitledBorder("Mode Manuelle"));
		
		panelManuelle.add(buttonManu);

		
		label = new JLabel("Démarrer le :");
        text = new JTextField(6);
        text.setEditable(false);
        bu = new JButton("choisir");
        
        
        
        Integer[] choixHheures = new Integer[24];
		for(i=0;i<24;i++){
			choixHheures[i]= i;
		}
		listeHeures = new JComboBox(choixHheures);
		listeHeures.setSelectedIndex(12);
		
		p = new JPanel();
        p.add(label);
        p.add(text);
        p.add(bu);
        p.add(new JLabel("à"));
        p.add(listeHeures);
        p.add(new JLabel("H"));
        bu.addActionListener(this);
        
        pickDate = new JFrame();
        
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
		fenetreManu.setIconImage(Toolkit.getDefaultToolkit().getImage("images/myIcon.png"));
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
		Font newTextFieldFont=new Font(choix.getFont().getName(),Font.BOLD,40);
		choix.setForeground(new Color(255,0,0));
		choix.setFont(newTextFieldFont);
		choix.setHorizontalAlignment(JTextField.CENTER);
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
		 imgStop = new ImageIcon("images/pause.png");
		 imgMonte = new ImageIcon("images/fleche_up.png");
		 imgDescend = new ImageIcon("images/fleche_down.png");
		

		
		panelAscenseur = new JPanel(new GridLayout(3,6));	
		ascA = new JTextField();
		ascA.setHorizontalAlignment(JTextField.CENTER);
		ascB = new JTextField();
		ascB.setHorizontalAlignment(JTextField.CENTER);
		ascC = new JTextField();
		ascC.setHorizontalAlignment(JTextField.CENTER);
		ascD = new JTextField();
		ascD.setHorizontalAlignment(JTextField.CENTER);
		ascE = new JTextField();
		ascE.setHorizontalAlignment(JTextField.CENTER);
		ascF = new JTextField();
		ascF.setHorizontalAlignment(JTextField.CENTER);
		
		ascA.setEditable(false);
		ascB.setEditable(false);
		ascC.setEditable(false);
		ascD.setEditable(false);
		ascE.setEditable(false);
		ascF.setEditable(false);
		
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
		
		img0 = new JLabel();
		img0.setHorizontalAlignment(JLabel.CENTER);
		img1 = new JLabel();
		img1.setHorizontalAlignment(JLabel.CENTER);
		img2 = new JLabel();
		img2.setHorizontalAlignment(JLabel.CENTER);
		img3 = new JLabel();
		img3.setHorizontalAlignment(JLabel.CENTER);
		img4 = new JLabel();
		img4.setHorizontalAlignment(JLabel.CENTER);
		img5 = new JLabel();
		img5.setHorizontalAlignment(JLabel.CENTER);
		
		//img0.setAlignmentX(Component.CENTER_ALIGNMENT);
		img0.setIcon(imgStop);
		img1.setIcon(imgStop);
		img2.setIcon(imgStop);
		img3.setIcon(imgStop);
		img4.setIcon(imgStop);
		img5.setIcon(imgStop);
		
		panelAscenseur.add(img0);
		panelAscenseur.add(img1);
		panelAscenseur.add(img2);
		panelAscenseur.add(img3);
		panelAscenseur.add(img4);
		panelAscenseur.add(img5);
		
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
		tableauFile.setPreferredScrollableViewportSize(new Dimension(500, 100));
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
		
		labelHorloge = new JLabel("M");
		labelHorloge.setBorder(new TitledBorder("Date actuelle"));
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
		fenetreStats.setIconImage(Toolkit.getDefaultToolkit().getImage("images/myIcon.png"));
		fenetreStats.setLayout(new BorderLayout());
		objStats = new Statistiques();
		
		/**
		 * Declaration des panels
		 */
		panelHaut = new JPanel();
		panelHaut.setBorder(new TitledBorder(blackline, "Ascenseurs", TitledBorder.CENTER, 0, null));
		panelTitre = new JPanel();
		panelCont = new JPanel();
		panelBas = new JPanel();
		panelBas.setBorder(new TitledBorder(blackline, "Statistiques Globales", TitledBorder.CENTER, 0, null));
		panelRefresh = new JPanel();
		panelRefresh.setBorder(new TitledBorder(blackline, "Rafraichissement", TitledBorder.CENTER, 0, null));
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		
		/**
		 * Declaration des labels
		 */
		labelAscenseurs = new JLabel("Ascenseurs");
		labelStats = new JLabel("Statistiques Globales");
		labelNbre = new JLabel("Nombre d'appels total");
		labelConsoTotAsc = new JLabel("Consommation totale (W)");
		labelConso = new JLabel("Consommation moyenne (W/Appel)");
		labelVide = new JLabel("");
		a = new JLabel("A",SwingConstants.CENTER);
		b = new JLabel("B",SwingConstants.CENTER);
		c = new JLabel("C",SwingConstants.CENTER);
		d = new JLabel("D",SwingConstants.CENTER);
		e = new JLabel("E",SwingConstants.CENTER);
		f = new JLabel("F",SwingConstants.CENTER);
		labelConsoMoy = new JLabel("Consommation Moyenne (W/H)",SwingConstants.CENTER);
		labelConsoTot = new JLabel("Consommation Totale (W)",SwingConstants.CENTER);
		labelAttMoy = new JLabel("Attente Moyenne (Sec)",SwingConstants.CENTER);
		labelNbrAppTot = new JLabel("Nombre d'appels Total",SwingConstants.CENTER);
		labelDureeSimu = new JLabel("Durée simulation (Sec)",SwingConstants.CENTER);
		bouttonRefreshStat = new JButton("Rafraichir");
		bouttonRefreshStat.addActionListener(this);
		
		/**
		 * Declaration des TextFiels
		 */
		nbr1 = new JTextField(6);
		nbr1.setEditable(false);
		nbr2 = new JTextField(6);
		nbr2.setEditable(false);
		nbr3 = new JTextField(6);
		nbr3.setEditable(false);
		nbr4 = new JTextField(6);
		nbr4.setEditable(false);
		nbr5 = new JTextField(6);
		nbr5.setEditable(false);
		nbr6 = new JTextField(6);
		nbr6.setEditable(false);
		
		consoTotAsc1 = new JTextField(6);
		consoTotAsc1.setEditable(false);
		consoTotAsc2= new JTextField(6);
		consoTotAsc2.setEditable(false);
		consoTotAsc3 = new JTextField(6);
		consoTotAsc3.setEditable(false);
		consoTotAsc4 = new JTextField(6);
		consoTotAsc4.setEditable(false);
		consoTotAsc5 = new JTextField(6);
		consoTotAsc5.setEditable(false);
		consoTotAsc6 = new JTextField(6);
		consoTotAsc6.setEditable(false);
		
		conso1 = new JTextField(6);
		conso1.setEditable(false);
		conso2 = new JTextField(6);
		conso2.setEditable(false);
		conso3 = new JTextField(6);
		conso3.setEditable(false);
		conso4 = new JTextField(6);
		conso4.setEditable(false);
		conso5 = new JTextField(6);
		conso5.setEditable(false);
		conso6 = new JTextField(6);
		conso6.setEditable(false);
		ConsoMoy = new JTextField();
		ConsoMoy.setEditable(false);
		ConsoTot = new JTextField();
		ConsoTot.setEditable(false);
		AttMoy = new JTextField();
		AttMoy.setEditable(false);
		NbrAppTot = new JTextField();
		NbrAppTot.setEditable(false);
		DureeSimu = new JTextField();
		DureeSimu.setEditable(false);

		
		/**
		 * Panel Ascenseurs contenant un tableau
		 */
		panelHaut.setLayout(new FlowLayout());
		panelTitre.setLayout(new GridLayout(4,1,5,2));
		panelCont.setLayout(new GridLayout(4,6,5,2));
		panelTitre.add(labelVide);
		panelCont.add(a);
		panelCont.add(b);
		panelCont.add(c);
		panelCont.add(d);
		panelCont.add(e);
		panelCont.add(f);
		panelTitre.add(labelNbre);
		panelCont.add(nbr1);
		panelCont.add(nbr2);
		panelCont.add(nbr3);
		panelCont.add(nbr4);
		panelCont.add(nbr5);
		panelCont.add(nbr6);
		panelTitre.add(labelConsoTotAsc);
		panelCont.add(consoTotAsc1);
		panelCont.add(consoTotAsc2);
		panelCont.add(consoTotAsc3);
		panelCont.add(consoTotAsc4);
		panelCont.add(consoTotAsc5);
		panelCont.add(consoTotAsc6);
		panelTitre.add(labelConso);
		panelCont.add(conso1);
		panelCont.add(conso2);
		panelCont.add(conso3);
		panelCont.add(conso4);
		panelCont.add(conso5);
		panelCont.add(conso6);
		panelHaut.add(panelTitre);
		panelHaut.add(panelCont);
		
		/**
		 * Panel Staistiques globales contenant un tableau
		 */
		panelBas.setLayout(new GridLayout(1,3,5,2));
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
		fenetreVisu.setIconImage(Toolkit.getDefaultToolkit().getImage("images/myIcon.png"));
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
	
	public void refreshStats(){
		int i;
		  objStats.calculStatistique(laBatterie);
		  
		  for(i=0;i<6;i++){  
				nbr1.setText(""+objStats.getTabnbAppel()[0]);
				nbr2.setText(""+objStats.getTabnbAppel()[1]);
				nbr3.setText(""+objStats.getTabnbAppel()[2]);
				nbr4.setText(""+objStats.getTabnbAppel()[3]);
				nbr5.setText(""+objStats.getTabnbAppel()[4]);
				nbr6.setText(""+objStats.getTabnbAppel()[5]);
				
				consoTotAsc1.setText(""+objStats.getTabConso()[0]);
				consoTotAsc2.setText(""+objStats.getTabConso()[1]);
				consoTotAsc3.setText(""+objStats.getTabConso()[2]);
				consoTotAsc4.setText(""+objStats.getTabConso()[3]);
				consoTotAsc5.setText(""+objStats.getTabConso()[4]);
				consoTotAsc6.setText(""+objStats.getTabConso()[5]);
				
				conso1.setText(""+objStats.getTabConsoMoyenne()[0]);
				conso2.setText(""+objStats.getTabConsoMoyenne()[1]);
				conso3.setText(""+objStats.getTabConsoMoyenne()[2]);
				conso4.setText(""+objStats.getTabConsoMoyenne()[3]);
				conso5.setText(""+objStats.getTabConsoMoyenne()[4]);
				conso6.setText(""+objStats.getTabConsoMoyenne()[5]);
				
				ConsoMoy.setText(""+objStats.getConsoMoyenneTotale());
				ConsoTot.setText(""+objStats.getConsoTotal());
				AttMoy.setText(""+objStats.getAttenteMoyenne());
				NbrAppTot.setText(""+objStats.getNbAppelTotal());
				DureeSimu.setText(""+objStats.getTotalDuree());
		  }
	}
	
	
	public ImageIcon refreshImg(Ascenseur asc){
		if(asc.isArret()){
			return imgStop;
		}
		else{
			if(asc.isMonte()){
				return imgMonte;
			}
			else{
				return imgDescend;
			}
		}
	}
	

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == bu){
			int i;
			String ddate = "";
			DatePicker D = new DatePicker(pickDate);
			for(i=0;i<3;i++){
				tableDateSim[i] = D.setPickedDateTable()[i];
			}
			ddate = ddate+tableDateSim[0]+"/";
			ddate = ddate+(tableDateSim[1]+1)+"/";
			ddate = ddate+tableDateSim[2]+"";
			text.setText(ddate);
			
			buttonManu.setEnabled(true);
		  }
			
		if(arg0.getSource() == buttonManu){
			int i;
			  fenetreManu.setVisible(true);
			  fenetreChoix.setVisible(false);
			  
				  laBatterie = new Batterie(xtemps, sec, tableDateSim[0], tableDateSim[1], tableDateSim[2], listeHeures.getSelectedIndex());
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
		  
				labelHorloge.setText(""+laBatterie.getCal().getDateActuelle().getTime());
				refreshStats();
				
				
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
			  refreshStats();
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
			ImageIcon antIcon = new ImageIcon("images/ascenseur.gif");
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

			try {
				unAscenseur = laBatterie.assignerAppel(laBatterie.creationAppelManu(js, jv));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			choix.setText(ascenseurString[unAscenseur.getIdAscenseur()]);		
		  }
		  
		  if(arg0.getSource() == bouttonRefreshStat){
			  refreshStats();
		  }
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg1.equals("horloge")){
			//Calendrier calTemp = new Calendrier(xtemps, sec);
//			Date dateTemp = new Date();
//			calTemp = laBatterie.getCal();
//			dateTemp = laBatterie.getCal().getDateActuelle().getTime();
			labelHorloge.setText(""+laBatterie.getCal().getDateActuelle().getTime());
			

		}
		
		
		if(arg1.equals("deplacement0") || arg1.equals("repo0")){
			ascA.setText(""+laBatterie.getTabAscenseur().get(0).getPositionActuelle());
			slider0.setValue(laBatterie.getTabAscenseur().get(0).getPositionActuelle()+4);
			img0.setIcon(refreshImg(laBatterie.getTabAscenseur().get(0)));
		}
		if(arg1.equals("deplacement1") || arg1.equals("repo1")){
			ascB.setText(""+laBatterie.getTabAscenseur().get(1).getPositionActuelle());
			slider1.setValue(laBatterie.getTabAscenseur().get(1).getPositionActuelle()+4);
			img1.setIcon(refreshImg(laBatterie.getTabAscenseur().get(1)));
		}
		if(arg1.equals("deplacement2") || arg1.equals("repo2")){
			ascC.setText(""+laBatterie.getTabAscenseur().get(2).getPositionActuelle());
			slider2.setValue(laBatterie.getTabAscenseur().get(2).getPositionActuelle()+4);
			img2.setIcon(refreshImg(laBatterie.getTabAscenseur().get(2)));
		}
		if(arg1.equals("deplacement3") || arg1.equals("repo3")){
			ascD.setText(""+laBatterie.getTabAscenseur().get(3).getPositionActuelle());
			slider3.setValue(laBatterie.getTabAscenseur().get(3).getPositionActuelle()+4);
			img3.setIcon(refreshImg(laBatterie.getTabAscenseur().get(3)));
		}
		if(arg1.equals("deplacement4") || arg1.equals("repo4")){
			ascE.setText(""+laBatterie.getTabAscenseur().get(4).getPositionActuelle());
			slider4.setValue(laBatterie.getTabAscenseur().get(4).getPositionActuelle()+4);
			img4.setIcon(refreshImg(laBatterie.getTabAscenseur().get(4)));
		}
		if(arg1.equals("deplacement5") || arg1.equals("repo5")){
			ascF.setText(""+laBatterie.getTabAscenseur().get(5).getPositionActuelle());
			slider5.setValue(laBatterie.getTabAscenseur().get(5).getPositionActuelle()+4);
			img5.setIcon(refreshImg(laBatterie.getTabAscenseur().get(5)));
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
			laBatterie.setXTempx(sliderAcc.getValue());
		}
		
	}

}