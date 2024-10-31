package model;

public class Funcionario extends Pessoa {
	
	private String cargo;
	private Double salario;
	private String turno;
	private Integer horasTrabalhadas;
	
	
	public Funcionario(String nome, String cpf, String cargo, Double salario, String turno, Integer horasTrabalhadas) {
		super(nome, cpf);
		this.cargo = cargo;
		this.salario = salario;
		this.turno = turno;
		this.horasTrabalhadas = horasTrabalhadas;
	}
	
	//colocar funções específicas aqui depois do construtor!
	
	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public Double getSalario() {
		return salario;
	}


	public void setSalario(Double salario) {
		this.salario = salario;
	}


	public String getTurno() {
		return turno;
	}


	public void setTurno(String turno) {
		this.turno = turno;
	}


	@Override
	public String toString() {
		return "Funcionario [cargo=" + cargo + ", salario=" + salario + ", turno=" + turno + "]";
	}
	
}
