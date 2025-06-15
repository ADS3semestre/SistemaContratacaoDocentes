package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.destny.fila.Fila;
import com.destny.model.ListaLib;

import model.Cursos;

public class CSVCursos {

    // Cursos --- Read
    public static Fila<Cursos> getCursos()  throws Exception{
        String fileName = CSVController.getFileName("cursos.csv");
        Fila<Cursos> cursos = new Fila<>();
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                Cursos curso = new Cursos(row[0], row[1], Integer.parseInt(row[2]));
                cursos.Insert(curso);
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
        return cursos;
    }

    public static ListaLib<Cursos>getListaCursos() throws Exception {
    	Fila<Cursos> cur = getCursos();
    	ListaLib<Cursos> cursos = new ListaLib<>();
    	int t = cur.Size();
    	for (int i = 0; i < t; i++) {
    		cursos.addLast(cur.Remove());
    	}
    	return cursos;
    }
    
    
    // Cursos -- Create
    
    public static void addCurso(Cursos curso) throws Exception{
        String fileName = CSVController.getFileName("cursos.csv");
        String nCurso = curso.getNome();
        String aCurso = curso.getArea();
        String Cursocod = String.valueOf(curso.getCodigo());

        String line = ("\n" + nCurso + "," + aCurso + "," + Cursocod);

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.append(line);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
    // Update All Cursos
    
    private static void updateAllCursos(ListaLib<Cursos> cursos) throws Exception {
        String fileName = CSVController.getFileName("cursos.csv");
    	String cabecalho = "Nome,Área,Código";
    	int t = cursos.size();
    	
    	try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
    		writer.append(cabecalho);
    		for (int tamanho = 0; tamanho < t; tamanho++) {
    			Cursos curs = cursos.get(tamanho);
    			
    			String nCurso = curs.getNome();
    			String aCurso = curs.getArea();
    			String Cursocod = String.valueOf(curs.getCodigo());
    			
    			String line = ("\n" + nCurso + "," + aCurso + "," + Cursocod);
    			writer.append(line);
    		}
    		writer.flush();
    		writer.close();
    } catch (IOException e) {
    	e.printStackTrace();
    }
}
    
    // Remove Cursos - Delete
    
    public static void removeCursos(int i) throws Exception {
    	ListaLib<Cursos> cursos = new ListaLib<>();
    	cursos = getListaCursos();
    	try {
    		cursos.remove(i);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		updateAllCursos(cursos);
    	}
    }
    
    // Update Cursos - Update
    
    public static void updateCursos(Cursos curso, int i) throws Exception {
    	ListaLib<Cursos> cursos = new ListaLib<>();
    	cursos = getListaCursos();
    	try {
    		cursos.remove(i);
    		cursos.add(curso, i);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		updateAllCursos(cursos);
    	}
    }
}