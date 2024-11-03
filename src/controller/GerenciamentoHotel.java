package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GerenciamentoHotel {
	private Scanner sc;
	private Map<Integer, Gerenciamento> gerenciamentos;

	public GerenciamentoHotel(Scanner scanner) {
		this.sc = scanner;
		this.gerenciamentos = new HashMap<>();
		this.gerenciamentos.put(1, new GerenciamentoHospede(scanner));
		this.gerenciamentos.put(2, new GerenciamentoFuncionario(scanner));
		this.gerenciamentos.put(3, new GerenciamentoQuarto(scanner));
		this.gerenciamentos.put(4, new GerenciamentoReserva(scanner));
	}

	// public void inicializarSistema()

	public void gerenciar(int tipoGerenciamento) {
		Gerenciamento gerenciamento = gerenciamentos.get(tipoGerenciamento);
		if (gerenciamento != null) {
			exibirMenuGerenciamento(sc, gerenciamento);
		} else {
			System.out.println("Tipo de gerenciamento inválido.");
		}
	}

	private static int lerOpcao(Scanner sc) {
		while (true) {
			try {
				return Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.print("Entrada inválida. Por favor, insira um número: ");
			}
		}
	}

	public void exibirMenuPrincipal() {
		while (true) {
			System.out.println(
					"\nMenu de gerenciamento: \n 1) Hóspede \n 2) Funcionario \n 3) Quarto \n 4) Reserva \n 0) Sair do sistema");
			System.out.print("Digite sua opção: ");
			int opcaoMenu = lerOpcao(sc);

			if (opcaoMenu == 0) {
				System.out.println("Saindo do Sistema, obrigada por usar.");
				break;
			}

			Gerenciamento gerenciamento = null;
			switch (opcaoMenu) {
			case 1 -> gerenciamento = gerenciamentos.get(1);
			case 2 -> gerenciamento = gerenciamentos.get(2);
			case 3 -> gerenciamento = gerenciamentos.get(3);
			case 4 -> gerenciamento = gerenciamentos.get(4);
			default -> System.out.println("Opção inválida, digite novamente");
			}

			if (gerenciamento != null) {
				exibirMenuGerenciamento(sc, gerenciamento);
			}
		}
	}

	private void exibirMenuGerenciamento(Scanner sc, Gerenciamento gerenciamento) {
		while (true) {
			System.out.println("\nOpções Disponíveis: ");
			System.out.println(" 1) Adicionar ");
			System.out.println(" 2) Editar ");
			System.out.println(" 3) Excluir ");
			System.out.println(" 4) Listar ");
			System.out.println(" 5) Buscar ");

			// Exibir opções específicas
			for (var opcaoEspecifica : gerenciamento.getOpcoesEspecificas().entrySet()) {
				System.out.printf(" %d) %s\n", opcaoEspecifica.getKey(), opcaoEspecifica.getValue());
			}

			System.out.println(" 0) Voltar para menu principal"); // Opção 0 no final
			System.out.print("Digite sua opção: ");
			int opcao = lerOpcao(sc);
			System.out.println(); // Pular linha

			switch (opcao) {
			case 1 -> gerenciamento.adicionar();
			case 2 -> gerenciamento.editar();
			case 3 -> gerenciamento.excluir();
			case 4 -> gerenciamento.listar();
			case 5 -> gerenciamento.buscar();
			case 0 -> {
				return;
			} // Volta para o menu principal
			default -> {
				if (gerenciamento.getOpcoesEspecificas().containsKey(opcao)) {
					// Executa a opção específica
					gerenciamento.executarOpcaoEspecifica(opcao, sc);
				} else {
					System.out.println("Opção inválida, digite novamente");
				}
			}
			}
		}
	}

	// public void adicionarReserva(Reserva reserva) {
	// Verificações de consistência e lógica de adição de reserva
	// Poderia envolver chamadas para gerenciamentoHospede e gerenciamentoQuarto
	// }

	// Outros métodos para gerenciar operações do hotel...
}