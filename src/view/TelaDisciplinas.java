package view;

import java.awt.EventQueue;

import com.destny.fila.Fila;
import com.destny.model.ListaLib;

import controller.*;
import view.TelaManterDisciplina;
import model.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TelaDisciplinas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fila<Disciplinas> filaDisc = CSVDisciplinas.getDisciplinas();
					TelaDisciplinas frame = new TelaDisciplinas(filaDisc);
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
	public TelaDisciplinas(Fila<Disciplinas> filaDisc) throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdd = new JButton("+ Adicionar disciplina");
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.setBounds(6, 46, 538, 36);
		contentPane.add(btnAdd);
		
		ActionListener actListenerAdd = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Disciplinas disciplina = new Disciplinas("","","",null,0,"");
				TelaManterDisciplina.main(false,disciplina, 0);
				dispose();
			}
		};
		
		btnAdd.addActionListener(actListenerAdd);
		
		int pos = 94;
		int tam = filaDisc.Size();
		// Painel que irá conter os painéis das disciplinas
		
		JPanel panelContainer = new JPanel();
		panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));


		for (int i = 0; i < tam; i++) {
			Disciplinas disc = filaDisc.Remove();

			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			//panel.setBounds(6, pos, 538, 123);
			panel.setPreferredSize(new java.awt.Dimension(1024, 123));
			panel.setMaximumSize(new java.awt.Dimension(1024, 123));
			panel.setLayout(null);
			

			JButton btnEdicao = new JButton("Editar");
			btnEdicao.setIcon(new ImageIcon("./img/edit.png"));
			btnEdicao.setBounds(395, 6, 110, 36);
			panel.add(btnEdicao);

			JButton btnInsc = new JButton("Inscritos");
			btnInsc.setIcon(new ImageIcon("./img/alunos.png"));
			btnInsc.setBounds(395, 42, 110, 36);
			panel.add(btnInsc);

			JButton btnApaga = new JButton("Apagar");
			btnApaga.setIcon(new ImageIcon("./img/delete.png"));
			btnApaga.setBounds(395, 78, 110, 36);
			panel.add(btnApaga);
			
			final int posA = i;
			
			ActionListener actListenerEdit = new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					TelaManterDisciplina.main(true,disc,posA);
					dispose();
				}
			};
			
			ActionListener actListenerInsc = new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					TelaDisciplinas_Inscritos.main(null);
					dispose();
				}
			};
			
			final int val = i;
			
			ActionListener actListenerApaga = new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						CSVDisciplinas.removeDisciplina(val);
						main(null);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dispose();
				}
			};
			
			btnApaga.addActionListener(actListenerApaga);
			btnEdicao.addActionListener(actListenerEdit);
			btnInsc.addActionListener(actListenerInsc);
			
			//----------------------------------------------------
			
			int posBlock = 6;
			
			JLabel txtDisc = new JLabel("Nome da Disciplina: " + disc.getNomeDisciplina());
			txtDisc.setFont(new Font("Arial", Font.PLAIN, 16));
			txtDisc.setBounds(6, posBlock, 350, 16);
			panel.add(txtDisc);
			
			posBlock += 19;
			
			JLabel txtCodDisc = new JLabel("Código da Disciplina: " + disc.getCodigoDisciplina());
			txtCodDisc.setBounds(6, posBlock, 350, 16);
			panel.add(txtCodDisc);
			
			posBlock += 16;
			
			JLabel txtDiaSemana = new JLabel("Data ministrada: " + disc.getDataMinistrada());
			txtDiaSemana.setBounds(6, posBlock, 350, 16);
			panel.add(txtDiaSemana);
			
			posBlock += 16;
			
			JLabel txtHorarioIni = new JLabel("Horário inicial: " + disc.getHoraInicio().toString());
			txtHorarioIni.setBounds(6, posBlock, 350, 16);
			panel.add(txtHorarioIni);
			
			posBlock += 16;
			
			JLabel txtHorasDia = new JLabel("Horas por dia: " + Integer.toString(disc.getHorasDiarias()));
			txtHorasDia.setBounds(6, posBlock, 350, 16);
			panel.add(txtHorasDia);
			
			posBlock += 16;
			
			JLabel lblNewLabel = new JLabel("Código do curso: " + disc.getCodCurso());
			lblNewLabel.setBounds(6, posBlock, 350, 16);
			panel.add(lblNewLabel);
		
			pos += 135;

			panelContainer.add(panel);
			panelContainer.add(Box.createVerticalStrut(10)); 
		}

		// Coloca o painelContainer no JScrollPane
		JScrollPane scrollPane = new JScrollPane(panelContainer);
		scrollPane.setBounds(6, 94, 533, 200);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		contentPane.add(scrollPane);

		
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon("./img/voltar.png"));
		btnVoltar.setBounds(6, 6, 92, 30);
		contentPane.add(btnVoltar);
		
		JLabel lblNewLabel_1 = new JLabel("Disciplinas");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(219, 9, 125, 28);
		contentPane.add(lblNewLabel_1);
		
		ActionListener actListenerBack = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaInicial.main(null);
				dispose();
			}
		};
		
		btnVoltar.addActionListener(actListenerBack);
		
		}
}
