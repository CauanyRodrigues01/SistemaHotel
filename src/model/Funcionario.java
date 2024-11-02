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
	
	// Funções específicas para manipulação do Funcionário
	public void registrarHoras(int horas) {
		this.horasTrabalhadas += horas;
	}

	public double calcularSalario() {
		return horasTrabalhadas * salarioPorHora;
	}
	
	// Getters e Setters
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Double getSalarioPorHora() {
		return salarioPorHora;
	}

	public void setSalarioPorHora(Double salarioPorHora) {
		this.salarioPorHora = salarioPorHora;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Integer getHorasTrabalhadas() {
		return horasTrabalhadas;
	}

	public void setHorasTrabalhadas(Integer horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}

	@Override
	public String toString() {
		return "Funcionario [cargo=" + cargo + ", salarioPorHora=" + salarioPorHora + ", turno=" + turno
				+ ", horasTrabalhadas=" + horasTrabalhadas + "]";
	}

}
