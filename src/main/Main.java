package main;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Scanner;

import controller.Gerenciamento;
import controller.GerenciamentoFuncionario;
import controller.GerenciamentoHospede;
import controller.GerenciamentoQuarto;
import controller.GerenciamentoReserva;

public class Main {
	public static void main(String[] args) {

		System.out.print("Seja Bem-Vindo ao Sistema do Hotel!\n");

		// Usando try-with-resources para garantir o fechamento do Scanner
		try (Scanner sc = new Scanner(System.in)) {
			GerenciamentoHospede gerenciamentoHospede = new GerenciamentoHospede();
			GerenciamentoFuncionario gerenciamentoFuncionario = new GerenciamentoFuncionario();
			GerenciamentoQuarto gerenciamentoQuarto = new GerenciamentoQuarto();
			GerenciamentoReserva gerenciamentoReserva = new GerenciamentoReserva();

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
				case 1 -> gerenciamento = gerenciamentoHospede;
				case 2 -> gerenciamento = gerenciamentoFuncionario;
				case 3 -> gerenciamento = gerenciamentoQuarto;
				case 4 -> gerenciamento = gerenciamentoReserva;
				default -> System.out.println("Opção inválida, digite novamente");
				}

				if (gerenciamento != null) {
					exibirMenuGerenciamento(sc, gerenciamento);
				}
			}
		} // O Scanner será fechado automaticamente ao final do bloco try
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

	private static void exibirMenuGerenciamento(Scanner sc, Gerenciamento gerenciamento) {
		while (true) {
			System.out.println("\nOpções Disponíveis: \n 1) Adicionar \n 2) Editar \n 3) Excluir \n 4) Listar");

			// Verifica se há opções específicas e as exibe
			Map<Integer, String> opcoesEspecificas = Map.of();
			try {
				Method method = gerenciamento.getClass().getMethod("getOpcoesEspecificas");
				opcoesEspecificas = (Map<Integer, String>) method.invoke(gerenciamento);
				for (Map.Entry<Integer, String> opcao : opcoesEspecificas.entrySet()) {
					System.out.println(" " + opcao.getKey() + ") " + opcao.getValue());
				}
			} catch (Exception e) {
				System.out.println("Erro ao carregar opções específicas: " + e.getMessage());
			}

			System.out.println(" 0) Voltar para menu principal");
			System.out.print("Digite sua opção: ");
			int opcao = lerOpcao(sc);
			System.out.println(); // pular linha (medida meramente visual)

			switch (opcao) {
				case 1 -> gerenciamento.adicionar();
				case 2 -> gerenciamento.editar();
				case 3 -> gerenciamento.excluir();
				case 4 -> gerenciamento.listar();
				case 0 -> { return; } // Sai do menu de gerenciamento e volta ao principal
				default -> {
					// Verifica se a opção específica existe e executa o método usando reflection
					if (opcoesEspecificas.containsKey(opcao)) {
						try {
							String metodoNome = opcoesEspecificas.get(opcao).replaceAll(" ", "");
							Method metodoEspecifico = gerenciamento.getClass().getMethod(metodoNome);
							metodoEspecifico.invoke(gerenciamento);
						} catch (Exception e) {
							System.out.println("Erro ao executar opção específica: " + e.getMessage());
						}
					} else {
						System.out.println("Opção inválida, digite novamente");
					}
				}
			}
		}
	}
}
