package model;

import java.time.*;

public class Disciplinas {
	private String codigoDisciplina;
	private String nomeDisciplina;
	private String dataMinistrada;
	private LocalTime horaInicio;
	private int horasDiarias;
	private String codCurso;
	
	public Disciplinas(String nomeDisciplina, String codigoDisciplina, String dataMinistrada, LocalTime horaInicio, int horasDiarias, String codCurso) {
		this.codigoDisciplina = codigoDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.dataMinistrada = dataMinistrada;
		this.horaInicio = horaInicio;
		this.horasDiarias = horasDiarias;
		this.codCurso = codCurso;
		
	}

	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getDataMinistrada() {
		return dataMinistrada;
	}

	public void setDataMinistrada(String dataMinistrada) {
		this.dataMinistrada = dataMinistrada;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public int getHorasDiarias() {
		return horasDiarias;
	}

	public void setHorasDiarias(int horasDiarias) {
		this.horasDiarias = horasDiarias;
	}

	public String getCodCurso() {
		return codCurso;
	}

	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}
	
	
}
