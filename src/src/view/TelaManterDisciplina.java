package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.destny.model.ListaLib;

import control.CSVController;
import model.Cursos;
import model.Disciplinas;

import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class TelaManterDisciplina extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeDisciplina;
	private JTextPane txtpnDigiteO;
	private JTextField codDisciplina;
	private JTextPane txtpnDigiteODia;
	private JComboBox diaSemana;
	private JTextPane txtpnDigiteOHorrio;
	private JTextField horarioDisciplina;
	private JTextPane txtpnDigiteAQuantidade;
	private JTextField qtdHrsSemanais;
	private JTextPane txtpnSelecioneOCurso;
	private JComboBox cursoDisciplina;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(Boolean isEditMode) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaManterDisciplina frame = new TelaManterDisciplina(isEditMode);
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
	public TelaManterDisciplina(Boolean isEditMode) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnAdicionarDisciplina = new JTextPane();
		txtpnAdicionarDisciplina.setBackground(new Color(237, 238, 238));
		if(isEditMode){
			txtpnAdicionarDisciplina.setText("Editar Disciplina");
		}else {
			txtpnAdicionarDisciplina.setText("Adicionar Disciplina");
		}
		txtpnAdicionarDisciplina.setFont(new Font("Arial", Font.PLAIN, 24));
		txtpnAdicionarDisciplina.setBounds(198, 6, 206, 31);
		contentPane.add(txtpnAdicionarDisciplina);
		
		JTextPane txtpnDisciplina = new JTextPane();
		txtpnDisciplina.setEditable(false);
		txtpnDisciplina.setText("Nome da disciplina");
		txtpnDisciplina.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnDisciplina.setBackground(new Color(238, 238, 238));
		txtpnDisciplina.setBounds(20, 51, 132, 37);
		contentPane.add(txtpnDisciplina);
		
		nomeDisciplina = new JTextField();
		nomeDisciplina.setBounds(157, 49, 421, 37);
		contentPane.add(nomeDisciplina);
		nomeDisciplina.setColumns(10);
		
		txtpnDigiteO = new JTextPane();
		txtpnDigiteO.setEditable(false);
		txtpnDigiteO.setText("Código da disciplina");
		txtpnDigiteO.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnDigiteO.setBackground(UIManager.getColor("Button.background"));
		txtpnDigiteO.setBounds(20, 102, 132, 37);
		contentPane.add(txtpnDigiteO);
		
		codDisciplina = new JTextField();
		codDisciplina.setColumns(10);
		codDisciplina.setBounds(157, 100, 421, 37);
		contentPane.add(codDisciplina);
		
		txtpnDigiteODia = new JTextPane();
		txtpnDigiteODia.setEditable(false);
		txtpnDigiteODia.setText("Dia da semana");
		txtpnDigiteODia.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnDigiteODia.setBackground(UIManager.getColor("Button.background"));
		txtpnDigiteODia.setBounds(20, 153, 132, 37);
		contentPane.add(txtpnDigiteODia);
		
		diaSemana = new JComboBox();
		diaSemana.setModel(new DefaultComboBoxModel(new String[] {"Domingo", "Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado"}));
		diaSemana.setFont(new Font("Arial", Font.PLAIN, 16));
		diaSemana.setBounds(157, 149, 421, 41);
		contentPane.add(diaSemana);
		
		txtpnDigiteOHorrio = new JTextPane();
		txtpnDigiteOHorrio.setEditable(false);
		txtpnDigiteOHorrio.setText("Horário inicial da disciplina");
		txtpnDigiteOHorrio.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnDigiteOHorrio.setBackground(UIManager.getColor("Button.background"));
		txtpnDigiteOHorrio.setBounds(20, 202, 132, 35);
		contentPane.add(txtpnDigiteOHorrio);
		
		horarioDisciplina = new JTextField();
		horarioDisciplina.setToolTipText("Digite o horário aqui");
		horarioDisciplina.setColumns(10);
		horarioDisciplina.setBounds(157, 200, 421, 37);
		contentPane.add(horarioDisciplina);
		
		txtpnDigiteAQuantidade = new JTextPane();
		txtpnDigiteAQuantidade.setEditable(false);
		txtpnDigiteAQuantidade.setText("Quantidade de horas semanais");
		txtpnDigiteAQuantidade.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnDigiteAQuantidade.setBackground(UIManager.getColor("Button.background"));
		txtpnDigiteAQuantidade.setBounds(20, 251, 132, 37);
		contentPane.add(txtpnDigiteAQuantidade);
		
		qtdHrsSemanais = new JTextField();
		qtdHrsSemanais.setColumns(10);
		qtdHrsSemanais.setBounds(157, 249, 421, 37);
		contentPane.add(qtdHrsSemanais);
		
		txtpnSelecioneOCurso = new JTextPane();
		txtpnSelecioneOCurso.setEditable(false);
		txtpnSelecioneOCurso.setText("Curso");
		txtpnSelecioneOCurso.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnSelecioneOCurso.setBackground(UIManager.getColor("Button.background"));
		txtpnSelecioneOCurso.setBounds(20, 307, 132, 17);
		contentPane.add(txtpnSelecioneOCurso);
		
		cursoDisciplina = new JComboBox();
		ListaLib<Cursos> listaCurs = CSVController.getCSVdata("./files/disciplinas.csv");
		String[] cursos;
		for(int i=0; i<listaCurs.size(); i++) {
			Cursos curs = listaCurs.get(i);
			cursos[i] = curs.getNome();
		};
		
		cursoDisciplina.setModel(new DefaultComboBoxModel(cursos));
		cursoDisciplina.setBounds(156, 303, 423, 27);
		contentPane.add(cursoDisciplina);
		
		btnNewButton = new JButton("Enviar");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(461, 342, 117, 29);
		contentPane.add(btnNewButton);
		
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
		
		ActionListener actEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int curso;
				for(int j=0; j<listaCurs.size(); j++) {
					Cursos curs = listaCurs.get(j);
					if (cursoDisciplina.getSelectedItem().toString().equals(curs.getNome())){
						curso = curs.getCodigo();
					}
				};
				Disciplinas disc = new Disciplinas(codDisciplina.getText(),nomeDisciplina.getText(), diaSemana.getSelectedItem().toString(),LocalTime.parse(horarioDisciplina.getText()), Integer.parseInt(qtdHrsSemanais.getText()), cursoDisciplina.getSelectedItem().toString());
				System.out.println(disc.getNomeDisciplina());
				TelaDisciplinas.main(null);
				dispose();
			}
		};
		
		btnNewButton.addActionListener(actEnviar);
		
	}
}
