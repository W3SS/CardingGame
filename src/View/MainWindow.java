package View;

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

import Control.AtorJogador;
import Control.EndStatus;
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
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;


public class MainWindow extends JFrame {

	private AtorJogador jogador;
	private String[] Hid = new String[5];
	private String[] C1id = new String[5];
	private String[] C2id = new String[5];
	private int player1Life, player2Life;
	private JPanel panelGameWindow, panelTopBar, panelCards, panelRightBar, panelStart;
	private JButton btnC2[], btnC1[], btnH[], btnEP, btnET;
	private JLabel LblPoints;
	private JTextPane txtpnOQueFazer;
	private ImageIcon imgH[], imgC1[], imgC2[];
	private JTextArea battles;
	private JScrollPane panelLeftBar;
	private ImageIcon imgET = new ImageIcon("database/img/ENC_TURNO.png");
	private ImageIcon imgEP = new ImageIcon("database/img/ENC_PARTIDA.png");
	private Game game;
	private GameState state;

	
	public MainWindow(final Game game, AtorJogador jogador) {
	
		this.game = game;
		this.jogador = jogador;
		super.setResizable(false);
		super.setVisible(true);
		super.setTitle("Marvel vs. DC");		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setBounds(100, 10, 200, 200);
		
		super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
	}
	
