package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import model.Hospede;
import model.Quarto;

public class GerenciamentoHotel {
	private Scanner sc;
	private Map<Integer, Gerenciamento> gerenciamentos;

	public GerenciamentoHotel(Scanner scanner) {
		this.sc = scanner;
		this.gerenciamentos = new HashMap<>();
		this.gerenciamentos.put(1, new GerenciamentoHospede(scanner, this));
		this.gerenciamentos.put(2, new GerenciamentoFuncionario(scanner, this));
		this.gerenciamentos.put(3, new GerenciamentoQuarto(scanner, this));
		this.gerenciamentos.put(4, new GerenciamentoReserva(scanner, this));
	}

    public boolean reservarQuarto(int numQuarto) {
    	GerenciamentoQuarto gerenciamentoQuarto = (GerenciamentoQuarto) gerenciamentos.get(3);
        return gerenciamentoQuarto.reservarQuarto(numQuarto);
    }

    public boolean liberarQuarto(int numQuarto) {
    	GerenciamentoQuarto gerenciamentoQuarto = (GerenciamentoQuarto) gerenciamentos.get(3);
        return gerenciamentoQuarto.liberarQuarto(numQuarto);
    }
    
    public boolean ocuparQuarto(int numQuarto) {
    	GerenciamentoQuarto gerenciamentoQuarto = (GerenciamentoQuarto) gerenciamentos.get(3);
        return gerenciamentoQuarto.ocuparQuarto(numQuarto);
    }

//    public void realizarCheckIn(int idReserva) {
//    	GerenciamentoReserva gerenciamentoReserva = (GerenciamentoReserva) gerenciamentos.get(4);
//        gerenciamentoReserva.fazerCheckIn();
//    }
//
//    public void realizarCheckOut(int idReserva) {
//    	GerenciamentoReserva gerenciamentoReserva = (GerenciamentoReserva) gerenciamentos.get(4);
//        gerenciamentoReserva.fazerCheckOut();
//    }
    
    public Optional<Hospede> buscarHospedePorCpf(String cpf) {
        GerenciamentoHospede gerenciamentoHospede = (GerenciamentoHospede) gerenciamentos.get(1);
        return gerenciamentoHospede.buscarHospedePorCpf(cpf);
    }

    public Optional<Quarto> buscarQuartoPorNumero(int numeroQuarto) {
        GerenciamentoQuarto gerenciamentoQuarto = (GerenciamentoQuarto) gerenciamentos.get(3);
        return gerenciamentoQuarto.buscarQuartoPorNumero(numeroQuarto);
    }


	int lerOpcaoMenu(Scanner sc) {
		while (true) {
			try {
				return Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.print("Entrada inválida. Por favor, insira um número: ");
			}
		}
	}
	
    String lerCpf() {
        while (true) {
            System.out.print("Informe o CPF: ");
            String cpf = sc.nextLine();
            if (cpf.matches("\\d{11}")) {
                return cpf; // CPF válido (apenas números e 11 dígitos)
            } else {
                System.out.println("CPF inválido. Certifique-se de que está no formato correto (11 dígitos).");
            }
        }
    }

    int lerIdReserva() {
        while (true) {
            System.out.print("Digite o ID da Reserva: ");
            try {
                int id = sc.nextInt();
                if (id <= 0) {
                    System.out.println("\nDigite um valor maior que zero.\n");
                } else {
                    return id;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            } 
        }
    }
    
    int lerNumQuarto() {
        while (true) {
            System.out.print("Digite o número do Quarto: ");
            try {
                int id = sc.nextInt();
                if (id <= 0) {
                    System.out.println("\nDigite um valor maior que zero.\n");
                } else {
                    return id;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                sc.nextLine(); // Limpar o buffer
            }
        }
    }
    
    int lerInt() {
    	while (true) {
	        try {
	            int inteiro = sc.nextInt();
	            return inteiro;
	        } catch (InputMismatchException e) {
	            System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
	        } 
    	}
    }
    
    int lerNumeroHospedes() {
        while (true) {
            System.out.print("Digite a quantidade de hóspedes (máximo 10): ");
            try {
                int numeroHospede = sc.nextInt();
                if (numeroHospede <= 0 || numeroHospede > 10) {
                    System.out.println("A quantidade de hóspedes deve ser entre 1 e 10.");
                } else {
                    return numeroHospede;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            } finally {
            	sc.nextLine(); // Limpar o buffer
            }
        }
    }
    
    LocalDate lerDataEntrada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Digite a data de entrada (dd/MM/yyyy): ");
            try {
                LocalDate dataEntrada = LocalDate.parse(sc.nextLine(), formatter);
                
                // Verifica se a data de entrada é hoje ou posterior a hoje
                if (dataEntrada.isBefore(LocalDate.now())) {
                    System.out.println("A data de entrada não pode ser anterior a hoje. Tente novamente.");
                } else {
                    return dataEntrada;
                }
                
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Tente novamente.");
            }
        }
    }

    LocalDate lerDataSaida(LocalDate dataEntrada) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Digite a data de saída (dd/MM/yyyy): ");
            try {
                LocalDate dataSaida = LocalDate.parse(sc.nextLine(), formatter);
                if (dataSaida.isBefore(dataEntrada)) {
                    System.out.println("A data de saída deve ser posterior à data de entrada.");
                } else {
                    return dataSaida;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Tente novamente.");
            }
        }
    }
    
    LocalDate lerDataNascimento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
            try {
                LocalDate dataNascimento = LocalDate.parse(sc.nextLine(), formatter);
                // Verifica se a data de nascimento é uma data futura
                if (dataNascimento.isAfter(LocalDate.now())) {
                    System.out.println("A data de nascimento não pode ser uma data futura. Tente novamente.");
                } else {
                    return dataNascimento;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Tente novamente.");
            }
        }
    }
    
	public void exibirMenuPrincipal() {
		while (true) {
			System.out.println(
					"\nMenu de gerenciamento: \n 1) Hóspede \n 2) Funcionario \n 3) Quarto \n 4) Reserva \n 0) Sair do sistema");
			System.out.print("Digite sua opção: ");
			int opcaoMenu = lerOpcaoMenu(sc);

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
			int opcao = lerOpcaoMenu(sc);
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
}