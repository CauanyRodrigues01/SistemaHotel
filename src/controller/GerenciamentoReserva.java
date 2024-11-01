package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Hospede;
import model.Reserva;

public class GerenciamentoReserva implements Gerenciamento {

	private List<Reserva> reservas;
	private List<Hospede> hospedes;

	public GerenciamentoReserva() {
		this.reservas = new ArrayList<>();
		this.hospedes = new ArrayList<>();
	}

	@Override
	public void adicionar() {
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("---- Você escolheu a opção de reservar um quarto ---");

		System.out.println("Informe seu nome: ");
		String nomeHospedeReserva = sc.nextLine();

		System.out.println("Informe seu CPF: ");
		String cpfHospedeReserva = sc.nextLine();

		System.out.println("Informe sua data de nascimento: ");
		String dataNascimentoHospedeReserva = sc.nextLine();
		LocalDate dataNascimento = LocalDate.parse(dataNascimentoHospedeReserva, formatter);

		System.out.println("Digite seu endereço: ");
		String endereçoHospedeReserva = sc.nextLine();

		System.out.println("Digite o seu número para contato: ");
		String contatoHospedeReserva = sc.nextLine();

		Hospede hospede = new Hospede(nomeHospedeReserva, cpfHospedeReserva, dataNascimentoHospedeReserva,
				endereçoHospedeReserva, contatoHospedeReserva);
		// hospedes.add(hospede);

		// reserva

		System.out.println("Digite a quantidade de hóspedes: ");
		int numeroHospede = sc.nextInt();

		System.out.print("Digite a data de entrada (dd/MM/yyyy): ");
		String dataEntradaReserva = sc.nextLine();
		LocalDate dataEntrada = LocalDate.parse(dataEntradaReserva, formatter);

		System.out.println("Digite a data de saída (dd/MM/yyyy): ");
		String dataSaidaReserva = sc.nextLine();
		LocalDate dataSaida = LocalDate.parse(dataSaidaReserva, formatter);

		System.out.println("Qual o tipo do quarto? ( solteiro, casal, suíte) ");
		String tipoQuarto = sc.nextLine();

		System.out.println("Informe o id da Reserva");
		Integer idReserva = sc.nextInt();

		Reserva reserva = new Reserva(numeroHospede, dataEntrada, dataSaida, tipoQuarto, hospede, idReserva);
		reservas.add(reserva);
		// obs: rever a lista de hospedes.

		System.out.println("Sua reserva foi realizado com sucesso! Obrigada pela prefêrencia. ");

	}
	public void fazerCheckIn(int idReserva) {
	    for (Reserva reserva : reservas) {
	        if (reserva.getIdReserva().equals(idReserva)) { 
	            if (reserva.getStatus().equals("disponivel")) { 
	                System.out.println("Check-In realizado com sucesso para a reserva com ID: " + idReserva);
	            } else {
	                System.out.println("O Check-In não pode ser realizado, o quarto se encontra ocupado.");
	            }
	            return; 
	        }
	    }
	    System.out.println("Reserva não encontrada com o ID: " + idReserva); 
	}
	
	

	@Override
	public void editar() {

	}

	@Override
	public void excluir() {

	}

	@Override
	public void listar() {
		System.out.println("Listagem de Reservas do Hotel : ");
		for (Reserva reserva : reservas) {
			System.out.println(reservas);
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
