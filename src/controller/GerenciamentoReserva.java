package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private final Scanner sc;

    public GerenciamentoReserva(Scanner scanner) {
        this.reservas = new ArrayList<>();
        this.sc = scanner;
    }

    public void fazerCheckIn(Scanner sc) {
        System.out.print("Digite o ID da Reserva: ");
        int idReserva = sc.nextInt();
        sc.nextLine(); // Consumir nova linha

        Optional<Reserva> optionalReserva = buscarReservaPorId(idReserva);
        if (optionalReserva.isPresent()) {
            Reserva reserva = optionalReserva.get();
            reserva.getQuarto().setStatus("indisponível");
            System.out.println("Check-In realizado com sucesso para a reserva com ID: " + reserva.getIdReserva());
        } else {
            System.out.println("Reserva não encontrada com o ID: " + idReserva);
        }
    }

    public void fazerCheckOut(Scanner sc) {
        System.out.print("Digite o ID da Reserva: ");
        int idReserva = sc.nextInt();
        sc.nextLine(); // Consumir nova linha

        Optional<Reserva> optionalReserva = buscarReservaPorId(idReserva);
        if (optionalReserva.isPresent()) {
            Reserva reserva = optionalReserva.get();
            Quarto quartoDaReserva = reserva.getQuarto();
            quartoDaReserva.setStatus("disponível");

            double valorTotal = reserva.calcularValorReserva(quartoDaReserva.getPrecoDiaria());
            System.out.printf("Check-Out realizado com sucesso para a reserva com ID: %d.%n", idReserva);
            System.out.printf("Valor total a ser pago: R$ %.2f%n", valorTotal);
            reserva.setStatus("disponível");
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
	    System.out.print("Digite o ID da reserva para buscar: ");
	    int idReserva = sc.nextInt();
	    sc.nextLine(); // Consumir a nova linha

	    buscarReservaPorId(idReserva).ifPresentOrElse(
	        reserva -> System.out.println("Reserva encontrada: " + reserva),
	        () -> System.out.println("Reserva não encontrada.")
	    );
	}

    @Override
    public void adicionar() {
    	
    	Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Informe o CPF do Hóspede: ");
        String cpf = sc.nextLine();

        GerenciamentoHospede gerenciamentoHospede = new GerenciamentoHospede(sc);
        Optional<Hospede> hospedeOptional = gerenciamentoHospede.buscarHospedePorCpf(cpf);

        if (hospedeOptional.isEmpty()) {
            System.out.println("Hóspede não encontrado. Certifique-se de que o hóspede está cadastrado.");
            return;
        }

        Hospede hospede = hospedeOptional.get();

        System.out.print("Digite a quantidade de hóspedes: ");
        int numeroHospede = sc.nextInt();
        sc.nextLine(); // Consumir nova linha

        System.out.print("Digite a data de entrada (dd/MM/yyyy): ");
        LocalDate dataEntrada = LocalDate.parse(sc.nextLine(), formatter);

        System.out.print("Digite a data de saída (dd/MM/yyyy): ");
        LocalDate dataSaida = LocalDate.parse(sc.nextLine(), formatter);

        System.out.print("Digite o número do Quarto: ");
        int numQuarto = sc.nextInt();

        GerenciamentoQuarto gerenciamentoQuarto = new GerenciamentoQuarto(sc);
        Optional<Quarto> quartoOptional = gerenciamentoQuarto.buscarQuartoPorNumero(numQuarto);

        if (quartoOptional.isEmpty()) {
            System.out.println("Quarto não encontrado. Certifique-se de que o quarto está cadastrado.");
            return;
        }

        Quarto quarto = quartoOptional.get();

        System.out.print("Informe o id da Reserva: ");
        Integer idReserva = sc.nextInt();

        Reserva reserva = new Reserva(numeroHospede, dataEntrada, dataSaida, quarto, hospede, idReserva);
        hospede.adicionarReserva(reserva);
        reservas.add(reserva);

        System.out.println("Sua reserva foi realizada com sucesso! Obrigada pela preferência.");
    }

    @Override
    public void editar() {
        System.out.print("Informe o ID da Reserva que deseja editar: ");
        int idReserva = sc.nextInt();
        sc.nextLine(); // Consumir nova linha

        buscarReservaPorId(idReserva).ifPresentOrElse(reserva -> {
            System.out.print("Digite a nova data de entrada (dd/MM/yyyy): ");
            LocalDate novaDataEntrada = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            reserva.setDataEntrada(novaDataEntrada);

            System.out.print("Digite a nova data de saída (dd/MM/yyyy): ");
            LocalDate novaDataSaida = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            reserva.setDataSaida(novaDataSaida);

            System.out.println("Reserva editada com sucesso!");
        }, () -> System.out.println("Reserva não encontrada com o ID: " + idReserva));
    }

    @Override
    public void excluir() {
        System.out.print("Informe o ID da Reserva que deseja cancelar: ");
        int idReserva = sc.nextInt();
        sc.nextLine(); // Consumir a nova linha

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
            case 6 -> fazerCheckIn(sc);
            case 7 -> fazerCheckOut(sc);
            default -> System.out.println("Opção específica inválida");
        }
    }
}
