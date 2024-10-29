package main;

import java.util.Scanner;

import model.Hospede;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Seja Bem-Vindo ao Sistema do Hotel!\n");

		while (true) {
			System.out.println(
					"\nOpções Disponíveis: \n 1) Cadastrar Hospede \n 0) Sair do sistema");
			System.out.println("Digite sua opção.");
			String opcao = sc.nextLine();

			try {
				int opcaoMenu = Integer.parseInt(opcao);

				if (opcaoMenu == 1) {
					System.out.println("Cadastramento de hospede");

					Hospede testeHospede = new Hospede("Cauany", "12345-5", "12/12/12", "rua marechal", "(11) 1 1111-111");
					System.out.println(testeHospede);

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
