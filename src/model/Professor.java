package model;

public class Professor {
	private String CPF;
	private String Nome;
	private double quantidadePontos;
	private String area;
	
	public Professor(String Nome, String CPF, double quantidadePontos, String area) {
		this.CPF = CPF;
		this.Nome = Nome;
		this.quantidadePontos = quantidadePontos;
		this.area = area;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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
