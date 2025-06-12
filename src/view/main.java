package view;

import java.awt.Cursor;
import java.time.LocalTime;

import com.destny.model.ListaLib;

import controller.*;
import model.Disciplinas;
import model.Cursos;
import model.Inscricao;
import model.Professor;

public class main {
    public static void main(String[] args) throws Exception {
        /* 
        LocalTime time = LocalTime.parse("21:10:00");
        Disciplinas disc = new Disciplinas("Sistemas Operacionais I","ISO001","Terça-Feira",time,4,"1110482");
        //Disciplinas disc = new Disciplinas("Wellington","IHC001","Quarta-Feira",time,2,"1110482");
        int i = 4;
        CSVDisciplinas.removeDisciplina(i);
        */
        /* 
        //Testando Inscrição
        
        Inscricao insc = new Inscricao("20260111104", "32578964726", "TESTTETESET");
        CSVIncricao.updateInscricao(insc, 3);
        */
        //Testando Cursos
        /* 
        Cursos curso = new Cursos("Teste", "Teste", 1110486);
        CSVCursos.updateCursos(curso, 3);
        */

        /* Professor professor = new Professor("4444444444", "Leandro", 344.3);
        CSVController.addProfessor(professor); */
        ListaLib<Professor> professores = CSVProfessor.getProfessor();
        int t = professores.size();
        for(int i = 0; i < t; i++){
            System.out.println(professores.get(i).getQuantidadePontos());
        }
        System.out.println("-----------------------------------");
        professores = CSVProfessor.quickSort(professores, 0, t-1);
        for(int i = 0; i < t; i++){
            System.out.println(professores.get(i).getQuantidadePontos());
        }
    }
}
