package view;

import java.time.LocalTime;
import control.CSVController;
import model.Disciplinas;

public class main {
    public static void main(String[] args) throws Exception {
        LocalTime time = LocalTime.parse("21:10:00");
        //Disciplinas disc = new Disciplinas("Sistemas Operacionais I","ISO001","Terça-Feira",time,4,"1110482");
        Disciplinas disc = new Disciplinas("Wellington","IHC001","Quarta-Feira",time,2,"1110482");
        int i = 2;
        CSVController.updateDisciplina(disc, i);
    }
}
