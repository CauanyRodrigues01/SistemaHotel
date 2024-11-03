package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import model.Hospede;
import model.Quarto;
import model.Reserva;

public class GerenciamentoReserva implements Gerenciamento {

    private final List<Reserva> reservas;
    private final Scanner sc;

    public GerenciamentoReserva(Scanner scanner) {
        this.reservas = new ArrayList<>();
        this.sc = scanner;
    }

    public void fazerCheckIn() {
        int idReserva = lerIdReserva();

        Optional<Reserva> optionalReserva = buscarReservaPorId(idReserva);
        if (optionalReserva.isPresent()) {
            Reserva reserva = optionalReserva.get();
            Quarto quarto = reserva.getQuarto();

            if (quarto.getStatus().equals("disponível")) {
                quarto.setStatus("indisponível");
                System.out.println("Check-In realizado com sucesso para a reserva com ID: " + reserva.getIdReserva());
            } else {
                System.out.println("Quarto está indisponível para check-in.");
            }
        } else {
            System.out.println("Reserva não encontrada com o ID: " + idReserva);
        }
    }

    public void fazerCheckOut() {
        int idReserva = lerIdReserva();

        Optional<Reserva> optionalReserva = buscarReservaPorId(idReserva);
        if (optionalReserva.isPresent()) {
            Reserva reserva = optionalReserva.get();
            Quarto quartoDaReserva = reserva.getQuarto();

            if (quartoDaReserva.getStatus().equals("indisponível")) {
                quartoDaReserva.setStatus("disponível");

                double valorTotal = reserva.calcularValorReserva(quartoDaReserva.getPrecoDiaria());
                System.out.printf("Check-Out realizado com sucesso para a reserva com ID: %d.%n", idReserva);
                System.out.printf("Valor total a ser pago: R$ %.2f%n", valorTotal);
            } else {
                System.out.println("Quarto já está disponível para check-out.");
            }
        } else {
            System.out.println("Reserva não encontrada com o ID: " + idReserva);
        }
    }
    
    private String lerCpf() {
        while (true) {
            System.out.print("Informe o CPF do Hóspede: ");
            String cpf = sc.nextLine();
            if (cpf.matches("\\d{11}")) {
                return cpf; // CPF válido (apenas números e 11 dígitos)
            } else {
                System.out.println("CPF inválido. Certifique-se de que está no formato correto (11 dígitos).");
            }
        }
    }

    private int lerIdReserva() {
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
                System.out.println("Entrada inválida. Por favor, insira um número inteiro para o ID da reserva.");
            } finally {
            	sc.nextLine(); // Limpar o buffer
            }
        }
    }
    
    private int lerNumeroHospedes() {
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
    
    private LocalDate lerDataEntrada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Digite a data de entrada (dd/MM/yyyy): ");
            try {
                LocalDate dataEntrada = LocalDate.parse(sc.nextLine(), formatter);
                return dataEntrada;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Tente novamente.");
            }
        }
    }

    private LocalDate lerDataSaida(LocalDate dataEntrada) {
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

    // Método auxiliar para buscar quarto pelo id e retorná-lo
    protected Optional<Reserva> buscarReservaPorId(int idReserva) {
        return reservas.stream()
                       .filter(r -> r.getIdReserva() == idReserva)
                       .findFirst();
    }
    
	@Override
	public void buscar() {
	    int idReserva = lerIdReserva();

	    buscarReservaPorId(idReserva).ifPresentOrElse(
	        reserva -> System.out.println("Reserva encontrada: " + reserva),
	        () -> System.out.println("Reserva não encontrada.")
	    );
	}

    @Override
    public void adicionar() {

        String cpf = lerCpf();

        GerenciamentoHospede gerenciamentoHospede = new GerenciamentoHospede(sc);
        Optional<Hospede> hospedeOptional = gerenciamentoHospede.buscarHospedePorCpf(cpf);
        
        if (hospedeOptional.isEmpty()) {
            System.out.println("Hóspede não encontrado. Certifique-se de que o hóspede está cadastrado.");
            return;
        }

        Hospede hospede = hospedeOptional.get();

        int numeroHospede = lerNumeroHospedes();

        LocalDate dataEntrada = lerDataEntrada();

        LocalDate dataSaida = lerDataSaida(dataEntrada);

        System.out.print("Digite o número do Quarto: ");
        int numQuarto = sc.nextInt();

        GerenciamentoQuarto gerenciamentoQuarto = new GerenciamentoQuarto(sc);
        Optional<Quarto> quartoOptional = gerenciamentoQuarto.buscarQuartoPorNumero(numQuarto);

        if (quartoOptional.isEmpty()) {
            System.out.println("Quarto não encontrado. Certifique-se de que o quarto está cadastrado.");
            return;
        }

        Quarto quarto = quartoOptional.get();

        System.out.print("Informe o id da Reserva: "); //TODO fazer o id automatico
        Integer idReserva = sc.nextInt();

        Reserva reserva = new Reserva(numeroHospede, dataEntrada, dataSaida, quarto, hospede, idReserva);
        hospede.adicionarReserva(reserva);
        reservas.add(reserva);

        System.out.println("Sua reserva foi realizada com sucesso! Obrigada pela preferência.");
    }

    @Override
    public void editar() {
    	int idReserva = lerIdReserva();

        buscarReservaPorId(idReserva).ifPresentOrElse(reserva -> {
            System.out.print("Digite a nova data de entrada (dd/MM/yyyy): ");
            
            LocalDate novaDataEntrada = lerDataEntrada();
            reserva.setDataEntrada(novaDataEntrada);
            
            LocalDate novaDataSaida = lerDataSaida(novaDataEntrada);
            reserva.setDataSaida(novaDataSaida);

            System.out.println("Reserva editada com sucesso!");
        }, () -> System.out.println("Reserva não encontrada com o ID: " + idReserva));
    }

    @Override
    public void excluir() {
    	int idReserva = lerIdReserva();

        buscarReservaPorId(idReserva).ifPresentOrElse(reserva -> {
            reserva.getQuarto().setStatus("disponível");
            reservas.remove(reserva);
            System.out.println("Reserva cancelada com sucesso!");
        }, () -> System.out.println("Reserva não encontrada com o ID: " + idReserva));
    }

    @Override
    public void listar() {
        if (reservas.isEmpty()) {
            System.out.println("Não há reservas cadastradas.");
        } else {
        	System.out.println("Listagem de Reservas do Hotel:");
            reservas.forEach(System.out::println);
        }
    }
    
    @Override
    public Map<Integer, String> getOpcoesEspecificas() {
        return Map.of(
            6, "Fazer Check-in",
            7, "Fazer Check-out"
        );
    }

    @Override
    public void executarOpcaoEspecifica(int opcao, Scanner sc) {
        switch (opcao) {
            case 6 -> fazerCheckIn();
            case 7 -> fazerCheckOut();
            default -> System.out.println("Opção específica inválida");
        }
    }
}
