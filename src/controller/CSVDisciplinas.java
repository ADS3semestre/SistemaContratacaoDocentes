package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

import com.destny.fila.Fila;
import com.destny.model.ListaLib;

import model.Disciplinas;
import model.Inscricao;

public class CSVDisciplinas {
    // Get Disciplinas - Read
    public static Fila<Disciplinas> getDisciplinas() {
        Fila<Disciplinas> disciplinas = new Fila<>();
        BufferedReader reader = null;
        String line = "";
        String fileName = CSVController.getFileName("disciplinas.csv");

        try {
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine(); // Pula o cabeçalho do arquivo
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                Disciplinas disciplina = new Disciplinas(row[0], row[1], row[2], LocalTime.parse(row[3]),
                        Integer.parseInt(row[4]), row[5]);
                disciplinas.Insert(disciplina);
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

    public static ListaLib<Disciplinas> getListaDisc() throws Exception {
        Fila<Disciplinas> discs = getDisciplinas();
        ListaLib<Disciplinas> disciplinas = new ListaLib<>();
        int t = discs.Size();
        for (int i = 0; i < t; i++) {
            disciplinas.addLast(discs.Remove());
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
        disciplinas = getListaDisc();
        String codDisc = disciplinas.get(i).getCodigoDisciplina();

        BufferedReader reader = null;
        String line = "";
        String fileName = CSVController.getFileName("inscricoes.csv");
        int count = 0;
        ListaLib<Integer> toRemove = new ListaLib<>();

        try {
            disciplinas.remove(i);
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                if (row[2].contains(codDisc)) {
                    toRemove.addLast(count);
                    ;
                }
                count++;
            }
            int t = toRemove.size();
            for (int j = t - 1; j >= 0; j--) {
                CSVInscricao.removeInscricao(toRemove.get(j));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            updateAllDisciplinas(disciplinas);
        }
    }

    // Update Disciplina - Update
    public static void updateDisciplina(Disciplinas disciplina, int i) throws Exception {
        ListaLib<Disciplinas> disciplinas = new ListaLib<>();
        disciplinas = getListaDisc();
        try {
            disciplinas.remove(i);
            disciplinas.add(disciplina, i);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            updateAllDisciplinas(disciplinas);
        }
    }

    private static int hashCode(int t, String codDisc) {
        int cod = codDisc.charAt(0) + codDisc.charAt(3) + codDisc.charAt(2) + Integer.parseInt(codDisc.substring(3));
        cod = cod % t;
        return cod;
    }

    private static ListaLib<Disciplinas> getActiveDisc() throws Exception {
        ListaLib<Disciplinas> allDisc = getListaDisc();
        ListaLib<Disciplinas> activeDiscs = new ListaLib<Disciplinas>();
        ListaLib<Inscricao> inscs = CSVInscricao.getListaInsc();
        int tInsc = inscs.size();
        int tDisc = allDisc.size();
        for (int i = 0; i < tDisc; i++) {
            for (int j = 0; j < tInsc; j++) {
                String codDisc = allDisc.get(i).getCodigoDisciplina();
                if (codDisc.equals(inscs.get(j).getCodigoDisciplina())) {
                    activeDiscs.addLast(allDisc.get(i));
                    break;
                }
            }
        }
        return activeDiscs;
    }

    public static ListaLib<Disciplinas>[] getHashTable() throws Exception {
        int tableSize = 5;

        ListaLib<Disciplinas> listaDisc = getActiveDisc();
        int tLista = listaDisc.size();
        ListaLib<Disciplinas> hashTable[] = new ListaLib[tableSize];
        if (listaDisc.isEmpty()) {
            return hashTable;
        }

        for (int i = 0; i < tableSize; i++) {
            hashTable[i] = new ListaLib<Disciplinas>();
        }

        for (int i = 0; i < tLista; i++) {
            String codDisc = listaDisc.get(i).getCodigoDisciplina();
            int hashCode = hashCode(tableSize, codDisc);
            hashTable[hashCode].addLast(listaDisc.get(i));
        }
        return hashTable;
    }

    public static ListaLib<Inscricao>[] filterByName() throws Exception {
        ListaLib<Disciplinas> listaDisc = getListaDisc();
        ListaLib<Inscricao> listaInsc = CSVInscricao.getListaInsc();
        int tDisc = listaDisc.size();
        int tInsc = listaInsc.size();
        ListaLib<Inscricao> filtered[] = new ListaLib[tDisc];
        if (listaDisc.isEmpty()) {
            return filtered;
        }

        for (int i = 0; i < tDisc; i++) {
            filtered[i] = new ListaLib<Inscricao>();
            String codDisc = listaDisc.get(i).getCodigoDisciplina();
            for (int j = 0; j < tInsc; j++) {
                if (codDisc.equals(listaInsc.get(j).getCodigoDisciplina())) {
                    filtered[i].addLast(listaInsc.get(j));
                }
            }
        }
        return filtered;
    }
}
