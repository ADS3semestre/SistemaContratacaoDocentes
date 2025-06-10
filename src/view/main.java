package view;

import java.time.LocalTime;
import control.CSVController;
import model.Disciplinas;

public class main {
    public static void main(String[] args) {
        LocalTime time = LocalTime.parse("19:20:00");
        Disciplinas disc = new Disciplinas("Sistemas Operacionais I","ISO001","Terça-Feira",time,4,"1110482");

        CSVController.addDiciplina(disc);
    }
}
