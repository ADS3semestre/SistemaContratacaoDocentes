package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.CSVDisciplinas;
import controller.CSVInscricao;
import controller.CSVProfessor;
import model.Inscricao;
import model.Professor;
import model.Disciplinas;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import com.destny.fila.Fila;

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
					Fila<Inscricao> filaInsc = CSVInscricao.getInscricao();
					Fila<Disciplinas> filaDisc = CSVDisciplinas.getDisciplinas();
					TelaInscricoes frame = new TelaInscricoes(filaInsc, filaDisc);
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
	public TelaInscricoes(Fila<Inscricao> filaInsc,Fila<Disciplinas> filaDisc) throws Exception {
		
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
		
		ActionListener actListenerAdd = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inscricao emptyInsc = new Inscricao("","","");
				TelaManterInscricao.main(false, emptyInsc, 0);
				dispose();
			}
		};
		
		btnInsc.addActionListener(actListenerAdd);
		
		int pos = 137;
		
		JPanel panelContainer = new JPanel();
		panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
		
		int tam = filaInsc.Size();
		
		for(int i=0; i<tam; i++) {
			
			Fila<Professor> filaProf = CSVProfessor.getProfessor();
		
			Inscricao insc = filaInsc.Remove();
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(6, pos, 538, 124);
			contentPane.add(panel);
			panel.setLayout(null);
			
				JLabel txtNome = new JLabel();
				txtNome.setBounds(6, 10, 300, 19);
				panel.add(txtNome);
				txtNome.setFont(new Font("Arial", Font.PLAIN, 16));
				
				JLabel txtCodDis = new JLabel();
				txtCodDis.setText("Código da disciplina: "+insc.getCodigoDisciplina());
				txtCodDis.setFont(new Font("Arial", Font.PLAIN, 13));
				txtCodDis.setBounds(6, 29 , 300, 16);
				panel.add(txtCodDis);
				
				
				JLabel txtCPF = new JLabel();
				txtCPF.setBounds(6, 45, 330, 16);
				panel.add(txtCPF);
				txtCPF.setText("CPF: " + insc.getCPF());
				txtCPF.setFont(new Font("Arial", Font.PLAIN, 13));
				
				
				JLabel txtCod = new JLabel();
				txtCod.setText("Código do processo: " + insc.getCodProcesso());
				txtCod.setFont(new Font("Arial", Font.PLAIN, 13));
				txtCod.setBounds(6, 61, 300, 16);
				panel.add(txtCod);
				
				JLabel txtPont = new JLabel();
				txtPont.setFont(new Font("Arial", Font.PLAIN, 13));
				txtPont.setBounds(6, 78, 330, 16);
				panel.add(txtPont);
				
				int tam2 = filaProf.Size();
				
				for (int j=0; j<tam2; j++) {
					Professor prof = filaProf.Remove();
					if(insc.getCPF().equals(prof.getCPF())){
						txtNome.setText("Nome do professor: " + prof.getNome());
						txtPont.setText("Quantidade de pontos: "+Double.toString(prof.getQuantidadePontos()));
					}
				}
				
				
				JButton btnEdita = new JButton("Editar");
				btnEdita.setIcon(new ImageIcon("./img/edit.png"));
				btnEdita.setBounds(425, 10, 96, 36);
				panel.add(btnEdita);
				
				JButton btnApaga = new JButton("Apagar");
				btnApaga.setIcon(new ImageIcon("./img/delete.png"));
				btnApaga.setBounds(425, 45, 96, 36);
				panel.add(btnApaga);
				
				pos += 145;
				
				
				final int aux = i;
				
				ActionListener actListenerEdit = new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						TelaManterInscricao.main(true, insc, aux);
						dispose();
					}
				};
				
				ActionListener actListenerApaga = new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							CSVInscricao.removeInscricao(aux);
						} catch (Exception e) {
							e.printStackTrace();
						}
						main(null);
						dispose();
					}
				};
				
				btnApaga.addActionListener(actListenerApaga);
				btnEdita.addActionListener(actListenerEdit);
		
				panelContainer.add(panel);
				panelContainer.add(Box.createVerticalStrut(10));
				panelContainer.setPreferredSize(new java.awt.Dimension(520,((txtNome.getPreferredSize().height+110)*tam)));
		}
		
		// Coloca o painelContainer no JScrollPane
		JScrollPane scrollPane = new JScrollPane(panelContainer);
		scrollPane.setBounds(6, 130, 533, 170);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		contentPane.add(scrollPane);
		
		String[] vet = new String[filaDisc.Size()];
		for(int i=0; i<filaDisc.Size(); i++) {
			Disciplinas disc = filaDisc.Remove();
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
