package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class TelaDisciplinas_Inscritos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDisciplinas_Inscritos frame = new TelaDisciplinas_Inscritos();
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
	public TelaDisciplinas_Inscritos() {
		setBounds(100, 100, 550, 349);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtInscricoes = new JTextPane();
		txtInscricoes.setEditable(false);
		txtInscricoes.setBackground(new Color(238, 238, 238));
		txtInscricoes.setFont(new Font("Arial", Font.PLAIN, 24));
		txtInscricoes.setText("Inscritos da Disciplina");
		txtInscricoes.setBounds(163, 10, 236, 28);
		contentPane.add(txtInscricoes);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(6, 74, 538, 127);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextPane txtNome = new JTextPane();
		txtNome.setBounds(6, 6, 270, 19);
		panel.add(txtNome);
		txtNome.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNome.setText("Nome");
		
		JTextPane txtCPF = new JTextPane();
		txtCPF.setBounds(6, 25, 75, 16);
		panel.add(txtCPF);
		txtCPF.setText("CPF");
		txtCPF.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JTextPane txtCod = new JTextPane();
		txtCod.setText("CodigoProcesso");
		txtCod.setFont(new Font("Arial", Font.PLAIN, 13));
		txtCod.setBounds(6, 43, 110, 16);
		panel.add(txtCod);
		
		JTextPane txtPont = new JTextPane();
		txtPont.setText("Pontuacao");
		txtPont.setFont(new Font("Arial", Font.PLAIN, 13));
		txtPont.setBounds(6, 62, 110, 16);
		panel.add(txtPont);
		
		JTextPane txtCodDis = new JTextPane();
		txtCodDis.setText("CodigoDisciplina");
		txtCodDis.setFont(new Font("Arial", Font.PLAIN, 13));
		txtCodDis.setBounds(6, 80, 110, 16);
		panel.add(txtCodDis);
		
		JTextPane txtPontos = new JTextPane();
		txtPontos.setText("Status");
		txtPontos.setFont(new Font("Arial", Font.PLAIN, 13));
		txtPontos.setBounds(6, 100, 110, 16);
		panel.add(txtPontos);
		
		JLabel lblDisciplina = new JLabel("New label");
		lblDisciplina.setBounds(184, 42, 182, 16);
		contentPane.add(lblDisciplina);
		lblDisciplina.setHorizontalAlignment(JLabel.CENTER);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon("./img/voltar.png"));
		btnVoltar.setBounds(6, 6, 92, 30);
		contentPane.add(btnVoltar);
		
		ActionListener actListenerBack = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaDisciplinas.main(null);
				dispose();
			}
		};
		
		btnVoltar.addActionListener(actListenerBack);
	}
}
