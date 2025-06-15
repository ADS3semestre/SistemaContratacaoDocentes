package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import com.destny.fila.Fila;

import controller.CSVInscricao;
import controller.CSVProfessor;
import model.Disciplinas;
import model.Inscricao;
import model.Professor;


public class TelaDisciplinas_Inscritos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(Disciplinas disc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDisciplinas_Inscritos frame = new TelaDisciplinas_Inscritos(disc);
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
	public TelaDisciplinas_Inscritos(Disciplinas disc) throws Exception {
		setBounds(100, 100, 550, 349);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txtInscricoes = new JLabel();
		txtInscricoes.setBackground(new Color(238, 238, 238));
		txtInscricoes.setFont(new Font("Arial", Font.PLAIN, 24));
		txtInscricoes.setText("Inscritos da Disciplina");
		txtInscricoes.setBounds(163, 10, 236, 28);
		contentPane.add(txtInscricoes);
		
		JPanel panelContainer = new JPanel();
		panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
		
		Fila<Inscricao> filaInscDisc = CSVInscricao.filterByName(disc.getNomeDisciplina(),CSVInscricao.getInscricao());
		
		int tam = filaInscDisc.Size();
		
		for(int i=0; i<tam; i++) {
			
			Inscricao insc = filaInscDisc.Remove();
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(6, 74, 538, 127);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JLabel txtNome = new JLabel();
			txtNome.setBounds(6, 6, 400, 19);
			panel.add(txtNome);
			txtNome.setFont(new Font("Arial", Font.PLAIN, 16));
			
			
			JLabel txtCPF = new JLabel();
			txtCPF.setBounds(6, 25, 400, 16);
			panel.add(txtCPF);
			txtCPF.setText("CPF: " + insc.getCPF());
			txtCPF.setFont(new Font("Arial", Font.PLAIN, 13));
			
			JLabel txtCod = new JLabel();
			txtCod.setText("Código do processo: " + insc.getCodProcesso());
			txtCod.setFont(new Font("Arial", Font.PLAIN, 13));
			txtCod.setBounds(6, 41, 400, 16);
			panel.add(txtCod);
			
			JLabel txtPont = new JLabel();
			txtPont.setFont(new Font("Arial", Font.PLAIN, 13));
			txtPont.setBounds(6, 57, 400, 16);
			panel.add(txtPont);
			
			JLabel txtCodDis = new JLabel();
			txtCodDis.setText("Código da Disciplina: " + insc.getCodigoDisciplina());
			txtCodDis.setFont(new Font("Arial", Font.PLAIN, 13));
			txtCodDis.setBounds(6, 73, 400, 16);
			panel.add(txtCodDis);
			
			Fila<Professor> filaProf = CSVProfessor.getProfessor();
			int tamProf = filaProf.Size();
			for(int j=0; j<tamProf; j++) {
				Professor p = filaProf.Remove();
				if(p.getCPF().equals(insc.getCPF())) {
					txtNome.setText("Nome: " + p.getNome());
					txtPont.setText("Quantidade de pontos: " + Double.toString(p.getQuantidadePontos()));
				}
			}
			
			
			panelContainer.add(panel);
			panelContainer.add(Box.createVerticalStrut(10));
			panelContainer.setPreferredSize(new java.awt.Dimension(520,((txtNome.getPreferredSize().height+120)*tam)));
		}
		
		
		JScrollPane scrollPane = new JScrollPane(panelContainer);
		scrollPane.setBounds(6, 94, 533, 200);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		contentPane.add(scrollPane);
		
		JLabel lblDisciplina = new JLabel(disc.getNomeDisciplina());
		lblDisciplina.setBounds(125, 42, 300, 16);
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
