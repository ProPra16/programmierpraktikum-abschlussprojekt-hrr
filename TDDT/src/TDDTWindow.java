import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TDDTWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TDDTWindow frame = new TDDTWindow();
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
	public TDDTWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnWhleAufgabe = new JButton("W\u00E4hle Aufgabe");
		
		JButton btnWhleKatalog = new JButton("W\u00E4hle Katalog");
		
		JButton btnNchstePhase = new JButton("N\u00E4chste Phase");
		
		JButton btnLetztePhase = new JButton("Letzte Phase");
		
		JButton btnKompiliere = new JButton("Kompiliere");
		btnKompiliere.setForeground(Color.BLACK);
		btnKompiliere.setBackground(Color.RED);
		btnKompiliere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JEditorPane editorPane = new JEditorPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnWhleKatalog, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
						.addComponent(btnWhleAufgabe, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
					.addGap(174)
					.addComponent(btnKompiliere)
					.addGap(167)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnLetztePhase, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNchstePhase, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
				.addComponent(editorPane, GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnWhleKatalog)
								.addComponent(btnLetztePhase))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnWhleAufgabe)
								.addComponent(btnNchstePhase)))
						.addComponent(btnKompiliere, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(editorPane, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
