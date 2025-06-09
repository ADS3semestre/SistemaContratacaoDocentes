package view;

import java.awt.EventQueue;
import com.destny.model.ListaLib;
import view.TelaManterDisciplina;
import model.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import control.*;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TelaDisciplinas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static int pos = 94;

	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaLib<Disciplinas> listaDisc = CSVController.getDisciplinas();
					TelaDisciplinas frame = new TelaDisciplinas(listaDisc);
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
	public TelaDisciplinas(ListaLib<Disciplinas> listaDisc) throws Exception {
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
		
			for(int i=0; i<listaDisc.size(); i++) {
				Disciplinas disc = listaDisc.get(i);
				
				JPanel panel = new JPanel();
				panel.setBackground(new Color(255, 255, 255));
				panel.setBounds(6, pos, 538, 123);
				contentPane.add(panel);
				panel.setLayout(null);
					
					int posBtn = 6;
					
					JButton btnEdicao = new JButton("Editar");
					btnEdicao.setIcon(new ImageIcon("./img/edit.png"));
					btnEdicao.setBounds(419, posBtn, 110, 36);
					panel.add(btnEdicao);
					
					posBtn += 36;
					
					JButton btnInsc = new JButton("Inscritos");
					btnInsc.setIcon(new ImageIcon("./img/alunos.png"));
					btnInsc.setBounds(419, posBtn, 110, 36);
					panel.add(btnInsc);
					
					posBtn += 36;
					
					JButton btnApaga = new JButton("Apagar");
					btnApaga.setIcon(new ImageIcon("./img/delete.png"));
					btnApaga.setBounds(419, posBtn, 110, 36);
					panel.add(btnApaga);
					
					ActionListener actListenerAdd = new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							TelaManterDisciplina.main(false);
							dispose();
						}
					};
					
					ActionListener actListenerEdit = new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							TelaManterDisciplina.main(true);
							dispose();
						}
					};
					
					ActionListener actListenerInsc = new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							TelaDisciplinas_Inscritos.main(null);
							dispose();
						}
					};
					
					btnAdd.addActionListener(actListenerAdd);
					btnEdicao.addActionListener(actListenerEdit);
					btnInsc.addActionListener(actListenerInsc);
					
					//----------------------------------------------------
					
					int posBlock = 6;
					
					JLabel txtDisc = new JLabel(disc.getNomeDisciplina());
					txtDisc.setFont(new Font("Arial", Font.PLAIN, 16));
					txtDisc.setBounds(6, posBlock, 238, 16);
					panel.add(txtDisc);
					
					posBlock += 19;
					
					JLabel txtCodDisc = new JLabel(disc.getCodigoDisciplina());
					txtCodDisc.setBounds(6, posBlock, 220, 16);
					panel.add(txtCodDisc);
					
					posBlock += 16;
					
					JLabel txtDiaSemana = new JLabel(disc.getCodigoDisciplina());
					txtDiaSemana.setBounds(6, posBlock, 220, 16);
					panel.add(txtDiaSemana);
					
					posBlock += 16;
					
					JLabel txtHorarioIni = new JLabel(disc.getHoraInicio().toString());
					txtHorarioIni.setBounds(6, posBlock, 220, 16);
					panel.add(txtHorarioIni);
					
					posBlock += 16;
					
					JLabel txtHorasDia = new JLabel(Integer.toString(disc.getHorasDiarias()));
					txtHorasDia.setBounds(6, posBlock, 220, 16);
					panel.add(txtHorasDia);
					
					posBlock += 16;
					
					JLabel lblNewLabel = new JLabel(disc.getCodCurso());
					lblNewLabel.setBounds(6, posBlock, 220, 16);
					panel.add(lblNewLabel);
				
				pos += 135;
			}
		
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
