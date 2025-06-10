package control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

import com.destny.model.ListaLib;
import model.Disciplinas;
import model.Professor;
import model.Inscricao;

public class CSVController {

    public CSVController() {
        super();
    }

    private static String getOS() {
        String os = System.getProperty("os.name");
        return os;
    }

    static String os = getOS(); // Pega o SO para abrir o arquivo corretamente

    // ----------------------------- Sessão Disciplinas  -------------------------------

    // Get File Name Disciplina
    private static String getFileNameDisciplina() {
        String fileName = "";
        if (os.contains("Windows")) {
            fileName = ".\\files\\disciplinas.csv";
        } else {
            fileName = "./files//disciplinas.csv";
        }
        return fileName;
    }

    // Get Disciplinas - Read
    public static ListaLib<Disciplinas> getDisciplinas() {
        ListaLib<Disciplinas> disciplinas = new ListaLib<>();
        BufferedReader reader = null;
        String line = "";
        String fileName = getFileNameDisciplina();

        try {
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine(); // Pula o cabeçalho do arquivo
            while ((line = reader.readLine()) != null) { //
                String[] row = line.split(",");
                Disciplinas disciplina = new Disciplinas(row[0], row[1], row[2], LocalTime.parse(row[3]),
                        Integer.parseInt(row[4]), row[5]);
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
    public static void addDiciplina(Disciplinas disciplina) {
        String fileName = getFileNameDisciplina();

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
    private static void updateAllDisciplinas(ListaLib<Disciplinas> disciplinas) throws Exception{
        String fileName = getFileNameDisciplina();
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
    public static void removeDisciplina(Disciplinas disciplina, int i) throws Exception {
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
    public static void updateDisciplina(Disciplinas disciplina, int i) throws Exception{
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

    // ----------------------------- Sessão Professor -------------------------------
    public static ListaLib<Professor> getProfessor() {
        ListaLib<Professor> professores = new ListaLib<>();
        BufferedReader reader = null;
        String line = "";
        String fileName = "";

        if (os.contains("Windows")) {
            fileName = ".\\files\\disciplinas.csv";
        } else {
            fileName = "./files//disciplinas.csv";
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

    // -------------------------------Sessão Inscrição----------------------------------

    public static ListaLib<Inscricao> getInscricao() {
        ListaLib<Inscricao> inscricao = new ListaLib<>();
        BufferedReader reader = null;
        String line = "";
        String fileName = "";

        if (os.contains("Windows")) {
            fileName = ".\\files\\inscricao.csv";

        } else {
            fileName = "./files/inscricao.csv";
        }
        try {
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                Inscricao insc = new Inscricao(row[0], row[1], row[2]);
                inscricao.addLast(insc);
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

}
