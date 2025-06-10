package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.destny.model.ListaLib;
import model.Inscricao;
import model.Professor;
import control.CSVController;
import model.Disciplinas;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;

public class TelaInscricoes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaLib<Inscricao> listaInsc = CSVController.getDisciplinas();
					ListaLib<Professor> listaProf = CSVController.getCSVdata("./files/disciplinas.csv");
					ListaLib<Disciplinas> listaDisc = CSVController.getDisciplinas();
					TelaInscricoes frame = new TelaInscricoes(listaInsc, listaProf);
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
	public TelaInscricoes(ListaLib<Inscricao> listaInsc, ListaLib<Professor> listaProf,ListaLib<Disciplinas> listaDisc) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInsc = new JButton("+ Adicionar inscrição");
		btnInsc.setBackground(new Color(255, 255, 255));
		btnInsc.setBounds(6, 46, 538, 36);
		contentPane.add(btnInsc);
		
		JLabel txtInscricoes = new JLabel();
		txtInscricoes.setBackground(new Color(238, 238, 238));
		txtInscricoes.setFont(new Font("Arial", Font.PLAIN, 24));
		txtInscricoes.setText("Inscrições");
		txtInscricoes.setBounds(227, 6, 108, 28);
		contentPane.add(txtInscricoes);
		
		int pos = 137;
		
		for(int i=0; i<listaInsc.size(); i++) {
			
			Inscricao insc = listaInsc.get(i);
			int posItn = 6;
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(6, pos, 538, 124);
			contentPane.add(panel);
			panel.setLayout(null);
			
				JLabel txtNome = new JLabel();
				txtNome.setBounds(6, posItn, 78, 19);
				panel.add(txtNome);
				txtNome.setFont(new Font("Arial", Font.PLAIN, 16));
				posItn += 19;
				
				JLabel txtCPF = new JLabel();
				txtCPF.setBounds(6, posItn, 75, 16);
				panel.add(txtCPF);
				txtCPF.setText("CPF: " + insc.getCPF());
				txtCPF.setFont(new Font("Arial", Font.PLAIN, 13));
				posItn += 16;
				
				JLabel txtCod = new JLabel();
				txtCod.setText("Código do processo: " + insc.getCodProcesso());
				txtCod.setFont(new Font("Arial", Font.PLAIN, 13));
				txtCod.setBounds(6, posItn, 110, 16);
				panel.add(txtCod);
				posItn += 16;
				
				JLabel txtPont = new JLabel();
				txtPont.setFont(new Font("Arial", Font.PLAIN, 13));
				txtPont.setBounds(6, posItn, 110, 16);
				panel.add(txtPont);
				
				for (int j=0; j<listaProf.size(); j++) {
					Professor prof = listaProf.get(j);
					if(prof.getCPF() == insc.getCPF()) {
						txtNome.setText("Nome do professor: " + prof.getNome());
						txtPont.setText("Quantidade de pontos: "+Double.toString(prof.getQuantidadePontos()));
					}
				}
				
				
				JLabel txtCodDis = new JLabel();
				txtCodDis.setText("Código da disciplina: "+insc.getCodigoDisciplina());
				txtCodDis.setFont(new Font("Arial", Font.PLAIN, 13));
				txtCodDis.setBounds(6, posItn, 110, 16);
				panel.add(txtCodDis);
				
				int posBtn = 24;
				
				
				JButton btnNewButton_1 = new JButton("Editar");
				btnNewButton_1.setIcon(new ImageIcon("./img/edit.png"));
				btnNewButton_1.setBounds(437, posBtn, 90, 36);
				panel.add(btnNewButton_1);
				posBtn += 40;
				
				JButton btnNewButton_1_1 = new JButton("Apagar");
				btnNewButton_1_1.setIcon(new ImageIcon("./img/delete.png"));
				btnNewButton_1_1.setBounds(437, posBtn, 90, 36);
				panel.add(btnNewButton_1_1);
				
				pos += 145;
				
				ActionListener actListenerAdd = new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						TelaManterInscricao.main(false);
						dispose();
					}
				};
				
				ActionListener actListenerEdit = new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						TelaManterInscricao.main(true);
						dispose();
					}
				};
				
				btnInsc.addActionListener(actListenerAdd);
				btnNewButton_1.addActionListener(actListenerEdit);
		
		}
		
		String[] vet = new String[listaDisc.size()];
		for(int i=0; i<listaDisc.size(); i++) {
			Disciplinas disc = listaDisc.get(i);
			vet[i] = disc.getNomeDisciplina();
		}
		
		JComboBox comboDisc = new JComboBox();
		comboDisc.setModel(new DefaultComboBoxModel(vet));
		comboDisc.setBounds(61, 98, 235, 27);
		contentPane.add(comboDisc);
		
		JComboBox comboPontuacao = new JComboBox();
		comboPontuacao.setModel(new DefaultComboBoxModel(new String[] {"Pontuação - Padrão", "Pontuação - Crescente", "Pontuação - Decrescente"}));
		comboPontuacao.setBounds(308, 98, 236, 27);
		contentPane.add(comboPontuacao);
		
		JLabel txtFiltros = new JLabel();
		txtFiltros.setText("Filtros");
		txtFiltros.setFont(new Font("Arial", Font.PLAIN, 13));
		txtFiltros.setBackground(UIManager.getColor("Button.background"));
		txtFiltros.setBounds(16, 102, 43, 16);
		contentPane.add(txtFiltros);
		
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon("./img/voltar.png"));
		btnVoltar.setBounds(6, 6, 92, 30);
		contentPane.add(btnVoltar);
		
		
		
		ActionListener actListenerBack = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaInicial.main(null);
				dispose();
			}
		};
		
		btnVoltar.addActionListener(actListenerBack);
		
		
	}
}
