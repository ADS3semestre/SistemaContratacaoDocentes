package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import controller.CSVInscricao;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;


public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
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
	public TelaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Cursos");
		btnNewButton.setBounds(20, 76, 188, 62);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Disciplinas");
		btnNewButton_1.setBounds(239, 76, 188, 62);
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Professor");
		btnNewButton_3.setBounds(239, 167, 188, 62);
		btnNewButton_3.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("Inscrição");
		btnNewButton_2.setBounds(20, 167, 188, 62);
		btnNewButton_2.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(btnNewButton_2);
		
		JLabel txtOpcao = new JLabel("Escolha uma opção");
		txtOpcao.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		txtOpcao.setBounds(89, 18, 278, 38);
		contentPane.add(txtOpcao);
		
		ActionListener actListenerC = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCursos.main(null);
				dispose();
			}
		};
		
		ActionListener actListenerD = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaDisciplinas.main(null);
				dispose();
			}
		};
		ActionListener actListenerP = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaProfessor.main(null);
				dispose();
			}
		};
		ActionListener actListenerI = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					TelaInscricoes.main(CSVInscricao.getInscricao(),0,0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				dispose();
			}
		};
		
		
		btnNewButton.addActionListener(actListenerC);
		btnNewButton_1.addActionListener(actListenerD);
		btnNewButton_3.addActionListener(actListenerP);
		btnNewButton_2.addActionListener(actListenerI);
		
	}
	
}
