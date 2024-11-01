package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Funcionario;
import model.Hospede;

public class GerenciamentoFuncionario implements Gerenciamento {

	// atributos aqui!
	private List<Funcionario> funcionarios;

	// construtor aqui!
	public GerenciamentoFuncionario() {
		this.funcionarios = new ArrayList<>();
	}
	// metodos especificos aqui!

	@Override
	public void adicionar() {
		Scanner sc = new Scanner(System.in);

		System.out.println("---- Você escolheu a opção para cadastrar um novo funcionário ---");

		System.out.println("Informe o nome do novo funcionário: ");
		String nomeNovoFuncionario = sc.nextLine();

		System.out.println("Informe o CPF: ");
		String cpfNovoFuncionario = sc.nextLine();

		System.out.println("Informe o cargo: ");
		String cargoNovoFuncionario = sc.nextLine();

		System.out.println("Informe o salário por hora: ");
		double salarioPorHoraNovoFuncionario = sc.nextDouble();

		System.out.println("Informe o turno de trabalho: ");
		String turnoNovoFuncionario = sc.nextLine();

		Funcionario funcionario = new Funcionario(nomeNovoFuncionario, cpfNovoFuncionario, 
				cargoNovoFuncionario, salarioPorHoraNovoFuncionario ,turnoNovoFuncionario, null);

	}

	@Override
	public void editar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir() {
		// TODO Auto-generated method stub

	}

	@Override
	public void listar() {
		// TODO Auto-generated method stub

	}

	// gets e sets e toString aqui!

}
