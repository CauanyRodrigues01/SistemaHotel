package main;

import java.util.Scanner;

import controller.GerenciamentoFuncionario;
import controller.GerenciamentoHospede;
import controller.GerenciamentoQuarto;
import controller.GerenciamentoReserva;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		GerenciamentoHospede gerenciamentoHospede = new GerenciamentoHospede();
		GerenciamentoFuncionario gerenciamentoFuncionario = new GerenciamentoFuncionario();
		GerenciamentoQuarto gerenciamentoQuarto = new GerenciamentoQuarto();
		GerenciamentoReserva gerenciamentoReserva = new GerenciamentoReserva();

		System.out.print("Seja Bem-Vindo ao Sistema do Hotel!\n");

		while (true) {
			System.out.println(
					"\nMenu de gerenciamento: \n 1) Hóspede \n 2) Funcionario \n 3) Quarto \n 4) Reserva \n 0) Sair do sistema");
			System.out.print("Digite sua opção: ");
			String opcao = sc.nextLine();

			try {
				int opcaoMenu = Integer.parseInt(opcao);

				if (opcaoMenu == 1) {

					while (true) {
						System.out.println(
								"\nOpções Disponíveis: \n 1) Cadastrar Hospede \n 2) Editar Hóspede \n 3) Excluir Hóspede \n 4) Listar Hóspedes \n 0) Voltar para menu");
						System.out.print("Digite sua opção: ");
						opcao = sc.nextLine();

						try {
							int opcaoHospede = Integer.parseInt(opcao);

							if (opcaoHospede == 1) {

								gerenciamentoHospede.adicionar();

							} else if (opcaoHospede == 2) {

								gerenciamentoHospede.editar();

							} else if (opcaoHospede == 3) {

								gerenciamentoHospede.excluir();

							} else if (opcaoHospede == 4) {

								gerenciamentoHospede.listar();

							} else if (opcaoHospede == 0) {

								break;

							} else {

								System.out.println("Opção inválida, digite novamente");

							}
						} catch (NumberFormatException e) {

							System.out.println("Entrada inválida. Por favor, insira um número.");

						}
					}

				} else if (opcaoMenu == 2) {

					while (true) {
						System.out.println(
								"\nOpções Disponíveis: \n 1) Cadastrar Funcionario \n 2) Editar Funcionario \n 3) Excluir Funcionarios \n 4) Listar Funcionario \n 0) Voltar para menu");
						System.out.print("Digite sua opção: ");
						opcao = sc.nextLine();

						try {
							int opcaoFuncionario = Integer.parseInt(opcao);

							if (opcaoFuncionario == 1) {

								gerenciamentoFuncionario.adicionar();

							} else if (opcaoFuncionario == 2) {

								gerenciamentoFuncionario.editar();

							} else if (opcaoFuncionario == 3) {

								gerenciamentoFuncionario.excluir();

							} else if (opcaoFuncionario == 4) {

								gerenciamentoFuncionario.listar();

							} else if (opcaoFuncionario == 0) {

								break;

							} else {

								System.out.println("Opção inválida, digite novamente");

							}
						} catch (NumberFormatException e) {

							System.out.println("Entrada inválida. Por favor, insira um número.");

						}
					}

				} else if (opcaoMenu == 3) {

					while (true) {
						System.out.println(
								"\nOpções Disponíveis: \n 1) Cadastrar Quarto \n 2) Editar Quarto \n 3) Excluir Quarto \n 4) Listar Quartos \n 0) Voltar para menu");
						System.out.print("Digite sua opção: ");
						opcao = sc.nextLine();

						try {
							int opcaoQuarto = Integer.parseInt(opcao);

							if (opcaoQuarto == 1) {

								gerenciamentoQuarto.adicionar();

							} else if (opcaoQuarto == 2) {

								gerenciamentoQuarto.editar();

							} else if (opcaoQuarto == 3) {

								gerenciamentoQuarto.excluir();

							} else if (opcaoQuarto == 4) {

								gerenciamentoQuarto.listar();

							} else if (opcaoQuarto == 0) {

								break;

							} else {

								System.out.println("Opção inválida, digite novamente");

							}
						} catch (NumberFormatException e) {

							System.out.println("Entrada inválida. Por favor, insira um número.");

						}
					}

				} else if (opcaoMenu == 4) {

					while (true) {
						System.out.println(
								"\nOpções Disponíveis: \n 1) Cadastrar Reserva \n 2) Editar Reserva \n 3) Excluir Reserva \n 4) Listar Reservas \n 0) Voltar para menu");
						System.out.print("Digite sua opção: ");
						opcao = sc.nextLine();

						try {
							int opcaoReserva = Integer.parseInt(opcao);

							if (opcaoReserva == 1) {

								gerenciamentoReserva.adicionar();

							} else if (opcaoReserva == 2) {

								gerenciamentoReserva.editar();

							} else if (opcaoReserva == 3) {

								gerenciamentoReserva.excluir();

							} else if (opcaoReserva == 4) {

								gerenciamentoReserva.listar();

							} else if (opcaoReserva == 0) {

								break;

							} else {

								System.out.println("Opção inválida, digite novamente");

							}
						} catch (NumberFormatException e) {

							System.out.println("Entrada inválida. Por favor, insira um número.");

						}
					}

				} else if (opcaoMenu == 0) {
					System.out.println("Saindo do Sistema, obrigada por usar.");
					break;
				} else {
					System.out.println("Opção inválida, digite novamente");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Por favor, insira um número.");
			}
		}

		sc.close();

	}

}
