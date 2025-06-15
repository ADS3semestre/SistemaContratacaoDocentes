package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.destny.fila.Fila;
import com.destny.model.ListaLib;

import model.Professor;

public class CSVProfessor {
    
    // Get Professor -- Read
    public static Fila<Professor> getProfessor() {
        Fila<Professor> professores = new Fila<>();
        BufferedReader reader = null;
        String line = "";
        String fileName = CSVController.getFileName("professor.csv");

        
        try {
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine(); // Pula o cabeçalho do arquivo
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                Professor prof = new Professor(row[0], row[1], Double.parseDouble(row[2]),row[3]);
                professores.Insert(prof);
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
        return professores;
    }
    
    public static ListaLib<Professor>getListaProf() throws Exception {
    	Fila<Professor> pro = getProfessor();
    	ListaLib<Professor> professores = new ListaLib<>();
    	int t = pro.Size();
    	for (int i = 0; i < t; i++) {
    		professores.addLast(pro.Remove());
    	}
    	return professores;
    }
    
    
    // Add Professor -- Create

    public static void addProfessor(Professor professor) {
        String line = "";
        String fileName = CSVController.getFileName("professor.csv");
       
        String cpfProf = professor.getCPF();
        String nome = professor.getNome();
        Double qtdPontos = professor.getQuantidadePontos();
        String area = professor.getArea();

        line = ("\n" + cpfProf + "," + nome + "," + qtdPontos + "," + area);

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.append(line);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       //Update All Professores
    private static void updateAllProfessor(ListaLib<Professor> professor) throws Exception{
        String fileName = CSVController.getFileName("professor.csv");
        String cabecalho = "Nome,CPF,Quantidade de Pontos,Área";
        int i = professor.size();

        try(PrintWriter writer = new PrintWriter(new FileWriter(fileName))){
            writer.append(cabecalho);
            for(int tamanho = 0; tamanho <i; tamanho++){
                Professor prof = professor.get(tamanho);

                String cPF = prof.getCPF();
                String nome = prof.getNome();
                String qtdPontos = Double.toString(prof.getQuantidadePontos());
                String area =  prof.getArea();

                 String line = ("\n" + nome + "," + cPF + "," + qtdPontos + ","+area);
                 writer.append(line);
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();

    }
}
     // Remove Professor - Delete
    public static void removeProfessor(int i) throws Exception{
        ListaLib<Professor> professor = new ListaLib<>();
        professor = getListaProf();
        try{
            professor.remove(i);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            updateAllProfessor(professor);
        }
    }


      //Update Professor - Update
    public static void updateProfessor(Professor prof, int i) throws Exception{
        ListaLib<Professor> professor = new ListaLib<>();
        professor = getListaProf();
        try{
            professor.remove(i);
            professor.add(prof, i);
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            updateAllProfessor(professor);
        }
    }

//Ordenar por Qtd de Pontos
    public static ListaLib<Professor> quickSort(ListaLib<Professor> professores, int inicio, int fim) throws Exception{
        if(fim > inicio){
            int pIndex = dividir(professores, inicio, fim);
            quickSort(professores, inicio, pIndex -1);
            quickSort(professores, pIndex + 1, fim);
        }
        return professores;
    }

    private static int dividir(ListaLib<Professor> professores, int inicio, int fim) throws Exception{
        Professor profPivot = professores.get(inicio);
        double pivot = profPivot.getQuantidadePontos();
        int pEsq = inicio + 1;
        int pDir = fim;

        while(pEsq <= pDir){
            while (pEsq <= pDir && professores.get(pEsq).getQuantidadePontos() <= pivot) {
                pEsq++;
            }
            while (pDir >= pEsq && professores.get(pDir).getQuantidadePontos() > pivot){
                pDir--;
            }
            if (pEsq < pDir){
                trocar(professores, pEsq, pDir);
                pEsq++;
                pDir--;
            }
        }
        trocar(professores, inicio, pDir);
        return pDir;
    }

    private static void trocar(ListaLib<Professor> professores, int i, int j) throws Exception{
    Professor profI = professores.get(i);
    professores.add(professores.get(j), i);
    professores.remove(i+1);
    professores.add(profI, j);
    professores.remove(j+1);
    }
}