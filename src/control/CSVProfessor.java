package control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.destny.model.ListaLib;

import model.Professor;

public class CSVProfessor {
    static String os = CSVController.getOS();

    // Get Professor -- Read
    public static ListaLib<Professor> getProfessor() {
        ListaLib<Professor> professores = new ListaLib<>();
        BufferedReader reader = null;
        String line = "";
        String fileName = "";

        if (os.contains("Windows")) {
            fileName = ".\\files\\professor.csv";
        } else {
            fileName = "./files/professor.csv";
        }

        try {
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine(); // Pula o cabeçalho do arquivo
            while ((line = reader.readLine()) != null) { //
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
    // Get Professor -- Create

    public static void addProfessor(Professor professor) {
        String line = "";
        String fileName = "";

        if (os.contains("Windows")) {
            fileName = ".\\files\\professor.csv";
        } else {
            fileName = "./files/professor.csv";
        }

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
}
