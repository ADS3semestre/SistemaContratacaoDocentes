package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.destny.model.ListaLib;

import control.CSVController;
import model.Cursos;
import model.Professor;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TelaProfessor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static int pos = 94;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaLib<Professor> listaProf = CSVController.getCSVdata("./files/disciplinas.csv");
					TelaProfessor frame = new TelaProfessor(listaProf);
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
	public TelaProfessor(ListaLib<Professor> listaProf) {
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
		
		JTextPane txtProf = new JTextPane();
		txtProf.setEditable(false);
		txtProf.setBackground(new Color(238, 238, 238));
		txtProf.setFont(new Font("Arial", Font.PLAIN, 24));
		txtProf.setText("Professores");
		txtProf.setBounds(215, 6, 138, 28);
		contentPane.add(txtProf);
		
		for(int i=0; i<listaProf.size(); i++) {
					
					int posItn = 6;
					Professor prof = listaProf.get(i);
		
					JPanel panel = new JPanel();
					panel.setBackground(new Color(255, 255, 255));
					panel.setBounds(6, pos, 538, 73);
					contentPane.add(panel);
					panel.setLayout(null);
					
					JButton btnEdit = new JButton("");
					btnEdit.setIcon(new ImageIcon("./img/edit.png"));
					btnEdit.setBounds(344, 16, 90, 36);
					panel.add(btnEdit);
					
					JButton btnDelete = new JButton("");
					btnDelete.setIcon(new ImageIcon("./img/delete.png"));
					btnDelete.setBounds(439, 16, 90, 36);
					panel.add(btnDelete);
					
					//----------------------------------------------------------
					
					JLabel txtNome = new JLabel(prof.getNome());
					txtNome.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
					txtNome.setBounds(6, posItn, 272, 16);
					panel.add(txtNome);
					
					posItn += 16;
					
					JLabel txtCPF = new JLabel(prof.getCPF());
					txtCPF.setBounds(6, posItn, 272, 16);
					panel.add(txtCPF);
					
					posItn += 16;
					
					JLabel txtPontos = new JLabel(Double.toString(prof.getQuantidadePontos()));
					txtPontos.setBounds(6, posItn, 272, 16);
					panel.add(txtPontos);
					
					pos += 80;
		
		}
		
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
		
		ActionListener actListenerAdd = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaManterProfessor.main(false);
				dispose();
			}
		};
		
		ActionListener actListenerEdit = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaManterProfessor.main(true);
				dispose();
			}
		};
		
		btnAdd.addActionListener(actListenerAdd);
		btnEdit.addActionListener(actListenerAdd);
	}
}
