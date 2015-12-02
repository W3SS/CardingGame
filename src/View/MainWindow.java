package View;

import Model.Battle;
import Model.Card;
import Model.DeckEnum;
import Model.Field;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Control.Game;
import Control.GameState;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;


public class MainWindow extends JFrame {

	private String[] Hid = new String[5];
	private String[] C1id = new String[5];
	private String[] C2id = new String[5];
	private int player1Life, player2Life;
	private JPanel panelGame, panelTopBar, panelCards, panelRightBar, panelStart, panelWindow;
	private JButton btnC2[], btnC1[], btnH[], btnEP, btnET;
	private JLabel LblPoints;
	private JTextPane txtpnOQueFazer;
	private ImageIcon imgH[], imgC1[], imgC2[];
	private JTextArea battles;
	private JScrollPane panelLeftBar;
	private ImageIcon imgET = new ImageIcon("database/img/ENC_TURNO.png");
	private ImageIcon imgEP = new ImageIcon("database/img/ENC_PARTIDA.png");
	int[] lastClickPos;
	private Game game;
	private GameState state;

	
	public MainWindow(final Game game) {
	
		super.setResizable(false);
		this.game = game;
		super.setTitle("Marvel vs. DC");		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setBounds(100, 10, 200, 200);
		this.panelWindow = new JPanel();
		this.panelWindow.setBackground(Color.RED);
		this.panelWindow.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.panelWindow.setLayout(new BorderLayout(0, 0));
		super.setContentPane(this.panelWindow);
		super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
		super.setVisible(true);
	}
	
	private void clearWindow() {
		if (this.panelGame != null) {
			this.panelWindow.remove(panelGame);
			this.panelGame = null;
		}
		if (this.panelStart != null) {
			this.panelWindow.remove(panelStart);
			this.panelStart = null;
		}
	}
	
	public void showStartScreen(boolean connected) {
		this.clearWindow();
		this.panelStart = new JPanel();
		ImageIcon backgroung = new ImageIcon("database/img/TITLE.png");
		panelStart.setLayout(null);
		JLabel background = new JLabel();
		System.out.println("yubinoklbiluhoil");
		background.setBounds(0, 0, 769, 408);
		background.setIcon(backgroung);
		panelStart.setBackground(Color.BLACK);
		super.setBounds(super.getX(), super.getY(), 769, 408);;
		System.out.println("yubinoklbiluhoil");
		
		if (!connected) {
			System.out.println("if");
			
			JButton btnConectar = new JButton("Conectar");
			btnConectar.setBounds(280, 196, 98, 25);
			panelStart.add(btnConectar);
			
			btnConectar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String host = JOptionPaneTools.askString("Insira o host do servidor:", "localhost");
					game.connect(host);
				}
			});
			
			
			JButton btnSair = new JButton("Sair");
			btnSair.setBounds(400, 196, 62, 25);
			panelStart.add(btnSair);
			
			btnSair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});

		} else {
			System.out.println("else");
			
			JButton btnIniciar = new JButton("Iniciar");
			btnIniciar.setBounds(280, 196, 78, 25);
			panelStart.add(btnIniciar);

			JButton btnDesconectar = new JButton("Desconectar");
			btnDesconectar.setBounds(380, 196, 125, 25);
			panelStart.add(btnDesconectar);
			
		}
		System.out.println("END");
		
		panelStart.add(background);

		this.panelWindow.add(this.panelStart, BorderLayout.CENTER);
		panelStart.repaint();
		panelWindow.repaint();
		super.repaint();
		super.setVisible(false);
		super.setVisible(true);
