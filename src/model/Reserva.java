package model;

import java.time.LocalDate;

public class Reserva {
	private int numeroHospede;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;
	private Quarto quarto;
	private Hospede hospede;

	public Reserva(int numeroHospede, LocalDate dataEntrada, LocalDate dataSaida, Quarto quarto, Hospede hospede) {
		super();
		this.numeroHospede = numeroHospede;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.quarto = quarto;
		this.hospede = hospede;
	}

	public int getNumeroHospede() {
		return numeroHospede;
	}

	public void setNumeroHospede(int numeroHospede) {
		this.numeroHospede = numeroHospede;
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

	@Override
	public String toString() {
		return "Reserva [numeroHospede=" + numeroHospede + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida
				+ ", quarto=" + quarto + ", hospede=" + hospede + "]";
	}

}
