package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.destny.model.ListaLib;

import model.Professor;

public class CSVProfessor {
    
    // Get Professor -- Read
    public static ListaLib<Professor> getProfessor() {
        ListaLib<Professor> professores = new ListaLib<>();
        BufferedReader reader = null;
        String line = "";
        String fileName = CSVController.getFileName("professor.csv");


        try {
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine(); // Pula o cabeçalho do arquivo
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                Professor prof = new Professor(row[0], row[1], Double.parseDouble(row[2]));
                professores.addLast(prof);
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
    // Add Professor -- Create

    public static void addProfessor(Professor professor) {
        String line = "";
        String fileName = CSVController.getFileName("professor.csv");
       
        String cpfProf = professor.getCPF();
        String nome = professor.getNome();
        Double qtdPontos = professor.getQuantidadePontos();

        line = ("\n" + cpfProf + "," + nome + "," + qtdPontos);

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
        String cabecalho = "Professor, CPF, Nome, Quantidade de Pontos";
        int i = professor.size();

        try(PrintWriter writer = new PrintWriter(new FileWriter(fileName))){
            writer.append(cabecalho);
            for(int tamanho = 0; tamanho <i; tamanho++){
                Professor prof = professor.get(tamanho);

                String cPF = prof.getCPF();
                String nome = prof.getNome();
                String qtdPontos = Double.toString(prof.getQuantidadePontos());

                 String line = ("\n" + cPF + "," + nome + "," + qtdPontos);
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
        professor = getProfessor();
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
        professor = getProfessor();
        try{
            professor.remove(i);
            professor.add(prof, i);
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            updateAllProfessor(professor);
        }
    }
    public static void quickSort(ListaLib<Professor> professores, int inicio, int fim){
        if(inicio < fim){
            int pIndex = dividir(professores, inicio, fim);
            quickSort(professores, inicio, pIndex -1);
            quickSort(professores, pIndex + 1, fim);
        }
    }
    private static int dividir(ListaLib<Professor> prof, int inicio, int fim){
        Professor pivot = professor.getQuantidadePontos();
        int i = inicio -1;

        for (int j = inicio; j < fim; j++){
            if(prof.get(j).getQuantidadePontos() <= pivot.getQuantidadePontos()){
                i++;
                troca(prof, i+1, fim);
            }
        }
        troca(prof, i + 1, fim);
        return i + 1;
    }

    private static void troca(ListaLib<Professor> professores, int i, int j){
        Professor aux = professores.get(i);
        professores.set(i, professores.get(j));
        professores.set(j, aux);

    }
}
