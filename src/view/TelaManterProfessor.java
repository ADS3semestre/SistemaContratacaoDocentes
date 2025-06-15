package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CSVProfessor;
import model.Professor;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TelaManterProfessor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeProf;
	private JLabel txtpnDigiteO;
	private JTextField cpfProf;
	private JLabel txtPontuacao;
	private JTextField pontosProf;
	private JButton btnEnvia;
	private JTextField areaProf;

	/**
	 * Launch the application.
	 */
	public static void main(Boolean isEditMode, Professor prof, int i) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaManterProfessor frame = new TelaManterProfessor(isEditMode, prof, i);
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
	public TelaManterProfessor(Boolean isEditMode, Professor prof, int i) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txtpnAdicionarDisciplina = new JLabel();
		txtpnAdicionarDisciplina.setBackground(new Color(237, 238, 238));
		if(isEditMode){
			txtpnAdicionarDisciplina.setText("Editar Professor");
		}else {
			txtpnAdicionarDisciplina.setText("Adicionar Professor");
		}
		txtpnAdicionarDisciplina.setFont(new Font("Arial", Font.PLAIN, 24));
		txtpnAdicionarDisciplina.setBounds(198, 6, 213, 31);
		contentPane.add(txtpnAdicionarDisciplina);
		
		JLabel txtpnDisciplina = new JLabel();
		txtpnDisciplina.setText("Digite o nome do professor");
		txtpnDisciplina.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnDisciplina.setBackground(new Color(238, 238, 238));
		txtpnDisciplina.setBounds(20, 51, 132, 37);
		contentPane.add(txtpnDisciplina);
		
		nomeProf = new JTextField();
		nomeProf.setBounds(157, 49, 421, 37);
		contentPane.add(nomeProf);
		nomeProf.setColumns(10);
		
		txtpnDigiteO = new JLabel();
		txtpnDigiteO.setText("CPF do professor");
		txtpnDigiteO.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnDigiteO.setBackground(UIManager.getColor("Button.background"));
		txtpnDigiteO.setBounds(20, 102, 132, 37);
		contentPane.add(txtpnDigiteO);
		
		cpfProf = new JTextField();
		cpfProf.setColumns(10);
		cpfProf.setBounds(157, 100, 421, 37);
		contentPane.add(cpfProf);
		
		txtPontuacao = new JLabel();
		txtPontuacao.setText("Pontuação do professor");
		txtPontuacao.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPontuacao.setBackground(UIManager.getColor("Button.background"));
		txtPontuacao.setBounds(20, 147, 132, 35);
		contentPane.add(txtPontuacao);
		
		pontosProf = new JTextField();
		pontosProf.setToolTipText("Digite o horário aqui");
		pontosProf.setColumns(10);
		pontosProf.setBounds(157, 145, 421, 37);
		contentPane.add(pontosProf);
		
		btnEnvia = new JButton("Enviar");
		btnEnvia.setBackground(new Color(255, 255, 255));
		btnEnvia.setBounds(461, 252, 117, 29);
		contentPane.add(btnEnvia);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon("./img/voltar.png"));
		btnVoltar.setBounds(6, 6, 92, 30);
		contentPane.add(btnVoltar);
		
		JLabel txtareaProf = new JLabel();
		txtareaProf.setText("Área");
		txtareaProf.setFont(new Font("Arial", Font.PLAIN, 14));
		txtareaProf.setBackground(UIManager.getColor("Button.background"));
		txtareaProf.setBounds(20, 196, 132, 35);
		contentPane.add(txtareaProf);
		
		areaProf = new JTextField();
		areaProf.setToolTipText("Digite o horário aqui");
		areaProf.setColumns(10);
		areaProf.setBounds(157, 194, 421, 37);
		contentPane.add(areaProf);
		
		if(isEditMode) {
			cpfProf.setText(prof.getCPF());
			nomeProf.setText(prof.getNome());
			pontosProf.setText(Double.toString(prof.getQuantidadePontos()));
			areaProf.setText(prof.getArea());
		}
		
		ActionListener actListenerEnvia = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Professor profNew = new Professor(nomeProf.getText(),cpfProf.getText(),Double.parseDouble(pontosProf.getText()), areaProf.getText());
				if(isEditMode) {
					try {
						CSVProfessor.updateProfessor(profNew, i);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					try {
						CSVProfessor.addProfessor(profNew);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				TelaProfessor.main(null);
				dispose();
			}
		};
		
		btnEnvia.addActionListener(actListenerEnvia);
		
		
		ActionListener actListenerBack = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaProfessor.main(null);
				dispose();
			}
		};
		
		btnVoltar.addActionListener(actListenerBack);
	}
}
