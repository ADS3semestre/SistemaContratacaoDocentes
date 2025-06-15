package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.destny.fila.Fila;

import controller.CSVCursos;
import controller.CSVDisciplinas;
import model.Cursos;
import model.Disciplinas;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TelaManterDisciplina extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeDisciplina;
	private JLabel txtpnDigiteO;
	private JTextField codDisciplina;
	private JLabel txtpnDigiteODia;
	private JComboBox diaSemana;
	private JLabel txtpnDigiteOHorrio;
	private JTextField horarioDisciplina;
	private JLabel txtpnDigiteAQuantidade;
	private JTextField qtdHrsSemanais;
	private JLabel txtpnSelecioneOCurso;
	private JComboBox cursoDisciplina;
	private JButton btnEnviar;

	/**
	 * Launch the application.
	 */
	public static void main(Boolean isEditMode, Disciplinas disciplina, int pos) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					TelaManterDisciplina frame = new TelaManterDisciplina(isEditMode, disciplina,pos);
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
	public TelaManterDisciplina(Boolean isEditMode, Disciplinas disciplina, int pos) throws Exception{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txtpnAdicionarDisciplina = new JLabel();
		txtpnAdicionarDisciplina.setBackground(new Color(237, 238, 238));
		if (isEditMode) {
			txtpnAdicionarDisciplina.setText("Editar Disciplina");
		} else {
			txtpnAdicionarDisciplina.setText("Adicionar Disciplina");
		}
		
		
		txtpnAdicionarDisciplina.setFont(new Font("Arial", Font.PLAIN, 24));
		txtpnAdicionarDisciplina.setBounds(178, 6, 274, 31);
		contentPane.add(txtpnAdicionarDisciplina);
		
		JLabel txtpnDisciplina = new JLabel();
		txtpnDisciplina.setText("Nome da disciplina");
		txtpnDisciplina.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnDisciplina.setBackground(new Color(238, 238, 238));
		txtpnDisciplina.setBounds(20, 51, 132, 37);
		contentPane.add(txtpnDisciplina);
		
		nomeDisciplina = new JTextField();
		nomeDisciplina.setBounds(157, 49, 421, 37);
		contentPane.add(nomeDisciplina);
		nomeDisciplina.setColumns(10);
		
		txtpnDigiteO = new JLabel();
		txtpnDigiteO.setText("Código da disciplina");
		txtpnDigiteO.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnDigiteO.setBackground(UIManager.getColor("Button.background"));
		txtpnDigiteO.setBounds(20, 102, 132, 37);
		contentPane.add(txtpnDigiteO);
		
		codDisciplina = new JTextField();
		codDisciplina.setColumns(10);
		codDisciplina.setBounds(157, 100, 421, 37);
		contentPane.add(codDisciplina);
		
		txtpnDigiteODia = new JLabel();
		txtpnDigiteODia.setText("Dia da semana");
		txtpnDigiteODia.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnDigiteODia.setBackground(UIManager.getColor("Button.background"));
		txtpnDigiteODia.setBounds(20, 153, 132, 37);
		contentPane.add(txtpnDigiteODia);
		
		String[] diaSemanaStr = {"Domingo", "Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado"};
		diaSemana = new JComboBox();
		diaSemana.setModel(new DefaultComboBoxModel(diaSemanaStr));
		diaSemana.setFont(new Font("Arial", Font.PLAIN, 16));
		diaSemana.setBounds(157, 149, 421, 41);
		contentPane.add(diaSemana);
		
		txtpnDigiteOHorrio = new JLabel();
		txtpnDigiteOHorrio.setText("Horário inicial");
		txtpnDigiteOHorrio.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnDigiteOHorrio.setBackground(UIManager.getColor("Button.background"));
		txtpnDigiteOHorrio.setBounds(20, 202, 132, 35);
		contentPane.add(txtpnDigiteOHorrio);
		
		horarioDisciplina = new JTextField();
		horarioDisciplina.setToolTipText("Digite o horário aqui");
		horarioDisciplina.setColumns(10);
		horarioDisciplina.setBounds(157, 200, 421, 37);
		contentPane.add(horarioDisciplina);
		
		txtpnDigiteAQuantidade = new JLabel();
		txtpnDigiteAQuantidade.setText("Horas semanais");
		txtpnDigiteAQuantidade.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnDigiteAQuantidade.setBackground(UIManager.getColor("Button.background"));
		txtpnDigiteAQuantidade.setBounds(20, 251, 132, 37);
		contentPane.add(txtpnDigiteAQuantidade);
		
		qtdHrsSemanais = new JTextField();
		qtdHrsSemanais.setColumns(10);
		qtdHrsSemanais.setBounds(157, 249, 421, 37);
		contentPane.add(qtdHrsSemanais);
		
		txtpnSelecioneOCurso = new JLabel();
		txtpnSelecioneOCurso.setText("Curso");
		txtpnSelecioneOCurso.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnSelecioneOCurso.setBackground(UIManager.getColor("Button.background"));
		txtpnSelecioneOCurso.setBounds(20, 307, 132, 17);
		contentPane.add(txtpnSelecioneOCurso);
		
		cursoDisciplina = new JComboBox();
		Fila<Cursos> filaCurs = CSVCursos.getCursos();
		String[] cursos = new String[filaCurs.Size()];
		Cursos[] vetCursos = new Cursos[filaCurs.Size()];
		int tamAux = filaCurs.Size();
		for(int i=0; i<tamAux; i++) {
			vetCursos[i] = filaCurs.Remove();
			cursos[i] = vetCursos[i].getNome();
		};
		
		cursoDisciplina.setModel(new DefaultComboBoxModel(cursos));
		cursoDisciplina.setBounds(156, 303, 423, 27);
		contentPane.add(cursoDisciplina);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBackground(new Color(255, 255, 255));
		btnEnviar.setBounds(461, 342, 117, 29);
		contentPane.add(btnEnviar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon("./img/voltar.png"));
		btnVoltar.setBounds(6, 6, 92, 30);
		contentPane.add(btnVoltar);
		
		//-------------- Edição de dados --------------
		
		if(isEditMode) {
			nomeDisciplina.setText(disciplina.getNomeDisciplina());
			codDisciplina.setText(disciplina.getCodigoDisciplina());
			
			for(int i=0; i<diaSemanaStr.length; i++) {
				if (diaSemanaStr[i].equals(disciplina.getDataMinistrada())) {
					diaSemana.setSelectedIndex(i);
				}
			}
		
			horarioDisciplina.setText(disciplina.getHoraInicio().toString());
			qtdHrsSemanais.setText(Integer.toString(disciplina.getHorasDiarias()));
			String nomeCurso = "";
			int posCursoAux = filaCurs.Size();
			for (int j=0; j<posCursoAux; j++) {
				Cursos curs = filaCurs.Remove();
				if(disciplina.getCodCurso().equals(curs.getCodigo())) {
					nomeCurso = curs.getNome();
				}
			};
			for(int i=0; i<cursos.length; i++) {
				if (cursos[i].equals(nomeCurso)){
					cursoDisciplina.setSelectedIndex(i);
				}
			}
		};
		
		
		ActionListener actListenerBack = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaDisciplinas.main(null);
				dispose();
			}
		};
		
		btnVoltar.addActionListener(actListenerBack);
		
			ActionListener actEnviar = new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String curso = "";
					try {
						Fila<Cursos> filaCurs2 = CSVCursos.getCursos();
						int posCursoAux = filaCurs2.Size();
						for(int j=0; j<posCursoAux; j++) {
							Cursos c = filaCurs2.Remove();
							if (cursoDisciplina.getSelectedItem().toString().equals(c.getNome().toString())){
								curso = Integer.toString(c.getCodigo());
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					Disciplinas disc = new Disciplinas(nomeDisciplina.getText(),codDisciplina.getText(), diaSemana.getSelectedItem().toString(),LocalTime.parse(horarioDisciplina.getText()), Integer.parseInt(qtdHrsSemanais.getText()), curso);
					
					if(isEditMode) {
						try {
							CSVDisciplinas.updateDisciplina(disc, pos);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else {
						try {
							CSVDisciplinas.addDisciplina(disc);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					TelaDisciplinas.main(null);
					dispose();	
				}
			};
			
			btnEnviar.addActionListener(actEnviar);
			
		}
		
}


