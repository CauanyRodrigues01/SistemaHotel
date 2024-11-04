package model;

import java.time.LocalDate;

public class Reserva {
	
	private static int contador = 1;	
	private int idReserva;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;
	private int numeroHospede;
	private Quarto quarto;
	private Hospede hospede;
	private String status;
	
	public Reserva(int numeroHospede, LocalDate dataEntrada, LocalDate dataSaida, Quarto quarto, Hospede hospede) {
		this.numeroHospede = numeroHospede;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.quarto = quarto;
		this.hospede = hospede;
		this.idReserva = contador++;
		this.status = "disponível";
		
	}
	
	// Funções específicas para manipulação das Reservas
	public int duracaoEstadia()  {
		 int diaEntrada = this.dataEntrada.getDayOfYear();
	     int diaSaida = this.dataSaida.getDayOfYear();
	     return diaSaida - diaEntrada;
	}
	
	public double calcularValorReserva(double precoDiaria) {
        int duracaoDiasReserva = duracaoEstadia();
        System.out.println(duracaoDiasReserva * precoDiaria);
        return duracaoDiasReserva * precoDiaria;    
    }
	
	// Getters e Setters
	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public int getNumeroHospede() {
		return numeroHospede;
	}

	public void setNumeroHospede(int numeroHospede) {
		this.numeroHospede = numeroHospede;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida
				+ ", numeroHospede=" + numeroHospede + ", quarto=" + quarto + ", hospede=" + hospede + ", status="
				+ status + "]";
	}

}
