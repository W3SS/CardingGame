package View;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public final class LogWindow extends JFrame {
	
	private JPanel contentPane;

	public LogWindow(String status) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setForeground(Color.WHITE);
		textPane.setBackground(Color.DARK_GRAY);
		textPane.setText(status);
		scrollPane.setViewportView(textPane);
		super.setDefaultCloseOperation(HIDE_ON_CLOSE);
		try {
			Thread.sleep(110);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setVisible(true);
		super.setFocusable(true);
	}

}
