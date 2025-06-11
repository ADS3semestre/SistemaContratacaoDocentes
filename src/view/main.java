package view;

import java.awt.Cursor;
import java.time.LocalTime;
import control.*;
import model.Disciplinas;
import model.Cursos;
import model.Inscricao;
import model.Professor;

public class main {
    public static void main(String[] args) throws Exception {
        
        LocalTime time = LocalTime.parse("21:10:00");
        Disciplinas disc = new Disciplinas("Sistemas Operacionais I","ISO001","Terça-Feira",time,4,"1110482");
        //Disciplinas disc = new Disciplinas("Wellington","IHC001","Quarta-Feira",time,2,"1110482");
        int i = 4;
        CSVDisciplinas.removeDisciplina(i);
        
        //Testando Inscrição
        /* 
        Inscricao insc = new Inscricao("20260111104", "32578964726", "ISO001");
        CSVController.addInscricao(insc);
        */
        //Testando Cursos
        /* 
        Cursos curso = new Cursos("Teste", "Teste", 1110486);
        CSVController.addCurso(curso);
        */

        /* Professor professor = new Professor("4444444444", "Leandro", 344.3);
        CSVController.addProfessor(professor); */
    }
}
