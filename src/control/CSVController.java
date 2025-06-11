package control;

import java.io.File;
import java.io.PrintWriter;

public class CSVController {

    public CSVController() {
        super();
    }

    public static String getOS() {
        String os = System.getProperty("os.name");
        return os;
    }

    public static String getFileName(String fileName){
        String path = "";
        if (getOS().contains("Windows")) {
            path = ".\\files\\" + fileName;
        } else {
<<<<<<< Updated upstream
            path = "./files/" + fileName;
=======
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
    private static void updateAllDisciplinas(ListaLib<Disciplinas> disciplinas) throws Exception {
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

    // ----------------------------- Sessão Professor -------------------------------
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

    public static void addProfessor(Professor professor){
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

    //Update All Professor
    private static void updateAllProfessor(ListaLib<Professor> professor) throws Exception{
        String fileName = getFileNameProfessor();
        String cabecalho = "Professor, CPF, Nome, Quantidade de Pontos";
        int i = professor.size();

        try(PrintWriter writer = new PrintWriter(new FileWriter(fileName)))){
            writer.append(cabecalho);
            for(int tamanho = 0; tamanho <i; tamanho++){
                Professor prof = professor.get(tamanho);

                String cPF = professor.getCPF();
                String nome = professor.getNome();
                String qtdPontos = Double.parseDouble(professor.getQuantidadePontos());

                 String line = ("\n" + prof + "," + cPF + "," + nome + "," + qtdPontos);
                 writer.append(line);
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();

    }
}
     // Remove Professor - Delete
    public static void removeProfessor(Professor professor, int i) throws Exception{
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
    public static void updateProfessor(Professor professor, int i) throws Exception{
        ListaLib<Professor> professor = new ListaLib<>();
        professor = getProfessor();
        try{
            professor.remove(i);
            professor.add(professor, i);
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            updateAllProfessor(professor);
        }

   
  

    }

    // -------------------------------Sessão Inscrição----------------------------------

    // Get Inscrição -- Read
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
    // Get Inscrição -- Create

    public static void addInscricao(Inscricao inscricao) {

        String line = "";
        String fileName = "";

        if (os.contains("Windows")) {
            fileName = ".\\files\\inscricao.csv";
        } else {
            fileName = "./files/inscricao.csv";
        }

        String cInscProc = inscricao.getCodProcesso();
        String cpfInsc = inscricao.getCPF();
        String cInscDisc = inscricao.getCodigoDisciplina();

        line = ("\n" + cInscProc + "," + cpfInsc + "," + cInscDisc);

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.append(line);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ----------------------------- Sessão Cursos ----------------------------

    // Cursos --- Read

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
>>>>>>> Stashed changes
        }
        return path;
    }
}