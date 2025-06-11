package control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

import com.destny.model.ListaLib;

import model.Disciplinas;

public class CSVDisciplinas {
    static String os = CSVController.getOS();

    // Get Disciplinas - Read
    public static ListaLib<Disciplinas> getDisciplinas() {
        ListaLib<Disciplinas> disciplinas = new ListaLib<>();
        BufferedReader reader = null;
        String line = "";
        String fileName = CSVController.getFileName("disciplinas.csv");

        try {
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine(); // Pula o cabeçalho do arquivo
            while ((line = reader.readLine()) != null) { //
                String[] row = line.split(",");
                Disciplinas disciplina = new Disciplinas(row[0], row[1], row[2], LocalTime.parse(row[3]), Integer.parseInt(row[4]), row[5]);
                disciplinas.addLast(disciplina);
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
        return disciplinas;
    }

    // Add Disciplinas - Create
    public static void addDisciplina(Disciplinas disciplina) {
        String fileName = CSVController.getFileName("disciplinas.csv");


        String cDisc = disciplina.getCodigoDisciplina();
        String nDisc = disciplina.getNomeDisciplina();
        String data = disciplina.getDataMinistrada();
        String hInicio = disciplina.getHoraInicio().toString();
        String hDiarias = String.valueOf(disciplina.getHorasDiarias());
        String cCurso = disciplina.getCodCurso();

        String line = ("\n" + nDisc + "," + cDisc + "," + data + "," + hInicio + "," + hDiarias + "," + cCurso);

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.append(line);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update All Disciplinas
    private static void updateAllDisciplinas(ListaLib<Disciplinas> disciplinas) throws Exception {
        String fileName = CSVController.getFileName("disciplinas.csv");

        String cabecalho = "Disciplina,Código,Dia,Hora Inicial,Horas diárias,Codigo do Curso";
        int t = disciplinas.size();

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.append(cabecalho);
            for (int tamanho = 0; tamanho < t; tamanho++) { // verificar se precisa de um flush aqui
                Disciplinas disc = disciplinas.get(tamanho);

                String cDisc = disc.getCodigoDisciplina();
                String nDisc = disc.getNomeDisciplina();
                String data = disc.getDataMinistrada();
                String hInicio = disc.getHoraInicio().toString();
                String hDiarias = String.valueOf(disc.getHorasDiarias());
                String cCurso = disc.getCodCurso();

                String line = ("\n" + nDisc + "," + cDisc + "," + data + "," + hInicio + "," + hDiarias + ","
                        + cCurso);
                writer.append(line);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Remove Disciplina - Delete
    public static void removeDisciplina(int i) throws Exception {
        ListaLib<Disciplinas> disciplinas = new ListaLib<>();
        disciplinas = getDisciplinas();
        try {
            disciplinas.remove(i);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            updateAllDisciplinas(disciplinas);
        }
    }

    // Update Disciplina - Update
    public static void updateDisciplina(Disciplinas disciplina, int i) throws Exception {
        ListaLib<Disciplinas> disciplinas = new ListaLib<>();
        disciplinas = getDisciplinas();
        try {
            disciplinas.remove(i);
            disciplinas.add(disciplina, i);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            updateAllDisciplinas(disciplinas);
        }
    }

}
