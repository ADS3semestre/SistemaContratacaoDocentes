package control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.destny.model.ListaLib;

import model.Cursos;

public class CSVCursos {
    static String os = CSVController.getOS();

    public static ListaLib<Cursos> getCursos() {
        ListaLib<Cursos> cursos = new ListaLib<>();
        BufferedReader reader = null;
        String line = "";
        String fileName = "";

        if (os.contains("Windows")) {
            fileName = ".\\files\\cursos.csv";
        } else {
            fileName = "./files/cursos.csv";
        }
        try {
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                Cursos curso = new Cursos(row[0], row[1], Integer.parseInt(row[2]));
                cursos.addLast(curso);
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

    // Cursos -- Create

    public static void addCurso(Cursos curso) {
        String fileName = "";

        if (os.contains("Windows")) {
            fileName = ".\\files\\cursos.csv";
        } else {
            fileName = "./files/cursos.csv";
        }

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
}
