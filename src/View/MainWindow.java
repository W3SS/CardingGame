package View;

import Model.Card;
import Model.DeckEnum;
import Model.Field;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;


public class MainWindow extends JFrame {

	private String[] Hid = new String[5];
	private String[] C1id = new String[5];
	private String[] C2id = new String[5];
	private int player1Life, player2Life;
	private JPanel panelGameWindow, panelTopBar, panelCards, panelSideBar;
	private JButton btnC20, btnC21, btnC22, btnC23, btnC24, btnC10, btnC11, btnC12, btnC13, btnC14, 
			btnH0, btnH1, btnH2, btnH3, btnH4, btnEncerrarPartida, btnNewButton;
	private JLabel LblPoints1, lblYourLife , label, lblInimigoPontos, lblEnemyLife, label_1, lblVezPlayer,
			lblNewLabel;
	private JTextPane txtpnOQueFazer;
	private ImageIcon imgH0, imgH1, imgH2, imgH3, imgH4, imgC10, imgC11, imgC12, imgC13, imgC14, imgC20,
			imgC21, imgC22, imgC23, imgC24;
	private ImageIcon imgET = new ImageIcon("database/img/ENC_TURNO.png");
	private ImageIcon imgEP = new ImageIcon("database/img/ENC_PARTIDA.png");
	int[] lastClickPos;
	private Game game;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainWindow frame = new MainWindow();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	/**
	 * Create the frame.
	 */
	public MainWindow(Game game) {
		setResizable(false);
		this.game = game;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 10, 200, 200);
		panelGameWindow = new JPanel();
		panelGameWindow.setBackground(Color.BLACK);
		panelGameWindow.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelGameWindow.setLayout(new BorderLayout(0, 0));
		setContentPane(panelGameWindow);
		
//		this.redraw();
	}
	
	private void loadImages() {
		
		imgH0 = new ImageIcon("database/img/" + Hid[0] + ".png");
		imgH1 = new ImageIcon("database/img/" + Hid[1] + ".png");
		imgH2 = new ImageIcon("database/img/" + Hid[2] + ".png");
		imgH3 = new ImageIcon("database/img/" + Hid[3] + ".png");
		imgH4 = new ImageIcon("database/img/" + Hid[4] + ".png");
		
		imgC10 = new ImageIcon("database/img/" + C1id[0] + ".png");
		imgC11 = new ImageIcon("database/img/" + C1id[1] + ".png");
		imgC12 = new ImageIcon("database/img/" + C1id[2] + ".png");
		imgC13 = new ImageIcon("database/img/" + C1id[3] + ".png");
		imgC14 = new ImageIcon("database/img/" + C1id[4] + ".png");
		
		imgC20 = new ImageIcon("database/img/" + C2id[0] + ".png");
		imgC21 = new ImageIcon("database/img/" + C2id[1] + ".png");
		imgC22 = new ImageIcon("database/img/" + C2id[2] + ".png");
		imgC23 = new ImageIcon("database/img/" + C2id[3] + ".png");
		imgC24 = new ImageIcon("database/img/" + C2id[4] + ".png");
	}
	
	private void addButton(JButton button) {
		button.setPreferredSize(new Dimension(152, 210));
		panelCards.add(button);
	}
	
	private void createCardButtons() {
		
		btnC20 = new JButton(imgC20);
		btnC21 = new JButton(imgC21);
		btnC22 = new JButton(imgC22);
		btnC23 = new JButton(imgC23);
		btnC24 = new JButton(imgC24);
		
		btnC10 = new JButton(imgC10);
		btnC11 = new JButton(imgC11);
		btnC12 = new JButton(imgC12);
		btnC13 = new JButton(imgC13);
		btnC14 = new JButton(imgC14);
		
		btnH0 = new JButton(imgH0);
		btnH1 = new JButton(imgH1);
		btnH2 = new JButton(imgH2);
		btnH3 = new JButton(imgH3);
		btnH4 = new JButton(imgH4);
			
	}
	
	private void addCardButons() {

		addButton(btnC20);
		addButton(btnC21);
		addButton(btnC22);
		addButton(btnC23);
		addButton(btnC24);
		
		addButton(btnC10);
		addButton(btnC11);
		addButton(btnC12);
		addButton(btnC13);
		addButton(btnC14);
		
		addButton(btnH0);
		addButton(btnH1);
		addButton(btnH2);
		addButton(btnH3);
		addButton(btnH4);
		
	}
	
	private void createListeners() {
		this.createListenerHand(btnH0, 0);
		this.createListenerHand(btnH1, 1);
		this.createListenerHand(btnH2, 2);
		this.createListenerHand(btnH3, 3);
		this.createListenerHand(btnH4, 4);
		
		this.createListenerCamp1(btnC10, 0);
		this.createListenerCamp1(btnC11, 1);
		this.createListenerCamp1(btnC12, 2);
		this.createListenerCamp1(btnC13, 3);
		this.createListenerCamp1(btnC14, 4);
		
		this.createListenerCamp2(btnC20, 0);
		this.createListenerCamp2(btnC21, 1);
		this.createListenerCamp2(btnC22, 2);
		this.createListenerCamp2(btnC23, 3);
		this.createListenerCamp2(btnC24, 4);
	}
	
	private void buildTopBar() {
		
		if (panelTopBar != null) {
			panelGameWindow.remove(panelTopBar);
		}
		
		panelTopBar = new JPanel();
		panelTopBar.setBackground(Color.LIGHT_GRAY);
		panelGameWindow.add(panelTopBar, BorderLayout.NORTH);
		
		LblPoints1 = new JLabel("PONTUAÇÃO:    Você:");
		panelTopBar.add(LblPoints1);
		
		lblYourLife = new JLabel(this.player1Life + "");
		panelTopBar.add(lblYourLife);
		
		lblInimigoPontos = new JLabel("   Inimigo:");
		panelTopBar.add(lblInimigoPontos);
		
		lblEnemyLife = new JLabel(this.player2Life + "");
		panelTopBar.add(lblEnemyLife);		
		
	}
	
	public void redraw() {
		
		if (panelCards != null)
			panelGameWindow.remove(panelCards);
				
		panelCards = new JPanel();
		panelCards.setBackground(Color.BLACK);
		panelGameWindow.add(panelCards, BorderLayout.CENTER);
		panelCards.setLayout(new GridLayout(3, 5, 0, 0));
		
		loadImages();
		createCardButtons();
		addCardButons();
		createListeners();
		
		buildTopBar();
		
		if (panelSideBar != null) {
			panelGameWindow.remove(panelSideBar);
		}
		panelSideBar = new JPanel();
		panelSideBar.setBackground(Color.BLACK);
		panelGameWindow.add(panelSideBar, BorderLayout.EAST);
		panelSideBar.setLayout(new GridLayout(3, 1, 0, 0));
		
		txtpnOQueFazer = new JTextPane();
		txtpnOQueFazer.setBackground(Color.DARK_GRAY);
		txtpnOQueFazer.setText("PŔOXIMO PASSO: \n" + this.getStateDescription());
		txtpnOQueFazer.setForeground(Color.WHITE);;
		txtpnOQueFazer.setEditable(false);
		
		
		panelSideBar.add(txtpnOQueFazer);
		btnNewButton = new JButton(imgET);
		btnNewButton.setPreferredSize(new Dimension(120, 210));
		
		panelSideBar.add(btnNewButton);
		this.createListenerEncTurno(btnNewButton);
		
		btnEncerrarPartida = new JButton(imgEP);
		btnEncerrarPartida.setPreferredSize(new Dimension(120, 210));
		panelSideBar.add(btnEncerrarPartida);
		this.pack();
	}
	
	private String getStateDescription() {
		return "iubjknsifkfai";
	}
	
	public DeckEnum getChoosenDeck() {
		return DeckEnum.MARVEL;
	}

	public void showNewField(Field field) {
		Map<Integer, Card> cardsOn1 = field.getCardsOn1();
		Map<Integer, Card> cardsOn2 = field.getCardsOn2();
		List<Card> handCards = field.getPlayer1Hand();
		
		this.player1Life = field.getPlayer1().getPoints();
		this.player2Life = field.getPlayer2().getPoints();
		
		for (int k = 0; k < 5; k++) {
			if (k >= handCards.size()) {
				this.Hid[k] = "NULL";
				continue;
			}
			Card card = handCards.get(k);
			if (card != null) {
				this.Hid[k] = card.getId() + "";
				System.out.println(card.getId());
			} else {
				this.Hid[k] = "NULL";
				System.out.println("NULLLL");
			}
		}
		
		
		for (int k = 0; k < 5; k++) {
			if (cardsOn1.containsKey(k)) {
				this.C1id[k] = cardsOn1.get(k).getId() + "";
			} else {
				this.C1id[k] = "NULL";
			}
		}
		
		
		for (int k = 0; k < 5; k++) {
			if (cardsOn2.containsKey(k)) {
				this.C2id[k] = cardsOn2.get(k).getId() + "";
			} else {
				this.C2id[k] = "NULL";
			}
		}
		
		this.redraw();
		
		
	}

	public int[] getSelectedFieldPos() {
		
//		btnH0.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
		return null;
	}

	public int[] getLastClick() {
		// TODO Auto-generated method stub
		return null;
	}
	
		
	public void createListenerHand(JButton button, final int pos1) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final int[] position = {0, pos1};
				game.handClick(position);
			}
		});
	}
	
	public void createListenerCamp1(JButton button, final int pos1) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final int[] position = {1, pos1};
				game.camp1Click(position);
			}
		});
	}
	
	public void createListenerCamp2(JButton button, final int pos1) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final int[] position = {2, pos1};
				game.camp2Click(position);
			}
		});
	}
	
	public void createListenerEncTurno(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.clickEndTurn();;
			}
		});
	}


}
