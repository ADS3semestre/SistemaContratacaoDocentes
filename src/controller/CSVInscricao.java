package controller;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.destny.fila.Fila;
import com.destny.model.ListaLib;

import model.Disciplinas;
import model.Inscricao;
import model.Professor;

public class CSVInscricao {

    // Get Inscrição -- Read
    
    public static Fila<Inscricao> getInscricao() {
        Fila<Inscricao> inscricao = new Fila<>();
        BufferedReader reader = null;
        String line = "";
        String fileName = CSVController.getFileName("inscricoes.csv");

        try {
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                Inscricao insc = new Inscricao(row[0], row[1], row[2]);
                inscricao.Insert(insc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return inscricao;
    }
    
    public static ListaLib<Inscricao>getListaInsc() throws Exception {
    	Fila <Inscricao> inscric = getInscricao();
    	ListaLib<Inscricao> inscricao = new ListaLib<>();
    	int t = inscric.Size();
    	for (int i = 0; i < t; i++) {
    		inscricao.addLast(inscric.Remove());
    	}
    	return inscricao;
    }

    public static String generateCodProc(String codDisc){
        String codProc = "";
        int num = 100 + (int)(Math.random() * 900);
        codProc = codDisc + Integer.toString(num);
        return codProc;
    }

    
    // Get Inscrição -- Create

    public static void addInscricao(Inscricao insc) {

        String line = "";
        String fileName = CSVController.getFileName("inscricoes.csv");

        String cInscProc = insc.getCodProcesso();
        String cpfInsc = insc.getCPF();
        String cInscDisc = insc.getCodigoDisciplina();

        line = ("\n" + cInscProc + "," + cpfInsc + "," + cInscDisc);

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.append(line);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update All Inscrição
    
    private static void updateAllInscricao(ListaLib<Inscricao> inscricao) throws Exception {
    	String fileName = CSVController.getFileName("inscricoes.csv");
    	String cabecalho = "Código do Processo,CPF,Código da Disciplina";
    	int t = inscricao.size();
    	
    	try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
    		writer.append(cabecalho);
    		for (int tamanho = 0; tamanho < t; tamanho++) {
    			Inscricao inscri = inscricao.get(tamanho);
    			
    			String cInscProc = inscri.getCodProcesso();
    			String cpfInsc = inscri.getCPF();
    			String cInscDisc = inscri.getCodigoDisciplina();
    			
    			String line = ("\n" + cInscProc + "," + cpfInsc + "," + cInscDisc);
    			writer.append(line);
    		}
    		writer.flush();
    		writer.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    // Remove Inscrição - Delete
    
    public static void removeInscricao(int i) throws Exception {
    	ListaLib<Inscricao> inscricao = new ListaLib<>();
    	inscricao = getListaInsc();
    	try {
    		inscricao.remove(i);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		updateAllInscricao(inscricao);
    	}
    }
    
    // Update Inscrição - Update
    
    public static void updateInscricao(Inscricao insc, int i) throws Exception {
    	ListaLib<Inscricao> inscricao = new ListaLib<>();
    	inscricao = getListaInsc();
    	try {
    		inscricao.remove(i);
    		inscricao.add(insc, i);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		updateAllInscricao(inscricao);
    	}
    }
    
    public static Fila<Inscricao> filterByName(String nome) throws Exception {
				Fila<Disciplinas> filaDisc = CSVDisciplinas.getDisciplinas();
				Fila<Inscricao> filaInscFiltro = new Fila<>();
				Fila<Inscricao> filaInsc2 = getInscricao();
				int tamAux = filaDisc.Size();
				int posVetD = 0;
				try {
					for(int i=0; i<tamAux; i++) {
						Disciplinas d = filaDisc.Remove();
						int tamInsc2 = filaInsc2.Size();
						
						if(d.getNomeDisciplina().equals(nome)) {	
							posVetD = i;
							for(int j=0; j<tamInsc2; j++) {
								Inscricao ins = filaInsc2.Remove();
								if(ins.getCodigoDisciplina().equals(d.getCodigoDisciplina())) {
									filaInscFiltro.Insert(ins);
								}
							}
						}
					}
				}catch(Exception e) {
					e.getMessage();
				}
				
				return filaInscFiltro;
    }
    
    public static Fila<Inscricao> filterQuick(Boolean isAscendingOrder) throws Exception{
    	Fila<Professor> filaProf2 = CSVProfessor.getProfessor();
		ListaLib<Professor> listaProf = new ListaLib<>();
		int tam2 = filaProf2.Size();
		for(int i=0;i<tam2;i++) {
			try {
				listaProf.addLast(filaProf2.Remove());
				listaProf = CSVProfessor.quickSort(listaProf, 0,tam2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Fila<Inscricao> filaInscOrd = new Fila<>();
		
		if(isAscendingOrder) {
			try {
				for(int i=tam2-1;i>=0;i--) {
					Fila<Inscricao> filaInsc = getInscricao();
					Professor p = listaProf.get(i);
					int tamAuxFila = filaInsc.Size();
					for(int j=0;j<tamAuxFila;j++) {
						Inscricao in = filaInsc.Remove();
						if(in.getCPF().equals(p.getCPF())) {
							filaInscOrd.Insert(in);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				for(int i=0;i<tam2;i++) {
					Fila<Inscricao> filaInsc = getInscricao();
					Professor p = listaProf.get(i);
					int tamAuxFila = filaInsc.Size();
					for(int j=0;j<tamAuxFila;j++) {
						Inscricao in = filaInsc.Remove();
						if(in.getCPF().equals(p.getCPF())) {
							filaInscOrd.Insert(in);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return filaInscOrd;
    }
}