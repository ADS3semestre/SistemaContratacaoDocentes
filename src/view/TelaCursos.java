package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.destny.fila.Fila;
import com.destny.model.ListaLib;

import controller.CSVController;
import controller.CSVCursos;
import model.Cursos;
import model.Disciplinas;

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

public class TelaCursos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fila<Cursos> filaCurs = CSVCursos.getCursos();
					TelaCursos frame = new TelaCursos(filaCurs);
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
	public TelaCursos(Fila<Cursos> filaCurs) throws Exception{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdd = new JButton("+ Adicionar curso");
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.setBounds(6, 46, 538, 36);
		contentPane.add(btnAdd);
		
		ActionListener actListenerAdd = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cursos emptyCurs = new Cursos("","",0);
				TelaManterCursos.main(false,emptyCurs, 0);
				dispose();
			}
		};
		
		btnAdd.addActionListener(actListenerAdd);
		
		int pos = 94;
		
		JPanel panelContainer = new JPanel();
		panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
		
		int tam = filaCurs.Size();
		for(int i=0; i<tam; i++) {
			
			Cursos curs = filaCurs.Remove();
		
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(6, pos, 538, 90);
			contentPane.add(panel);
			panel.setLayout(null);
			
				JButton btnEditar = new JButton("Editar");
				btnEditar.setIcon(new ImageIcon("./img/edit.png"));
				btnEditar.setBounds(325, 17, 90, 36);
				panel.add(btnEditar);
				
				JButton btnApagar = new JButton("Apagar");
				btnApagar.setIcon(new ImageIcon("./img/delete.png"));
				btnApagar.setBounds(420, 17, 90, 36);
				panel.add(btnApagar);
				
				//-------------------------------------------
				
				JLabel txtCurso = new JLabel("<html><div style='width:250px;'>Nome do curso: " + curs.getNome()+"</html>");
				txtCurso.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
				panel.add(txtCurso);
				txtCurso.setSize(300, Short.MAX_VALUE);
				txtCurso.setBounds(6, 6, 300, txtCurso.getPreferredSize().height);
				
				int posTxt = 6 + txtCurso.getPreferredSize().height + 4;
				
				
				JLabel txtCodigo = new JLabel("Código do curso: " + Integer.toString(curs.getCodigo()));
				txtCodigo.setBounds(6, posTxt, 350, 16);
				panel.add(txtCodigo);
				
				posTxt += 20;
				
				JLabel txtArea = new JLabel("Área do curso: " + curs.getArea());
				txtArea.setBounds(6, posTxt, 350, 16);
				panel.add(txtArea);
				
				pos += 90;
				
				final int aux = i;
				
				ActionListener actListenerEdit = new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						TelaManterCursos.main(true,curs, aux);
						dispose();
					}
				};
				
				btnEditar.addActionListener(actListenerEdit);
				
				
				ActionListener actListenerApaga = new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							CSVCursos.removeCursos(aux);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						main(null);
						dispose();
					}
				};
				
				btnApagar.addActionListener(actListenerApaga);
				
				panelContainer.add(panel);
				panelContainer.add(Box.createVerticalStrut(20));
				panelContainer.setPreferredSize(new java.awt.Dimension(520,((txtCurso.getPreferredSize().height+100)*tam)));

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
		
		JLabel txtCursos = new JLabel("Cursos");
		txtCursos.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		txtCursos.setBounds(238, 4, 92, 36);
		contentPane.add(txtCursos);
		
		ActionListener actListenerBack = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaInicial.main(null);
				dispose();
			}
		};
		
		btnVoltar.addActionListener(actListenerBack);
		
	}
	
	
}
