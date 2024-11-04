package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import model.Hospede;
import model.Quarto;
import model.Reserva;

public class GerenciamentoReserva implements Gerenciamento {

    private final List<Reserva> reservas;
    private final GerenciamentoHotel gerenciamentoHotel;
    private final Scanner sc;

    public GerenciamentoReserva(Scanner scanner, GerenciamentoHotel gerenciamentoHotel) {
        this.reservas = new ArrayList<>();
        this.sc = scanner;
        this.gerenciamentoHotel = gerenciamentoHotel;
    }

    public void fazerCheckIn() {
        int idReserva = this.gerenciamentoHotel.lerIdReserva();

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
        int idReserva = this.gerenciamentoHotel.lerIdReserva();

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

    // Método auxiliar para buscar quarto pelo id e retorná-lo
    protected Optional<Reserva> buscarReservaPorId(int idReserva) {
        return reservas.stream()
                       .filter(r -> r.getIdReserva() == idReserva)
                       .findFirst();
    }
    
	@Override
	public void buscar() {
	    int idReserva = this.gerenciamentoHotel.lerIdReserva();

	    buscarReservaPorId(idReserva).ifPresentOrElse(
	        reserva -> System.out.println("Reserva encontrada: " + reserva),
	        () -> System.out.println("Reserva não encontrada.")
	    );
	}

	@Override
	public void adicionar() {
	    Hospede hospede = null;
	    while (true) {
	        String cpf = this.gerenciamentoHotel.lerCpf();
	        Optional<Hospede> hospedeOptional = this.gerenciamentoHotel.buscarHospedePorCpf(cpf);

	        if (hospedeOptional.isPresent()) {
	            hospede = hospedeOptional.get();
	            break;  // Sai do loop quando encontrar um hóspede válido
	        } else {
	            System.out.println("Hóspede não encontrado. Certifique-se de que o hóspede está cadastrado.");
	            System.out.print("Deseja tentar novamente? (S/N): ");
	            String opcao = sc.nextLine().trim().toUpperCase();

	            if (opcao.equals("N")) {
	                System.out.println("Operação de reserva cancelada.");
	                return;  // Encerra o método se o usuário não quiser continuar
	            } else if (!opcao.equals("S")) {
	            	System.out.println("Opção inválida.");
	            }
	        }
	    }

	    Quarto quarto = null;
	    while (true) {
	        int numQuarto = gerenciamentoHotel.lerNumQuarto();
	        Optional<Quarto> quartoOptional = this.gerenciamentoHotel.buscarQuartoPorNumero(numQuarto);

	        if (quartoOptional.isPresent()) {
	        	
	        	boolean reservaQuarto = this.gerenciamentoHotel.reservarQuarto(numQuarto);
	        	
	        	if (reservaQuarto) {
	        		quarto = quartoOptional.get();
		            break;
	        	} 	        	
	             
	        } else {
	        	sc.nextLine();
	            System.out.println("Quarto não encontrado. Certifique-se de que o quarto está cadastrado.");
	            System.out.print("Deseja tentar novamente? (S/N): ");
	            String opcao = sc.nextLine().trim().toUpperCase();

	            
	            if (opcao.equals("N")) {
	                System.out.println("Operação de reserva cancelada.");
	                return; 
	            } else if (!opcao.equals("S")) {
	            	System.out.println("Opção inválida.");
	            }
	        }
	    }
	    
	    int numeroHospedes = this.gerenciamentoHotel.lerNumeroHospedes();
	    LocalDate dataEntrada = this.gerenciamentoHotel.lerDataEntrada();
	    LocalDate dataSaida = this.gerenciamentoHotel.lerDataSaida(dataEntrada);

	    Reserva reserva = new Reserva(numeroHospedes, dataEntrada, dataSaida, quarto, hospede);
	    hospede.adicionarReserva(reserva);
	    reservas.add(reserva);
	    //TODO adicionar reserva no hospede
	    System.out.println("Sua reserva foi realizada com sucesso! Obrigada pela preferência.");
	}


    @Override
    public void editar() {
    	int idReserva = this.gerenciamentoHotel.lerIdReserva();

        buscarReservaPorId(idReserva).ifPresentOrElse(reserva -> {
            System.out.print("Digite a nova data de entrada (dd/MM/yyyy): ");
            
            LocalDate novaDataEntrada = this.gerenciamentoHotel.lerDataEntrada();
            reserva.setDataEntrada(novaDataEntrada);
            
            LocalDate novaDataSaida = this.gerenciamentoHotel.lerDataSaida(novaDataEntrada);
            reserva.setDataSaida(novaDataSaida);

            System.out.println("Reserva editada com sucesso!");
        }, () -> System.out.println("Reserva não encontrada com o ID: " + idReserva));
    }

    @Override
    public void excluir() {
    	int idReserva = this.gerenciamentoHotel.lerIdReserva();

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
