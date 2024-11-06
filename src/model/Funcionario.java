package model;

public class Funcionario extends Pessoa {

	private String cargo;
	private double salarioPorHora;
	private String turno;
	private int horasTrabalhadas;

	public Funcionario(String nome, String cpf, String cargo, Double salarioPorHora, String turno) {
		super(nome, cpf);
		this.cargo = cargo;
		this.salarioPorHora = salarioPorHora;
		this.turno = turno;
		this.horasTrabalhadas = 0;
	}
	
    public void registrarHoras(int horas) {
    	this.horasTrabalhadas += horas;
    }

    public double calcularSalario() {
        return this.horasTrabalhadas * this.salarioPorHora; 
    }
	
	// Getters e Setters
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalarioPorHora() {
		return salarioPorHora;
	}

	public void setSalarioPorHora(double salarioPorHora) {
		this.salarioPorHora = salarioPorHora;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public int getHorasTrabalhadas() {
		return horasTrabalhadas;
	}

	public void setHorasTrabalhadas(int horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}

	@Override
	public String toString() {
		return "Funcionario [cargo=" + cargo + ", salarioPorHora=" + salarioPorHora + ", turno=" + turno
				+ ", horasTrabalhadas=" + horasTrabalhadas + "]";
	}

}
