package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.destny.fila.Fila;

import controller.CSVDisciplinas;
import controller.CSVProfessor;
import controller.CSVInscricao;
import model.Disciplinas;
import model.Inscricao;
import model.Professor;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class TelaManterInscricao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel txtpnDigiteO;
	private JTextField codProcesso;
	private JLabel txtDisciplina;
	private JComboBox selecaoDisc;
	private JComboBox selecaoProf;
	private JButton btnEnvia;

	/**
	 * Launch the application.
	 */
	public static void main(Boolean isEditMode, Inscricao insc, int i) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaManterInscricao frame = new TelaManterInscricao(isEditMode, insc, i);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public TelaManterInscricao(Boolean isEditMode, Inscricao insc, int i) throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txtpnAdicionarDisciplina = new JLabel();
		txtpnAdicionarDisciplina.setBackground(new Color(237, 238, 238));
		if(isEditMode){
			txtpnAdicionarDisciplina.setText("Editar Inscrição");
		}else {
			txtpnAdicionarDisciplina.setText("Adicionar Inscrição");
		}
		txtpnAdicionarDisciplina.setFont(new Font("Arial", Font.PLAIN, 24));
		txtpnAdicionarDisciplina.setBounds(198, 6, 206, 31);
		contentPane.add(txtpnAdicionarDisciplina);
		
		JLabel txtProfessor = new JLabel();
		txtProfessor.setText("Professor");
		txtProfessor.setFont(new Font("Arial", Font.PLAIN, 14));
		txtProfessor.setBackground(new Color(238, 238, 238));
		txtProfessor.setBounds(20, 51, 132, 37);
		contentPane.add(txtProfessor);
		
		
		
		selecaoProf = new JComboBox();
			Fila<Professor> filaProf = CSVProfessor.getProfessor();
			int tamprof = filaProf.Size();
			String[] professor = new String[tamprof];
			for(int j=0; j<tamprof; j++) {
				Professor p = filaProf.Remove();
				professor[j]  =  p.getNome();
			}
		selecaoProf.setModel(new DefaultComboBoxModel(professor));
		selecaoProf.setFont(new Font("Arial", Font.PLAIN, 16));
		selecaoProf.setBounds(157, 49, 421, 41);
		contentPane.add(selecaoProf);
		
		
		txtpnDigiteO = new JLabel();
		txtpnDigiteO.setText("Código do processo");
		txtpnDigiteO.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnDigiteO.setBackground(UIManager.getColor("Button.background"));
		txtpnDigiteO.setBounds(20, 102, 132, 37);
		contentPane.add(txtpnDigiteO);
		
		codProcesso = new JTextField();
		codProcesso.setColumns(10);
		codProcesso.setBounds(157, 100, 421, 37);
		contentPane.add(codProcesso);
	
		txtDisciplina = new JLabel();
		txtDisciplina.setText("Disciplina");
		txtDisciplina.setFont(new Font("Arial", Font.PLAIN, 14));
		txtDisciplina.setBackground(UIManager.getColor("Button.background"));
		txtDisciplina.setBounds(20, 153, 132, 37);
		contentPane.add(txtDisciplina);
		
		selecaoDisc = new JComboBox();
		Fila<Disciplinas> filaDisc = CSVDisciplinas.getDisciplinas();
		int tamdisc = filaDisc.Size();
		String[] disciplinas = new String[tamdisc];
		for(int j=0; j<tamdisc; j++) {
			Disciplinas d = filaDisc.Remove();
			disciplinas[j]  =  d.getNomeDisciplina();
		}
		
		selecaoDisc.setModel(new DefaultComboBoxModel(disciplinas));
		selecaoDisc.setFont(new Font("Arial", Font.PLAIN, 16));
		selecaoDisc.setBounds(157, 149, 421, 41);
		contentPane.add(selecaoDisc);
		
		btnEnvia = new JButton("Enviar");
		btnEnvia.setBackground(new Color(255, 255, 255));
		btnEnvia.setBounds(461, 204, 117, 29);
		contentPane.add(btnEnvia);
		
		ActionListener actListenerEnvia = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String codDisc = "";
				String cpf = "";
				Fila<Disciplinas> filaDisc2 = CSVDisciplinas.getDisciplinas();
				int tamdiscaux = filaDisc2.Size();
				for(int j=0;j<tamdiscaux;j++) {
					Disciplinas d = new Disciplinas("","","",null,0,"");
					try {
						d = filaDisc2.Remove();
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(selecaoDisc.getSelectedItem().toString().equals(d.getNomeDisciplina())) {
						codDisc = d.getCodigoDisciplina(); 
					}
				}
				
				Fila<Professor> filaProf2 = CSVProfessor.getProfessor();
				int tamprofaux = filaProf2.Size();
				for(int j=0;j<tamprofaux;j++) {
					Professor p = new Professor("","",0.0,"");
					try {
						p = filaProf2.Remove();
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(selecaoProf.getSelectedItem().toString().equals(p.getNome())) {
						cpf = p.getCPF();
					}
				}
				Inscricao inscNew = new Inscricao(codProcesso.getText(),cpf,codDisc);
				CSVInscricao.addInscricao(inscNew);
				
				TelaInscricoes.main(null);
				dispose();
			}
		};
		
		btnEnvia.addActionListener(actListenerEnvia);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon("./img/voltar.png"));
		btnVoltar.setBounds(6, 6, 92, 30);
		contentPane.add(btnVoltar);
		
		ActionListener actListenerBack = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaInscricoes.main(null);
				dispose();
			}
		};
		
		btnVoltar.addActionListener(actListenerBack);
	}
}
