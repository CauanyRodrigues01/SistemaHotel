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
import model.StatusQuarto;
import model.StatusReserva;

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
        sc.nextLine();

        Optional<Reserva> optionalReserva = buscarReservaPorId(idReserva);

        optionalReserva.ifPresentOrElse(reserva -> {
            int numQuarto = reserva.getQuarto().getNumQuarto();
            
            if (gerenciamentoHotel.ocuparQuarto(numQuarto)) {
                System.out.println("Check-In realizado com sucesso!");
            } else {
                System.out.println("Não foi possível realizar o check-in.");
            }
            
        }, () -> System.out.println("Reserva não encontrada."));
    }

    public void fazerCheckOut() {
        int idReserva = this.gerenciamentoHotel.lerIdReserva();
        sc.nextLine();
        
        Optional<Reserva> optionalReserva = buscarReservaPorId(idReserva);

        optionalReserva.ifPresentOrElse(reserva -> {
            int numQuarto = reserva.getQuarto().getNumQuarto();
            
            
            
            if (gerenciamentoHotel.liberarQuarto(numQuarto)) {
            	
                // Calcular o valor total da reserva
                double precoDiaria = reserva.getQuarto().getPrecoDiaria();
                double valorTotal = reserva.calcularValorReserva(precoDiaria);
            	
            	reserva.setStatus(StatusReserva.CONCLUIDA);
            	System.out.printf("Check-Out realizado com sucesso! Valor total: R$ %.2f%n", valorTotal);
            } else {
                System.out.println("Não foi possível realizar o check-Out.");
            }
            
        }, () -> System.out.println("Reserva não encontrada."));
    }

    // Método auxiliar para buscar quarto pelo id e retorná-lo
    protected Optional<Reserva> buscarReservaPorId(int idReserva) {
        return reservas.stream()
                       .filter(r -> r.getIdReserva() == idReserva)
                       .findFirst();
    }
    
    private Hospede obterHospedeValido() {
        while (true) {
            String cpf = gerenciamentoHotel.lerCpf();
            Optional<Hospede> hospedeOptional = gerenciamentoHotel.buscarHospedePorCpf(cpf);
            if (hospedeOptional.isPresent()) return hospedeOptional.get();

            System.out.println("Hóspede não encontrado. Deseja tentar novamente? (S/N): ");
            if (!continuarOperacao()) return null;
        }
    }

    private Quarto obterQuartoValidoParaReserva() {
        while (true) {
            int numQuarto = gerenciamentoHotel.lerNumQuarto();
            if (gerenciamentoHotel.reservarQuarto(numQuarto)) {
                return gerenciamentoHotel.buscarQuartoPorNumero(numQuarto).orElse(null);
            }
            System.out.print("Deseja tentar novamente? (S/N): ");
            sc.nextLine();
            if (!continuarOperacao()) return null;
        }
    }

    private boolean continuarOperacao() {
        String opcao = sc.nextLine().trim().toUpperCase();
        return "S".equals(opcao);
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
	    Hospede hospede = obterHospedeValido();
	    if (hospede == null) return;
	    
	    int numeroHospedes = gerenciamentoHotel.lerNumeroHospedes();

	    Quarto quarto = obterQuartoValidoParaReserva();
	    if (quarto == null) return;

	    LocalDate dataEntrada = gerenciamentoHotel.lerDataEntrada();
	    LocalDate dataSaida = gerenciamentoHotel.lerDataSaida(dataEntrada);

	    Reserva reserva = new Reserva(numeroHospedes, dataEntrada, dataSaida, quarto, hospede);
	    hospede.adicionarReserva(reserva);
	    reservas.add(reserva);
	    System.out.println("Sua reserva foi realizada com sucesso!");
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
            reserva.getQuarto().setStatus(StatusQuarto.DISPONIVEL);
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
            7, "Fazer Check-in",
            6, "Fazer Check-out"
        );
    }

    @Override
    public void executarOpcaoEspecifica(int opcao, Scanner sc) {
        switch (opcao) {
            case 7 -> fazerCheckIn();
            case 6 -> fazerCheckOut();
            default -> System.out.println("Opção específica inválida");
        }
    }
}
