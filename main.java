package view;
import java.io.IOException;
import controller.FileController;
import controller.IGeradorCSV;

public class main {
	public static void main(String args[]) {
		IGeradorCSV arqCont = new FileController();
		String dirWin = "\"C:\\Users\\tamir\\eclipse-workspace\\SistemaContratacaoDocentes\\src\\model\\Disciplinas.java\"";
		try {
			arqCont.read(dirWin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