	public void showStartScreen(boolean connected) {
		super.setVisible(false);
		this.panelStart = new JPanel();
		ImageIcon backgroung = new ImageIcon("database/img/TITLE.png");
		this.panelStart.setLayout(null);
		JLabel background = new JLabel();
		background.setBounds(0, 0, 769, 408);
		background.setIcon(backgroung);
		this.panelStart.setBackground(Color.BLACK);
		super.setBounds(super.getX(), super.getY(), 769, 408);
		
		if (!connected) {
			
			JButton btnConectar = new JButton("Conectar");
			btnConectar.setBounds(280, 196, 98, 25);
			this.panelStart.add(btnConectar);
			
			btnConectar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String host = JOptionPaneTools.askString("Insira o host do servidor:", "localhost");
					jogador.connect(host);
				}
			});
			
			
			JButton btnSair = new JButton("Sair");
			btnSair.setBounds(400, 196, 62, 25);
			this.panelStart.add(btnSair);
			
			btnSair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jogador.exit();
				}
			});

		} else {
			
			JButton btnIniciar = new JButton("Iniciar");
			btnIniciar.setBounds(280, 196, 78, 25);
			this.panelStart.add(btnIniciar);
			
			btnIniciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jogador.startMatch();
				}
			});
			

			JButton btnDesconectar = new JButton("Desconectar");
			btnDesconectar.setBounds(380, 196, 125, 25);
			this.panelStart.add(btnDesconectar);
			
			btnDesconectar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jogador.disconnect();
				}
			});
			
		}
		
		this.panelStart.add(background);
		this.panelStart.repaint();
		super.setContentPane(this.panelStart);
		super.setVisible(true);
		super.repaint();
		
	}
	
	private void loadImages() {
		
		this.imgH = new ImageIcon[5];
		for (int k = 0; k < 5; k++) {
			this.imgH[k] = new ImageIcon("database/img/" + this.Hid[k] + ".png");
		}
		
		this.imgC1 = new ImageIcon[5];
		for (int k = 0; k < 5; k++) {
			this.imgC1[k] = new ImageIcon("database/img/" + this.C1id[k] + ".png");
		}
		
		this.imgC2 = new ImageIcon[5];
		for (int k = 0; k < 5; k++) {
			this.imgC2[k] = new ImageIcon("database/img/" + this.C2id[k] + ".png");
		}
		
	}
	
	private void addButton(JButton button) {
		button.setPreferredSize(new Dimension(152, 210));
		this.panelCards.add(button);
	}
	
	private void createCardButtons() {
		this.btnH = new JButton[5];
		for (int k = 0; k < 5; k++) {
			this.btnH[k] = new JButton();
		}
		
		this.btnC1 = new JButton[5];
		for (int k = 0; k < 5; k++) {
			this.btnC1[k] = new JButton();
		}
		
		this.btnC2 = new JButton[5];		
		for (int k = 0; k < 5; k++) {
			this.btnC2[k] = new JButton();
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
			this.createListenerHand(this.btnH[k], k);
		}
		
		for (int k = 0; k < 5; k++) {
			this.createListenerCamp1(this.btnC1[k], k);
		}
		
		for (int k = 0; k < 5; k++) {
			this.createListenerCamp2(this.btnC2[k], k);
		}
		
	}
	
	private void constructTopBar() {
		
		this.panelTopBar = new JPanel();
		this.panelTopBar.setBackground(Color.LIGHT_GRAY);
		this.LblPoints = new JLabel();
		this.panelTopBar.add(this.LblPoints);
		this.panelGameWindow.add(this.panelTopBar, BorderLayout.NORTH);
		
	}
	
	private void constructLeftBar() {
		this.battles = new JTextArea();
		this.battles.setEditable(true);
		this.battles.setForeground(Color.WHITE);
		this.battles.setBackground(Color.DARK_GRAY);
		this.panelLeftBar = new JScrollPane(this.battles);
		this.panelLeftBar.setPreferredSize(new Dimension(215, 630));
		this.panelLeftBar.setBackground(Color.BLACK);
		this.panelGameWindow.add(this.panelLeftBar, BorderLayout.WEST);
	}
	
	private void constructRightBar() {
		this.panelRightBar = new JPanel();
		this.panelRightBar.setBackground(Color.BLACK);
		this.panelGameWindow.add(this.panelRightBar, BorderLayout.EAST);
		this.panelRightBar.setLayout(new GridLayout(3, 1, 0, 0));
		
		
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
		this.panelGameWindow.add(this.panelCards, BorderLayout.CENTER);
		this.panelCards.setLayout(new GridLayout(3, 5, 0, 0));
		
	}
	
	public void draw(Field field) {
		
		super.setVisible(false);
		this.panelGameWindow = new JPanel();
		this.panelGameWindow.setBackground(Color.BLACK);
		this.panelGameWindow.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.panelGameWindow.setLayout(new BorderLayout(0, 0));
			
		this.constructPanelCards();
		this.createCardButtons();
		this.addCardButons();
		this.createListeners();
		this.constructTopBar();
		this.constructRightBar();
		this.constructLeftBar();
		
		this.updateGuiVariables(field);
		super.setContentPane(this.panelGameWindow);
		this.repaint();
		this.panelGameWindow.setBounds(super.getX(), super.getY(), 1105, 665);
		super.setBounds(super.getX(), super.getY(), 1105, 665);
		super.setVisible(true);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		super.pack();
		
	}
	
	public void alertEnemyStarted() {
		super.setVisible(false);
		super.setContentPane(new JLabel("  O outro jogador iniciou a partida e está escolhendo o seu deck, aguarde."));
		super.setVisible(true);
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
		
		if (this.panelGameWindow != null)
			this.panelGameWindow.repaint();
		
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
			
			case STARTING_MATCH:
				description = "Partida não\n"
							+ "iniciada.";
			break;
			
			case SELECT_HAND_CHOOSING_CARD_ON_HAND:
				description = "Escolha uma\n"
						    + "carta da sua\n"
						    + "mão (3ª linha)\n"
						    + "para colocar\n"
						    + "no seu campo.";
			break;
			
			case SELECT_HAND_CHOOSING_CARD_ON_1:
				description = "Escolha uma\n"
							+ "posição no seu\n"
							+ "campo (2ª linha)\n"
							+ "para colocar a\n"
							+ "carta escolhida.";
			break;
			
			case ATACK_CHOOSING_CARD_ON_1:
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

			case  ATACK_CHOOSING_CARD_ON_2:
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

			case RECEIVE_MOVE:
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

	private void updateGuiVariables(Field field) {
		
		this.updateCardsId(field);
		this.loadImages();
		this.updateCardButtons();
		this.updateLog(field);
		this.updatePoints();
		
	}
	
	private void updateLog(Field field) {
		
		
		this.battles.setText(field.getLog(EndStatus.NOT_FINISHED));
		
		this.panelLeftBar.repaint();
		JScrollBar vertical = this.panelLeftBar.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
		this.panelLeftBar.repaint();
		
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
		super.pack();
		
	}

	private void createListenerHand(JButton button, final int pos1) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final int[] position = {0, pos1};
				game.handClick(position);
			}
		});
	}
	
	private void createListenerCamp1(JButton button, final int pos1) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final int[] position = {1, pos1};
				game.camp1Click(position);
			}
		});
	}
	
	private void createListenerCamp2(JButton button, final int pos1) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final int[] position = {2, pos1};
				game.camp2Click(position);
			}
		});
	}
	
	private void createListenerEncTurno(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jogador.clickEndTurn();
			}
		});
	}
	
	private void createListenerEncPartida(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				askEndMatch();
			}
		});
	}

	public void alert(String text) {
		JOptionPaneTools.message(text, "Aviso");
	}

	public DeckEnum chooseDeck() {
		int chose = JOptionPaneTools.askOption("Escolha seu deck:", new String[] {"Marvel", "DC"});
		if (chose == 0) {
			return DeckEnum.MARVEL;
		} else if (chose == 1) {
			return DeckEnum.DC;
		} else {
			return null;
		}
	}
	
	private void askEndMatch() {
		int chose = JOptionPaneTools.askOption("Você quer mesmo encerrar a partida?", new String[] {"Não", "Sim"});
		if (chose == 1) {
			this.jogador.endMatch();
		}
	}
	
}
