package model;

public class Hospede extends Pessoa {
	
	private String dataNascimento;
	private String endereco;
	private String contato;

	public Hospede(String nome, String cpf, String dataNascimento, String endereco, String contato) {
		super(nome, cpf);
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.contato = contato;
	}
	
	//colocar funções específicas aqui depois do construtor!

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	@Override
	public String toString() {
		return "Hospede [nome=" + super.getNome() +", cpf=" + super.getCpf() + ", dataNascimento=" + dataNascimento + ", endereco=" + endereco + ", contato=" + contato + "]";
	}
	
}
