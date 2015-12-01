package View;

import Model.Card;
import Model.DeckEnum;
import Model.Field;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Game;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JTextPane;


public class MainWindow extends JFrame {

	private String[] Hid = new String[5];
	private String[] C1id = new String[5];
	private String[] C2id = new String[5];
	private JPanel contentPane, panel_1, panel, panel_2;
	private JButton btnA, btnB, btnC, btnD, btnE, button, button_1, button_2, button_3, button_4, 
			btnH, btnH_1, btnH_2, btnH_3, btnH_4, btnEncerrarPartida, btnNewButton;
	private JLabel lblVocPontos, lblPontos , label, lblInimigoPontos, lblPontos_1, label_1, lblVezPlayer,
			lblNewLabel;
	private JTextPane txtpnOQueFazer;
	private ImageIcon imgH1, imgH2, imgH3, imgH4, imgH5, imgC11, imgC12, imgC13, imgC14, imgC15, imgC21,
			imgC22, imgC23, imgC24, imgC25;
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
		this.game = game;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 680);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
//		this.redraw();
	}
	
	private void updateImages() {
		
		imgH1 = new ImageIcon("database/img/" + Hid[0] + ".png");
		imgH2 = new ImageIcon("database/img/" + Hid[1] + ".png");
		imgH3 = new ImageIcon("database/img/" + Hid[2] + ".png");
		imgH4 = new ImageIcon("database/img/" + Hid[3] + ".png");
		imgH5 = new ImageIcon("database/img/" + Hid[4] + ".png");
		
		imgC11 = new ImageIcon("database/img/" + C1id[0] + ".png");
		imgC12 = new ImageIcon("database/img/" + C1id[1] + ".png");
		imgC13 = new ImageIcon("database/img/" + C1id[2] + ".png");
		imgC14 = new ImageIcon("database/img/" + C1id[3] + ".png");
		imgC15 = new ImageIcon("database/img/" + C1id[4] + ".png");
		
		imgC21 = new ImageIcon("database/img/" + C2id[0] + ".png");
		imgC22 = new ImageIcon("database/img/" + C2id[1] + ".png");
		imgC23 = new ImageIcon("database/img/" + C2id[2] + ".png");
		imgC24 = new ImageIcon("database/img/" + C2id[3] + ".png");
		imgC25 = new ImageIcon("database/img/" + C2id[4] + ".png");
	}
	
	public void redraw() {
		if (panel != null) {
			contentPane.remove(panel);
		}
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 5, 0, 0));
		
		updateImages();
		
		btnA = new JButton(imgC21);
		panel.add(btnA);
		
		btnB = new JButton(imgC22);
		panel.add(btnB);
		
		btnC = new JButton(imgC23);
		panel.add(btnC);
		
		btnD = new JButton(imgC24);
		panel.add(btnD);
		
		btnE = new JButton(imgC25);
		panel.add(btnE);
		
		button = new JButton(imgC11);
		panel.add(button);
		
		button_1 = new JButton(imgC12);
		panel.add(button_1);
		
		button_2 = new JButton(imgC13);
		panel.add(button_2);
		
		button_3 = new JButton(imgC14);
		panel.add(button_3);
		
		button_4 = new JButton(imgC15);
		panel.add(button_4);
		
		btnH = new JButton(imgH1);
		panel.add(btnH);
		
		btnH_1 = new JButton(imgH2);
		panel.add(btnH_1);
		
		btnH_2 = new JButton(imgH3);
		panel.add(btnH_2);
		
		btnH_3 = new JButton(imgH4);
		panel.add(btnH_3);
		
		btnH_4 = new JButton(imgH5);
		panel.add(btnH_4);
		
		this.createListenerHand(btnH, 0);
		this.createListenerHand(btnH_1, 1);
		this.createListenerHand(btnH_2, 2);
		this.createListenerHand(btnH_3, 3);
		this.createListenerHand(btnH_4, 4);
		
		this.createListenerCamp1(button, 0);
		this.createListenerCamp1(button_1, 1);
		this.createListenerCamp1(button_2, 2);
		this.createListenerCamp1(button_3, 3);
		this.createListenerCamp1(button_4, 4);
		
		this.createListenerCamp2(btnA, 0);
		this.createListenerCamp2(btnB, 1);
		this.createListenerCamp2(btnC, 2);
		this.createListenerCamp2(btnD, 3);
		this.createListenerCamp2(btnE, 4);
		
		
		if (panel_1 != null) {
			contentPane.remove(panel_1);
		}
		panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		lblVocPontos = new JLabel("Você: ");
		panel_1.add(lblVocPontos);
		
		lblPontos = new JLabel("PONTOSVC");
		panel_1.add(lblPontos);
		
		label = new JLabel("      ");
		panel_1.add(label);
		
		lblInimigoPontos = new JLabel("Inimigo: ");
		panel_1.add(lblInimigoPontos);
		
		lblPontos_1 = new JLabel("PONTOSINIMIGO");
		panel_1.add(lblPontos_1);
		
		label_1 = new JLabel("                                        ");
		panel_1.add(label_1);
		
		lblVezPlayer = new JLabel("Vez: ");
		panel_1.add(lblVezPlayer);
		
		lblNewLabel = new JLabel("PLAYER");
		panel_1.add(lblNewLabel);
		
		if (panel_2 != null) {
			contentPane.remove(panel_2);
		}
		panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		contentPane.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		txtpnOQueFazer = new JTextPane();
		txtpnOQueFazer.setBackground(Color.LIGHT_GRAY);
		txtpnOQueFazer.setText("O QUE FAZER\n TESTE");
		txtpnOQueFazer.setEditable(false);
		panel_2.add(txtpnOQueFazer);
		btnNewButton = new JButton("ENC TURNO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_2.add(btnNewButton);
		
		btnEncerrarPartida = new JButton("ENC PART");
		panel_2.add(btnEncerrarPartida);
		this.pack();
	}
	
	
	public DeckEnum getChoosenDeck() {
		return DeckEnum.DC;
	}

	public void showNewField(Field field) {
		Map<Integer, Card> cardsOn1 = field.getCardsOn1();
		Map<Integer, Card> cardsOn2 = field.getCardsOn2();
		List<Card> handCards = field.getPlayer1Hand();
		
		
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
		
		System.out.println("BLABLABLA");
		
		for (int k = 0; k < 5; k++) {
			if (cardsOn1.containsKey(k)) {
				this.C1id[k] = cardsOn1.get(k).getId() + "";
			} else {
				this.C1id[k] = "NULL";
			}
		}	
		
		System.out.println("BLABLABLA2");
		
		for (int k = 0; k < 5; k++) {
			if (cardsOn2.containsKey(k)) {
				this.C2id[k] = cardsOn2.get(k).getId() + "";
			} else {
				this.C2id[k] = "NULL";
			}
		}
		System.out.println("BLABLABLA3");
		this.redraw();
		System.out.println("BLABLABLA4");
		
		
	}

	public int[] getSelectedFieldPos() {
		
//		btnH.addActionListener(new ActionListener() {
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


}
