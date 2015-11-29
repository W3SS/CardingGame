package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class test1 extends JFrame {

	private JPanel contentPane;
	private final JButton btnNewButton = new JButton("ENC TURNO");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test1 frame = new test1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 5, 0, 0));
		
		JButton btnA = new JButton("2 1");
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
			}
		});
		panel.add(btnA);
		
		ImageIcon imgH1;  
		imgH1 = new ImageIcon("/home/matteus/batata.jpg");  
		
		JButton btnB = new JButton("2 2");
		panel.add(btnB);
		
		JButton btnC = new JButton("2 3");
		panel.add(btnC);
		
		JButton btnD = new JButton("2 4");
		panel.add(btnD);
		
		JButton btnE = new JButton("2 5");
		panel.add(btnE);
		
		JButton button = new JButton("1 1");
		panel.add(button);
		
		JButton button_1 = new JButton("1 2");
		panel.add(button_1);
		
		JButton button_2 = new JButton("1 3");
		panel.add(button_2);
		
		JButton button_3 = new JButton("1 4");
		panel.add(button_3);
		
		JButton button_4 = new JButton("1 5");
		panel.add(button_4);
		
		JButton btnH = new JButton(imgH1);
		panel.add(btnH);
		
		JButton btnH_1 = new JButton("h 2");
		panel.add(btnH_1);
		
		JButton btnH_2 = new JButton("h 3");
		panel.add(btnH_2);
		
		JButton btnH_3 = new JButton("h 4");
		panel.add(btnH_3);
		
		JButton btnH_4 = new JButton("h 5");
		panel.add(btnH_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblVocPontos = new JLabel("Você: ");
		panel_1.add(lblVocPontos);
		
		JLabel lblPontos = new JLabel("PONTOSVC");
		panel_1.add(lblPontos);
		
		JLabel label = new JLabel("      ");
		panel_1.add(label);
		
		JLabel lblInimigoPontos = new JLabel("Inimigo: ");
		panel_1.add(lblInimigoPontos);
		
		JLabel lblPontos_1 = new JLabel("PONTOSINIMIGO");
		panel_1.add(lblPontos_1);
		
		JLabel label_1 = new JLabel("                                        ");
		panel_1.add(label_1);
		
		JLabel lblVezPlayer = new JLabel("Vez: ");
		panel_1.add(lblVezPlayer);
		
		JLabel lblNewLabel = new JLabel("PLAYER");
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		contentPane.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		JTextPane txtpnOQueFazer = new JTextPane();
		txtpnOQueFazer.setBackground(Color.LIGHT_GRAY);
		txtpnOQueFazer.setText("O QUE FAZER\n TESTE");
		txtpnOQueFazer.setEditable(false);
		panel_2.add(txtpnOQueFazer);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_2.add(btnNewButton);
		
		JButton btnEncerrarPartida = new JButton("ENC PART");
		panel_2.add(btnEncerrarPartida);
	}

}
