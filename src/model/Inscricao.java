package model;

public class Inscricao {
	private String codProcesso;
	private String codigoDisciplina;
	private String CPF;
	
	public Inscricao(String codProcesso, String CPF, String codigoDisciplina) {
		this.codProcesso = codProcesso;
		this.CPF = CPF;
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getCodProcesso() {
		return codProcesso;
	}

	public void setCodProcesso(String codProcesso) {
		this.codProcesso = codProcesso;
	}

	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}
	
}
	