package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.destny.fila.Fila;
import com.destny.model.ListaLib;

import model.Inscricao;

public class CSVIncricao {

    // Get Inscrição -- Read
    
    public static Fila<Inscricao> getInscricao() {
        Fila<Inscricao> inscricao = new Fila<>();
        BufferedReader reader = null;
        String line = "";
        String fileName = CSVController.getFileName("inscricao.csv");

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
    
    
    // Get Inscrição -- Create

    public static void addInscricao(Inscricao insc) {

        String line = "";
        String fileName = CSVController.getFileName("inscricao.csv");

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
    	String fileName = CSVController.getFileName("inscricao.csv");
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
}
