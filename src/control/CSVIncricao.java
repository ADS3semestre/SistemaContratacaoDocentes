package control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.destny.model.ListaLib;

import model.Inscricao;

public class CSVIncricao {
    static String os = CSVController.getOS();

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
}
