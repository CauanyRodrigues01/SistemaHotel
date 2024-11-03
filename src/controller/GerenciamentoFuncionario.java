package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import model.Funcionario;

public class GerenciamentoFuncionario implements Gerenciamento {

	private final List<Funcionario> funcionarios;
	private final Scanner sc;

	public GerenciamentoFuncionario(Scanner scanner) {
		this.funcionarios = new ArrayList<>();
		this.sc = scanner;
	}

	private Optional<Funcionario> buscarFuncionarioPorCpf(String cpfBuscar) {
		return funcionarios.stream().filter(h -> h.getCpf().equals(cpfBuscar)).findFirst();
	}

	@Override
	public void buscar() {
		System.out.print("Digite o CPF do Funcionário para buscar: ");
		String cpfBuscar = sc.nextLine(); // Usando o mesmo método para ler a entrada

		Optional<Funcionario> funcionarioBuscado = funcionarios.stream().filter(h -> h.getCpf().equals(cpfBuscar))
				.findFirst();

		System.out.println(funcionarioBuscado);

	}

	// Método para ler opções de forma segura
	private int lerOpcao(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor, insira um número: ");
            }
        }
	}

	@Override
	public void adicionar() {

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

		Funcionario novoFuncionario = new Funcionario(nomeNovoFuncionario, cpfNovoFuncionario, cargoNovoFuncionario,
				salarioPorHoraNovoFuncionario, turnoNovoFuncionario, null);

		funcionarios.add(novoFuncionario);
		System.out.println("Funcionário cadastrado com sucesso!");
	}

	@Override
	public void editar() {

		System.out.println("---- Você escolheu a opção para editar os dados de um funcionário ---");
		System.out.println("Informe o CPF do funcionario que deseja editar as informações: ");
		boolean encontrado = false;
		String cpfFuncionario = sc.nextLine();

		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getCpf().equals(cpfFuncionario)) {

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
				funcionario.setSalarioPorHora(salarioPorHoraEditadoFuncionario);
				funcionario.setTurno(turnoEditadoFuncionario);

				System.out.println("Dados atualizados com sucesso!");
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			System.out.println("CPF não encontrado.Certifique-se de que o funcionário está cadastrado.");
		}

	}

	@Override
	public void excluir() {

		System.out.println("---- Você escolheu a opção para excluir um funcionário ---");
		System.out.println("Informe o CPF do funcionario que deseja excluir: ");
		String cpfFuncionario = sc.nextLine();
		boolean encontrado = false;

		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getCpf().equals(cpfFuncionario)) {
				System.out.println("Funcionário encontrado!");
				System.out.println("Nome: " + funcionario.getNome());
				System.out.println("CPF: " + funcionario.getCpf());
				System.out.println("Cargo: " + funcionario.getCargo());

				System.out.println("Tem certeza de que deseja excluir este funcionário? (S/N)");
				String confirmacao = sc.nextLine();

				if (confirmacao.equalsIgnoreCase("S")) {
					funcionarios.remove(funcionario);
					System.out.println("Funcionário excluído com sucesso!");
					encontrado = true;
					break;
				} else {
					System.out.println("Exclusão cancelada.");
					encontrado = true;
					break;
				}
			}
		}
		if (!encontrado) {
			System.out.println("CPF não encontrado.Certifique-se de que o funcionário está cadastrado.");
		}

	}

	@Override
	public void listar() {
		if (funcionarios.isEmpty()) {
			System.out.println("Não existem funcionários cadastrados.");
		} else {
			System.out.println("Lista com os funcionários cadastrados:");
			for (Funcionario funcionario : funcionarios) {
				System.out.println(funcionario);
			}
		}
	}

	@Override
	public Map<Integer, String> getOpcoesEspecificas() {
		return Map.of();
	}

	@Override
	public void executarOpcaoEspecifica(int opcao, Scanner sc) {
		switch (opcao) {
		default -> System.out.println("Opção específica inválida");
		}
	}
}
