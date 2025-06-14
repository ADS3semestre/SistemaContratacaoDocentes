package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.destny.fila.Fila;
import com.destny.model.ListaLib;

import controller.CSVController;
import controller.CSVProfessor;
import model.Cursos;
import model.Professor;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TelaProfessor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fila<Professor> filaProf = CSVProfessor.getProfessor();
					TelaProfessor frame = new TelaProfessor(filaProf);
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
	public TelaProfessor(Fila<Professor> filaProf) throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdd = new JButton("+ Adicionar professor");
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.setBounds(6, 46, 538, 36);
		contentPane.add(btnAdd);
		
		ActionListener actListenerAdd = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Professor profNull = new Professor("","",0.0,"");
				TelaManterProfessor.main(false, profNull, 0);
				dispose();
			}
		};
		
		btnAdd.addActionListener(actListenerAdd);
		
		JLabel txtProf = new JLabel();
		txtProf.setBackground(new Color(238, 238, 238));
		txtProf.setFont(new Font("Arial", Font.PLAIN, 24));
		txtProf.setText("Professores");
		txtProf.setBounds(215, 6, 138, 28);
		contentPane.add(txtProf);
		
		int pos = 94;
		
		JPanel panelContainer = new JPanel();
		panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
		
		int tam = filaProf.Size();
		
		for(int i=0; i<tam; i++) {
					
				int posItn = 6;
				Professor prof = filaProf.Remove();
		
				JPanel panel = new JPanel();
				panel.setBackground(new Color(255, 255, 255));
				panel.setBounds(6, pos, 538, 73);
				contentPane.add(panel);
				panel.setLayout(null);
					
					JButton btnEdit = new JButton("Editar");
					btnEdit.setIcon(new ImageIcon("./img/edit.png"));
					btnEdit.setBounds(325, 16, 90, 36);
					panel.add(btnEdit);
					
					JButton btnDelete = new JButton("Apagar");
					btnDelete.setIcon(new ImageIcon("./img/delete.png"));
					btnDelete.setBounds(415, 16, 90, 36);
					panel.add(btnDelete);
					
					//----------------------------------------------------------
					
					JLabel txtNome = new JLabel("<html><div style='width:250px;'>Nome: " + prof.getNome()+"</html>");
					txtNome.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
					txtNome.setSize(300, Short.MAX_VALUE);
					txtNome.setBounds(6, posItn, 350, txtNome.getPreferredSize().height);
					panel.add(txtNome);
					
					posItn += txtNome.getPreferredSize().height;
					
					JLabel txtCPF = new JLabel("CPF: "+prof.getCPF());
					txtCPF.setBounds(6, posItn, 272, 16);
					panel.add(txtCPF);
					
					posItn += 16;
					
					JLabel txtPontos = new JLabel("Pontos: "+Double.toString(prof.getQuantidadePontos()));
					txtPontos.setBounds(6, posItn, 272, 16);
					panel.add(txtPontos);
					
					pos += 80;
					
					final int aux = i;
					
					ActionListener actListenerEdit = new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							TelaManterProfessor.main(true, prof, aux);
							dispose();
						}
					};
					
					btnEdit.addActionListener(actListenerEdit);
					
					ActionListener actListenerApaga = new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try {
								CSVProfessor.removeProfessor(aux);
							} catch (Exception e) {
								e.printStackTrace();
							}
							main(null);
							dispose();
						}
					};
					
					btnDelete.addActionListener(actListenerApaga);
					
					panelContainer.add(panel);
					panelContainer.add(Box.createVerticalStrut(8));
					panelContainer.setPreferredSize(new java.awt.Dimension(520,((txtNome.getPreferredSize().height+60)*tam)));
		}

		JScrollPane scrollPane = new JScrollPane(panelContainer);
		scrollPane.setBounds(6, 94, 533, 200);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		contentPane.add(scrollPane);
		
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
