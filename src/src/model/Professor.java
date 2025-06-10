package model;

public class Professor {
	private String CPF;
	private String Nome;
	private double quantidadePontos;
	
	public Professor(String CPF, String Nome, double quantidadePontos) {
		this.CPF = CPF;
		this.Nome = Nome;
		this.quantidadePontos = quantidadePontos;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public double getQuantidadePontos() {
		return quantidadePontos;
	}

	public void setQuantidadePontos(double quantidadePontos) {
		this.quantidadePontos = quantidadePontos;
	}
}
