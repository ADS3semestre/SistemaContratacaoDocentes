package control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;

import com.destny.model.ListaLib;
import model.Disciplinas;

public class CSVController {

    public CSVController(){
        super();
    }

    static String os = getOS(); //Pega o SO para abrir o arquivo corretamente

    public static ListaLib<Disciplinas> getDisciplinas() {
        ListaLib<Disciplinas> disciplinas = new ListaLib<>();
        BufferedReader reader = null;
        String line = "";
        String fileName = "";

        if (os.contains("Windows")) {
            fileName = ".\\files\\disciplinas.csv";
        }
        if (os.contains("Linux")){
            fileName = ".//files//disciplinas.csv";
        }

        try {
            reader = new BufferedReader(new FileReader(fileName));
            reader.readLine(); //Pula o cabeçalho do arquivo
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

    private static String getOS() {
		String os = System.getProperty("os.name");
		return os;
	}
}
