package model;

public class Funcionario extends Pessoa {

	private String cargo;
	private Double salarioPorHora;
	private String turno;
	private Integer horasTrabalhadas;

	public Funcionario(String nome, String cpf, String cargo, Double salarioPorHora, String turno,
			Integer horasTrabalhadas) {
		super(nome, cpf);
		this.cargo = cargo;
		this.salarioPorHora = salarioPorHora;
		this.turno = turno;
		this.horasTrabalhadas = horasTrabalhadas;
	}

	// colocar funções específicas aqui depois do construtor!

	public void registrarHoras(int horas) {
		this.horasTrabalhadas += horas;
	}

	public double calcularSalario() {
		return horasTrabalhadas * salarioPorHora;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Double getsalarioPorHora() {
		return salarioPorHora;
	}

	public void setsalarioPorHora(Double salarioPorHora) {
		this.salarioPorHora = salarioPorHora;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	@Override
	public String toString() {
		return "Funcionario [cargo=" + cargo + ", salario=" + salarioPorHora + ", turno=" + turno + "]";
	}

}
