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
            int opcaoMenu = exibirMenuPrincipal(sc);
            if (opcaoMenu == 0) {
                System.out.println("\nSaindo do Sistema, obrigada por usar.");
                break;
            }
            switch (opcaoMenu) {
                case 1 -> menuHospede(sc, gerenciamentoHospede);
                case 2 -> menuFuncionario(sc, gerenciamentoFuncionario);
                case 3 -> menuQuarto(sc, gerenciamentoQuarto);
                case 4 -> menuReserva(sc, gerenciamentoReserva);
                default -> System.out.println("Opção inválida, digite novamente");
            }
        }
        sc.close();
    }

    private static int exibirMenuPrincipal(Scanner sc) {
        System.out.println("\nMenu de gerenciamento: \n 1) Hóspede \n 2) Funcionario \n 3) Quarto \n 4) Reserva \n 0) Sair do sistema");
        System.out.print("Digite sua opção: ");
        return lerOpcao(sc);
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

    private static void menuHospede(Scanner sc, GerenciamentoHospede gerenciamentoHospede) {
        while (true) {
            System.out.println("\nOpções Disponíveis: \n 1) Cadastrar Hospede \n 2) Editar Hóspede \n 3) Excluir Hóspede \n 4) Listar Hóspedes \n 0) Voltar para menu");
            System.out.print("Digite sua opção: ");
            int opcao = lerOpcao(sc);
            switch (opcao) {
                case 1 -> gerenciamentoHospede.adicionar();
                case 2 -> gerenciamentoHospede.editar();
                case 3 -> gerenciamentoHospede.excluir();
                case 4 -> gerenciamentoHospede.listar();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida, digite novamente");
            }
        }
    }
    
    private static void menuFuncionario(Scanner sc, GerenciamentoFuncionario gerenciamentoFuncionario) {
        while (true) {
        	System.out.println("\nOpções Disponíveis: \n 1) Cadastrar Funcionario \n 2) Editar Funcionario \n 3) Excluir Funcionarios \n 4) Listar Funcionario \n 0) Voltar para menu");
			System.out.print("Digite sua opção: ");
            int opcao = lerOpcao(sc);
            switch (opcao) {
                case 1 -> gerenciamentoFuncionario.adicionar();
                case 2 -> gerenciamentoFuncionario.editar();
                case 3 -> gerenciamentoFuncionario.excluir();
                case 4 -> gerenciamentoFuncionario.listar();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida, digite novamente");
            }
        }
    }
    
    private static void menuQuarto(Scanner sc, GerenciamentoQuarto gerenciamentoQuarto) {
        while (true) {
        	System.out.println("\nOpções Disponíveis: \n 1) Cadastrar Quarto \n 2) Editar Quarto \n 3) Excluir Quarto \n 4) Listar Quartos \n 0) Voltar para menu");
			System.out.print("Digite sua opção: ");
            int opcao = lerOpcao(sc);
            switch (opcao) {
                case 1 -> gerenciamentoQuarto.adicionar();
                case 2 -> gerenciamentoQuarto.editar();
                case 3 -> gerenciamentoQuarto.excluir();
                case 4 -> gerenciamentoQuarto.listar();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida, digite novamente");
            }
        }
    }

    private static void menuReserva(Scanner sc, GerenciamentoReserva gerenciamentoReserva) {
        while (true) {
        	System.out.println("\nOpções Disponíveis: \n 1) Cadastrar Reserva \n 2) Editar Reserva \n 3) Excluir Reserva \n 4) Listar Reservas \n 0) Voltar para menu");
			System.out.print("Digite sua opção: ");
            int opcao = lerOpcao(sc);
            switch (opcao) {
                case 1 -> gerenciamentoReserva.adicionar();
                case 2 -> gerenciamentoReserva.editar();
                case 3 -> gerenciamentoReserva.excluir();
                case 4 -> gerenciamentoReserva.listar();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida, digite novamente");
            }
        }
    }
}
