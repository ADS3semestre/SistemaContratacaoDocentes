package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CSVCursos;
import model.Cursos;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TelaManterCursos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomeCurso;
	private JLabel txtpnDigiteO;
	private JTextField codCurso;
	private JLabel txtpnDigiteODia;
	private JButton btnEnviar;
	private JTextField areaCurso;

	/**
	 * Launch the application.
	 */
	public static void main(Boolean isEditMode, Cursos curso, int i) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaManterCursos frame = new TelaManterCursos(isEditMode, curso, i);
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
	public TelaManterCursos(Boolean isEditMode, Cursos curso, int i) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txtpnAdicionarDisciplina = new JLabel();
		txtpnAdicionarDisciplina.setBackground(new Color(237, 238, 238));
		if(isEditMode){
			txtpnAdicionarDisciplina.setText("Editar Cursos");
		}else {
			txtpnAdicionarDisciplina.setText("Adicionar Cursos");
		}
		
		txtpnAdicionarDisciplina.setFont(new Font("Arial", Font.PLAIN, 24));
		txtpnAdicionarDisciplina.setBounds(218, 6, 181, 31);
		contentPane.add(txtpnAdicionarDisciplina);
		
		JLabel txtNomeCurso = new JLabel();
		txtNomeCurso.setText("Nome do curso");
		txtNomeCurso.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNomeCurso.setBackground(new Color(238, 238, 238));
		txtNomeCurso.setBounds(20, 51, 132, 37);
		contentPane.add(txtNomeCurso);
		
		nomeCurso = new JTextField();
		nomeCurso.setBounds(157, 49, 421, 37);
		contentPane.add(nomeCurso);
		nomeCurso.setColumns(10);
		
		txtpnDigiteO = new JLabel();
		txtpnDigiteO.setText("Código do curso");
		txtpnDigiteO.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnDigiteO.setBackground(UIManager.getColor("Button.background"));
		txtpnDigiteO.setBounds(20, 102, 132, 37);
		contentPane.add(txtpnDigiteO);
		
		codCurso = new JTextField();
		codCurso.setColumns(10);
		codCurso.setBounds(157, 100, 421, 37);
		contentPane.add(codCurso);
		
		txtpnDigiteODia = new JLabel();
		txtpnDigiteODia.setText("Área do curso");
		txtpnDigiteODia.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnDigiteODia.setBackground(UIManager.getColor("Button.background"));
		txtpnDigiteODia.setBounds(20, 153, 132, 37);
		contentPane.add(txtpnDigiteODia);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBackground(new Color(255, 255, 255));
		btnEnviar.setBounds(461, 207, 117, 29);
		contentPane.add(btnEnviar);
		
		areaCurso = new JTextField();
		areaCurso.setColumns(10);
		areaCurso.setBounds(157, 153, 421, 37);
		contentPane.add(areaCurso);
		
		//se for modo de edição, ele preenche os campos
		if(isEditMode){
			nomeCurso.setText(curso.getNome());
			areaCurso.setText(curso.getArea());
			codCurso.setText(Integer.toString(curso.getCodigo()));
		}
		
		
		//envia aqui abaixo
		ActionListener actEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cursos cursoNew = new Cursos(nomeCurso.getText(),areaCurso.getText(),Integer.parseInt(codCurso.getText()));
				if(isEditMode) {
					try {
						CSVCursos.updateCursos(cursoNew, i);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}else {
					try {
						CSVCursos.addCurso(cursoNew);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				TelaCursos.main(null);
				dispose();
			}
		};
		
		btnEnviar.addActionListener(actEnviar);
		
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon("./img/voltar.png"));
		btnVoltar.setBounds(6, 6, 92, 30);
		contentPane.add(btnVoltar);
		
		ActionListener actListenerBack = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCursos.main(null);
				dispose();
			}
		};
		
		btnVoltar.addActionListener(actListenerBack);
	}
}
