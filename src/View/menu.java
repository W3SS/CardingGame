//package View;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import java.awt.FlowLayout;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//
//public class menu extends JFrame {
//
//	private JPanel contentPane;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					menu frame = new menu();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public menu() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		
//		// remover panel gameWindow;
//		
//		// deve estar na classe:
//		JPanel startPanel = new JPanel();
//		ImageIcon backgroung = new ImageIcon("database/img/TITLE.png");
//		startPanel.setLayout(null);
//		JLabel background = new JLabel();
//		background.setBounds(0, 0, 769, 408);
//		background.setIcon(backgroung);
//		startPanel.setBackground(Color.BLACK);
//		super.setBounds(super.getX(), super.getY(), 769, 408);;
//		
//		if (!connected) {
//			JButton btnConectar = new JButton("Conectar");
//			btnConectar.setBounds(280, 196, 98, 25);
//			startPanel.add(btnConectar);
//
//			JButton btnSair = new JButton("Sair");
//			btnSair.setBounds(400, 196, 62, 25);
//			startPanel.add(btnSair);
//
//		} else {
//			JButton btnIniciar = new JButton("Iniciar");
//			btnIniciar.setBounds(280, 196, 78, 25);
//			startPanel.add(btnIniciar);
//
//			JButton btnDesconectar = new JButton("Desconectar");
//			btnDesconectar.setBounds(380, 196, 125, 25);
//			startPanel.add(btnDesconectar);
//			
//		}
//
//		startPanel.add(background);
//		setContentPane(startPanel);
//		
//	}
//
//}
