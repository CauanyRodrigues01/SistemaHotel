package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Funcionario;

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
		sc.nextLine();

		System.out.println("Informe o turno de trabalho: ");
		String turnoNovoFuncionario = sc.nextLine();

		Funcionario funcionario = new Funcionario(nomeNovoFuncionario, cpfNovoFuncionario, cargoNovoFuncionario,
				salarioPorHoraNovoFuncionario, turnoNovoFuncionario, null);
		
		sc.close();
	}

	@Override
	public void editar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("---- Você escolheu a opção para editar os dados de um funcionário ---");
		System.out.println("Informe o CPF do funcionario que deseja editar as informações: ");
		boolean encontrado = false;
		String cpfFuncionario = sc.nextLine();
		
		for (Funcionario funcionario : funcionarios) {
			if(funcionario.getCpf() .equals(cpfFuncionario)) {
				
				System.out.println("---- Os dados do funcionário serão editados ----");
				
				System.out.println("Informe o nome do funcionário: ");
				String nomeEditadoFuncionario = sc.nextLine();

				System.out.println("Informe o CPF: ");
				String cpfEditadoFuncionario = sc.nextLine();

				System.out.println("Informe o cargo:: ");
				String cargoEditadoFuncionario = sc.nextLine();

				System.out.println("Novo salário por hora: ");
				double salarioPorHoraEditadoFuncionario = sc.nextDouble();
				sc.nextLine();

				System.out.println("Informe o turno de trabalho: ");
				String turnoEditadoFuncionario = sc.nextLine();
				
				funcionario.setNome(nomeEditadoFuncionario);
				funcionario.setCpf(cpfEditadoFuncionario);
				funcionario.setCargo(cargoEditadoFuncionario);
				funcionario.setsalarioPorHora(salarioPorHoraEditadoFuncionario);
				funcionario.setTurno(turnoEditadoFuncionario);
				
				System.out.println("Dados atualizados com sucesso!");
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			System.out.println("CPF não localizado");
		}

		sc.close();
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