//		super.pack();
	}
	
	private void loadImages() {
		
		this.imgH = new ImageIcon[5];
		for (int k = 0; k < 5; k++) {
			this.imgH[k] = new ImageIcon("database/img/" + Hid[k] + ".png");
		}
		
		this.imgC1 = new ImageIcon[5];
		for (int k = 0; k < 5; k++) {
			this.imgC1[k] = new ImageIcon("database/img/" + C1id[k] + ".png");
		}
		
		this.imgC2 = new ImageIcon[5];
		for (int k = 0; k < 5; k++) {
			this.imgC2[k] = new ImageIcon("database/img/" + C2id[k] + ".png");
		}
		
	}
	
	private void addButton(JButton button) {
		button.setPreferredSize(new Dimension(152, 210));
		this.panelCards.add(button);
	}
	
	private void createCardButtons() {
		this.btnH = new JButton[5];
		for (int k = 0; k < 5; k++) {
			this.btnH[k] = new JButton(new ImageIcon(""));
		}
		
		this.btnC1 = new JButton[5];
		for (int k = 0; k < 5; k++) {
			this.btnC1[k] = new JButton("");
		}
		
		this.btnC2 = new JButton[5];		
		for (int k = 0; k < 5; k++) {
			this.btnC2[k] = new JButton("");
		}
		
	}
	
	private void updateCardButtons() {
		
		for (int k = 0; k < 5; k++) {
			this.btnH[k].setIcon(this.imgH[k]);
		}
		
		for (int k = 0; k < 5; k++) {
			this.btnC1[k].setIcon(this.imgC1[k]);
		}
		
		for (int k = 0; k < 5; k++) {
			this.btnC2[k].setIcon(this.imgC2[k]);
		}
		
	}
	
	private void addCardButons() {

		for (int k = 0; k < 5; k++) {
			this.addButton(this.btnC2[k]);
		}
		
		for (int k = 0; k < 5; k++) {
			this.addButton(this.btnC1[k]);
		}
		

		for (int k = 0; k < 5; k++) {
			this.addButton(this.btnH[k]);
		}
		
	}
	
	private void createListeners() {
		
		for (int k = 0; k < 5; k++) {
			this.createListenerHand(btnH[k], k);
		}
		
		for (int k = 0; k < 5; k++) {
			this.createListenerCamp1(btnC1[k], k);
		}
		
		for (int k = 0; k < 5; k++) {
			this.createListenerCamp2(btnC2[k], k);
		}
		
	}
	
	private void constructTopBar() {
		
		this.panelTopBar = new JPanel();
		this.panelTopBar.setBackground(Color.LIGHT_GRAY);
		this.LblPoints = new JLabel();
		this.panelTopBar.add(this.LblPoints);
		this.panelGame.add(this.panelTopBar, BorderLayout.NORTH);
		
	}
	
	private void constructLeftBar() {
		this.battles = new JTextArea();
		this.battles.setEditable(true);
		this.battles.setForeground(Color.WHITE);
		this.battles.setBackground(Color.DARK_GRAY);
		this.panelLeftBar = new JScrollPane(battles);
		this.panelLeftBar.setPreferredSize(new Dimension(215, 630));
		this.panelLeftBar.setBackground(Color.BLACK);
		panelGame.add(this.panelLeftBar, BorderLayout.WEST);
	}
	
	private void constructRightBar() {
		this.panelRightBar = new JPanel();
		this.panelRightBar.setBackground(Color.BLACK);
		this.panelGame.add(this.panelRightBar, BorderLayout.EAST);
		panelRightBar.setLayout(new GridLayout(3, 1, 0, 0));
		
		
		this.txtpnOQueFazer = new JTextPane();
		this.txtpnOQueFazer.setBackground(Color.DARK_GRAY);
		this.txtpnOQueFazer.setForeground(Color.WHITE);
		this.txtpnOQueFazer.setEditable(false);
		this.panelRightBar.add(this.txtpnOQueFazer);
		
		this.btnET = new JButton(this.imgET);
		this.btnET.setPreferredSize(new Dimension(120, 210));
		this.panelRightBar.add(this.btnET);
		this.createListenerEncTurno(this.btnET);
		
		this.btnEP = new JButton(this.imgEP);
		this.btnEP.setPreferredSize(new Dimension(120, 210));
		this.panelRightBar.add(this.btnEP);
		this.createListenerEncPartida(this.btnEP);

	}
	
	private void constructPanelCards() {
		
		this.panelCards = new JPanel();
		this.panelCards.setBackground(Color.BLACK);
		this.panelGame.add(this.panelCards, BorderLayout.CENTER);
		this.panelCards.setLayout(new GridLayout(3, 5, 0, 0));
		
	}
	
	public void draw(Field field) {
				
		
		this.constructPanelCards();
		
		this.createCardButtons();
		
		this.addCardButons();
		
		this.createListeners();
		
		this.constructTopBar();
		
		this.constructRightBar();
		
		this.constructLeftBar();
		
		super.pack();
		
		this.updateGuiVariables(field);
		
		this.repaint();
		
		this.clearWindow();
		this.panelWindow.add(panelGame, BorderLayout.CENTER);;
	}
	
	public void repaint() {
		
		if (this.panelCards != null)
			this.panelCards.repaint();
		else 
		
		if (this.panelRightBar != null)
			this.panelRightBar.repaint();
		else
		
		if (this.panelTopBar != null)
			this.panelTopBar.repaint();
		else
		
		if (this.panelGame != null)
			this.panelGame.repaint();
		
		super.repaint();
	}
	
	public void setState(GameState state) {
		this.state = state;
		this.updatePoints();
		this.repaint();
	}
	
	private String getStateDescription() {
		String description;
		switch (this.state) {
			
			case INICIANDO_PARTIDA:
				description = "Partida não\n"
							+ "iniciada.";
			break;
			
			case JG_ESCOLHER_CARTA_MAO:
				description = "Escolha uma\n"
						    + "carta da sua\n"
						    + "mão (3ª linha)\n"
						    + "para colocar\n"
						    + "no seu campo.";
			break;
			
			case JG_ESCOLHER_CARTA_CAMPO1:
				description = "Escolha uma\n"
							+ "posição no seu\n"
							+ "campo (2ª linha)\n"
							+ "para colocar a\n"
							+ "carta escolhida.";
			break;
//			
			case AO_ESCOLHER_CARTA_CAMPO1:
				description = "Escolha a carta\n"
							+ "do seu campo\n"
							+ "(2ª linha) com\n"
							+ "a qual você quer\n"
							+ "atacar seu\n"
							+ "oponente,\n"
							+ "ou então clique\n"
							+ "em encerrar\n"
							+ "turno para\n"
							+ "passar a vez.";
			break;

			case  AO_ESCOLHER_CARTA_CAMPO2:
				description = "Escolha a carta\n"
							+ "do campo inimigo\n"
							+ "(1ª linha) que\n"
							+ "você quer atacar\n"
							+ "com a cara\n"
							+ "escolhida,\n"
							+ "ou então clique\n"
							+ "em encerrar\n"
							+ "turno para\n"
							+ "passar a vez.";
				break;

			case RECEBER_JOGADA:
				description = "Aguarde até que\n"
							+ "o seu oponente\n"
							+ "termine a sua\n"
							+ "rodada.";
			break;
			
			default :
				description = "Estado inválido.";
	
		}
		return description;
	}
	
	public DeckEnum getChoosenDeck() {
		return DeckEnum.MARVEL;
	}

	private void updateGuiVariables(Field field) {
		
		updateCardsId(field);
		loadImages();
		updateCardButtons();
		updateBattles(field);
		updatePoints();
		
	}
	
	private void updateBattles(Field field) {
		String battles = "BATALHAS: \n\n";
		
		for (Battle battle : field.getBattles()) {
			battles += battle.getReport() + "\n\n";
		}
		
		this.battles.setText(battles);
		
		JScrollBar vertical = this.panelLeftBar.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
		
	}
	
	private void updateCardsId(Field field) {
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
			} else {
				this.Hid[k] = "NULL";
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
		
	}
	
	private void updatePoints() {
		if (this.LblPoints == null || this.txtpnOQueFazer == null)
			return;
		
		this.LblPoints.setText("PONTUAÇÃO:    Você: " + this.player1Life 
				+ "    Inimigo: " + this.player2Life);	
		
		this.txtpnOQueFazer.setText("PŔOXIMO PASSO: \n\n" + this.getStateDescription());
	}
	
	public void showNewField(Field field) {
		
		this.updateGuiVariables(field);
		this.repaint();
		
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
				game.clickEndTurn();
			}
		});
	}
	
	public void createListenerEncPartida(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.finalizeMatch(1);
			}
		});
	}


}
