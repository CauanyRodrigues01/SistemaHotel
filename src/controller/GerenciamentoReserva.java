package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import model.Hospede;
import model.Quarto;
import model.Reserva;

public class GerenciamentoReserva implements Gerenciamento {

	private List<Reserva> reservas;
	private List<Hospede> hospedes;
	private List<Quarto> quartos;

	public GerenciamentoReserva() {
		this.reservas = new ArrayList<>();
		this.hospedes = new ArrayList<>();
		this.quartos = new ArrayList<>();
	}

	public Optional<Hospede> buscarPorCpf(String cpf) {
		return hospedes.stream().filter(h -> h.getCpf().equals(cpf)).findFirst();
	}

	private Optional<Quarto> buscarQuarto(int numQuarto) {
		return quartos.stream().filter(quarto -> quarto.getNumQuarto() == numQuarto).findFirst();
	}

	@Override
	public void adicionar() {
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("Informe o CPF do Hóspde: ");
		String cpf = sc.nextLine();

		Optional<Hospede> hospedeOptional = buscarPorCpf(cpf);

		if (hospedeOptional.isEmpty()) {
			System.out.println("Hóspede não encontrado. Certifique-se de que o hóspede está cadastrado.");
			return;
		}

		Hospede hospede = hospedeOptional.get();

		System.out.println("Digite a quantidade de hóspedes: ");
		int numeroHospede = sc.nextInt();
		sc.nextLine();

		System.out.print("Digite a data de entrada (dd/MM/yyyy): ");
		String dataEntradaReserva = sc.nextLine();
		LocalDate dataEntrada = LocalDate.parse(dataEntradaReserva, formatter);

		System.out.println("Digite a data de saída (dd/MM/yyyy): ");
		String dataSaidaReserva = sc.nextLine();
		LocalDate dataSaida = LocalDate.parse(dataSaidaReserva, formatter);

		System.out.println("Digite o número do Quarto: ");
		int numQuarto = sc.nextInt();
		Optional<Quarto> quartoOptional = buscarQuarto(numQuarto);

		if (quartoOptional.isEmpty()) {
			System.out.println("Quarto não encontrado. Certifique-se de que o quarto está cadastrado.");
			return;
		}

		Quarto quarto = quartoOptional.get();

		System.out.println("Informe o id da Reserva");
		Integer idReserva = sc.nextInt();

		Reserva reserva = new Reserva(numeroHospede, dataEntrada, dataSaida, quarto, hospede, idReserva);
		hospede.adicionarReserva(reserva);
		reservas.add(reserva);

		System.out.println("Sua reserva foi realizada com sucesso! Obrigada pela preferência.");
	}

	public void fazerCheckIn(int idReserva) {
		for (Reserva reserva : reservas) {
			if (reserva.getIdReserva().equals(idReserva)) {
				reserva.getQuarto().setStatus("indisponível");
				System.out.println("Check-In realizado com sucesso para a reserva com ID: " + idReserva);
				return;
			}
		}

		System.out.println("A Reserva do hotel não foi encontrada com o ID: " + idReserva);
	}

	public void fazerCheckOut(int idReserva, double precoDiaria) {
		for (Reserva reserva : reservas) {
			if (reserva.getIdReserva().equals(idReserva)) {
				reserva.getQuarto().setStatus("disponível");

				double valorTotal = reserva.calcularValorReserva(precoDiaria);
				System.out.printf("Check-Out realizado com sucesso para a reserva com ID: %d.%n", idReserva);
				System.out.printf("Valor total a ser pago: R$ %.2f%n", valorTotal);
				reserva.setStatus("disponível");
				return;
			}
		}
		System.out.println("Reserva não encontrada com o ID: " + idReserva);
	}

	@Override
	public void editar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Informe o ID da Reserva que deseja editar: ");
		int idReserva = sc.nextInt();

		for (Reserva reserva : reservas) {
			if (reserva.getIdReserva().equals(idReserva)) {
				System.out.print("Digite a nova data de entrada (dd/MM/yyyy): ");
				String novaDataEntrada = sc.next();
				LocalDate dataEntrada = LocalDate.parse(novaDataEntrada, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				reserva.setDataEntrada(dataEntrada);

				System.out.print("Digite a nova data de saída (dd/MM/yyyy): ");
				String novaDataSaida = sc.next();
				LocalDate dataSaida = LocalDate.parse(novaDataSaida, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				reserva.setDataSaida(dataSaida);

				System.out.println("Reserva editada com sucesso!");
				return;
			}
		}

	}

	@Override
	public void excluir() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Informe o ID da Reserva que deseja cancelar: ");
		int idReserva = sc.nextInt();

		for (Reserva reserva : reservas) {
			if (reserva.getIdReserva().equals(idReserva)) {
				reserva.getQuarto().setStatus("disponível");
				reservas.remove(reserva);
				System.out.println("Reserva cancelada com sucesso!");
				return;
			}
		}
	}

	@Override
	public void listar() {
		System.out.println("Listagem de Reservas do Hotel:");
		for (Reserva reserva : reservas) {
			System.out.println(reserva);
		}
	}

	public List<Hospede> getHospedes() {
		return hospedes;
	}

	public void setHospedes(List<Hospede> hospedes) {
		this.hospedes = hospedes;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
}
